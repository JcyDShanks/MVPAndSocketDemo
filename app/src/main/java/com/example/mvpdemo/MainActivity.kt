package com.example.mvpdemo

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity(),MainContract.View {

    private val activity: WeakReference<MainContract.View> = WeakReference(this)

    val presenter = MainPresenter(activity)

    private var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when(msg?.what){
                1 -> {
                    presenter.changeUI()
                }
            }
        }
    }

    companion object {
        fun start(context: Context){
            var intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Thread(Runnable {
            Thread.sleep(10000)
            handler.sendEmptyMessage(1)
        }).start()

        textTv.setOnClickListener {
            Main2Activity.start(this)
        }
    }

    override fun showText(string: String) {
        textTv.text = string
    }

}
