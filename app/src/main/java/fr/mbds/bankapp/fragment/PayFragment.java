/*
 * Created by Grace BK on 1/12/19 5:28 AM
 *
 * Copyright (c) 2019. School project.
 */

package fr.mbds.bankapp.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.mbds.bankapp.R;

public class PayFragment extends Fragment {

    private SharedPreferences sharedPref;

    public PayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pay, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar_pay);
        // toolbar.setLogo(R.drawable.ic_account);

        sharedPref = getActivity().getSharedPreferences(getString(R.string.pref_user), Context.MODE_PRIVATE);
        String money = sharedPref.getString(getString(R.string.pref_pay), "0");
        toolbar.setTitle(money + " £");
        toolbar.setSubtitle("Montant à payer");


        return view;
    }
}
