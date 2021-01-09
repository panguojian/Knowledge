package net.isspgj.knowledge.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.isspgj.network.RestClient
import com.isspgj.network.callback.IRequest
import kotlinx.android.synthetic.main.activity_net_work.*
import net.isspgj.knowledge.R
import java.util.*

class NetWorkActivity : AppCompatActivity() {
    companion object {
        const val TAG = "NetWorkActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net_work)

        request.setOnClickListener {
            val params = WeakHashMap<String, Any>()
            params["lang"] = "zh"
            params["channel"] = "google"
            params["version"] = "1.4.41"
            params["client_type"] = "android"
            params["clienttype"] = "android"
            val time = System.currentTimeMillis() / 1000L
            params["timestamp"] = time.toString()
            params["end"] = "end"

            RestClient.builder().url("/api/v2/AD/get_ad_list").success {
                Log.i(TAG, it)
            }.error { code, msg ->
                Log.i(TAG, "$code ==> $msg")
            }.failure {
                Log.i(TAG, "${it.message}")
            }.request(object : IRequest {
                override fun onRequestEnd() {
                    Log.i(TAG, "onRequestEnd")
                }

                override fun onRequestStart() {
                    Log.i(TAG, "onRequestStart")
                }
            }).params(params).build().post()
        }
    }
}