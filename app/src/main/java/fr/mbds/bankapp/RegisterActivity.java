/*
 * Created by Grace BK on 1/10/19 5:49 AM
 *
 * Copyright (c) 2019. School project.
 */

package fr.mbds.bankapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import fr.mbds.bankapp.fragment.RegisterFragment;

public class RegisterActivity extends FragmentActivity implements RegisterFragment.OnFragmentInteractionListener {


    FrameLayout frameLayout;

    RegisterFragment phoneFragment = RegisterFragment.newInstance(1, "phone", "");
    RegisterFragment passwordFragment = RegisterFragment.newInstance(2, "password", "");
    RegisterFragment nameFragment = RegisterFragment.newInstance(3, "name", "");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        frameLayout = findViewById(R.id.register_fl);

        if (savedInstanceState == null) {
            showFragment(phoneFragment);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void clickPhoneToPassword(Boolean b) {
        if (b) {
            showFragment(passwordFragment);
        }
    }

    @Override
    public void clickPasswordToUser(Boolean b) {
        if (b) {

        }
    }


    public void showFragment(RegisterFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frameLayout.getId(), fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //showFragment(phoneFragment);
        startActivity(new Intent(RegisterActivity.this, LoginActivity2.class));
        finish();
    }
}
