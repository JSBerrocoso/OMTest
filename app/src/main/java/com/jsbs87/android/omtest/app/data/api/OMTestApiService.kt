package com.jsbs87.android.omtest.app.data.api

import com.jsbs87.android.omtest.app.data.entity.MovieEntity
import com.jsbs87.android.omtest.app.data.entity.RecommentationEntity
import com.jsbs87.android.omtest.app.data.entity.ResponseEntity
import com.jsbs87.android.omtest.app.domain.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OMTestApiService {

    @GET("rtv/v1/GetUnifiedList?client=json&statuses=published&definitions=SD;HD;4K&external_category_id=SED_3880&filter_empty_categories=true")
    fun getUnifiedList(): Call<ResponseEntity<List<MovieEntity>>>

    @GET("rtv/v1/GetVideo?client=json")
    fun getVideo(@Query("external_id") externalId: String): Call<ResponseEntity<MovieEntity>>

    @GET("reco/v1/GetVideoRecommendationList?client=json&type=all&subscription=false&filter_viewed_content=true&max_results=10&blend=ar_od_blend_2424video&max_pr_level=8&quality=SD,HD&services=2424VIDEO")
    fun getVideoRecommendationList(@Query("params") externalContentId: String): Call<ResponseEntity<List<RecommentationEntity>>>


}