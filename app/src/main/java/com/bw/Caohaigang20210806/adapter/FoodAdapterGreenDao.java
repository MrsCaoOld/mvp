package com.bw.Caohaigang20210806.adapter;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bw.Caohaigang20210806.R;
import com.bw.Caohaigang20210806.food.Food;
import com.bw.Caohaigang20210806.fragment.CarFragment;
import com.bw.Caohaigang20210806.util.ImageLoader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class FoodAdapterGreenDao extends BaseQuickAdapter<Food, BaseViewHolder> {
    private List<Food> data;
    public FoodAdapterGreenDao(int layoutResId, @Nullable List<Food> data) {
        super(layoutResId, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, Food item) {
        ImageLoader.imageRoundedLoader(item.getPic(),5,helper.getView(R.id.image_food_car));
            helper.setText(R.id.text_food_car_name,item.getTitle()+":");
        helper.setText(R.id.text_food_car_money,""+item.getNum());
        CheckBox view = helper.getView(R.id.check);
        if(item.getCheck().equals("1")){
            view.setChecked(true);
        }else{
            view.setChecked(false);
        }

        view.setOnClickListener(new View.OnClickListener() {
            int i = 0;
            int length;
            @Override
            public void onClick(View v) {
                if(view.isChecked()){
                    item.setCheck("1");
                    i++;
                    int num = item.getNum();
                    CarFragment.sum+=num;
                }else{
                    item.setCheck("2");
                    int num = item.getNum();
                    CarFragment.sum-=num;
                }
                CarFragment.text.setText("合计："+CarFragment.sum+"元");
                for (Food datum : data) {
                    length = datum.getCheck().length();
                    System.out.println("+++++++++++++"+length);
                    if(length==i){
                        CarFragment.rvCheck.setChecked(true);
                    }else{
                        CarFragment.rvCheck.setChecked(false);
                    }
                }

            }
        });
    }
}
