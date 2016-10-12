package com.github.diwakar1988.rxandroid;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.diwakar1988.rxandroid.form.FormActivity;
import com.github.diwakar1988.rxandroid.timer.TimerActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnForm).setOnClickListener(this);
        findViewById(R.id.btnTimer).setOnClickListener(this);
        findViewById(R.id.btnWebService).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnForm) {
            startActivity(FormActivity.createIntent(MainActivity.this));
        } else if (v.getId() == R.id.btnTimer) {

            runTimer();

        } else if (v.getId() == R.id.btnWebService) {

        }
    }

    private void runTimer() {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_input);
        dialog.findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) dialog.findViewById(R.id.et_value);

                String seconds = et.getText().toString();
                if (TextUtils.isEmpty(seconds)){
                    Toast.makeText(MainActivity.this, "Invalid Value!", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(TimerActivity.createIntent(MainActivity.this,Integer.parseInt(seconds)));
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
