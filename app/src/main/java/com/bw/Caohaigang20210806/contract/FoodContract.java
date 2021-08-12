package com.bw.Caohaigang20210806.contract;

import com.bw.Caohaigang20210806.entity.FoodEntity;
import com.bw.mvp.model.IModel;
import com.bw.mvp.view.IView;

import io.reactivex.Observer;

public interface FoodContract {
    interface foodModel extends IModel{
        void fooda(Observer<FoodEntity>observer);
    }
    interface foodView extends IView{
        void foods(FoodEntity foodEntity);
    }
}
