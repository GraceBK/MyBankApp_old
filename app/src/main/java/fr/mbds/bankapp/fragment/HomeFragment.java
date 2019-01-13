/*
 * Created by Grace BK on 1/12/19 5:28 AM
 *
 * Copyright (c) 2019. School project.
 */

package fr.mbds.bankapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import fr.mbds.bankapp.LoadCreditActivity;
import fr.mbds.bankapp.R;


public class HomeFragment extends Fragment {

    private DatabaseReference mDatabase;


    private LinearLayout btnAdd;
    private TextView textView;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //String userId = mDatabase.push().getKey();

        Toolbar toolbar = view.findViewById(R.id.toolbar_main);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);

        textView = view.findViewById(R.id.profile_username);
        Log.w("azerty", textView.getText().toString());

        btnAdd = view.findViewById(R.id.left_btn);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LoadCreditActivity.class));
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        //mDatabase = FirebaseDatabase.getInstance().getReference("comptes");

    }
}
