package com.example.payoneertesttask.network;

import com.example.payoneertesttask.data.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    @GET("optile/checkout-android/develop/shared-test/lists/listresult.json")
    Call<ApiResponse> getPaymentMethods();
}
