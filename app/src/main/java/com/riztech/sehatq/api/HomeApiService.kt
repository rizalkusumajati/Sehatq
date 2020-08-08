package com.riztech.sehatq.api

import com.riztech.sehatq.model.HomeResponse
import io.reactivex.Single
import javax.inject.Inject


class HomeApiService @Inject constructor(var api: HomeApi){

    fun getHome(): Single<List<HomeResponse>> {
        return api.getHome()
    }

}