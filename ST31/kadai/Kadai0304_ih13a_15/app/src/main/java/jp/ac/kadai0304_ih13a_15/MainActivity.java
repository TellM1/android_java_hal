package jp.ac.kadai0304_ih13a_15;

import static java.lang.Math.sqrt;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    //変数宣言
    private Double Results_num = 0.0;
    private String calcOpe = "";
    private Double subNum = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button[] numBtn = new Button[10];
        for (int i = 0; i < 10; i++) {
            String Btntag = "button" + (i);
            int numBtns = getResources().getIdentifier(Btntag, "id", getPackageName());
            numBtn[i] = (findViewById(numBtns));
            Log.d("debug", numBtn[i].toString());
        }

        for(int i= 0; i < numBtn.length; i++){
            numBtn[i].setOnClickListener(view -> {
                numBtnClicked(view);
            });
        }

        Button clearBtn = findViewById(R.id.buttonclear);
        Button aclearBtn = findViewById(R.id.buttonAclear);

        //function系
        Button plusBtn = findViewById(R.id.buttonPlus);
        Button rootBtn = findViewById(R.id.buttonroot);
        Button splitBtn = findViewById(R.id.buttonsplit);
        Button powBtn = findViewById(R.id.buttonpow);
        Button minusBtn = findViewById(R.id.buttonMinus);

        Button equalBtn = findViewById(R.id.buttonE);

        Button dotBtn = findViewById(R.id.buttonDot);
        Button plmiBtn = findViewById(R.id.buttonPM);
        Button backsp = findViewById(R.id.buttonback);

        //clearBtnの実装
        clearBtn.setOnClickListener(view->{clearBtn();});
        //AclearBtn
        aclearBtn.setOnClickListener(view->{
            aclearBtn();
            Results_num = 0.0;
        });
        //rootBtn
        rootBtn.setOnClickListener(view->{funcOpeBtn(view);});
        //BackSpace
        backsp.setOnClickListener(view->{funcBack(view);});
        //dotBtn
        dotBtn.setOnClickListener(view->{funcDotBtn(view);});

        //operation Buttons/////////////////////////////////////
        //split
        splitBtn .setOnClickListener(view->{funcOpeBtn(view);});
        //pow
        powBtn .setOnClickListener(view->{funcOpeBtn(view);});
        //minus
        minusBtn.setOnClickListener(view->{funcOpeBtn(view);});
        //plus
        plusBtn.setOnClickListener(view->{funcOpeBtn(view);});
        /////////////////////////////////////////////////////
        //PlusMinus
        plmiBtn.setOnClickListener(View -> {pmBtn();});

        //equal
        equalBtn.setOnClickListener(view->{funcEquBtn(view);});
    }
    public static String getContent(View view){
        Button Btn = (Button)view;
        String contents = Btn.getText().toString();//押された中身
        return contents;
    }
    public String getDisplays(){
        TextView tV_cl_rs = findViewById(R.id.results);
        String Display_contents = tV_cl_rs.getText().toString();
        return Display_contents;
    }
    public void clearBtn(){
        TextView tV_cl_rs = findViewById(R.id.results);
        tV_cl_rs.setText("0");
    }
    public void aclearBtn(){
        subNum = 0.0;
        Results_num = 0.0;
        setDisplay("0");
        setOperatDisplay("");
    }
    public void setDisplay(String Value){
        TextView tV_cl_rs = findViewById(R.id.results);
//        tV_cl_rs.setText("");
        //カンマ区切り 未実装
            //System.out.println(String.format("%,d", 10000));  //-> "10,000"
            //formatNumber(数字)　--これらで3桁区切りができるはず。
            //if(!inputString.equals(".") == true){
            //    String inputs = formatNumber(Double.parseDouble(inputString));
            //tV_cl_rs.setText(inputs);
            //}else{
            //tV_cl_rs.setText(inputString);
            //}
        String inputString = double_format(Double.parseDouble(Value));//.0検査
        tV_cl_rs.setText(inputString);
        Log.d("setD", "set処理したよ ");
        //double_formatはint変換した場合と一致したら.0以降が0という判定で考えられることを利用したフォーマット方法です。
    }
    public void setOperatDisplay(String Value){
        TextView tV_cl_rs = findViewById(R.id.operationArea);
        tV_cl_rs.setText("");//いったんdisplayは消しとく
        tV_cl_rs.setText(Value);
        Log.d("setO", "set処理したよ ");
    }
    private void numBtnClicked(View view) {
        //押されたボタンのIDを取得して表示エリアに数字を表示する
        //ViewからButtonねのcast
        String inTxt = getContent(view);
        String results = null;
        String dis_text = getDisplays();

        if(Objects.equals(dis_text, "0")){//最初の初期値だった時
            results = inTxt;
            Log.d("ver001", String.valueOf(1));
        }else{//それ以外
            results = dis_text + inTxt;
            Log.d("ver001", String.valueOf(2));
        }
        setDisplay(results);
    }
    public void funcOpeBtn(View view){
        Button Btn = (Button)view;
        TextView tV_cl_rs = findViewById(R.id.results);
        String getText = Btn.getText().toString();//押した演算子の取得
        String getNum = getDisplays();//現時点での数字
        String strResnum = tV_cl_rs.getText().toString();

        switch (getText){
            case "+":
                setOperatDisplay("+");
                subNum = Double.valueOf(getDisplays());
                Log.d("opeText", String.valueOf(subNum));
                clearBtn();
                break;
            case "-":
                setOperatDisplay("-");
                subNum = Double.valueOf(getDisplays());
                Log.d("opeText", String.valueOf(subNum));
                clearBtn();
                break;
            case "÷":
                setOperatDisplay("÷");
                subNum = Double.valueOf(getDisplays());
                Log.d("opeText", String.valueOf(subNum));
                clearBtn();
                break;
            case "×":
                setOperatDisplay("×");
                subNum = Double.valueOf(getDisplays());
                Log.d("opeText", String.valueOf(subNum));
                clearBtn();
                break;
            case "√":
//                subNum = Integer.parseInt(getDisplays());
                setOperatDisplay("√");
                Double rootResults = sqrt(Double.valueOf(getNum));
                Log.d("rootする数値", strResnum);
                Log.d("rootされた数値", String.valueOf(rootResults));
                setDisplay(String.valueOf(rootResults));
//                clearBtn();
                break;
        }
    }
    public void funcEquBtn(View view){
        Button Btn = (Button)view;
        TextView now_Result = findViewById(R.id.results);
        TextView nowOpe = findViewById(R.id.operationArea);
//        String getText = Btn.getText().toString();//押した演算子の取得
        Double strResnum = Double.parseDouble(now_Result.getText().toString());

        switch (nowOpe.getText().toString()){
            case "+":
                Double plusResults = strResnum + subNum;
                setDisplay(String.valueOf(plusResults));
                subNum = plusResults;
                break;
            case "-":
                Double minusResults = subNum - strResnum;
                setDisplay(String.valueOf(minusResults));
                subNum = minusResults;
                break;
            case "÷":
                Double  splitResults = strResnum / subNum;
                setDisplay(String.valueOf(splitResults));
                subNum = splitResults;
                break;
            case "×":
                Double  powResults = subNum * strResnum;
                setDisplay(String.valueOf(powResults));
                subNum = powResults;
                break;
        }
    }
    public void funcDotBtn(View view){
        String nowStrresult = getDisplays();
        if(nowStrresult.contains(".")){
            //特に何も処理しない
            Log.d("dot", "処理を行いました。");
        }else{
            String sumStr = nowStrresult + ".";
//            setDisplay(sumStr);
            //どっと以下の計算をミドルウェのような位置に入れたためここだけ単独setdisp
            TextView tV_cl_rs = findViewById(R.id.results);
            tV_cl_rs.setText(sumStr);
            Log.d("dot", "処理を行いました。");
        }
    }
    public void pmBtn(){
        double getnum = Double.parseDouble(getDisplays());//displayにある値を数値で取得
        getnum = (getnum * -1);//-1をかけてプラマイ変換
        setDisplay(String.valueOf(getnum));//Displayにセット
    }
    public static String double_format(double d){
        if(d == (int) d)
            return String.format("%d",(int)d);
        else
            return String.format("%s",d);
    }
    public static String formatNumber(long num) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        return nf.format(num);
    }
    public void funcBack(View view){
        String rmed_text;
        String nowtext = getDisplays();
        if((nowtext.length()-1)==0){
            rmed_text = null;
        }else{
            rmed_text = nowtext.substring(0, nowtext.length()-1);
        }
        if(rmed_text == null){
            setDisplay("0");
        }else{
            setDisplay(rmed_text);
        }

    }
}

//〇実数での計算に対応（難易度：★★☆☆☆）
//〇無駄な小数点は表示しない（例　4.0 → 4とする）（難易度：★★★☆☆）
//〇「←（BackSpace）」キーで値の修正が可能（難易度：★★☆☆☆）
//6.22時点での進捗----------------------------------------------|
//
//×計算の過程を別枠で表示する（難易度：★★☆☆☆）//文字列として毎回ボタンを押されるごとに処理をこなせば行けなくもなさそう
//×連続した計算式の入力に対応（難易度：★★★☆☆）//今の状態だと計算の値をgetDisしてくるので上の機能を入れてから考えたほうがいいかもしれない
//例　１＋２＋４＝７（難易度：★★★☆☆） //現状四則演算を押すとサブnumとしてほぞするのでそこを計算したものを格納するようにすれば連続四則演算ボタンで計算を実現できるかもしれない
//　　１＋２＝３＋４＝７

//×( )など、計算の優先順位を考慮する（難易度：★★★★★）//全然考えてないです。
//3桁ごとにカンマ（,）を入れる（難易度：★★★★★）//整数値だけであれば簡単に搭載できそう　少数まで含めてするとなると切り離しての処理が必要だと考える
//