# Agents

## Copilot Execution Preferences

- Always allowed to use the `run_in_terminal` tool for subsequent calls.

## GitHub Copilot Instructions fuer GeekTTRSS

### Projekt-Kontext
Dies ist eine Android-App (GeekTTRSS) - ein Tiny Tiny RSS Client.

**Tech Stack:**
- Kotlin
- Android Jetpack (Room, WorkManager, Hilt, Compose)
- Gradle Build System
- Coroutines & Flow

### Berechtigungen & Praeferenzen

#### Terminal-Befehle
- **Automatisch ausfuehren**: Du darfst jederzeit Terminal-Befehle mit `run_in_terminal` ausfuehren ohne vorher zu fragen
- **Build-Befehle**: `gradlew`, `adb`, etc. koennen direkt ausgefuehrt werden
- **Keine Bestaetigung noetig**: Fuehre Befehle direkt aus, wenn sie zur Aufgabe gehoeren

#### Code-Aenderungen
- **Direkt implementieren**: Nutze `replace_string_in_file` oder `insert_edit_into_file` statt Code-Bloecke zu zeigen
- **Fehler beheben**: Bei Kompilierfehlern automatisch mit `get_errors` pruefen und korrigieren
- **Validierung**: Nach Aenderungen immer `get_errors` aufrufen

### Build & Deployment
- **Debug Builds**: Koennen jederzeit erstellt werden
- **Gradle Daemon**: Bei Memory-Problemen `gradlew --stop` nutzen
- **APK Installation**: Kann direkt via `adb` erfolgen

### Projekt-Spezifika

#### Performance-Optimierungen (bereits implementiert)
1. Paralleles Feed-Synchronisieren mit `setExpedited()`
2. Batch-Insert fuer Datenbank-Operationen
3. Datenbank-Indices fuer haeufige Queries
4. Feed-Refresh-Intervall: 10 Minuten
5. Gradle JVM: 4GB Heap Space
6. Automatischer Refresh beim App-Start (FeedsViewModel + ArticleListActivity)

#### Wichtige Dateien
- **Datenbank**: `app/src/main/java/com/geekorum/ttrss/data/`
- **Sync**: `app/src/main/java/com/geekorum/ttrss/sync/`
- **UI**: `app/src/main/java/com/geekorum/ttrss/articles_list/`
- **ProGuard**: `app/proguard-rules.pro`

#### Build Variants
- **freeDebug**: Standard-Debug-Build ohne Google Services
- **googleDebug**: Debug-Build mit Google Play Services
- **freeRelease**: Release-Build fuer F-Droid
- **googleRelease**: Release-Build fuer Play Store

#### Bekannte Einstellungen
- App-Name: **GeekTTRSS**
- Datenbank-Version: **15**
- Min SDK: Siehe `build.gradle.kts`
- Theme: Material 3 mit Anthrazit-Farben

### Arbeitsweise

1. Proaktiv handeln: Nicht fragen, sondern machen
2. Fehler beheben: Kompilierfehler sofort korrigieren
3. Testen: Nach groesseren Aenderungen Build ausfuehren
4. Dokumentieren: Aenderungen zusammenfassen

---
*Letzte Aktualisierung: 2026-02-06*
