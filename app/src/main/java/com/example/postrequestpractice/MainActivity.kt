package com.example.postrequestpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var addbutton:Button
    lateinit var textView: TextView
    var userdata:List<UserDetails.Datum>?=null
    var displayResponse = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addbutton=findViewById(R.id.addbutton)
        textView=findViewById(R.id.textView)

        addbutton.setOnClickListener {
            val intent= Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }

        getUserDetails(onResult = {
            userdata = it
            val datumList = userdata
            for (datum in datumList!!) {
                displayResponse += "${datum.name} ${datum.location} "+"\n"+"\n"
            }
            textView.text=displayResponse
        })

    }

    private fun getUserDetails(onResult: (List<UserDetails.Datum>?) -> Unit) {
        val apiInterface = APIClient().getClinet()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.getUserDetails()?.enqueue(object : Callback<List<UserDetails.Datum>> {
                override fun onResponse(
                    call: Call<List<UserDetails.Datum>>,
                    response: Response<List<UserDetails.Datum>>
                ) {
                    onResult(response.body())
                }

                override fun onFailure(call: Call<List<UserDetails.Datum>>, t: Throwable) {
                    onResult(null)
                }

            })
        }
    }
}