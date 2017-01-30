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
public class History extends Fragment {
    TextView tvhistoryhead1, tvhistoryhead2, tvhistorycontent1, tvhistorycontent2;

    public History() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        getActivity().setTitle("History");

        tvhistoryhead1 = (TextView) view.findViewById(R.id.headhistory1);
        tvhistoryhead2 = (TextView) view.findViewById(R.id.headhistory2);
        tvhistorycontent1 = (TextView) view.findViewById(R.id.contenthistory1);
        tvhistorycontent2 = (TextView) view.findViewById(R.id.contenthistory2);

        tvhistoryhead1.setText(getResources().getString(R.string.historyhead1));
        tvhistoryhead2.setText(getResources().getString(R.string.historyhead2));
        tvhistorycontent1.setText(getResources().getString(R.string.historycontent1));
        tvhistorycontent2.setText(getResources().getString(R.string.historycontent2));

        return view;
    }

}
