package com.example.payoneertesttask.di;

import com.example.payoneertesttask.network.ApiServices;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    String baseUrl = "https://raw.githubusercontent.com/";

//    @Provides
//    @Singleton
//    OkHttpClient provideOkHttpClient (HttpLoggingInterceptor httpLoggingInterceptor) {
//        return HttpClient.create(httpLoggingInterceptor);
//    }



    @Singleton
    @Provides
    public ApiServices getApiServices (Retrofit retrofit){
        return retrofit.create(ApiServices.class);
    }

    @Singleton
    @Provides
    public Retrofit getRetrofitInstance (){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

}
