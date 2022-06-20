package com.example.pandu.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pandu.entity.DomisiliEntity;
import com.example.pandu.entity.JenkelEntity;
import com.example.pandu.entity.LabelEntity;
import com.example.pandu.entity.OpdEntity;
import com.example.pandu.entity.UsiaEntity;
import com.example.pandu.helper.StatusCallback;
import com.example.pandu.network.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyViewModel extends ViewModel {
    StatusCallback callback;

    private final MutableLiveData<List<UsiaEntity>> _getUsia = new MutableLiveData<>();
    public LiveData<List<UsiaEntity>> getUsia() {
        return _getUsia;
    }

    private  final  MutableLiveData<List<LabelEntity>> _getLabel = new MutableLiveData<>();
    public  LiveData<List<LabelEntity>> getLabel(){return  _getLabel;};

    private final MutableLiveData<List<OpdEntity>> _getOpd = new MutableLiveData<>();
    public LiveData<List<OpdEntity>> getOpd(){return _getOpd;};

    private final MutableLiveData<List<DomisiliEntity>> _getDomisili = new MutableLiveData<>();
    public LiveData<List<DomisiliEntity>> getDomisili(){return _getDomisili;};

    private final MutableLiveData<List<JenkelEntity>> _getGender = new MutableLiveData<>();
    public LiveData<List<JenkelEntity>> getGender(){return  _getGender;};

    public final void getDataUsia(){
//        callback.loading();
        APIService.endPoint().getUsia()
                .enqueue(new Callback<List<UsiaEntity>>() {
                    @Override
                    public void onResponse(Call<List<UsiaEntity>> call, Response<List<UsiaEntity>> response) {
                        if (response.isSuccessful()){
                            ArrayList<UsiaEntity> result = (ArrayList<UsiaEntity>) response.body();
                            _getUsia.setValue(result);
//                            callback.success();
                        }else {
//                            callback.empty();
                            _getUsia.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<UsiaEntity>> call, Throwable t) {
//                        callback.error(t.getMessage());
//                        Toast.makeText(getApplicationContext(), "Error :"+t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.d("TEST", t.getMessage());
                    }
                });
    }

    public final void getDataLabel(){
//        callback.loading();
        APIService.endPoint().getLbl()
                .enqueue(new Callback<List<LabelEntity>>() {
                    @Override
                    public void onResponse(Call<List<LabelEntity>> call, Response<List<LabelEntity>> response) {
                        if(response.isSuccessful()){
                            ArrayList<LabelEntity> result = (ArrayList<LabelEntity>) response.body();
                            _getLabel.setValue(result);
//                            callback.success();
                        }else{
//                            callback.empty();
                            _getLabel.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<LabelEntity>> call, Throwable t) {
//                        callback.error(t.getMessage());
                        Log.d("TEST", t.getMessage());
                    }
                });
    }

    public final void getDataOpd(){
//        callback.loading();
        APIService.endPoint().getOpd()
                .enqueue(new Callback<List<OpdEntity>>() {
                    @Override
                    public void onResponse(Call<List<OpdEntity>> call, Response<List<OpdEntity>> response) {
                        if(response.isSuccessful()){
                            ArrayList<OpdEntity> result = (ArrayList<OpdEntity>) response.body();
                            _getOpd.setValue(result);
//                            callback.success();
                        }else{
//                            callback.empty();
                            _getOpd.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<OpdEntity>> call, Throwable t) {
//                        callback.error(t.getMessage());
                        Log.d("TEST", t.getMessage());
                    }
                });
    }

    public final void getDataDomisili(){
//        callback.loading();
        APIService.endPoint().getDomisili()
                .enqueue(new Callback<List<DomisiliEntity>>() {
                    @Override
                    public void onResponse(Call<List<DomisiliEntity>> call, Response<List<DomisiliEntity>> response) {
                        if (response.isSuccessful()){
                            ArrayList<DomisiliEntity> result = (ArrayList<DomisiliEntity>) response.body();
                            _getDomisili.setValue(result);
//                            callback.success();
                        }else{
//                            callback.empty();
                            _getDomisili.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<DomisiliEntity>> call, Throwable t) {
//                        callback.error(t.getMessage());
                        Log.d("TEST", t.getMessage());
                    }
                });
    }

    public final void getDataGender(){
//        callback.loading();
        APIService.endPoint().getJenkel()
                .enqueue(new Callback<List<JenkelEntity>>() {
                    @Override
                    public void onResponse(Call<List<JenkelEntity>> call, Response<List<JenkelEntity>> response) {
                        if (response.isSuccessful()){
                            ArrayList<JenkelEntity> result = (ArrayList<JenkelEntity>) response.body();
                            _getGender.setValue(result);
//                            callback.success();
                        }else {
//                            callback.empty();
                            _getDomisili.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<JenkelEntity>> call, Throwable t) {
//                      callback.error(t.getMessage());
                        Log.d("TEST", t.getMessage());
                    }
                });
    }
}
