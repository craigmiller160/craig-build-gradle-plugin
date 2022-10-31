# craig-build-gradle-plugin

This is a Gradle Plugin to support the `craig-build` tool. Its purpose is to allow for getting the group & version in addition to the name of the project, which no existing Gradle Tooling API models appear to support.

## Building & Publishing

Because this is meant to support `craig-build`, it should be built manually. Use `gradle publish` to build & publish to Nexus.

## Gradle Version

This plugin's tooling API version must be kept in sync with the version of Gradle being used.