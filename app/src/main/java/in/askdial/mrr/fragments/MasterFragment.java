package in.askdial.mrr.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import in.askdial.mrr.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends Fragment {
    Toolbar toolbar, footer;
    ImageView settingsmenu, sharemenu, detailsimage;
    TextView tv_detailshead, tv_detailscontent;
    int image, position;
    String detailshead="";
    String[] detailscontent;

    public MasterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_master, container, false);


        /*setHasOptionsMenu(true);*/

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        //footer = (Toolbar) view.findViewById(R.id.footer);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        Bundle bundle = new Bundle();
        bundle = getArguments();
        image = bundle.getInt("image");
        if (bundle.getString("head") != null) {
            detailshead = bundle.getString("head");
            position = bundle.getInt("pos");
        }

        detailscontent = getResources().getStringArray(R.array.departmentscontentlist);
        detailsimage = (ImageView) view.findViewById(R.id.details_image);
        //settingsmenu = (ImageView) view.findViewById(R.id.settings_menu);
        //sharemenu = (ImageView) view.findViewById(R.id.share_menu);
        tv_detailshead = (TextView) view.findViewById(R.id.details_image_Text);
        tv_detailscontent = (TextView) view.findViewById(R.id.content_txt);

        detailsimage.setImageResource(image);
        if (!detailshead.equals("")) {
            tv_detailshead.setText(detailshead);
            tv_detailscontent.setText(detailscontent[position].toString());
        }

        //showPopup();

        return view;
    }

    /*private void showPopup() {
        settingsmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final PopupMenu popupMenu = new PopupMenu(getActivity(), view);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_1:
                                Snackbar.make(view, "First", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;

                            case R.id.menu_2:
                                Snackbar.make(view, "Second", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;

                            case R.id.menu_3:
                                Snackbar.make(view, "Third", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;

                            case R.id.menu_4:
                                Snackbar.make(view, "Fourth", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                        }
                        return false;
                    }
                });
                getActivity().getMenuInflater().inflate(R.menu.test_menu, popupMenu.getMenu());
                popupMenu.show();
            }
        });*/
        /*sharemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), view);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });
                getActivity().getMenuInflater().inflate(R.menu.test_menu, popupMenu.getMenu());
                popupMenu.show();
            }
        });
        }*/

}
