package com.shakib.icenine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    SharedPreferences mSharedPref;
    SharedPreferences.Editor mPrefEditor;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //SharedPref
        mSharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mPrefEditor = mSharedPref.edit();


        //views
        ImageView cat = (ImageView) findViewById(R.id.imageView);
        pass = (EditText) findViewById(R.id.pass);

        cat.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(View view) {
                check();
            }
        });


    }

    private void check(){
        if(mSharedPref.contains("PASS")){
            if(pass.getText().toString().equals(mSharedPref.getString("PASS", null))){
                Intent mainAct = new Intent(this, MainActivity.class);
                mainAct.putExtra("pin", 101099);
                startActivity(mainAct);
                finish();
            } else {
                Toast.makeText(this, "Meeow!", Toast.LENGTH_SHORT).show();
            }
        } else {
            mPrefEditor.putString("PASS", pass.getText().toString());
            mPrefEditor.commit();
            Intent mainAct = new Intent(this, MainActivity.class);
            mainAct.putExtra("pin", 101099);
            startActivity(mainAct);
        }
    }


}
