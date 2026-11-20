# LANAHJUNE

Starter Android application for the LANAHJUNE Minecraft AI Studio.

## Current starter features
- Native Android app using Kotlin + Jetpack Compose
- MOD / PACK / SHADER sections
- Switchable Pastel and Minecraft themes
- Prompt entry screen and preview placeholder
- GitHub Actions workflow to build a debug APK

## Important
This is the initial UI starter, not the finished AI product. AI chat, upload, preview rendering,
GitHub research, cloud storage, Java/Bedrock generators and build workers must be implemented
and connected in later milestones. No API keys are included.

## Build in GitHub Actions
1. Create a GitHub repository named `LANAHJUNE`.
2. Upload/push all project files to the `main` branch.
3. Open Actions and run `Build LANAHJUNE APK` (or push to main).
4. Download the `LANAHJUNE-debug-apk` artifact.

## Termux notes
Termux can manage the source, Git operations and backend development. Building Android APKs
directly on Android can be fragile because the Android SDK/build-tools environment is not
officially supported in the same way as a standard Linux workstation. The included cloud
workflow is the reliable initial APK build route.

## Minimum environment
- Android minSdk: 23
- JDK: 17
- Android compileSdk: 35
- Gradle: 8.9
