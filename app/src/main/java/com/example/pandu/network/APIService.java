package com.example.pandu.network;

import android.util.Log;
import android.widget.Toast;

import com.example.pandu.entity.LabelEntity;
import com.example.pandu.entity.UsiaEntity;
import com.example.pandu.helper.StatusCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {
    private static String BASE_URL = "http://192.168.1.10:8000";
    private static Retrofit retrofit;

    public static APIEndPoint endPoint() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .readTimeout(120, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit.create(APIEndPoint.class);
    }

//    public List<LabelEntity> getDataLabel(){
//        final List<LabelEntity> labelList = new ArrayList<>();
//        callback.loading();
//        APIService.endPoint().getLbl()
//                .enqueue(new Callback<List<LabelEntity>>() {
//                    @Override
//                    public void onResponse(Call<List<LabelEntity>> call, Response<List<LabelEntity>> response) {
//                        if (response.isSuccessful()){
//                            ArrayList<LabelEntity> result = (ArrayList<LabelEntity>) response.body();
//                            labelList.addAll(result);
//                            callback.success();
//                        }else{
//                            callback.empty();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<LabelEntity>> call, Throwable t) {
//                        callback.error(t.getMessage());
//                    }
//                });
//
//        return labelList;
//    }

}
