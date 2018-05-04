package com.shakib.icenine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Login extends AppCompatActivity {
    private boolean[] ones = {false, false, false, false, false, false, false, false, false};
    private boolean[] tens = {false, false, false, false, false, false, false, false, false};

    private char ten;
    private char one;

    private char inputOne;
    private char inputTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        //views
        Switch s1 = (Switch) findViewById(R.id.switch1); //1
        Switch s2 = (Switch) findViewById(R.id.switch2); //2
        Switch s3 = (Switch) findViewById(R.id.switch3); //3
        Switch s4 = (Switch) findViewById(R.id.switch4); //4
        Switch s5 = (Switch) findViewById(R.id.switch5); //5
        Switch s6 = (Switch) findViewById(R.id.switch6); //6
        Switch s7 = (Switch) findViewById(R.id.switch7); //7
        Switch s8 = (Switch) findViewById(R.id.switch8); //8
        Switch s10 = (Switch) findViewById(R.id.switch10); //9

        Switch s11 = (Switch) findViewById(R.id.switch11); //8-2
        Switch s12 = (Switch) findViewById(R.id.switch12); //9-2
        Switch s13 = (Switch) findViewById(R.id.switch13); //7-2
        Switch s14 = (Switch) findViewById(R.id.switch14); //6-2
        Switch s15 = (Switch) findViewById(R.id.switch15); //5-2
        Switch s16 = (Switch) findViewById(R.id.switch16); //4-2
        Switch s17 = (Switch) findViewById(R.id.switch17); //3-2
        Switch s18 = (Switch) findViewById(R.id.switch18); //2-2
        Switch s19 = (Switch) findViewById(R.id.switch19); //1-2

        Switch s20 = (Switch) findViewById(R.id.switch20); //0
        Switch s21 = (Switch) findViewById(R.id.switch21); //0-2

        DateFormat dateFormat = new SimpleDateFormat("mm");
        Date date = new Date();
        ten = dateFormat.format(date).charAt(1);
        one = dateFormat.format(date).charAt(0);

        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {@Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {inputOne = '1';check();}});
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {@Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {inputOne = '2';check();}});
        s3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {@Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {inputOne = '3';check();}});
        s4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {@Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {inputOne = '4';check();}});
        s5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {@Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {inputOne = '5';check();}});
        s6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {@Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {inputOne = '6';check();}});
        s7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {@Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {inputOne = '7';check();}});
        s8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {@Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {inputOne = '8';check();}});
        s10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {@Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {inputOne = '9';check();}});

        s19.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {@Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {inputTwo = '1';check();}});
        s18.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {@Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {inputTwo = '2';check();}});
        s17.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {@Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {inputTwo = '3';check();}});
        s16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {@Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {inputTwo = '4';check();}});
        s15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {@Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {inputTwo = '5';check();}});
        s14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {@Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {inputTwo = '6';check();}});
        s13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {@Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {inputTwo = '7';check();}});
        s12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {@Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {inputTwo = '8';check();}});
        s11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {@Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {inputTwo = '9';check();}});

        s20.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {@Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {inputOne = '0';check();}});
        s21.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {@Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {inputTwo = '0';check();}});


    }

    private void check(){
        DateFormat dateFormat = new SimpleDateFormat("mm");
        Date date = new Date();
        ten = dateFormat.format(date).charAt(1);
        one = dateFormat.format(date).charAt(0);
        Log.i("pass", "" + inputOne + inputTwo);
        if(ten == inputOne && one == inputTwo){
            Intent mainAct = new Intent(this, MainActivity.class);
            mainAct.putExtra("pin", 101099);
            startActivity(mainAct);

        }
    }


}
