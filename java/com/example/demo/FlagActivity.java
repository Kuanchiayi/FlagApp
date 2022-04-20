package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class FlagActivity extends AppCompatActivity {

    ImageView img;
    TextView tv_name;
    TextView tv_population;
    TextView tv_language;
    TextView tv_capital;
    TextView tv_currency;
    TextView tv_area;
    TextView tv_region;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag);


        initView();
        //取資料
        Bundle bundle = this.getIntent().getExtras();
        Flag flag = (Flag) bundle.getSerializable("obj");

        //設置各種view
        img.setImageResource(flag.getImg());
        tv_name.setText(flag.getName());
        tv_population.setText("人口：" + flag.getPopulation());
        tv_capital.setText("首都：" + flag.getCapital());
        tv_currency.setText("貨幣：" + flag.getCurrency());
        tv_language.setText("語言：" + flag.getLanguage());
        tv_area.setText("佔地面積：" + flag.getArea());
    }
    public void initView(){
        img = findViewById(R.id.img);
        tv_name = findViewById(R.id.name);
        tv_population = findViewById(R.id.population);
        tv_language = findViewById(R.id.language);
        tv_capital = findViewById(R.id.capital);
        tv_currency = findViewById(R.id.money);
        tv_area = findViewById(R.id.area);
        tv_region = findViewById(R.id.region);
    }


}