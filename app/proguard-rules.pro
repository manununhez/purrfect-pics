# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


#https://stackoverflow.com/questions/42782328/retrofit-2-returns-null-in-release-apk-when-minifyenable-but-ok-in-debug-apk
-keep class com.manuelnunez.apps.core.services.dto.* {*;}

### OKHTTP3 ###
-dontwarn com.squareup.okhttp3.**
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-keep class okhttp3.**{ *; }

### RETROFIT 2 ###
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

# GSON
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature
# For using GSON @Expose annotation
-keepattributes *Annotation*
-keepattributes EnclosingMethod
-keep class com.google.gson.stream.** { *; }

# Gson additional rules
-keep class com.google.gson.** { *; }
-keepattributes Signature
-keepattributes *Annotation*
-keepclassmembers class com.google.gson.** {
    <fields>;
    <methods>;
}
# Kotlin metadata
-keep class kotlin.Metadata { *; }

### Google Apis ###
-dontwarn com.google.android.**
-keep class com.google.android.**{ *; }
-keep interface com.google.android.** { *; }

## Kotlin Coroutines
-keepclassmembers class kotlinx.coroutines.DispatchedCoroutine {
    private <fields>;
}

#ViewModel
-keep class * extends androidx.lifecycle.ViewModel

#Hilt
-keep class androidx.hilt.* {*;}

## Fragment names
-keepnames class * extends androidx.fragment.app.Fragment

-keepnames class * extends android.os.Parcelable
-keepnames class * extends java.io.Serializable

### R8
## Gson
-keepclassmembers,allowobfuscation class * {
 @com.google.gson.annotations.SerializedName <fields>;
}

-keep class com.google.gson.reflect.TypeToken { *; }
-keep class * extends com.google.gson.reflect.TypeToken
## Kotlin suspend functions
-keep class kotlin.coroutines.Continuation

-allowaccessmodification

-keepattributes *Annotation*,Signature,InnerClasses,EnclosingMethod,SourceFile,LineNumberTable

-dontwarn org.apiguardian.api.API$Status
-dontwarn org.apiguardian.api.API


# With R8 full mode generic signatures are stripped for classes that are not
 # kept. Suspend functions are wrapped in continuations where the type argument
 # is used.
 -keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

 # R8 full mode strips generic signatures from return types if not kept.
 -if interface * { @retrofit2.http.* public *** *(...); }
 -keep,allowoptimization,allowshrinking,allowobfuscation class <3>

 # With R8 full mode generic signatures are stripped for classes that are not kept.
 -keep,allowobfuscation,allowshrinking class retrofit2.Response