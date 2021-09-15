package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.sofix.SoHotFix;

import java.io.File;
import java.io.IOException;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    TextView tvNative;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvNative=findViewById(R.id.tvNative);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE},100);
        }
    }

    public void soFix(View view) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                // 从服务器下载 so ，比对 so 的版本
                // 现在下好了，在我的手机里面 /so/libmain.so
                // 先调用 sdk 方法动态加载或者修复
//                File mainSoPath = new File(Environment.getExternalStorageDirectory(),"libnative-lib.so");
                File mainSoPath = new File(getCacheDir(),"libnative-lib.so");
                if(!mainSoPath.exists()){
                    return false;
                }
                File libSoPath = new File(getDir("lib",Context.MODE_PRIVATE),"so");
                if(!libSoPath.exists()){
                    libSoPath.mkdirs();
                }
                File dst = new File(libSoPath,"libnative-lib.so");
                try {
                    FileUtil.copyFile(mainSoPath,dst);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }

                try {
                    SoHotFix soHotFix = new SoHotFix(MainActivity.this);
                    soHotFix.injectLoadPath(libSoPath.getAbsolutePath());
                    // 手动先加载起来
                    // System.loadLibrary("unity1.so");
                    System.loadLibrary("native-lib");
                    System.load(dst.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                if(result){
                    Toast.makeText(MainActivity.this,"soFix 完成",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"soFix 失败",Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();

    }

    public void loadSo(View view) {
        UnityPlayer player = new UnityPlayer();
       tvNative.setText(player.stringFromJNI());
        Log.e("TAG","OK");
    }
}
