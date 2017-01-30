package in.askdial.mrr.fragments;


        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.net.ConnectivityManager;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.webkit.WebView;
        import android.widget.TextView;
        import android.widget.Toast;

        import in.askdial.mrr.R;
        import in.askdial.mrr.values.FunctionCalls;

/**
 * A simple {@link Fragment} subclass.
 */
public class Broucher extends Fragment {
    TextView tvhead, tvcontent;

    View view;
    FunctionCalls functionCalls;

    public Broucher() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_broucher, container, false);
        /*
        tvhead = (TextView) view.findViewById(R.id.headbroucher);
        tvcontent = (TextView) view.findViewById(R.id.contentbroucher);
        tvhead.setText(getResources().getString(R.string.broucherhead));
        tvcontent.setText(getResources().getString(R.string.brouchercontent));*/

        checkInternetConnection();
        WebView webview = (WebView) view.findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        String pdf = "http://www.mrrnaturecure.com/MRR%20Brochure.pdf";
        webview.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf);

        return view;
    }   

    public boolean pdfRun(){

        if (functionCalls.haveNetworkConnection(getActivity())){


        }else{
            Toast.makeText(getActivity(), "Please turn on the Internet", Toast.LENGTH_SHORT).show();
        }
        return Boolean.parseBoolean(null);
    }

    private boolean checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        // test for connection
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {
            /*new loadListView().execute();*/
            return true;
        } else {
            Toast.makeText(getActivity(), "Internet Connection Required" , Toast.LENGTH_LONG).show();


            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Internet Connection Required")
                    .setCancelable(false)
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            getActivity();
                            //System.exit(0);
                        }

                    });
            AlertDialog alert = builder.create();
            alert.show();

        }

        return false;
    }


}
