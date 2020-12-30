package net.isspgj.mvp

/**
 * Created by pgj on 2020/12/29
 **/
abstract class PBasePresenter<T : PIView>(protected var mView: T?) : PIPresenter {
    override fun onRelease() {
        mView = null
    }
}