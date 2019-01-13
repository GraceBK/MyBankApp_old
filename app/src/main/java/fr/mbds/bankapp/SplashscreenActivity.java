/*
 * Created by Grace BK on 1/10/19 5:54 AM
 *
 * Copyright (c) 2019. School project.
 */

package fr.mbds.bankapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashscreenActivity extends AppCompatActivity {

    private static final int AUTO_WAIT_DELAY_MILLIS = 1000;

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.CustomByGraceBK);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splashscreen);

        mContentView = findViewById(R.id.fullscreen_content);
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        String sharedText = null;


        if (Objects.equals(action, "fr.mbds.bankapp.TRANSACTION") && type != null) {
            if ("text/plain".equals(type)) {
                //handleSendText(intent); // Handle text being sent
                sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
                if (sharedText != null) {
                    Toast.makeText(getApplicationContext(), sharedText, Toast.LENGTH_LONG).show();
                }
            }
        } else {
            // Handle other intents, such as being started from the home screen
        }

        final String finalSharedText = sharedText;
        mHideHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user != null) {
                    // User is signed in
                    Intent iGoToMain = new Intent(SplashscreenActivity.this, MainActivity.class);
                    iGoToMain.putExtra("EXTRA_PAY_VALUE", finalSharedText);
                    startActivity(iGoToMain);
                } else {
                    // No user is signed in
                    Intent iGoToLogin = new Intent(SplashscreenActivity.this, LoginActivity.class);
                    iGoToLogin.putExtra("EXTRA_PAY_VALUE", finalSharedText);
                    startActivity(iGoToLogin);
                }
                finish();
            }
        }, AUTO_WAIT_DELAY_MILLIS);
    }
}
