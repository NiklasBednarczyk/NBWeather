# Crashlytics
-keepattributes SourceFile,LineNumberTable        # Keep file names and line numbers.
-keep public class * extends java.lang.Exception  # Optional: Keep custom exceptions.

# DataStore
-keepclassmembers class * extends com.google.protobuf.GeneratedMessageLite* {
   <fields>;
}

# Retrofit
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
