package in.askdial.mrr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.avenues.lib.activity.InitialScreenActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import in.askdial.mrr.dataposting.ConnectingTask;
import in.askdial.mrr.dataposting.DataRestAPI;
import in.askdial.mrr.values.Content;
import in.askdial.mrr.values.FunctionCalls;

public class CartActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    ConnectingTask task;
    Content detailsvalue;
    FunctionCalls functionCalls;
    String Token_type = "", Access_token = "", Expires_in = "", Address = "hai";
    TextView roomPr, roomName, roomQty, roomSubT, roomDate;
    Button Confirm, Cancel;
    String rPrice, rName, rQty, rSubT, rDate;
    int Order_id;
    String BASE_URL = DataRestAPI.BASE_URL_ACCOUNT;

    Thread confirmThread, cancelThread;
    static ProgressDialog dialog = null;

    String Fname,Lname ,address,City,State,Country, address_id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        functionCalls = new FunctionCalls();
        task = new ConnectingTask();
        detailsvalue = new Content();

        Intent i = getIntent();
        Bundle b = i.getExtras();
        rName = b.getString("RoomName");
        rPrice = b.getString("RoomPrice");
        rQty = b.getString("RoomQuantity");
        rSubT = b.getString("RoomSubtotal");
        rDate = b.getString("RoomDate");

        settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        editor = settings.edit();
        Access_token = settings.getString("Access_token", "");
        Token_type = settings.getString("Token_type", "");
        Expires_in = settings.getString("Expires_in", "");

        roomName = (TextView) findViewById(R.id.tv_roomName);
        roomPr = (TextView) findViewById(R.id.tv_roomprice);
        roomQty = (TextView) findViewById(R.id.tv_noOfroom);
        roomSubT = (TextView) findViewById(R.id.tv_total_amount);
        roomDate = (TextView) findViewById(R.id.tv_bkDate);

        Confirm = (Button) findViewById(R.id.btn_confirm);
        Cancel = (Button) findViewById(R.id.btn_cancel);
        setValues();

        new AdressInfo(Address).execute();
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectingTask.ConfirmCart cart = task.new ConfirmCart(Access_token, detailsvalue);
                cart.execute();
                dialog = ProgressDialog.show(CartActivity.this, "", "Confirming Order...", true);
                dialog.setCancelable(true);
                confirmThread = null;
                Runnable runnable = new confirmTimer();
                confirmThread = new Thread(runnable);
                confirmThread.start();
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectingTask.CancelCart cart = task.new CancelCart(Access_token, detailsvalue);
                cart.execute();
                dialog = ProgressDialog.show(CartActivity.this, "", "Canceling Order...", true);
                dialog.setCancelable(true);
                cancelThread = null;
                Runnable runnable = new cancelTimer();
                cancelThread = new Thread(runnable);
                cancelThread.start();

            }
        });

    }

    private void setValues() {
        roomName.setText(rName);
        roomPr.setText(rPrice);
        roomQty.setText(rQty);
        roomSubT.setText(rSubT);
        roomDate.setText(rDate);
    }

    class confirmTimer implements Runnable {

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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (detailsvalue.isOrderIDSuccess()) {
                        detailsvalue.setOrderIDSuccess(false);
                        dialog.dismiss();
                        Order_id = detailsvalue.getOrder_ID();
                        String Order_ID = String.valueOf(Order_id);
                        Intent intent = new Intent(CartActivity.this, InitialScreenActivity.class);
                        intent.putExtra("amount", rSubT);
                        intent.putExtra("order_id", Order_ID);
                        // intent.putExtra("Order_id",Order_id);
                        intent.putExtra("access_token",Access_token);
                        intent.putExtra("fname",Fname);
                        intent.putExtra("lastname",Lname);
                        intent.putExtra("address",address);
                        intent.putExtra("city",City);
                        intent.putExtra("state",State);
                        intent.putExtra("country",Country);
                        intent.putExtra("address_ID",address_id);
                        startActivity(intent);
                        finish();
                        Toast.makeText(CartActivity.this, "Order Is Confirmed", Toast.LENGTH_SHORT).show();
                        confirmThread.interrupt();
                    }
                    if (detailsvalue.isOrdeIDFailure()) {
                        detailsvalue.setOrdeIDFailure(false);
                        dialog.dismiss();
                        confirmThread.interrupt();
                        Toast.makeText(CartActivity.this, "Order Not Confimed and try again ", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class cancelTimer implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    doWork1();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void doWork1() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (detailsvalue.isDeleteCartSuccess()) {
                        detailsvalue.setDeleteCartSuccess(false);
                        dialog.dismiss();
                        CartActivity.super.onBackPressed();
                       /* Intent intent = new Intent(CartActivity.this, Accomadations.class);
                       *//* intent.putExtra("RoomName",title);
                        intent.putExtra("RoomPrice",Amount);
                        intent.putExtra("RoomQuantity",quantity);
                        intent.putExtra("RoomSubtotal",SubTotal);
                        intent.putExtra("RoomDate",issueDate);*//*
                        startActivity(intent);*/
                        Toast.makeText(CartActivity.this, "Order Is Canceled", Toast.LENGTH_SHORT).show();
                        confirmThread.interrupt();
                    }
                    if (detailsvalue.isDeleteCartFailure()) {
                        detailsvalue.setDeleteCartFailure(false);
                        dialog.dismiss();
                        confirmThread.interrupt();
                        Toast.makeText(CartActivity.this, "Order Is still in Cart", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //GetAddress Cart

    public class AdressInfo extends AsyncTask<String, String, String> {
        String result = "";
        String address;

        public AdressInfo(String Address) {
            address = Address;

        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                result = requestAddress(address);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            getAddress(result);
        }
    }


    public String requestAddress(String Address) {
        String response = "";
        JSONObject datamap = new JSONObject();
        try {
            datamap.put("", "");
            response = UrlPostAccessTokenConnection(""/*, datamap*/);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }

    public void getAddress(String result) {
        Log.d("debug", result);
        try {
            JSONObject jo = new JSONObject(result);
            if (jo != null) {
                // boolean success = new Integer(0).equals(jo.get("success"));
                boolean success = (boolean) jo.get("success");
                if (success == true) {
                    JSONObject data = jo.getJSONObject("data");
                   // address = data.getString("address_1");
                    JSONArray subArray = data.getJSONArray("addresses");
                    for (int i = 0; i < subArray.length(); i++) {
                        JSONObject addr1 = subArray.getJSONObject(i);
                        Fname = addr1.getString("firstname");
                        Lname = addr1.getString("lastname");
                        address = addr1.getString("address_1");
                        City = addr1.getString("city");
                        State = addr1.getString("zone");
                        Country = addr1.getString("country");
                        address_id=addr1.getString("address_id");
                    }
                    Toast.makeText(CartActivity.this, "Address Found", Toast.LENGTH_SHORT).show();
                } else if (success == false) {
                    Toast.makeText(CartActivity.this, "Address Not Found", Toast.LENGTH_SHORT).show();
                    // detailsvalue.setToken_failure(true);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String UrlPostAccessTokenConnection(String Post_Url/*, JSONObject datamap*/) throws IOException {
        String response = "";
        functionCalls.LogStatus("Connecting URL: " + BASE_URL + Post_Url);
        URL url = new URL(BASE_URL + Post_Url);
        functionCalls.LogStatus("URL Connection 1");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + String.valueOf(Access_token));
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        responseCode = con.getResponseCode();
        functionCalls.LogStatus("URL Connection 14");
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            functionCalls.LogStatus("URL Connection 15");
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            functionCalls.LogStatus("URL Connection 16");
            while ((line = br.readLine()) != null) {
                response += line;
            }
            functionCalls.LogStatus("URL Connection 17");
        } else {
            response = "";
            functionCalls.LogStatus("URL Connection 18");
        }
        /*StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();*/

        /*HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + String.valueOf(Access_token));
        //set headers and method
        Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
        //writer.write(getPostDataString(Access_token));
       // writer.write(String.valueOf(datamap));
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
        }*/
        functionCalls.LogStatus("URL Connection Response: " + response);
        return response;
    }
}
