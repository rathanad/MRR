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
public class Treatments extends Fragment {
    TextView tvhead, tvcontent;

    public Treatments() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_treatments, container, false);
        getActivity().setTitle("Treatment Offer");
        tvhead = (TextView) view.findViewById(R.id.headtreatment);
        tvcontent = (TextView) view.findViewById(R.id.contenttreatment);

        tvhead.setText(getResources().getString(R.string.treatmenthead));
        tvcontent.setText(getResources().getString(R.string.treatmentcontent));

        return view;
    }

}
