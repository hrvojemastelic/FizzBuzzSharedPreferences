package com.example.main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.main.languagedialog.ChooseLanguageFragmentDialog;
import com.example.tvchrvojefirst.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {
    MainPresenter mainPresenter=new MainPresenter(this);
    Button buttonChange,buttonToast,buttonFizzBuzz;

    String language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainPresenter.loadLanguage();
        setContentView(R.layout.activity_main);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        buttonChange=findViewById(R.id.button_change_language);
        buttonToast=findViewById(R.id.button_toast);
        buttonFizzBuzz=findViewById(R.id.button_fizzbuzz);
        mainPresenter.setButttonChangeText();

        buttonFizzBuzz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           mainPresenter.dialogFizzBuzz();


            }
        });

        buttonToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.dialogToastHelloWorld();

            }
        });


        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 mainPresenter.dialogChangeLanguage();
            }
        });
    }


    @Override
    public void setButtonChangeText(String language) {
        if (language.isEmpty()){
            buttonChange.setText("-");

        }else {
            buttonChange.setText(language);
        }

    }

    @Override
    public Context getContext() {
        return MainActivity.this;
    }


}
