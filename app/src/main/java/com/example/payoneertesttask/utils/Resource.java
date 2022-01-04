package com.example.payoneertesttask.utils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.payoneertesttask.data.ApiResponse;
import com.example.payoneertesttask.data.Applicable;

import java.util.List;


public class Resource<T> {
    @NonNull
    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;

    public Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }



    public static <T> Resource<T> success(@NonNull T  data) {
        return new Resource<T>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(@NonNull String msg, @Nullable MutableLiveData<Resource<ApiResponse>>  data) {
        return new Resource<T>(Status.ERROR, (T) data, msg);
    }



    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Status.LOADING, data, null);
    }

    public enum Status { SUCCESS, ERROR, LOADING}
}

