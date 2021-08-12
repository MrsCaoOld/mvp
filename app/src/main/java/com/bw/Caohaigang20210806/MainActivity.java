package com.bw.Caohaigang20210806;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.bw.Caohaigang20210806.adapter.FragmentAdapter;
import com.bw.Caohaigang20210806.entity.MyBtnEntity;
import com.bw.Caohaigang20210806.food.DaoMaster;
import com.bw.Caohaigang20210806.food.FoodDao;
import com.bw.Caohaigang20210806.fragment.CarFragment;
import com.bw.Caohaigang20210806.fragment.MainFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager vp;
    private CommonTabLayout tab;
    private ArrayList<Fragment>list = new ArrayList<>();
    private ArrayList<CustomTabEntity> tabEntitys = new ArrayList<>();
    public static FoodDao foodDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (CommonTabLayout) findViewById(R.id.tab);
        //ViewPager
        list.add(new MainFragment());
        list.add(new CarFragment());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), list);
        vp.setAdapter(fragmentAdapter);
        //CommonTabLayout
        tabEntitys.add(new MyBtnEntity("首页",0,0));
        tabEntitys.add(new MyBtnEntity("购物车",0,0));
        tab.setTabData(tabEntitys);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tab.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        DaoMaster.DevOpenHelper food = new DaoMaster.DevOpenHelper(this, "food");
        SQLiteDatabase writableDatabase = food.getWritableDatabase();
        foodDao = new DaoMaster(writableDatabase).newSession().getFoodDao();

    }
}