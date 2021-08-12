package com.bw.Caohaigang20210806.presenter;

import com.bw.Caohaigang20210806.contract.FoodContract;
import com.bw.Caohaigang20210806.entity.FoodEntity;
import com.bw.mvp.presenter.BasePresenter;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FoodPresenter extends BasePresenter<FoodContract.foodModel,FoodContract.foodView> {
    private Disposable disposable;
    public FoodPresenter(FoodContract.foodModel mModel, FoodContract.foodView mView) {
        super(mModel, mView);
    }
    public void fff(){
        mModel.fooda(new Observer<FoodEntity>() {
            @Override
            public void onSubscribe( Disposable d) {
//                disposable = d;
            }

            @Override
            public void onNext( FoodEntity foodEntity) {
                mView.foods(foodEntity);
            }

            @Override
            public void onError( Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if(disposable!=null){
//            disposable=null;
//        }
//    }
}
