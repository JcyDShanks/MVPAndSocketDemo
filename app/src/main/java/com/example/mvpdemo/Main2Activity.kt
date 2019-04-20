package com.example.mvpdemo

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_main2.*
import java.io.*
import java.net.Socket
import java.net.UnknownHostException

class Main2Activity : AppCompatActivity() {



    companion object {
        fun start(context: Context){
            var intent = Intent(context, Main2Activity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }

    }

    private var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when(msg?.what){
                1 -> {
                    textView1.text = SocketBuilder.getInstance().stringBuffer
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        button1.setOnClickListener {
            Thread{
                run {
//                    try {
//                        val socket = Socket("192.168.199.184",10001)
//                        val outputStream: OutputStream = socket.getOutputStream()
//                        outputStream.write(editTv.text.toString().toByteArray())
//                        outputStream.flush()
//                        socket.shutdownOutput()
//                        val inputStream: InputStream = socket.getInputStream()
//                        val inputStreamReader = InputStreamReader(inputStream)
//                        val bufferedReader = BufferedReader(inputStreamReader)
//                        var s:String? = bufferedReader.readLine()
//
//                        while (s != null) {
//                            stringBuffer.append(s)
//                            s = bufferedReader.readLine()
//                        }
//
//                        handler.sendEmptyMessage(1)
//
//                        bufferedReader.close()
//                        inputStreamReader.close()
//                        inputStream.close()
//                        outputStream.close()
//                        socket.close()
//                    } catch (e: UnknownHostException) {
//                        e.printStackTrace()
//                    } catch (e: IOException) {
//                        e.printStackTrace()
//                    }

                    SocketBuilder.getInstance().getConnect("192.168.199.184",10001,editTv.text.toString())
                    handler.sendEmptyMessage(1)
                }
            }.start()
        }

        button2.setOnClickListener {
            MainActivity.start(this)
        }
    }
}
