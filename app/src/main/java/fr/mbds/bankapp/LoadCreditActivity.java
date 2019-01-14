/*
 * Created by Grace BK on 1/10/19 2:57 PM
 *
 * Copyright (c) 2019. School project.
 */

package fr.mbds.bankapp;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class LoadCreditActivity extends AppCompatActivity {

    private static final String TAG = "["+LoadCreditActivity.class.getSimpleName()+"]";

    private LinearLayout linearLayout;
    private LinearLayout linearLayout2;
    private TextView msg;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_credit);

        Toolbar toolbar = findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        linearLayout = findViewById(R.id.help_img);
        linearLayout2 = findViewById(R.id.help_img_validate);
        msg = findViewById(R.id.see_creadit);
        btn = findViewById(R.id.valide_add);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!msg.getText().toString().equals("")) {
                    // TODO : call firebase et save
                    startActivity(new Intent(LoadCreditActivity.this, MainActivity.class));
                    finish();
                }
            }
        });

        onNewIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            // TODO Change image
            Parcelable[] rawMessages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMessages != null) {
                NdefMessage[] messages = new NdefMessage[rawMessages.length];
                for (int i = 0; i < rawMessages.length; i++) {
                    messages[i] = (NdefMessage) rawMessages[i];
                }
                // Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                NdefRecord record = messages[0].getRecords()[0];
                byte[] payload = record.getPayload();
                String text = new String(payload);
                Log.w(TAG, text);

                String delimiter = "\\[\\|\\]\\s*";

                String[] params = text.split(delimiter);

                if (params.length > 1) {
                    //
                    linearLayout.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    msg.setText("Ajout de "+params[1]);
                } else {
                    linearLayout.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "TAG non valide", Toast.LENGTH_LONG).show();
                }

            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoadCreditActivity.this, MainActivity.class));
        finish();
    }
}
