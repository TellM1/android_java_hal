package jp.ac.kadai01_ih13a_15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //btnクリック時のメソッドを記述
    public void click_test(View view){
        //Text view に任意の文字列を表示する
        //表示させたいviewをインスタンス化
        TextView tv = findViewById(R.id.textView);
        tv.setText("はじめてのアンドロイドスタジオ");
    }
}