package in.askdial.mrr.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.askdial.mrr.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Carrer extends Fragment {
    TextView tvhead, tvcontent;

    public Carrer() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carrer, container, false);

        tvhead = (TextView) view.findViewById(R.id.headcarrer);
        tvcontent = (TextView) view.findViewById(R.id.contentcarrer);

        tvhead.setText(getResources().getString(R.string.carrerhead));
        tvcontent.setText(getResources().getString(R.string.carrercontent));
        
        return view;
    }

}
