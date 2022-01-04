package com.example.payoneertesttask.repository;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.payoneertesttask.data.ApiResponse;
import com.example.payoneertesttask.data.Applicable;
import com.example.payoneertesttask.network.ApiServices;
import com.example.payoneertesttask.utils.Resource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    public ApiServices apiServices;
    public Repository(ApiServices apiServices) {
        this.apiServices = apiServices;
    }


    public void getPaymentMethods(MutableLiveData<Resource<ApiResponse>> liveData){
        liveData.setValue(Resource.loading(null));
        Call<ApiResponse> call = apiServices.getPaymentMethods();
        call.enqueue(new Callback<ApiResponse>() {

            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    liveData.postValue(Resource.success(response.body()));
//                    return Resource.error()
//                    liveData.postValue((response.body().getNetworks()).getApplicable());
//                    liveData.postValue(response.body());
                } else {
                    liveData.postValue(Resource.error(response.message(),liveData));
                }
            }


            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                liveData.postValue(null);

            }
        }
        );
    }
}
