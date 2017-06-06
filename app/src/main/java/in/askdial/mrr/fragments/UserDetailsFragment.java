package in.askdial.mrr.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import in.askdial.mrr.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailsFragment extends Fragment {

    String UName,ULast,Uemail,UMob,Udate;
    TextView uname,uLast,uEmail,uMob,uDate;
    Button home;
    public UserDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_blank, container, false);
        getActivity().setTitle("User Details");
        Bundle bundle = new Bundle();
        bundle = getArguments();

        UName = bundle.getString("userName");
        ULast = bundle.getString("userlast");
        Uemail = bundle.getString("useremail");
        UMob = bundle.getString("usermobile");
     // Udate = bundle.getString("date_added");

        uname= (TextView) view.findViewById(R.id.tv1_FirstName);
        uLast= (TextView) view.findViewById(R.id.tv2_LastName);
        uEmail= (TextView) view.findViewById(R.id.tv3_Email);
        uMob= (TextView) view.findViewById(R.id.tv4_Telephone);
       //uDate= (TextView) view.findViewById(R.id.tv5_Date);
        home= (Button) view.findViewById(R.id.btn_Home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        uname.setText(UName);
        uLast.setText(ULast);
        uEmail.setText(Uemail);
        uMob.setText(UMob);
       // uDate.setText(Udate);

        return  view;

    }

}
