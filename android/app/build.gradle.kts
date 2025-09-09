plugins {
    id("com.android.application")
    kotlin("android")
    id("dev.flutter.flutter-gradle-plugin")
    id("com.google.gms.google-services")
}

def localProperties = new Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use { stream ->
        localProperties.load(stream)
    }
}

val flutterVersionCode = localProperties.getProperty("flutter.versionCode")
if (flutterVersionCode == null) {
    throw new GradleException("Flutter version code not found. Please run 'flutter pub get' in your project.")
}

val flutterVersionName = localProperties.getProperty("flutter.versionName")
if (flutterVersionName == null) {
    throw new GradleException("Flutter version name not found. Please run 'flutter pub get' in your project.")
}

android {
    namespace = "com.example.myapp"
    compileSdk = 34
    ndkVersion = "25.1.8937393"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    sourceSets {
        getByName("main") {
            java.srcDirs("src/main/kotlin")
        }
    }

    defaultConfig {
        // TODO: Specify your own unique Application ID (https://developer.android.com/studio/build/application-id.html).
        applicationId = "com.example.myapp"
        // Formats the version name specified in pubspec.yaml into something readable by Android.
        // For example, "1.0.0" becomes "1.0.0".
        versionName = flutterVersionName
        // Formats the version code specified in pubspec.yaml into something readable by Android.
        // For example, "1" becomes "1".
        versionCode = flutterVersionCode.toInt()
        minSdk = 21
        targetSdk = 34
        multiDexEnabled = true
    }

    buildTypes {
        release {
            // TODO: Add your own signing config for the release build.
            // Signing with the debug keys for now, so `flutter run --release` works.
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

flutter {
    source = "../.."
}
