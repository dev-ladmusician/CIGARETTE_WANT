# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/ladmusician/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# make file mapping,seed,usage,config after build
-printmapping map.txt
-printseeds seed.txt
-printusage usage.txt
-printconfiguration config.txt

# not mix lines
-keepattributes SourceFile,LineNumberTable

# change variable, source file
-renamesourcefileattribute SourceFile

# ignore warning
-ignorewarnings

# external library
-keep class com.android.support:appcompat-v7.** { *; }