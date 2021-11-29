## Github Repository Search

Search for any user's repositories on Github.

Project uses rtx-framework module for application base which is also provided as maven repository.

> project-level gradle file.
```
buildscript {
    repositories {
        mavenCentral() // add this line.
    }
}
```
> module-level gradle file.
```
dependencies {
    implementation "io.github.reha2408:rtx-framework:1.0.4"
}
```
