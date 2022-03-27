package main.app.newspaper.network.common

import main.app.newspaper.network.client.RetrofitClient
import main.app.newspaper.network.interfaces.RetrofitService

object Common {
    private const val BASE_URL = "https://newsapi.org/v2/"
    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}