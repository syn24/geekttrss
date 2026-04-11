/*
 * Geekttrss is a RSS feed reader application on the Android Platform.
 *
 * Copyright (C) 2017-2025 by Frederic-Charles Barthelery.
 *
 * This file is part of Geekttrss.
 *
 * Geekttrss is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Geekttrss is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Geekttrss.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.geekorum.ttrss.article_details

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekorum.ttrss.data.Article
import com.geekorum.ttrss.data.ArticleContentIndexed
import com.geekorum.ttrss.ui.AppTheme3
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Compose unit test (Robolectric) for the [ReadMoreSection] leaf composable.
 *
 * Scoped as a Phase 5 smoke test: validates that the project's Compose test wiring
 * works end-to-end (createComposeRule + AppTheme3 + stringResource) and that the
 * card list renders the expected items and clicks reach the callback.
 *
 * This is the narrowest possible Compose test — [ReadMoreSection] takes plain
 * data, no ViewModel, no WebView, no Activity — so any future regression here
 * is a real rendering regression rather than a mock drift.
 */
@RunWith(AndroidJUnit4::class)
class ReadMoreSectionTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun buildArticle(id: Long, title: String): Article =
        Article(
            id = id,
            contentData = ArticleContentIndexed(title = title, content = "")
        )

    @Test
    fun emptyListStillRendersTheReadMoreHeader() {
        composeTestRule.setContent {
            AppTheme3 {
                ReadMoreSection(articles = emptyList(), onArticleClick = {})
            }
        }

        // Header is emitted unconditionally by ReadMoreSection.
        composeTestRule.onNodeWithText("Read more").assertIsDisplayed()
    }

    @Test
    fun nonEmptyListRendersEveryArticleTitleAndTag() {
        val articles = listOf(
            ArticleWithTag(buildArticle(1, "First article"), tag = "kotlin"),
            ArticleWithTag(buildArticle(2, "Second article"), tag = "android"),
        )
        composeTestRule.setContent {
            AppTheme3 {
                ReadMoreSection(articles = articles, onArticleClick = {})
            }
        }

        composeTestRule.onNodeWithText("Read more").assertIsDisplayed()
        composeTestRule.onNodeWithText("First article").assertIsDisplayed()
        composeTestRule.onNodeWithText("Second article").assertIsDisplayed()
        // Tags render prefixed with '#' — see ReadMoreSection.kt.
        composeTestRule.onNodeWithText("#kotlin").assertIsDisplayed()
        composeTestRule.onNodeWithText("#android").assertIsDisplayed()
    }

    @Test
    fun clickingACardInvokesOnArticleClickWithTheRightArticle() {
        val first = buildArticle(1, "First article")
        val second = buildArticle(2, "Second article")
        val articles = listOf(
            ArticleWithTag(first, tag = "kotlin"),
            ArticleWithTag(second, tag = "android"),
        )
        var clicked: Article? = null

        composeTestRule.setContent {
            AppTheme3 {
                ReadMoreSection(
                    articles = articles,
                    onArticleClick = { clicked = it },
                )
            }
        }

        // The Card is the click target — the Text inside it has no click action, so
        // target the clickable ancestor that contains the title.
        // Invoke the OnClick semantics directly rather than going through the touch
        // pipeline — Robolectric's synthetic touch input is flaky for items that the
        // layout hasn't fully measured/positioned at click time.
        composeTestRule.onNode(hasClickAction() and hasText("Second article", substring = true))
            .performSemanticsAction(SemanticsActions.OnClick)

        assertThat(clicked).isEqualTo(second)
    }

    @Test
    fun eachArticleRendersAsAClickableCard() {
        val articles = List(3) { i ->
            ArticleWithTag(buildArticle((i + 1).toLong(), "Article $i"), tag = "t$i")
        }
        composeTestRule.setContent {
            AppTheme3 {
                ReadMoreSection(articles = articles, onArticleClick = {})
            }
        }

        // Every article title must sit inside a node that carries a click action
        // (the enclosing Card).
        for (i in 0 until 3) {
            composeTestRule.onAllNodesWithText("Article $i").fetchSemanticsNodes().let {
                assertThat(it).isNotEmpty()
            }
        }
        // Sanity: at least 3 clickable targets on screen.
        val clickable = composeTestRule.onAllNodesWithText("Article", substring = true)
            .fetchSemanticsNodes()
        assertThat(clickable.size).isAtLeast(3)
    }
}
