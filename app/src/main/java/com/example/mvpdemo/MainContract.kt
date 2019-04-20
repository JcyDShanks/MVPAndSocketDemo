package com.example.mvpdemo

interface MainContract {
    interface View{
        fun showText(string: String)
    }
    interface Presenter{
        fun getData():String?
    }
}