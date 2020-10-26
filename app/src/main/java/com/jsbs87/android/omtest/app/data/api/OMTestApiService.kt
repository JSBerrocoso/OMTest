package com.jsbs87.android.omtest.app.data.api

import com.jsbs87.android.omtest.app.data.entity.FilmEntity
import com.jsbs87.android.omtest.app.data.entity.ResponseEntity
import retrofit2.Call
import retrofit2.http.GET

interface OMTestApiService {

    @GET("rtv/v1/GetUnifiedList?client=json&statuses=published&definitions=SD;HD;4K&external_category_id=SED_3880&filter_empty_categories=true")
    fun getFilms(): Call<ResponseEntity<List<FilmEntity>>>

}