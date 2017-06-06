package in.askdial.mrr.fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import in.askdial.mrr.CartActivity;
import in.askdial.mrr.R;
import in.askdial.mrr.dataposting.ConnectingTask;
import in.askdial.mrr.dataposting.DataRestAPI;
import in.askdial.mrr.values.Content;
import in.askdial.mrr.values.FunctionCalls;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Accomodation_details extends Fragment {

    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    TextView tvdescription, tvtitle;
    Button add_to_cart;

    int pos;
    String title, Amount, Prod_ID, issueDate, Nopersons, bookDate, noPer;
    private int mYear, mDay, mMonth;
    Button btn_issued_date;
    EditText edt_noPersons;

    TextView textview_issued_date, displayInteger;

    Thread cartthread;
    static ProgressDialog dialog = null;
    FunctionCalls functionCalls;
    ConnectingTask task;
    Content detailsvalue;
    int id, quantityCount, c2, c1 = 1, quantity,SubTotal, Quantity_no;

    String Token_type = "", Access_token = "", Expires_in = "", Select="",DateField="",AcTkn="cart";

    int count = 1;
    Button increase,decrease;

    String BASE_URL1 = DataRestAPI.BASE_URL11;

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
        title = bundle.getString("Title");
        Amount = bundle.getString("Amount");
        Prod_ID = bundle.getString("Product_ID");

        id = Integer.parseInt(Prod_ID);

        settings = getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        editor = settings.edit();

        Access_token = settings.getString("Access_token", "");
        Token_type = settings.getString("Token_type", "");
        Expires_in = settings.getString("Expires_in", "");

        tvdescription = (TextView) view.findViewById(R.id.textView8);
        tvtitle = (TextView) view.findViewById(R.id.textView9);
        add_to_cart = (Button) view.findViewById(R.id.add_to_cart);

        textview_issued_date = (TextView) view.findViewById(R.id.adhoc_issued_date_TxtVew);
        btn_issued_date = (Button) view.findViewById(R.id.adhoc_button_issued_date);
        edt_noPersons = (EditText) view.findViewById(R.id.edt_noPersons);
         displayInteger = (TextView)view.findViewById(R.id.integer_number);
        increase=(Button) view.findViewById(R.id.increase);
        decrease=(Button) view.findViewById(R.id.decrease);

        new CancelCart(AcTkn).execute();
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                displayInteger.setText(String.valueOf(count));

            }
        });

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                displayInteger.setText(String.valueOf(count));

            }
        });
        /*noPer= edt_noPersons.getText().toString();
        Nopersons= noPer + 1;
        quantityCount = Integer.parseInt(Nopersons);*/
        functionCalls = new FunctionCalls();
        task = new ConnectingTask();
        detailsvalue = new Content();

       /* btn_issued_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                // c.add(Calendar.DAY_OF_MONTH, 7);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String strMonth = String.valueOf(month), strDay = String.valueOf(day), strYear = String.valueOf(year);
                        if (month < 10) {
                            strMonth = "0" + strMonth;
                        }
                        if (day < 10) {
                            strDay = "0" + strDay;
                        }
                        // Handle the data here
                        textview_issued_date.setText(strYear + " - " + strMonth + " - " + strDay);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        //  bookDate= textview_issued_date.getText().toString();
*/

        btn_issued_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Dialog dialog = null;
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 10);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int date = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dp = new DatePickerDialog(getActivity(), dpd, year, month, date);
                dp.getDatePicker().setMinDate(cal.getTimeInMillis());
                dialog = dp;
                dialog.show();
            }
        });

        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*noPer = edt_noPersons.getText().toString();
                if (noPer.equals("")) {
                    noPer = "1";
                    quantityCount = Integer.parseInt(noPer);
                    c2 = quantityCount - c1;
                    quantity = c2 + 1;
                } else {
                    quantityCount = Integer.parseInt(noPer);
                    c2 = quantityCount - c1;
                    quantity = c2 + 1;
                }*/
                Quantity_no= Integer.parseInt(displayInteger.getText().toString());
                if(id == 65){
                    Select="255";
                    DateField="256";
                }else if(id == 66){
                    Select="257";
                    DateField="258";
                }else if(id == 52){
                    Select="243";
                    DateField="244";
                }else if(id == 53){
                    Select="245";
                    DateField="246";
                }else if(id == 54){
                    Select="247";
                    DateField="248";
                }else if(id == 55){
                    Select="249";
                    DateField="250";
                }else if(id == 56){
                    Select="251";
                    DateField="252";
                }
                issueDate = textview_issued_date.getText().toString();
                if (!textview_issued_date.getText().toString().equals("")) {
                    if (functionCalls.isInternetOn(getActivity())) {

                        ConnectingTask.AddToCart cart = task.new AddToCart(detailsvalue, id, Quantity_no, title, issueDate, Access_token,Select,DateField);
                        cart.execute();
                        dialog = ProgressDialog.show(getActivity(), "", "Adding to Cart...", true);
                        dialog.setCancelable(true);
                        cartthread = null;
                        Runnable runnable = new LoginTimer();
                        cartthread = new Thread(runnable);
                        cartthread.start();
                    } else {
                        Toast.makeText(getActivity(), "Please Tuen On the Internet", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    textview_issued_date.setError("Please Select date after 10 days");
                }
            }
        });

        if (pos == 0) {
            tvtitle.setText("Male General Ward");
            tvdescription.setText(getResources().getString(R.string.Male_general_ward));
            // add_to_cart= (Button) view.findViewById(R.id.add_to_cart);
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

    private void display(int number) {
        displayInteger.setText("" + number);
    }
    class LoginTimer implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    doWork();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void doWork() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (detailsvalue.isCartSuccess()) {
                        detailsvalue.setCartSuccess(false);
                        dialog.dismiss();
                        String quaNtity= String.valueOf(Quantity_no);
                        SubTotal= Quantity_no*1000;
                        String STotal=String.valueOf(SubTotal);
                        Intent intent = new Intent(getActivity(), CartActivity.class);
                        intent.putExtra("RoomName",title);
                        intent.putExtra("RoomPrice",Amount);
                        intent.putExtra("RoomQuantity",quaNtity);
                        intent.putExtra("RoomSubtotal",STotal);
                        intent.putExtra("RoomDate",issueDate);
                        getActivity().startActivity(intent);
                        Toast.makeText(getActivity(), "Added to Cart", Toast.LENGTH_SHORT).show();
                        cartthread.interrupt();
                    }
                    if (detailsvalue.isCartFailure()) {
                        detailsvalue.setCartFailure(false);
                        dialog.dismiss();
                        cartthread.interrupt();
                        Toast.makeText(getActivity(), "Not Added To Cart ", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Date Starttime = null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                try {
                    Starttime = new SimpleDateFormat("dd/MM/yyyy").parse((""+ dayOfMonth + "/" + ""+ (monthOfYear + 1) + "/" + ""+year));
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String dateselected = sdf.format(Starttime);
            Log.d("debug", dateselected);
            textview_issued_date.setText(dateselected);
        }
    };

    public class CancelCart extends AsyncTask<String, String, String> {
        String  result = "";
        String Ac_token;
        Content detailsValue;

        public CancelCart( String Access_tkn) {
            Ac_token=Access_tkn;

        }
        @Override
        protected String doInBackground(String... strings) {
            try {
                result = reqToEmptyCart(Ac_token);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
        @Override
        protected void onPostExecute(String result) {
            resultEmptyCartDetails(result, detailsValue);
        }
    }

    public String reqToEmptyCart(String AccessToken) {
        String response = "";
        JSONObject datamap = new JSONObject();
        try {
            datamap.put("empty", AccessToken);
            response = UrlDeleteAccessTokenConnection("cart/emptycart", datamap);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }

    public void resultEmptyCartDetails(String result, Content details) {
        Log.d("debug", result);
        try {
            JSONObject jo1 = new JSONObject(result);
            if (jo1 != null) {
                // boolean success = new Integer(0).equals(jo.get("success"));
                boolean success = (boolean) jo1.get("success");
                if (success == true) {
                    Toast.makeText(getActivity(), "Your Cart Is Empty Now", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Your Cart Is Not Empty", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String UrlDeleteAccessTokenConnection(String Post_Url, JSONObject datamap) throws IOException {
        String response = "";
        functionCalls.LogStatus("Connecting URL: " + BASE_URL1 + Post_Url);
        URL url = new URL(BASE_URL1 + Post_Url);
        functionCalls.LogStatus("URL Connection 1");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("DELETE");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + String.valueOf(Access_token));
        //set headers and method
        Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
        //writer.write(getPostDataString(Access_token));
        writer.write(String.valueOf(datamap));
        // json data
        writer.close();
        InputStream inputStream = conn.getInputStream();
        //input stream
        int responseCode = conn.getResponseCode();
        functionCalls.LogStatus("URL Connection 14");
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            functionCalls.LogStatus("URL Connection 15");
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            functionCalls.LogStatus("URL Connection 16");
            while ((line = br.readLine()) != null) {
                response += line;
            }
            functionCalls.LogStatus("URL Connection 17");
        } else {
            response = "";
            functionCalls.LogStatus("URL Connection 18");
        }
        functionCalls.LogStatus("URL Connection Response: " + response);
        return response;
    }

}
