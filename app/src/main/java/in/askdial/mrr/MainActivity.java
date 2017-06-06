package in.askdial.mrr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import in.askdial.mrr.dataposting.ConnectingTask;
import in.askdial.mrr.dataposting.DataRestAPI;
import in.askdial.mrr.values.Content;
import in.askdial.mrr.values.FunctionCalls;

public class MainActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    Button login_btn, register_btn, guest_btn;
    private static final int LOGIN_REQUEST = 1;
    String Email, Password;
    EditText editTxt_email, editTxt_password;
    FunctionCalls functionCalls;
    ConnectingTask task;
    Content detailsvalue;
    static ProgressDialog dialog = null;
    Thread loginthread;
    String granttype = "client_credentials";
    String Client_id = "451237", Client_secret = "789567";
    Thread accesstokenthread;
    String BASE_URL = DataRestAPI.BASE_URL2;

    String token_type = "", access_token = "", expires_in = "",username="", lastname, useremail,usermobile, User_date_added;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        setContentView(R.layout.activity_main);

        settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        editor = settings.edit();
        editor.apply();
        functionCalls = new FunctionCalls();
        detailsvalue = new Content();
        task = new ConnectingTask();
        accesstokenthread = null;
        Runnable runnable = new AccessTokenTimer();
        accesstokenthread = new Thread(runnable);
        accesstokenthread.start();

        editTxt_email = (EditText) findViewById(R.id.et_username);
        editTxt_password = (EditText) findViewById(R.id.et_password);
        login_btn = (Button) findViewById(R.id.login_btn);
        register_btn = (Button) findViewById(R.id.reg_btn);
        guest_btn = (Button) findViewById(R.id.guest_btn);

        editTxt_email.setText("");
        editTxt_password.setText("");
        Email = editTxt_email.getText().toString().trim();
        Password = editTxt_password.getText().toString();

        new GetAccessToken(Client_id, Client_secret, granttype).execute();
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (functionCalls.isInternetOn(MainActivity.this)) {
                    LoginDetails();
                } else {
                    Toast.makeText(MainActivity.this, "Please Turn On Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (functionCalls.isInternetOn(MainActivity.this)) {
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    // in.putExtra("consid", cons_Num);

                    startActivityForResult(intent, LOGIN_REQUEST);
                } else {
                    Toast.makeText(MainActivity.this, "Please Turn On Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });

        guest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                startActivity(intent);


            }
        });

    }

    public class GetAccessToken extends AsyncTask<String, String, String> {
        String result = "";
        String C_id, C_secret, cc;

        public GetAccessToken(String c_id, String Cl_scrt, String Ccr) {
            C_id = c_id;
            cc = Ccr;
            C_secret = Cl_scrt;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                result = getAccessToken_check(C_id, C_secret, cc);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            AccessToken(result);
        }
    }

    public String getAccessToken_check(String c_id, String c_scr, String cc) {
        String response = "";
        HashMap<String, String> datamap = new HashMap<>();
        datamap.put("client_id", c_id);
        datamap.put("client_secret", c_scr);
        datamap.put("grant_type", cc);
        try {
            response = UrlPostConnection("", datamap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public void AccessToken(String result) {
        Log.d("debug", result);
        try {
            JSONObject jo = new JSONObject(result);
            if (jo != null) {
                access_token = jo.getString("access_token");
                //detailsvalue.setAccess_token(access_token);
                expires_in = jo.getString("expires_in");
                // detailsvalue.setExpires_in(expires_in);
                token_type = jo.getString("token_type");
                // detailsvalue.setToken_type(token_type);

                editor.putString("Access_token", access_token);
                editor.putString("Expires_in", expires_in);
                editor.putString("Token_type", token_type);
                editor.commit();
                String Response = jo.getString("success");
                if (Response.equals(false)) {
                    Toast.makeText(MainActivity.this, "not Succes", Toast.LENGTH_SHORT).show();
                    // detailsvalue.setToken_failure(true);
                }

                /*if(Response.equals("")){
                    details.setMobile();
                }*/
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // if the result is capturing Image
        if (requestCode == LOGIN_REQUEST) {
            if (resultCode == RESULT_OK) {
                Bundle bnd = data.getExtras();
                Email = bnd.getString("email");
                Password = bnd.getString("password");

                editTxt_password.setText(Email);
                editTxt_password.setText(Password);

            } /*else if (resultCode == RESULT_CANCELED) {

                Toast.makeText(getApplicationContext(), "Login Failure", Toast.LENGTH_SHORT).show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(), "Sorry! Login Failure", Toast.LENGTH_SHORT).show();
            }*/
        }
    }

    class AccessTokenTimer implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    visiting();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                }
            }
        }
    }

    public void visiting() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {

                    String Message = "";

                  /*  Access_Token = detailsvalue.getAccess_token(access_token);
                    System.out.println("access Token" + Access_Token);

                    ConnectingTask.Login_user login = task.new Login_user(Email, Password, MainActivity.this, detailsvalue);
                    login.execute();

                    dialog = ProgressDialog.show(MainActivity.this, "", "Registering please wait..", true);
                    dialog.setCancelable(true);
                    loginthread = null;
                    Runnable runnable = new MainActivity.LoginTimer();
                    loginthread = new Thread(runnable);
                    loginthread.start();*/

                    //ConnectingTask.SendAccessToken login2 = task.new SendAccessToken(Access_Token);
                    // login2.execute();
                    /*if (detailsValue.isSmartIn()) {
                        appointmentsthread.interrupt();
                        detailsValue.setSmartIn(false);
                        dialog.dismiss();
                        Message = "Successfully Checked In";
                        functionCalls.smartCardStatus(Appointment_Details.this, Message);
                    }
                    if (detailsValue.isSmartOut()) {
                        appointmentsthread.interrupt();
                        detailsValue.setSmartOut(false);
                        dialog.dismiss();
                        Message = "Successfully Checked Out";
                        functionCalls.smartCardStatus(Appointment_Details.this, Message);
                    }
                    if (detailsValue.isSmartError()) {
                        appointmentsthread.interrupt();
                        detailsValue.setSmartError(false);
                        dialog.dismiss();
                        //Message = "Checking Error.. Please swipe again..";
                        Message = "Please check again...";
                        functionCalls.smartCardStatus(Appointment_Details.this, Message);
                    }*/
                } catch (Exception e) {
                }
            }
        });
    }

    private void LoginDetails() {
        Email = editTxt_email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (!editTxt_email.getText().toString().equals("") && Email.matches(emailPattern)) {
            Password = editTxt_password.getText().toString();
            if (!editTxt_password.getText().toString().equals("")) {

                //ConnectingTask.GetAccessToken login1 = task.new GetAccessToken(Client_id, Client_secret, granttype);
                //login1.execute();
                /*dialog = ProgressDialog.show(MainActivity.this, "", "Checking...", true);
                accesstokenthread = null;
                Runnable runnable = new AccessTokenTimer();
                accesstokenthread = new Thread(runnable);
                accesstokenthread.start();*/

                // Access_Token= detailsvalue.getAccess_token(access_token);


                // System.out.println("access Token" + Access_Token);

              /* ConnectingTask.SendAccessToken login2 = task.new SendAccessToken(Access_Token);
                login2.execute();*/

                ConnectingTask.Login_user login = task.new Login_user(Email, Password, MainActivity.this, detailsvalue, access_token);
                login.execute();
                dialog = ProgressDialog.show(MainActivity.this, "", "Logging please wait..", true);
                dialog.setCancelable(true);
                loginthread = null;
                Runnable runnable = new MainActivity.LoginTimer();
                loginthread = new Thread(runnable);
                loginthread.start();

            } else {
                editTxt_password.setError("Enter valid Password");
            }
        } else {
            editTxt_email.setError("Enter valid Email Address");
        }
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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (detailsvalue.isLoginSuccess()) {
                        detailsvalue.setLoginSuccess(false);
                        dialog.dismiss();
                        username=detailsvalue.getFirst_name();
                        lastname=detailsvalue.getLast_name();
                        useremail=detailsvalue.getEmail();
                        usermobile=detailsvalue.getMobile();
                        User_date_added=detailsvalue.getDate_added();
                        Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                        intent.putExtra("username", username);
                        intent.putExtra("userlast", lastname);
                        intent.putExtra("useremail", useremail);
                        intent.putExtra("usermobile", usermobile);
                        intent.putExtra("date_added", User_date_added);
                        startActivity(intent);
                        finish();
                        Toast.makeText(MainActivity.this, "WELCOME To MRR", Toast.LENGTH_SHORT).show();
                        loginthread.interrupt();
                    }
                    if (detailsvalue.isLoginNoMatch()) {
                        detailsvalue.setLoginNoMatch(false);
                        dialog.dismiss();
                        loginthread.interrupt();
                        Toast.makeText(MainActivity.this, "Login / Password incorrect", Toast.LENGTH_SHORT).show();
                    }
                    if (detailsvalue.isLoginFailure()) {
                        detailsvalue.setLoginFailure(false);
                        dialog.dismiss();
                        loginthread.interrupt();
                        Toast.makeText(MainActivity.this, "User already Logged", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String UrlPostConnection(String Post_Url, HashMap<String, String> datamap) throws IOException {
        String response = "";
        functionCalls.LogStatus("Connecting URL: " + BASE_URL + Post_Url);
        URL url = new URL(BASE_URL + Post_Url);
        functionCalls.LogStatus("URL Connection 1");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        functionCalls.LogStatus("URL Connection 2");
        conn.setReadTimeout(15000);
        functionCalls.LogStatus("URL Connection 3");
        conn.setConnectTimeout(15000);
        functionCalls.LogStatus("URL Connection 4");
        conn.setRequestMethod("POST");
        functionCalls.LogStatus("URL Connection 5");
        conn.setDoInput(true);
        functionCalls.LogStatus("URL Connection 6");
        conn.setDoOutput(true);
        functionCalls.LogStatus("URL Connection 7");

        OutputStream os = conn.getOutputStream();
        functionCalls.LogStatus("URL Connection 8");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        functionCalls.LogStatus("URL Connection 9");
        writer.write(getPostDataString(datamap));
        functionCalls.LogStatus("URL Connection 10");
        writer.flush();
        functionCalls.LogStatus("URL Connection 11");
        writer.close();
        functionCalls.LogStatus("URL Connection 12");
        os.close();
        functionCalls.LogStatus("URL Connection 13");
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

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            functionCalls.LogStatus(result.toString());
        }

        return result.toString();
    }
}
