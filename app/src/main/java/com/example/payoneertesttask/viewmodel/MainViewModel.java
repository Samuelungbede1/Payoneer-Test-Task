package com.example.payoneertesttask.viewmodel;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.payoneertesttask.data.ApiResponse;
import com.example.payoneertesttask.data.Applicable;
import com.example.payoneertesttask.network.ApiServices;
import com.example.payoneertesttask.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel implements LifecycleObserver {
    MutableLiveData<List<Applicable>> liveData;

    @Inject
    ApiServices apiServices;

    @Inject
    public MainViewModel() {
        liveData = new MutableLiveData();
    }

    public MutableLiveData<List<Applicable>> getLiveData(){
        return liveData;
    }


    public void  getPaymentMethods(){
        Repository repository = new Repository(apiServices);
        repository.getPaymentMethods(liveData);
    }
}
