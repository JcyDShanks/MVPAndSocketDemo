package com.example.mvpdemo

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.lang.Exception
import java.net.Socket

class SocketBuilder: AutoCloseable {

    private lateinit var socket: Socket
    private lateinit var outputStream: OutputStream
    private lateinit var inputStream: InputStream
    private lateinit var inputStreamReader: InputStreamReader
    private lateinit var bufferedReader: BufferedReader
    val stringBuffer = StringBuffer()


    companion object {
        private var INSTANCE: SocketBuilder? = null

        fun getInstance(): SocketBuilder {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = SocketBuilder()
                INSTANCE = instance
                return instance
            }
        }



    }

    fun getConnect(host: String,port: Int,message: String) {
        try {
            socket = Socket(host, port)
            outputStream = socket.getOutputStream()
            outputStream.write(message.toByteArray())
            outputStream.flush()
            socket.shutdownOutput()
            inputStream = socket.getInputStream()
            inputStreamReader = InputStreamReader(inputStream)
            bufferedReader = BufferedReader(inputStreamReader)
            var s: String? = bufferedReader.readLine()
            while (s != null) {
                stringBuffer.append(s)
                stringBuffer.append("\n")
                s = bufferedReader.readLine()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("异常",e.toString())
        }
    }

    override fun close() {
        bufferedReader.close()
        inputStreamReader.close()
        inputStream.close()
        outputStream.close()
        socket.close()
    }

}