package in.askdial.mrr.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.askdial.mrr.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Management extends Fragment {
    Toolbar toolbar;

    public Management() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.management_content, container, false);
        getActivity().setTitle("Management");

        /*toolbar = (Toolbar) view.findViewById(R.id.managementtoolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);*/

        return view;
    }

}
