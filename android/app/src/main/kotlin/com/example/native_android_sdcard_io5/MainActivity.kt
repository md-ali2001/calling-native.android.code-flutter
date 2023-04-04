package com.example.native_android_sdcard_io5

import android.widget.Toast
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import java.io.*

public class MainActivity: FlutterActivity() {
    private val CHANNEL= "com.example.native_android_sdcard_io5/toast"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger,CHANNEL).setMethodCallHandler{call, result->
            if( call.method=="load"){
                load()
            }
            else{
                result.notImplemented();
            }
        }
    }

    fun load() {
       val toast=Toast.makeText(this,"platform toast", Toast.LENGTH_LONG).show()
        return toast;

    }

}


