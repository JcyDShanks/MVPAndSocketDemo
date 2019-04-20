package com.example.mvpdemo

import java.lang.ref.WeakReference

class MainPresenter(mainActivity: WeakReference<MainContract.View>):MainContract.Presenter{


    private var mainActivity = mainActivity

    private var mainModel: MainModel? = null

    init {
        mainModel = MainModel()
    }

    override fun getData(): String? {
        return mainModel?.string
    }

    fun changeUI() {
        getData()?.let { mainActivity.get()?.showText(it) }
    }

}
