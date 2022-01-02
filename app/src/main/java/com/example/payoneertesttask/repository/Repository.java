package com.example.payoneertesttask.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.payoneertesttask.data.ApiResponse;
import com.example.payoneertesttask.data.Applicable;
import com.example.payoneertesttask.network.ApiServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    public ApiServices apiServices;

    public Repository(ApiServices apiServices) {
        this.apiServices = apiServices;
    }


    public void getPaymentMethods(MutableLiveData<List<Applicable>> liveData){
        Call<ApiResponse> call = apiServices.getPaymentMethods();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                if (response.isSuccessful()){
                    liveData.postValue(response.body().getNetworks().getApplicable());
                } else {
                    liveData.postValue(null);
                }

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                liveData.postValue(null);

            }
        });
    }
}
