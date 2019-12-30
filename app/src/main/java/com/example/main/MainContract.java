package com.example.main;

import android.content.Context;

public interface MainContract {
    interface MainView{
        void setButtonChangeText(String language);
        Context getContext();



    }
    interface MainPresenter{
        void setButttonChangeText();
        void dialogChangeLanguage();
        void dialogFizzBuzz();
        void dialogToastHelloWorld();
        void loadLanguage();
    }

}
