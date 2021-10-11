package com.example.postrequestpractice

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

//data class User(val n:String,val l:String)

interface APIInterface {
    @Headers("Content-Type: application/json")
    @GET("/test/")
    fun getUserDetails(): Call <List<UserDetails.Datum>>

    @Headers("Content-Type: application/json")
    @POST("/test/")
    fun addUserDetails(@Body userdata:UserDetails.Datum):Call<UserDetails.Datum>


}