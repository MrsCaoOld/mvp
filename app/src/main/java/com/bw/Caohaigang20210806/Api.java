package com.bw.Caohaigang20210806;

import com.bw.Caohaigang20210806.entity.FoodEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

    @GET("ios/cf/dish_list.php?stage_id=1&limit=20&page=1")
    Observable<FoodEntity>food();
}
