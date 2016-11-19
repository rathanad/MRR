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
public class Accomodation_details extends Fragment {
    TextView tvdescription, tvtitle;
    int pos;

    public Accomodation_details() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.accomadation_details, container, false);

        Bundle bundle = new Bundle();
        bundle = getArguments();
        pos = bundle.getInt("pos");

        tvdescription = (TextView) view.findViewById(R.id.textView8);
        tvtitle = (TextView) view.findViewById(R.id.textView9);

        if (pos == 0) {
            tvtitle.setText("Male General Ward");
            tvdescription.setText(getResources().getString(R.string.Male_general_ward));
        } else if (pos == 1) {
            tvtitle.setText("Female General Ward");
            tvdescription.setText(getResources().getString(R.string.Female_general_ward));
        } else if (pos == 2) {
            tvtitle.setText("Twin Sharing");
            tvdescription.setText(getResources().getString(R.string.Twin_Sharing));
        } else if (pos == 3) {
            tvtitle.setText("Three Sharing");
            tvdescription.setText(getResources().getString(R.string.Three_Sharing));
        } else if (pos == 4) {
            tvtitle.setText("Semi Deluxe");
            tvdescription.setText(getResources().getString(R.string.Semi_Deluxe));
        } else if (pos == 5) {
            tvtitle.setText("Deluxe");
            tvdescription.setText(getResources().getString(R.string.Deluxe));
        } else if (pos == 6) {
            tvtitle.setText("Executive Suits");
            tvdescription.setText(getResources().getString(R.string.Executive_Suits));
        }

        return view;
    }

}
