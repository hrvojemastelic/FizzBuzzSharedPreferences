package com.example.main;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


import com.example.main.languagedialog.ChooseLanguageFragmentDialog;
import com.example.tvchrvojefirst.R;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class MainPresenter implements MainContract.MainPresenter {
    Dialog dialog;
    MainContract.MainView mainView;
    String out;
    String language;
    public MainPresenter(MainContract.MainView mainView) {
        this.mainView = mainView;
    }


    //CHANGE TEXT INSIDE OF CHANGE BUTTON
    @Override
    public void setButttonChangeText() {
        mainView.setButtonChangeText(language);
    }

    //STARTING DIALOG FRAGMENT USED IN CHANGE BUTTON ON CLICK METHOD
    @Override
    public void dialogChangeLanguage() {
        DialogFragment singleChoiceDialog = new ChooseLanguageFragmentDialog();
        singleChoiceDialog.setCancelable(true);
        singleChoiceDialog.show(((MainActivity)mainView.getContext()).getSupportFragmentManager(), "Choose Language Dailog");
    }
    //Fizz buzz is a group word game for children to teach them about division.[1]
    // Players take turns to count incrementally, replacing any number divisible by three with the word "fizz", and any number divisible by five with the word "buzz".
    @Override
    public void dialogFizzBuzz() {
        dialog=new Dialog(mainView.getContext());
        final TextView messsage;
        Button start;
        dialog.setContentView(R.layout.fizzbuzz_dialog_layout);
        dialog.setCanceledOnTouchOutside(false);
        messsage=dialog.findViewById(R.id.fizzbuzz_message);
        start=dialog.findViewById(R.id.fizzbuzz_button);
        dialog.show();
        for (int i = 1; i <= 100; i++)
        {
            if( i % 3 == 0 && i % 5 ==0 ){
                messsage.append("fizzBuzz"+"\n");
            }
            else if ( i % 3 == 0 ) {
                messsage.append("fizz"+"\n");
            }
            else if( i % 5 == 0 ) {
                messsage.append("buzz" +"\n");
            }
            else {
                messsage.append(i +"\n");
            }
        }
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    //SHOW TOAST MESSAGE IF LANGUAGE IS NOT SELECTED
    //IF LANGUGE IS SELECTED SHOW ALERT DIALOG WITH HELLOWORLD
    @Override
    public void dialogToastHelloWorld() {
        SharedPreferences preferences=mainView.getContext().getSharedPreferences("Settings", MODE_PRIVATE);
        String language =preferences.getString("Selected_Language","");
        if (language.isEmpty()){
            Toast.makeText(mainView.getContext(),"Jezik nije odabran",Toast.LENGTH_LONG).show();

        }
        else {
            AlertDialog.Builder builder=new AlertDialog.Builder(mainView.getContext());
            builder.setTitle(mainView.getContext().getResources().getString(R.string.hello_world_dialog));

            AlertDialog alertDialog= builder.create();
            alertDialog.show();

        }
    }
    //LOAD LANGUAGE FROM SHARED PREFERENCES
    //METHOD IS USED FIRST IN ON CREATE BEFORE CONTENTVIEW SO IT CAN DETERMIN WHICH LANGUAGE IS SELECTED
    @Override
    public void loadLanguage() {
        SharedPreferences preferences=mainView.getContext().getSharedPreferences("Settings", MODE_PRIVATE);
        language =preferences.getString("Selected_Language","");
        Locale locale=new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        mainView.getContext().getResources().updateConfiguration(configuration,mainView.getContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor =mainView.getContext().getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("Selected_Language",language);
        editor.apply();

    }

}
