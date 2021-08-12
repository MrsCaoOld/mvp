package com.bw.Caohaigang20210806.adapter;

import androidx.annotation.Nullable;

import com.bw.Caohaigang20210806.R;
import com.bw.Caohaigang20210806.entity.FoodEntity;
import com.bw.Caohaigang20210806.util.ImageLoader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class FoodAdapter extends BaseQuickAdapter<FoodEntity.DataBean, BaseViewHolder> {
    public FoodAdapter(int layoutResId, @Nullable List<FoodEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FoodEntity.DataBean item) {
        ImageLoader.imageRoundedLoader(item.getPic(),5,helper.getView(R.id.image_food_main));
        helper.setText(R.id.text_food_main_name,item.getTitle());
        Float num = Float.valueOf(item.getNum());
        helper.setText(R.id.text_food_main_money,"￥"+num);
    }
}
