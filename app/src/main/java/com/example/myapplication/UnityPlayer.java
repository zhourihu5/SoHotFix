package com.example.myapplication;

public class UnityPlayer {
    static {
        System.loadLibrary("native-lib");
    }
    native String stringFromJNI();
}
