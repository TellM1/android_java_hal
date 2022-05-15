package jp.ac.kadai02_ih13a_15;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Button calcBtn; //ボタンのオブジェクト変数の配置
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcBtn = findViewById(R.id.calc); //ボタンのオブジェクトを取得
        calcBtn.setOnClickListener( v -> {//ラムダ形式
            Log.d("debug","click calc btn");//動作チェック用

            getDates();
        });
    }

    public void getDates(){
        TextView textView_bmi = findViewById(R.id.outputs_bmi);//BMI
        TextView textView_aw = findViewById(R.id.outputs_weight);//Average weight

        //edit textの中身を習得して計算をする
        EditText edit1 = findViewById(R.id.editTextTextPersonName1);//身長
        EditText edit2 = findViewById(R.id.editTextTextPersonName2);//体重

        Editable height_date = edit1.getText();//身長の取得
        Editable weight_date = edit2.getText();//体重の取得

        String height_date_s = height_date.toString();//String形式で取得
        String weight_date_s = weight_date.toString();

        int height_date_int = Integer.parseInt(height_date_s);//String to Intで変換
        int weight_date_int = Integer.parseInt(weight_date_s);

        //数値の単位変換について 正規表現も含めて　以下の文でやるとする
        //身長に関しての正規処理

        //体重に関しての正規処理




        //以下ログ用///////////////////////////////////////////////////////////////
        Log.d("debug",String.valueOf(height_date_int));//動作チェック用
        Log.d("debug", String.valueOf(weight_date_int));//動作チェック用
//        textView_bmi.setText((int) calcBMI(height_date_int,weight_date_int));
//        textView_aw.setText((int) calcBMI(height_date_int,weight_date_int));
    }

    private double calcBMI( double h, double w ){
        // BMI = 体重 ÷ (身長 x 身長)
        double bmi = 0;
        if(w > 0 && h > 0) {
            bmi = w / (h * h);
        }
        return bmi;
    }
}

