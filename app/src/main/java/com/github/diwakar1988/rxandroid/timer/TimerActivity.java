package com.github.diwakar1988.rxandroid.timer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.diwakar1988.rxandroid.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class TimerActivity extends AppCompatActivity {

    private static final String KEY_SECONDS = "seconds";
    private static final String TAG = TimerActivity.class.getSimpleName();

    public static Intent createIntent(Context context, int seconds){
        Intent  intent = new Intent(context,TimerActivity.class);
        intent.putExtra(KEY_SECONDS,seconds);
        return intent;
    }
    private int seconds;

    private TextView tvTimer ;
    private Subscription subscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        seconds = getIntent().getIntExtra(KEY_SECONDS,-1);
        tvTimer = (TextView) findViewById(R.id.txt_timer);

        updateTimer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopTimer();
    }

    private void stopTimer() {
        subscription.unsubscribe();
    }

    private void startTimer() {
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS,AndroidSchedulers.mainThread());
        subscription = observable.subscribe(new Observer<Long>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,"****onError()= "+e);
            }

            @Override
            public void onNext(Long aLong) {
                seconds-=1;
                if (seconds<0){
                    finish();
                    return;
                }

                updateTimer();
            }
        });
    }

    private void updateTimer() {
        tvTimer.setText(String.valueOf(seconds));
    }
}
