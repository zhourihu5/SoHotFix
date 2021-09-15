#!/bin/bash

mkdir so

cp  app/build/intermediates/stripped_native_libs/debug/out/lib/arm64-v8a/libnative-lib.so so/
chmod  -R 777  so/
#adb push so/libnative-lib.so /sdcard/libnative-lib.so1
#adb shell chmod  777 /sdcard/libnative-lib.so1