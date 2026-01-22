package com.example.networktest

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Okhttp","---onCreate()----")
        setContentView(R.layout.activity_main)
        val sendRequestBtn=findViewById<Button>(R.id.sendRequestBtn)
        sendRequestBtn.setOnClickListener {
            sendRequestWithOkHttp()
        }
    }

    private fun sendRequestWithOkHttp(){
        thread{
            try {
                val client= OkHttpClient()
                val request=Request.Builder()
                    .url("https://news.163.com")
                    .build()
                val response=client.newCall(request).execute()
                val responsedata= response.body.string()
                showResponse(responsedata)

            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }
    }


private fun showResponse(response:String){
    Log.d("Okhttp","---Begin response----")
    Log.d("Okhttp","address Network site  Reponse=:$response")
    runOnUiThread {
        val responseText=findViewById<TextView>(R.id.responseText)
        responseText.text=response
    }
}



}