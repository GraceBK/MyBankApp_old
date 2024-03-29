/*
 * Created by Grace BK on 1/12/19 5:28 AM
 *
 * Copyright (c) 2019. School project.
 */

package fr.mbds.bankapp.fragment;

import android.annotation.SuppressLint;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import fr.mbds.bankapp.LoadCreditActivity;
import fr.mbds.bankapp.R;
import fr.mbds.bankapp.models.Comptes;


public class HomeFragment extends Fragment {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("comptes");


    private LinearLayout btnAdd;
    private TextView tvUsername;
    private TextView tvMoney;


    public HomeFragment() {
        // Required empty public constructor
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //String userId = mDatabase.push().getKey();

        Toolbar toolbar = view.findViewById(R.id.toolbar_main);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);

        tvUsername = view.findViewById(R.id.profile_username);
        tvMoney = view.findViewById(R.id.profile_solde);
        tvUsername.setText(Objects.requireNonNull(mAuth.getCurrentUser()).getDisplayName());

        //mDatabase.child(mAuth.getCurrentUser().getUid());

        final float[] solde = new float[1];

        Query query = mDatabase.child(mAuth.getCurrentUser().getUid());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Comptes compte = dataSnapshot.getValue(Comptes.class);
                    Log.i("-------", ""+compte);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (Objects.equals(snapshot.getKey(), "beforeMoney")) {
                            Log.i("-------****", ""+snapshot.getValue());
                        }
                        assert compte != null;
                        solde[0] = compte.money;
                        tvMoney.setText(compte.money+"");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //tvMoney.setText(Float.toString(solde[0]));


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
    }
}
