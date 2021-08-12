package com.bw.Caohaigang20210806;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.Caohaigang20210806.entity.FoodEntity;
import com.bw.Caohaigang20210806.food.Food;
import com.bw.Caohaigang20210806.fragment.CarFragment;
import com.bw.Caohaigang20210806.fragment.MainFragment;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

public class GoodsMainActivity extends AppCompatActivity {
    private Banner banner;
    private TextView name;
    private TextView money;
    private ImageView goCar;
    private Button addCar;
    private Button buy;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_main);
        banner = (Banner) findViewById(R.id.banner);
        name = (TextView) findViewById(R.id.name);
        money = (TextView) findViewById(R.id.money);

        Intent intent = getIntent();
        String pic = intent.getStringExtra("pic");
        String title = intent.getStringExtra("title");
        Float num = Float.valueOf(intent.getIntExtra("num", 123));
        banner.setImages(MainFragment.data);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                FoodEntity.DataBean dataBean = (FoodEntity.DataBean) path;
                Glide.with(GoodsMainActivity.this).load(dataBean.getPic()).into(imageView);
            }
        });
        banner.start();
        name.setText(title);
        money.setText("￥"+num);

        goCar = (ImageView) findViewById(R.id.go_car);
        addCar = (Button) findViewById(R.id.add_car);
        buy = (Button) findViewById(R.id.buy);

        goCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(GoodsMainActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences aa = getSharedPreferences("aa", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = aa.edit();
                edit.putString("pic1",pic);
                edit.putString("title1",title);
                edit.putFloat("num1",num);
                edit.commit();
                Toast.makeText(GoodsMainActivity.this, "存入SP", Toast.LENGTH_SHORT).show();
            }
        });
        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Food food = new Food();
                food.setPic(pic);
                food.setTitle(title);
                food.setNum(title.length());
                MainActivity.foodDao.insert(food);
                Toast.makeText(GoodsMainActivity.this, ""+MainActivity.foodDao.loadAll().size(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}