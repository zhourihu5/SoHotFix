#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_myapplication_UnityPlayer_stringFromJNI(JNIEnv *env, jobject thiz) {
    std::string hello = "Hello from C++";
//    std::string hello = "so 升级啦";
    return env->NewStringUTF(hello.c_str());

}