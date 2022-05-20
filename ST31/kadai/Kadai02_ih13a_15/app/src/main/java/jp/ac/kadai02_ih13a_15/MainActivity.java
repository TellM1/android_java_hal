package jp.ac.kadai02_ih13a_15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.app.Dialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Button calcBtn; //ボタンのオブジェクト変数の配置
    private String message;
    private int iid = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcBtn = findViewById(R.id.calc); //ボタンのオブジェクトを取得
        calcBtn.setOnClickListener( v -> {//ラムダ形式
            Log.d("debug","click calc btn");//動作チェック用

            getDates();
        });
    }
    public void AlertEMC(String s){//アラートでメッセージを表示させる関数
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(s)
                .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // ボタンをクリックしたときの動作
                        //特に無し
                    }
                });
        builder.show();
    }

    public void Show_image(int i){
        ImageView Sample_image = findViewById(R.id.imageView);//サンプル画像表示領域
        switch (i){
            case 0:
                Sample_image.setImageResource(R.drawable.thin);
                break;
            case 1:
                Sample_image.setImageResource(R.drawable.normal);
                break;
            case 2:
                Sample_image.setImageResource(R.drawable.fat);
                break;
            case 3:
                Sample_image.setImageResource(R.drawable.sofat);
                break;
            case 4:
                Sample_image.setImageResource(R.drawable.sosofat);
                break;
            case 5:
                Sample_image.setImageResource(R.drawable.sososofat);
                break;
        }


    }

    public void getDates() {

        TextView textView_bmi = findViewById(R.id.outputs_bmi);//BMI
        TextView textView_aw = findViewById(R.id.outputs_weight);//Average weight
        TextView textView_mes = findViewById(R.id.outputs_mes);//肥満度mes
        //edit textの中身を習得して計算をする
        EditText edit1 = findViewById(R.id.editTextTextPersonName1);//身長
        EditText edit2 = findViewById(R.id.editTextTextPersonName2);//体重

        Editable height_date = edit1.getText();//身長の取得
        Editable weight_date = edit2.getText();//体重の取得

        String height_date_s = height_date.toString();//String形式で取得
        String weight_date_s = weight_date.toString();


        //数値の単位変換について 正規表現も含めて　以下の文でやるとする
        //正規表現
        String num_pattern = "^-?(0|[1-9]\\d*)(\\.\\d+|)$";//少数を含めて判定するパターン

        //Javaの正規表現ライブラリ
        Pattern height_p = Pattern.compile(num_pattern); // 正規表現パターンの読み込み
        Matcher height_m = height_p.matcher(height_date_s);

        Pattern weight_p = Pattern.compile(num_pattern); // 正規表現パターンの読み込み
        Matcher weight_m = weight_p.matcher(weight_date_s);

        //パターンと検査対象文字列の照合
        //文字列なのでStringの方を用いる
        boolean result1 = height_m.matches(); // 照合結果をtrueまたはfalseで取得する
        boolean result2 = weight_m.matches(); // 照合結果をtrueまたはfalseで取得する

        //身長に関しての正規処理
        double height_date_intd = 0;
        if (result1 != false) {
            Log.d("debug", "身長");//動作チェック用
            height_date_intd = Double.parseDouble(height_date_s)/100; //

            if(height_date_intd < 0.5 || height_date_intd > 3.00){
                AlertEMC("身長おかしくない！？");
                return;//処理を中断して巻き戻し あとの処理はされずに戻す
            }
            Log.d("debug", String.valueOf(height_date_intd));//動作チェック用
        } else {
            AlertEMC("中身ほんとに数字？");
            Log.d("debug", "中身が数値以外のものが入ってるかも");//動作チェック用
            return;
        }
        //体重に関しての正規処理
        double weight_date_intd = 0;
        if (result2) {
            Log.d("debug", "体重");//動作チェック用
            weight_date_intd = Double.parseDouble(weight_date_s);
            if(weight_date_intd < 0 || weight_date_intd > 300){
                AlertEMC("体重おかしくない！？");
                return;//処理を中断して巻き戻し あとの処理はされずに戻す
            }
        } else {
            AlertEMC("体重以外の値入れてない？");
            Log.d("debug", "中身が数値以外のものが入ってるかも");//動作チェック用
        }
        Log.d("debug", "BMI");//動作チェック用
        Log.d("debug", String.valueOf(calcBMI(height_date_intd, weight_date_intd)));//動作チェック用
        Log.d("debug", "AVW");//動作チェック用
        Log.d("debug", String.valueOf(calcAVW(height_date_intd)));//動作チェック用

        //計算結果をテキストviewに適応させる
        textView_bmi.setText(calcBMI(height_date_intd, weight_date_intd));
        textView_aw.setText(String.valueOf(calcAVW(height_date_intd)));

        double bmiss = Double.parseDouble(calcBMI(height_date_intd, weight_date_intd));

        //自分が痩せ型なのか肥満かの簡易指標
        if (bmiss < 18.5) {
            message = "低体重（やせ型）";
            iid = 0;
        } else if (bmiss >= 18.5 && bmiss < 25) {
            message = "普通体重";
            iid = 1;
        } else if (bmiss >= 25 && bmiss < 30) {
            message = "肥満(level1)";
            iid = 2;
        } else if (bmiss >= 30 && bmiss < 35) {
            message = "肥満(level2)";
            iid = 3;
        } else if (bmiss >= 35 && bmiss < 40) {
            message = "肥満(level3)";
            iid = 4;
        } else if (bmiss >= 40) {
            message = "肥満(level4)";
            iid = 5;
        }
        textView_mes.setText(message);
        Show_image(iid);
        //以下ログ用///////////////////////////////////////////////////////////////

    }

    private String calcBMI(double h, double w ) {
        // BMI = 体重 ÷ (身長 x 身長)
        double bmi = 0;
        String S_bmi = new String();
        if (w > 0 && h > 0) {
            bmi = (w / Math.pow(h, 2));
//            bmi = Math.round(bmi);
            //計算結果を小数点第1位までに丸めたい
//            Sbmi = String.valueOf(bmi);
            S_bmi = String.format("%.1f", bmi);
        }

        return S_bmi;
    }
    private String calcAVW(double h){
        // 標準体重 = (身長(m)×身長(m)) × 22
        double avw = 0;
        String S_avw = new String();
        if(h > 0) {
            avw = Math.pow(h, 2) * 22;
            S_avw = String.format("%.1f", avw);
        }
        return S_avw;
    }
}

