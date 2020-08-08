package com.riztech.sehatq.api

import com.riztech.sehatq.model.HomeResponse
import io.reactivex.Single
import retrofit2.http.GET

interface HomeApi {
    @GET("home")
    fun getHome(): Single<List<HomeResponse>>
}