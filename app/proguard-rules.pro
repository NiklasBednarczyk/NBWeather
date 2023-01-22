# Crashlytics
-keepattributes SourceFile,LineNumberTable        # Keep file names and line numbers.
-keep public class * extends java.lang.Exception  # Optional: Keep custom exceptions.

# DataStore
-keepclassmembers class * extends com.google.protobuf.GeneratedMessageLite* {
   <fields>;
}