package com.geekorum.ttrss.data;

import androidx.sqlite.db.SupportSQLiteOpenHelper;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class ArticlesDatabaseModule_ProvidesRoomDbHelper$app_freeDebugFactory implements Factory<SupportSQLiteOpenHelper> {
  private final Provider<ArticlesDatabase> databaseProvider;

  private ArticlesDatabaseModule_ProvidesRoomDbHelper$app_freeDebugFactory(
      Provider<ArticlesDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public SupportSQLiteOpenHelper get() {
    return providesRoomDbHelper$app_freeDebug(databaseProvider.get());
  }

  public static ArticlesDatabaseModule_ProvidesRoomDbHelper$app_freeDebugFactory create(
      Provider<ArticlesDatabase> databaseProvider) {
    return new ArticlesDatabaseModule_ProvidesRoomDbHelper$app_freeDebugFactory(databaseProvider);
  }

  public static SupportSQLiteOpenHelper providesRoomDbHelper$app_freeDebug(
      ArticlesDatabase database) {
    return Preconditions.checkNotNullFromProvides(ArticlesDatabaseModule.INSTANCE.providesRoomDbHelper$app_freeDebug(database));
  }
}
