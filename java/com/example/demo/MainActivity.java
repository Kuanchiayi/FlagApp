package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    ImageView img6;
    ImageView img7;
    ImageView img8;
    Button btn_game;

    //產生亂數
    int[] list = getList();
    //把image放到mArray
    int[] mArray = setImgArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //綁定元件
        initViews();
        //Set Image
        setImgResource();
        //監聽事件
        setListener();
    }
    private void initViews(){
        img1 = findViewById(R.id.image1);
        img2 = findViewById(R.id.image2);
        img3 = findViewById(R.id.image3);
        img4 = findViewById(R.id.image4);
        img5 = findViewById(R.id.image5);
        img6 = findViewById(R.id.image6);
        img7 = findViewById(R.id.image7);
        img8 = findViewById(R.id.image8);
        btn_game = findViewById(R.id.btn_game);
    }

    public void setImgResource(){
        img1.setImageResource(mArray[list[0]]);
        img2.setImageResource(mArray[list[1]]);
        img3.setImageResource(mArray[list[2]]);
        img4.setImageResource(mArray[list[3]]);
        img5.setImageResource(mArray[list[4]]);
        img6.setImageResource(mArray[list[5]]);
        img7.setImageResource(mArray[list[6]]);
        img8.setImageResource(mArray[list[7]]);
    }
    public void setListener() {
        img1.setOnClickListener(nextPage);
        img2.setOnClickListener(nextPage);
        img3.setOnClickListener(nextPage);
        img4.setOnClickListener(nextPage);
        img5.setOnClickListener(nextPage);
        img6.setOnClickListener(nextPage);
        img7.setOnClickListener(nextPage);
        img8.setOnClickListener(nextPage);
        btn_game.setOnClickListener(nextPage);
    }

    public int[] getList(){
        int[] list = new int[8];
        Random r = new Random();
        for (int i = 0; i<list.length;i++) {
            list[i] = r.nextInt(12);
            for(int j=0; j<i ; j++){
                //如果遇到重複值就重新給值
                if(list[j] == list[i]){
                    i--;
                    break;
                }
            }
        }
        return list;
    }

    public int[] setImgArray(){
        int[] mArray = {
                R.drawable.estonia,
                R.drawable.france,
                R.drawable.germany,
                R.drawable.ireland,
                R.drawable.italy,
                R.drawable.monaco,
                R.drawable.nigeria,
                R.drawable.poland,
                R.drawable.russia,
                R.drawable.uk,
                R.drawable.us,
                R.drawable.spain,
        };
        return mArray;
    }

    // 建立一個onClickListner共用
    public View.OnClickListener nextPage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.image1:
                        setApiImgNextPage(list[0]);
                        break;
                    case R.id.image2:
                        setApiImgNextPage(list[1]);
                        break;
                    case R.id.image3:
                        setApiImgNextPage(list[2]);
                        break;
                    case R.id.image4:
                        setApiImgNextPage(list[3]);
                        break;
                    case R.id.image5:
                        setApiImgNextPage(list[4]);
                        break;
                    case R.id.image6:
                        setApiImgNextPage(list[5]);
                        break;
                    case R.id.image7:
                        setApiImgNextPage(list[6]);
                        break;
                    case R.id.image8:
                        setApiImgNextPage(list[7]);
                        break;
                    case R.id.btn_game:
                        setBtnNextPage();
                        break;
                }
        }
    };

    //把Flag變成object
    public Object setFlag(int id){
        Flag estonia = new Flag(R.drawable.estonia, "estonia", "愛沙尼亞共和國","133.1萬","塔林","歐元","愛沙尼亞語","45,338 平方公里");
        Flag france = new Flag(R.drawable.france,"france","法蘭西共和國","6739萬","巴黎","歐元， 太平洋法郎","法語","643,801平方公里" );
        Flag germany = new Flag(R.drawable.germany,"germany","德意志聯邦共和國","8324萬","柏林","歐元","德語","357,386 平方公里");
        Flag ireland = new Flag(R.drawable.ireland,"ireland","愛爾蘭共和國","499.5萬","都柏林","歐元","英語、愛爾蘭語","70,273平方公里");
        Flag italy = new Flag(R.drawable.italy,"italy","義大利共和國","5955萬","羅馬","歐元","義大利語","309,338 平方公里");
        Flag monaco = new Flag(R.drawable.monaco,"monaco","摩納哥侯國","3.924萬","摩納哥","歐元","法語","202 公頃");
        Flag nigeria = new Flag(R.drawable.nigeria,"nigeria","奈及利亞聯邦共和國","2.061 億","阿布賈","奈拉","英語","923,768平方公里");
        Flag poland = new Flag(R.drawable.poland,"poland","波蘭共和國","3795萬","華沙","茲羅提","波蘭語","312,679平方公里");
        Flag russia = new Flag(R.drawable.russia,"russia","俄羅斯聯邦","1.441億","莫斯科","俄羅斯盧布","俄語","17,124,442平方公里");
        Flag uk = new Flag(R.drawable.uk,"United Kingdom","大不列顛暨北愛爾蘭聯合王國","6722萬","倫敦","英鎊","英語","242,495 平方公里");
        Flag us = new Flag(R.drawable.us,"United States of America","美利堅合眾國","3.295億","華盛頓哥倫比亞特區","美元","美語","9,833,520平方公里");
        Flag spain = new Flag(R.drawable.spain,"spain","西班牙王國","4735萬","馬德里","歐元","西班牙語","505,990平方公里");

        Object[] lss = {estonia,france,germany,ireland,italy,monaco,nigeria,poland,russia,uk,us,spain};
        return lss[id];
    }


    //帶值傳遞頁面
    public void setImgNextPage(int id){
        Flag obj = (Flag)setFlag(id);
        Intent intent = new Intent(MainActivity.this, FlagActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("obj", obj);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void setApiImgNextPage(int id){
        Flag obj = (Flag)setFlag(id);
        Intent intent = new Intent(MainActivity.this, FlagApiActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("obj", obj);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void setBtnNextPage(){

        Intent intent = new Intent(MainActivity.this, GameeActivity.class);
        startActivity(intent);
    }
}