package com.example.zivame_assignment.ui.gadgetlist.repository

import com.example.zivame_assignment.ui.gadgetlist.model.GadgetListResponse
import io.reactivex.Single
import retrofit2.http.GET

interface GadgetListApiService {

    @GET("/nancymadan/assignment/db")
    fun getAllGadgetsList(): Single<GadgetListResponse>
}