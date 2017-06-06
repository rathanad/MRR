package in.askdial.mrr.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.askdial.mrr.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoardMembersFragment extends Fragment {

    View view;


    public BoardMembersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_board_members, container, false);
        getActivity().setTitle("Board of Members");

        return view;
    }

}
