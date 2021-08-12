package com.bw.Caohaigang20210806.model;

import com.bw.Caohaigang20210806.Api;
import com.bw.Caohaigang20210806.contract.FoodContract;
import com.bw.Caohaigang20210806.entity.FoodEntity;
import com.bw.Caohaigang20210806.util.Utils;
import com.bw.mvp.model.BaseModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class FoodModel extends BaseModel implements FoodContract.foodModel {
    @Override
    public void fooda(Observer<FoodEntity> observer) {
        Retrofit abc = Utils.getUtils().abc("http://www.qubaobei.com/");
        Api api = abc.create(Api.class);
        api.food()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
