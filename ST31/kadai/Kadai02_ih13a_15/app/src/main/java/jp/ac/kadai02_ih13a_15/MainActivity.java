package jp.ac.kadai02_ih13a_15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sampleBtn = findViewById(R.id.button);
        sampleBtn.setOnClickListerner(view -> {
            //クリック時の操作について
            calcBMIs();
        });
    }
    //    BMI計算についての記述　calucBMIs()
    private void calcBMIs(){
        //edit textの中身を習得して計算をする
    }
}