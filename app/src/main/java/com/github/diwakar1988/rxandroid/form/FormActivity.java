package com.github.diwakar1988.rxandroid.form;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.github.diwakar1988.rxandroid.R;
import com.jakewharton.rxbinding.widget.RxTextView;

import rx.Observable;
import rx.functions.Func3;

public class FormActivity extends AppCompatActivity {

    private static final String TAG = FormActivity.class.getSimpleName();

    public static Intent createIntent(Context context){
        Intent  intent = new Intent(context,FormActivity.class);
        return intent;
    }

    private EditText etEmail;
    private EditText etPassword;
    private EditText etMobile;
    private Button btnSubmit;

    private Observable emailObservable;
    private Observable passwordObservable;
    private Observable phoneObservable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        etMobile = (EditText) findViewById(R.id.et_phone);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        emailObservable = RxTextView.textChangeEvents(etEmail).skip(1);
        passwordObservable = RxTextView.textChangeEvents(etPassword).skip(1);
        phoneObservable = RxTextView.textChangeEvents(etMobile).skip(1);


        createCombineLatest();
    }

    private void createCombineLatest() {

//        Observable<Boolean> observable = Observable.combineLatest(emailObservable, passwordObservable, phoneObservable, new Func3() {
//            @Override
//            public Boolean call(String email, String password, String phone) {
//                return true;
//            }
//        };
    }
}
