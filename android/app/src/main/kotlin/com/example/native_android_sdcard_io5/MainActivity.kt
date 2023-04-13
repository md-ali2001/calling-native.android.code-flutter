package com.example.native_android_sdcard_io5

import android.annotation.SuppressLint
import android.os.Environment
import android.widget.Toast
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import java.io.*

public class MainActivity: FlutterActivity() {
    private val CHANNEL= "com.example.native_android_sdcard_io5/toast"

    @SuppressLint("SdCardPath")
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger,CHANNEL).setMethodCallHandler{call, result->
            if( call.method=="showtoast"){
                showtoast();
            }
            if(call.method=="load")
            {
                val sb = StringBuilder();

                val FILE_NAME = "example3.txt"

//Get the text file

//Get the text file

                var fis: FileInputStream? = null
                try {
                    fis = openFileInput(FILE_NAME)
                   // val toast1=Toast.makeText(this,fis.available().toString(), Toast.LENGTH_LONG).show()
                    val isr = InputStreamReader(fis)
                    val br = BufferedReader(isr)


                    var text: String?
                    while (br.readLine().also { text = it } != null) {
                        sb.append(text)
                    }

                    //val toast=Toast.makeText(this,sb.toString(), Toast.LENGTH_LONG).show()

                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    if (fis != null) {
                        try {
                            fis.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }

                }

                result.success(sb.toString())

            }
            if(call.method=="save")
            {
                val FILE_NAME = "example3.txt"

                var fos: FileOutputStream? = null
                try {
                    fos = openFileOutput(FILE_NAME, MODE_PRIVATE)
                    fos.write("saved to sdcard".toByteArray())


                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    if (fos != null) {
                        try {
                            fos.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
            else{
                result.notImplemented();
            }
        }
    }

    fun showtoast() {
       val toast=Toast.makeText(this,"toast", Toast.LENGTH_LONG).show()
        return toast;

    }

    fun load(){



    }

}


