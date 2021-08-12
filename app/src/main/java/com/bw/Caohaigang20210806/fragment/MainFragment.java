package com.bw.Caohaigang20210806.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.Caohaigang20210806.GoodsMainActivity;
import com.bw.Caohaigang20210806.MainActivity;
import com.bw.Caohaigang20210806.R;
import com.bw.Caohaigang20210806.adapter.FoodAdapter;
import com.bw.Caohaigang20210806.adapter.FoodAdapterGreenDao;
import com.bw.Caohaigang20210806.contract.FoodContract;
import com.bw.Caohaigang20210806.entity.FoodEntity;
import com.bw.Caohaigang20210806.food.Food;
import com.bw.Caohaigang20210806.model.FoodModel;
import com.bw.Caohaigang20210806.presenter.FoodPresenter;
import com.bw.mvp.view.BaseFragment;
import com.bw.mvp.view.IFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import java.util.List;

public class MainFragment extends BaseFragment<FoodPresenter>implements IFragment, FoodContract.foodView {
    private RecyclerView recyclerView;
    private FoodAdapter foodAdapter;
    private PullToRefreshLayout pull;
    public static List<FoodEntity.DataBean> data;

    @Override
    public void foods(FoodEntity foodEntity) {
        ConnectivityManager systemService = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = systemService.getActiveNetworkInfo();
        if(activeNetworkInfo!=null){

            data = foodEntity.getData();
            foodAdapter = new FoodAdapter(R.layout.item_food_main,data);
            recyclerView.setAdapter(foodAdapter);
        }else{

        }

        pull.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                pull.finishRefresh();
            }

            @Override
            public void loadMore() {
                pull.finishLoadMore();
            }
        });
        foodAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), GoodsMainActivity.class);
                intent.putExtra("pic",foodAdapter.getData().get(position).getPic());
                intent.putExtra("title",foodAdapter.getData().get(position).getTitle());
                intent.putExtra("num",foodAdapter.getData().get(position).getNum());
                startActivity(intent);
            }
        });
//        foodAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
//                String pic = foodAdapter.getData().get(position).getPic();
//                String title = foodAdapter.getData().get(position).getTitle();
//                int num = foodAdapter.getData().get(position).getNum();
//                Food food = new Food();
//                food.setPic(pic);
//                food.setTitle(title);
//                food.setNum(num);
//                MainActivity.foodDao.insert(food);
//                Toast.makeText(getContext(), ""+MainActivity.foodDao.loadAll().size(), Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
    }

    @Override
    public int bandLayout() {
        return R.layout.fragment_main;
    }


    @Override
    public void initView() {
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        pull = (PullToRefreshLayout) findViewById(R.id.pull);
    }

    @Override
    public void initData() {
        mPresenter = new FoodPresenter(new FoodModel(),this);
        mPresenter.fff();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }
}