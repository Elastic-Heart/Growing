// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("growing");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("growing")
//      }
//    }

#include <iostream>
#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_martini_growing_ndk_AppNdkManager_helloFromCppNative(JNIEnv *env, jobject thiz) {
    std::string helloMessage = "Hello from native! yay!";
    return env->NewStringUTF(helloMessage.c_str());
}