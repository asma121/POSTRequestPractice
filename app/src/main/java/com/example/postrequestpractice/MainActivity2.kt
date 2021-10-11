package com.example.postrequestpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body

class MainActivity2 : AppCompatActivity() {
    lateinit var etname:EditText
    lateinit var etlocation:EditText
    lateinit var savebutton:Button
    lateinit var viewbutton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        etname=findViewById(R.id.etname)
        etlocation=findViewById(R.id.etlocation)
        savebutton=findViewById(R.id.savebutton)
        viewbutton=findViewById(R.id.viewbutton)

        savebutton.setOnClickListener {
            var f = UserDetails.Datum(etname.text.toString(), etlocation.text.toString())
            addUserDetails(f, onResult = {
                etname.setText("")
                etlocation.setText("")
            })
        }

        viewbutton.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun addUserDetails(f :UserDetails.Datum ,onResult: () -> Unit){
        val apiInterface = APIClient().getClinet()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.addUserDetails(f)?.enqueue(object : Callback<UserDetails.Datum?> {
                override fun onResponse(call: Call<UserDetails.Datum?>, response: Response<UserDetails.Datum?>) {
                    Toast.makeText(applicationContext,"User added",Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<UserDetails.Datum?>, t: Throwable) {
                    Toast.makeText(applicationContext,"Something went wrong",Toast.LENGTH_LONG).show()
                }

            })
        }
    }
}