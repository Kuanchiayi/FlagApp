package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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



public class FlagApiActivity extends AppCompatActivity {

    ImageView img;
    TextView tv_name;
    TextView tv_population;
    TextView tv_language;
    TextView tv_capital;
    TextView tv_currency;
    TextView tv_area;
    TextView tv_region;
    String returnStr;
    String country;
    String population;
    String capital;
    String area;
    String region;
    String currency_name;
    String symbol;
    String language_name;
    String subRegion;
    String country_name;
    String flagUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_api);

        initView();
        Bundle bundle = this.getIntent().getExtras();
        Flag flag = (Flag) bundle.getSerializable("obj");
        country_name = flag.getEn_name();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                  sendGet();
            }
        });
        thread.start();
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

    private void sendGet(){
        /*   建立連線   */
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))  //Log資訊
                .build();
        /*   傳送需求  */
        Request request = new Request.Builder()
                .url("https://restcountries.com/v2/name/" + country_name)
                .build();

        /*   設置回傳  */
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                /*如果傳送過程有發生錯誤*/
                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                /*    取得回傳   */
                returnStr = response.body().string();
                try {
                    /*  把取得的資訊放入info_array  */
                    JSONArray info_array = new JSONArray(returnStr);
                    JSONObject jsonObject = info_array.getJSONObject(0);
                    /*   用jsonObject抓取value   */
                    JSONObject flagObj = jsonObject.getJSONObject("flags");
                    flagUrl = flagObj.getString("png");

                    country = jsonObject.getString("name");
                    population = jsonObject.getString("population");
                    capital = jsonObject.getString("capital");

                    JSONArray currencies = new JSONArray(jsonObject.getString("currencies"));
                    for(int j=0; j<currencies.length(); j++){
                         JSONObject currenciesJSONObject = currencies.getJSONObject(j);
                         currency_name = currenciesJSONObject.getString("code");
                         symbol =  currenciesJSONObject.getString("symbol");
                    }

                    JSONArray languages = new JSONArray(jsonObject.getString("languages"));
                    for(int k=0; k<languages.length(); k++){
                         JSONObject languagesJSONObject = languages.getJSONObject(k);
                         language_name = languagesJSONObject .getString("name");
                    }
                    area = jsonObject.getString("area");
                    region = jsonObject.getString("region");
                    subRegion = jsonObject.getString("subregion");

                     /*      設置TextView      */
                    runOnUiThread(() -> {
                        Glide.with(FlagApiActivity.this).load(flagUrl).into(img);
                        tv_name.setText(country);
                        tv_population.setText(String.format("population : %s" , population));
                        tv_capital.setText(String.format("capital : %s", capital));
                        tv_currency.setText(String.format("currencies : %s(%s)", currency_name, symbol));
                        tv_language.setText(String.format("language : %s", language_name));
                        tv_area.setText(String.format("area : %s KM²", area));
                        tv_region.setText(String.format("region : %s - %s", region, subRegion));
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}