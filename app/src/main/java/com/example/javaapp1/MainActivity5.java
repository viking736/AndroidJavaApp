package com.example.javaapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
    }

    public void change(View v){
        Fragment fragment = null;
        switch (v.getId()){
            case R.id.button8:
                fragment = new Fragment1();
                break;
            case R.id.button7:
                fragment = new Fragment2();
                break;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.first_place, fragment);
        ft.commit();

    }
}