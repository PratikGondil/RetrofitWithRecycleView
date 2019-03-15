package com.example.pratikg.androidpractice.utils;

import com.example.pratikg.androidpractice.objects.ObjStudent;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface APIInterface {

    @GET("red_cash_api/getUserDetails")
    Call<ObjStudent> doGetListResources();

}
