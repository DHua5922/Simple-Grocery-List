#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jdouble JNICALL
Java_com_dylanhua_simplebuylist_MainActivity_getTotalPrice(JNIEnv *env, jobject thiz,
                                                          jobjectArray item_array) {
    double totalPrice = 0.0;
    int len = env->GetArrayLength(item_array);

    jclass jcls = env->FindClass("com/dylanhua/simplebuylist/Item");
    jmethodID mID = env->GetMethodID(jcls, "getPrice", "()D");
    for (int i = 0; i < len; i++) {
        auto obj = (jobject) env->GetObjectArrayElement(item_array, i);
        totalPrice += env->CallDoubleMethod(obj, mID);
    }

    return totalPrice;
}