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
public class Programs extends Fragment {
    TextView tvhead, tvcontent1, tvcontent2;

    public Programs() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_programs, container, false);

        tvhead = (TextView) view.findViewById(R.id.headprograms);
        tvcontent1 = (TextView) view.findViewById(R.id.contentprograms1);
        tvcontent2 = (TextView) view.findViewById(R.id.contentprograms2);

        tvhead.setText(getResources().getString(R.string.programhead));
        tvcontent1.setText(getResources().getString(R.string.programcontent1));
        tvcontent2.setText(getResources().getString(R.string.programcontent2));

        return view;
    }

}
