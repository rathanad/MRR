package in.askdial.mrr.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

import in.askdial.mrr.ContentActivity;
import in.askdial.mrr.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Contactus extends Fragment {

    View view;

    ImageView img_map, img_youtube, contact_image,check;
    GoogleMap googleMap;

    LatLng myPosition;


    Map mMap;
    MarkerOptions mMarker;

    public Contactus() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contactus, container, false);
        getActivity().setTitle("Contact Us");

        img_map = (ImageView) view.findViewById(R.id.map);
        contact_image = (ImageView) view.findViewById(R.id.contact_image);
        img_youtube = (ImageView) view.findViewById(R.id.youtube);
        check= (ImageView) view.findViewById(R.id.check);


        contact_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), ContentActivity.class);
                startActivity(intent);
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(browserIntent);
            }
        });


        img_map.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    googleMap.setMyLocationEnabled(true);
                } else {

                    // ... although note that there are restrictions on what this HTML can do.
                    // See the JavaDocs for loadData() and loadDataWithBaseURL() for more info.
                    // Show rationale and request permission.
                    Uri uri = Uri.parse("https://www.google.co.in/maps/dir/''/mrrnaturecure/data=!4m5!4m4!1m0!1m2!1m1!1s0x3bae2d10a6ee0d03:0xdd56b26596ce35b1?sa=X&ved=0ahUKEwi3jLP82MbRAhWJsY8KHSV3AAUQ9RcIcjAP");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                    WebView webview = new WebView(getActivity());

                    // Simplest usage: note that an exception will NOT be thrown
                    // if there is an error loading this page (see below).
                    webview.loadUrl("https://www.google.co.in/maps/dir/''/mrrnaturecure/data=!4m5!4m4!1m0!1m2!1m1!1s0x3bae2d10a6ee0d03:0xdd56b26596ce35b1?sa=X&ved=0ahUKEwi3jLP82MbRAhWJsY8KHSV3AAUQ9RcIcjAP");

                    // OR, you can also load from an HTML string:
                    String summary = "<html><body>AskDial<b>192</b>Map</body></html>";
                    webview.loadData(summary, "text/html", null);

                }

                // googleMap.getUiSettings().setMyLocationButtonEnabled(true);

            }
        });

        img_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=ZgniuB6-dRU");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

                WebView webview = new WebView(getActivity());

                // Simplest usage: note that an exception will NOT be thrown
                // if there is an error loading this page (see below).
                webview.loadUrl("https://www.youtube.com/watch?v=ZgniuB6-dRU");

                // OR, you can also load from an HTML string:
                String summary = "<html><body>AskDial<b>192</b>Map</body></html>";
                webview.loadData(summary, "text/html", null);
            }
        });




        return view;
    }


}
