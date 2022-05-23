package com.example.myoutlet.klarna

import com.example.myoutlet.model.Session
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface OrderService {

  @POST("payments/v1/sessions")
  fun createCreditSession(@Body payload: OrderPayload): Call<Session>
}