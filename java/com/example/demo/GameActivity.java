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

public class GameActivity extends AppCompatActivity {

    TextView title;
    TextView problem_total;
    TextView problem_now;
    TextView correct;
    TextView wrong;
    ImageView img1;
    ImageView img2;
    ImageView img3;

    int[] list = getList();              //img亂數
    int i = (int) (Math.random()*3) + 1; //title亂數
    int now_num;
    int correct_num;
    int wrong_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initView();
        /*   判斷現在第幾題   */
        Bundle bundle = this.getIntent().getExtras();
        if(bundle == null) {
            now_num = 1;
            correct_num = 0;
        } else {
            now_num = bundle.getInt("now_num");
            correct_num = bundle.getInt("correct_num");
            wrong_num = bundle.getInt("wrong_num");
        }
        problem_now.setText(String.valueOf(now_num));
        correct.setText(String.valueOf(correct_num));
        wrong.setText(String.valueOf(wrong_num));

        /*   設置題目title  */
        switch (i){
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
        img1.setImageResource(setFlag(list[0]).getImg());
        img2.setImageResource(setFlag(list[1]).getImg());
        img3.setImageResource(setFlag(list[2]).getImg());

        setListner();
    }

    public void initView(){
        title = findViewById(R.id.title);
        img1 = findViewById(R.id.image1);
        img2 = findViewById(R.id.image2);
        img3 = findViewById(R.id.image3);
        problem_now = findViewById(R.id.problem_now);
        problem_total = findViewById(R.id.problem_total);
        correct = findViewById(R.id.correct);
        wrong = findViewById(R.id.wrong);
    }
    public void setListner(){
        img1.setOnClickListener(action);
        img2.setOnClickListener(action);
        img3.setOnClickListener(action);
    }

    public View.OnClickListener action = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            System.out.println(list[0]);
            System.out.println(list[1]);
            System.out.println(list[2]);
            System.out.println("i="+i);
            switch(view.getId()) {
                case (R.id.image1):
                    if(Integer.parseInt(problem_now.getText().toString())>=5){
                        if(i==1) {
                            correct.setText(String.valueOf(Integer.parseInt(correct.getText().toString())+1));
                            EndDialog();
                        }
                        EndDialog();
                    }else{
                        if(i == 1){
                            correctDialog();//如果是對的flag
                        }else wrongDialog();
                    }
                    break;

                case (R.id.image2):
                    if(Integer.parseInt(problem_now.getText().toString())>=5){
                        if(i==2) {
                            correct.setText(String.valueOf(Integer.parseInt(correct.getText().toString())+1));
                            EndDialog();
                        }
                        EndDialog();
                    }else {
                        if (i == 2) {
                            correctDialog(); //如果是對的flag
                        } else wrongDialog();
                    }
                    break;

                case (R.id.image3):
                    if(Integer.parseInt(problem_now.getText().toString())>=5){
                        if(i==3) {
                            correct.setText(String.valueOf(Integer.parseInt(correct.getText().toString())+1));
                            EndDialog();
                        }
                        EndDialog();
                    }else {
                        if (i == 3) {
                            correctDialog();//如果是對的flag
                        } else wrongDialog();
                    }
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

    private void correctDialog()
    {
        int now = Integer.parseInt(problem_total.getText().toString()) - Integer.parseInt(problem_now.getText().toString()); //總共題數－現在題數 = 剩下幾題
        int now_num = Integer.parseInt(problem_now.getText().toString());   //現在第幾題
        int correct_answer = Integer.parseInt(String.valueOf(correct.getText()));      //答對題數
        int wrong_answer = Integer.parseInt(String.valueOf(wrong.getText()));
        new AlertDialog.Builder(this)
                .setTitle("答對了！")
                .setMessage("還有" + now + "題")
                .setPositiveButton("下一題",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(GameActivity.this, GameActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("correct_num", correct_answer+1); //答對+1
                                bundle.putInt("wrong_num", wrong_answer);    //答錯維持不變
                                bundle.putInt("now_num", now_num+1);
                                intent.putExtras(bundle);
//                                    intent.putExtra("now_num", now_num+1);
                                startActivity(intent);
                            }
                        })
                .show();
    }
    private void wrongDialog()
    {
        int i = Integer.parseInt(problem_total.getText().toString()) - Integer.parseInt(problem_now.getText().toString());
        int now_num = Integer.parseInt(problem_now.getText().toString());
        int correct_answer = Integer.parseInt(String.valueOf(correct.getText()));
        int wrong_answer = Integer.parseInt(String.valueOf(wrong.getText()));
        new AlertDialog.Builder(this)
                .setTitle("答錯了！")
                .setMessage("還有" + i + "題")
                .setPositiveButton("下一題",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(GameActivity.this, GameActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("correct_num", correct_answer); //答對維持不變
                                bundle.putInt("wrong_num", wrong_answer+1); //答錯+1
                                bundle.putInt("now_num", now_num+1);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        })
                .show();
    }
    private void EndDialog()
    {

        String total = String.valueOf(correct.getText());

        new AlertDialog.Builder(this)
                .setTitle("遊戲結束")
                .setMessage("共答對" + total + "題")
                .setPositiveButton("再玩一次",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(GameActivity.this, GameActivity.class);
                                startActivity(intent);
                            }
                        })
                .setNegativeButton("回首頁",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        })
                .show();
    }
}