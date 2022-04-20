package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameeActivity extends AppCompatActivity {

    TextView title;
    TextView problem;
    TextView Answer;
    ImageView img1;
    ImageView img2;
    ImageView img3;
    int question_now;
    int question = 5;
    int correct_answer=0;
    int wrong_answer=0;
    int title_num;
    String correct_wrong_value;
    int[] list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamee);

        initView();
        //畫面一開始設置頁面的各項
        set("initial");
        System.out.println("setting" + title_num);
        setListener();
    }

    public int[] getList(){
        int[] list = new int[3];
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
    public void initView(){
        title = findViewById(R.id.title);
        problem = findViewById(R.id.problem);
        Answer = findViewById(R.id.answer);
        img1 = findViewById(R.id.image1);
        img2 = findViewById(R.id.image2);
        img3 = findViewById(R.id.image3);
    }

    public void setListener(){
        img1.setOnClickListener(action);
        img2.setOnClickListener(action);
        img3.setOnClickListener(action);
    }

    public void set(String value){
        /*
         * title
         * 題目數（problem_now\problem）
         * 答對\答錯 (correct_num\wrong_num)
         * image
         * */
        list = getList();
        title_num = (int) (Math.random() * 3) + 1;
        /*
           題目是從三個亂數（list）裡面挑一個出來
        */
        switch (title_num) {
            case 1:
                title.setText(setFlag(list[0]).getName());
                break;
            case 2:
                title.setText(setFlag(list[1]).getName());
                break;
            case 3:
                title.setText(setFlag(list[2]).getName());
                break;
        }

        switch (value) {
            case "correct":
                correct_answer += 1;
                question_now = Integer.parseInt(problem.getText().toString().substring(0, 1)) + 1;
                break;
            case "wrong":
                wrong_answer += 1;
                question_now = Integer.parseInt(problem.getText().toString().substring(0, 1)) + 1;
                break;
            case "initial":
                /*  initial view   */
                correct_answer = 0;
                wrong_answer = 0;
                question_now = 1;
                break;
        }

        Answer.setText("答對:" + correct_answer + " 答錯:" + wrong_answer);
        problem.setText(String.format("%d/%d", question_now, question));
        img1.setImageResource(setFlag(list[0]).getImg());
        img2.setImageResource(setFlag(list[1]).getImg());
        img3.setImageResource(setFlag(list[2]).getImg());
    }
    public View.OnClickListener action = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case (R.id.image1):
                    decide_correct_wrong(1);
                    break;
                case R.id.image2:
                    decide_correct_wrong(2);
                    break;
                case R.id.image3:
                    decide_correct_wrong(3);
                    break;
            }

        }
    };

    public Flag setFlag(int id){
        Flag estonia = new Flag(R.drawable.estonia,"愛沙尼亞共和國");
        Flag france = new Flag(R.drawable.france,"法蘭西共和國");
        Flag germany = new Flag(R.drawable.germany,"德意志聯邦共和國");
        Flag ireland = new Flag(R.drawable.ireland,"愛爾蘭共和國");
        Flag italy = new Flag(R.drawable.italy,"義大利共和國");
        Flag monaco = new Flag(R.drawable.monaco,"摩納哥侯國");
        Flag nigeria = new Flag(R.drawable.nigeria,"奈及利亞聯邦共和國");
        Flag poland = new Flag(R.drawable.poland,"波蘭共和國");
        Flag russia = new Flag(R.drawable.russia,"俄羅斯聯邦");
        Flag uk = new Flag(R.drawable.uk,"大不列顛暨北愛爾蘭聯合王國");
        Flag us = new Flag(R.drawable.us,"美利堅合眾國");
        Flag spain = new Flag(R.drawable.spain,"西班牙王國");
        Flag[] ls = {estonia,france,germany,ireland,italy,monaco,nigeria,poland,russia,uk,us,spain};
        return ls[id];
    }


    private void show_Dialog(boolean correct){
        int now = Integer.parseInt(problem.getText().toString().substring(2,3)) - Integer.parseInt(problem.getText().toString().substring(0,1));
        String msg = "還有" + now + "題"; //還剩幾題
        String answer;

        if(correct){
            answer = "答對了";
            correct_wrong_value = "correct";
        }else{
            answer = "答錯了";
            correct_wrong_value = "wrong";
        }

        new AlertDialog.Builder(this)
                .setTitle(answer)
                .setMessage(msg)
                .setPositiveButton("下一題",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //設定變數
                                set(correct_wrong_value);
                            }
                        })
                .show();
    }

    private void endDialog(){
        String answer = "遊戲結束";
        String msg = "答對" + correct_answer + "題";
        new AlertDialog.Builder(this)
                .setTitle(answer)
                .setMessage(msg)
                .setPositiveButton("再玩一次",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(GameeActivity.this, GameeActivity.class);
                                startActivity(intent);
                            }
                        })
                .setNegativeButton("回首頁",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(GameeActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        })
                .show();
    }

    private void decide_correct_wrong(int img){
        if(question_now==5){
            if(title_num == img){
                correct_answer += 1;
            }
            endDialog();
        }else{
            if(title_num == img){
                show_Dialog(true);
            }else show_Dialog(false);
        }
    }
}