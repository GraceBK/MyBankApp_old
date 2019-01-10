/*
 * Created by Grace BK on 1/10/19 2:57 PM
 *
 * Copyright (c) 2019. School project.
 */

package fr.mbds.bankapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoadCreditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_credit);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoadCreditActivity.this, MainActivity.class));
        finish();
    }
}
