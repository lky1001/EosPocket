# EosPocket
Eos wallet for android.

## Reference
- https://github.com/plactal/EosCommander

## Required
- Android 4.3

## build apk
```
./gradlew clean build
```

## Features
- import account
- account balance
- account token
- account transfer history
- pin, fingerprint authorization

## TODO list
- create account
- transfer token
- stake
- account action history
- key signing
- vote
- ram market
- block explorer


## Android error fix
```
Configuration on demand is not supported by the current version of the Android Gradle plugin since you are using Gradle version 4.6 or above. Suggestion: disable configuration on demand by setting org.gradle.configureondemand=false in your gradle.properties file or use a Gradle version less than 4.6.
```

- Android Studio > Settings (Preferences for mac) > Build, Execution, Deployment > Compiler
- Uncheck Configure on Demand