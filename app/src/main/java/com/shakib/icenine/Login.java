package com.shakib.icenine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Login extends AppCompatActivity {
    SharedPreferences mSharedPref;
    SharedPreferences.Editor mPrefEditor;
    EditText pass;
    Boolean mAllowLogin;

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

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String currentDate = formatter.format(date);


        cat.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(View view) {
                check();
            }
        });

        if(mSharedPref.contains("DATE")){
            int sessionsleft = mSharedPref.getInt("SESSIONSLEFT", 1);
            String savedDate = mSharedPref.getString("DATE", null);
            if(savedDate.equals(currentDate)){
                mAllowLogin = sessionsleft > 0;
            }else{
                mPrefEditor.putString("DATE", currentDate);
                mPrefEditor.putInt("SESSIONSLEFT", 4);
                mPrefEditor.commit();
                mAllowLogin = true;
            }

        } else {
            mPrefEditor.putString("DATE", currentDate);
            mPrefEditor.putInt("SESSIONSLEFT", 4);
            mPrefEditor.commit();
            mAllowLogin = true;
        }

       if(isPackageInstalled("com.facebook.katana") || isPackageInstalled("com.facebook.lite") || isPackageInstalled("com.facebook.orca") || isPackageInstalled("com.facebook.mlite")){
            mAllowLogin = false;
       }

    }

    private void check(){

        if(mSharedPref.contains("PASS")){
            if("reset".equals(pass.getText().toString())){
                mPrefEditor.putInt("SESSIONSLEFT", 4);
                mPrefEditor.commit();
                mAllowLogin = true;
                meow();
                Log.i("DEBUGICENINE", "RESET DONE");
            }
            if(!mAllowLogin){
                meow();
                Log.i("DEBUGICENINE", "PASS INVALID");
                return;
            }
            if(pass.getText().toString().equals(mSharedPref.getString("PASS", null))){
                login();
                Intent mainAct = new Intent(this, MainActivity.class);
                mainAct.putExtra("pin", 101099);
                startActivity(mainAct);
                finish();
            } else {
                meow();
                Log.i("DEBUGICENINE", "PASS INVALID: \"" + pass.getText().toString() + "\"");
            }

        } else {
            mPrefEditor.putString("PASS", pass.getText().toString());
            mPrefEditor.commit();
            login();
            Intent mainAct = new Intent(this, MainActivity.class);
            mainAct.putExtra("pin", 101099);
            startActivity(mainAct);
            finish();
        }
    }

    private void meow(){
        Toast.makeText(this, "Meeow!", Toast.LENGTH_SHORT).show();
    }

    private void login(){
        int sessionsleft = mSharedPref.getInt("SESSIONSLEFT", 1);
        mPrefEditor.putInt("SESSIONSLEFT", sessionsleft - 1);
        mPrefEditor.commit();
        Log.i("DEBUGICENINE", "LOGGED IN");
    }

    private boolean isPackageInstalled(String packagename) {
        try {
            getPackageManager().getPackageInfo(packagename, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


}
