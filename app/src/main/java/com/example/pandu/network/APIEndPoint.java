package com.example.pandu.network;

import com.example.pandu.entity.DomisiliEntity;
import com.example.pandu.entity.JenkelEntity;
import com.example.pandu.entity.LabelEntity;
import com.example.pandu.entity.OpdEntity;
import com.example.pandu.entity.UsiaEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIEndPoint {
    @GET("/api/status")
    Call<List<LabelEntity>> getLbl();

    @GET("/api/gender")
    Call<List<JenkelEntity>> getJenkel();

    @GET("/api/opd")
    Call<List<OpdEntity>> getOpd();

    @GET("/api/usiaALL")
    Call<List<UsiaEntity>> getUsia();

    @GET("/api/domisili")
    Call<List<DomisiliEntity>> getDomisili();
}
