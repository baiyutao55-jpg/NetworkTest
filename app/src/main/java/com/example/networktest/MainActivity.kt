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
import org.xml.sax.InputSource
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import javax.xml.parsers.SAXParserFactory
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sendRequestBtn=findViewById<Button>(R.id.sendRequestBtn)
        sendRequestBtn.setOnClickListener {
            sendRequestWithOkHttp()
        }

        val ReadxmlBtn=findViewById<Button>(R.id.Readxml)

        ReadxmlBtn.setOnClickListener {
            ReadwithOkhttp()
        }

        val ReadSAXxmlBtn=findViewById<Button>(R.id.ReadSAxxml)

        ReadSAXxmlBtn.setOnClickListener {
            ReadwSAXOkhttp()
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

    Log.d("Okhttp","network reponse:$response")
    runOnUiThread {
        val responseText=findViewById<TextView>(R.id.responseText)
        responseText.text=response
    }
}


    private fun ReadwithOkhttp(){
        thread {
            try{
                val client= OkHttpClient()
                val request= Request.Builder()
                    .url("http://192.168.3.11/get_data.xml")
                    .build()
                val response=client.newCall(request).execute()
                val responseData=response.body?.string()
                if(responseData!=null){
                    parseXMLWithPull(responseData)
                }
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }
    }


    private fun parseXMLWithPull(xmlData:String){
        try{
            val factory= XmlPullParserFactory.newInstance()
            val xmlPullParser=factory.newPullParser()
            xmlPullParser.setInput(StringReader(xmlData))
            var eventType=xmlPullParser.eventType
            var id=""
            var name=""
            var version=""
            while(eventType!= XmlPullParser.END_DOCUMENT){
                val nodeName=xmlPullParser.name
                when(eventType){
                    XmlPullParser.START_TAG->{
                        when(nodeName){
                            "id"->id=xmlPullParser.nextText()
                            "name"->name=xmlPullParser.nextText()
                            "version"->version=xmlPullParser.nextText()
                        }
                    }

                    XmlPullParser.END_TAG->{
                        if("app"==nodeName){
                            Log.d("MainActivity","id is $id")
                            Log.d("MainActivity","name is $name")
                            Log.d("MainActivity","version is $version")
                        }
                    }

                }
                eventType=xmlPullParser.next()
            }



        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }


    private fun ReadwSAXOkhttp(){
        thread {
            try{
                val client= OkHttpClient()
                val request= Request.Builder()
                    .url("http://192.168.3.11/get_data.xml")
                    .build()
                val response=client.newCall(request).execute()
                val responseData=response.body?.string()
                if(responseData!=null){
                    parseXmlWithSAX(responseData)
                }
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }
    }



    private fun parseXmlWithSAX(xmlData:String){
        try{
            val factory= SAXParserFactory.newInstance()
            val xmlRead=factory.newSAXParser().xmlReader
            val handler= ContentHandler()
            xmlRead.contentHandler=handler
            xmlRead.parse(InputSource(StringReader(xmlData)))
        }
        catch (e: Exception){e.printStackTrace()}
    }

}