package in.askdial.mrr;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import in.askdial.mrr.dataposting.ConnectingTask;
import in.askdial.mrr.values.Content;
import in.askdial.mrr.values.FunctionCalls;

public class RegisterActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    ConnectingTask task;
    Content detailsvalue;
    FunctionCalls functionCalls;
    String FirstName = "", LastName = "", Email = "", Mobile = "", Address = "", City = "", State = "", Country = "", Password = "", Confirm_Password = "", Date_Added;
    int Zone = 1489;
    EditText editText_first_name, editText_last_name, editText_email, editText_mobile, editText_address, editText_city, editText_state,
            editText_country, editText_password, editText_confirm_pass;
    Button btn_submit;
    static ProgressDialog dialog = null;
    Thread mythread;
    TextView terms_cond;
    CheckBox checkBox;
    String Token_type = "", Access_token = "", Expires_in = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        detailsvalue = new Content();
        task = new ConnectingTask();
        functionCalls = new FunctionCalls();
        settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        editor = settings.edit();

        Access_token = settings.getString("Access_token", "");
        Token_type = settings.getString("Token_type", "");
        Expires_in = settings.getString("Expires_in", "");

        editText_first_name = (EditText) findViewById(R.id.edt_first_name);
        editText_last_name = (EditText) findViewById(R.id.edt_last_name);
        editText_email = (EditText) findViewById(R.id.edt_email);
        editText_mobile = (EditText) findViewById(R.id.edt_mobile);
        editText_address = (EditText) findViewById(R.id.edt_address);
        editText_city = (EditText) findViewById(R.id.edt_city);
        editText_country = (EditText) findViewById(R.id.edt_country);
        editText_state = (EditText) findViewById(R.id.edt_state);
        editText_password = (EditText) findViewById(R.id.edt_password);
        editText_confirm_pass = (EditText) findViewById(R.id.edt_confirm_password);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        terms_cond = (TextView) findViewById(R.id.terms_cond);
        btn_submit = (Button) findViewById(R.id.btn_submit);

        editText_first_name.requestFocus();

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (functionCalls.isInternetOn(RegisterActivity.this)) {
                    Registerdetails();
                } else {
                    Toast.makeText(RegisterActivity.this, "Please Turn On Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
        terms_cond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder existbuilder1 = new AlertDialog.Builder(RegisterActivity.this);
                existbuilder1.setTitle("TERMS AND CONDITIONS");
                existbuilder1.setCancelable(false);
                //existbuilder1.setMessage("This mobile number can not be processed...");
                existbuilder1.setMessage(Html.fromHtml("<font color='#000000'><b>Privacy Policy:\n" +
                        "\n" +
                        "We value the trust you place in us. That’s why we insist upon the highest standards for secure transactions and customer information privacy. Please read the following statement to learn more about our information gathering and dissemination practices.\n" +
                        "\n" +
                        "Note: Our privacy policy is subject to change anytime without notice. You are requested to review this policy periodically in order to make sure you are aware of any changes.\n" +
                        "\n" +
                        "By using this website you agree to the terms and conditions of this policy. IF YOU DO NOT AGREE TO THE TERMS AND CONDITIONS OF THIS POLICY, PLEASE DO NOT PRECEDE FURTHER TO USE THIS WEBSITE.\n" +
                        "\n" +
                        "Personal Information Collected:\n" +
                        "\n" +
                        "MRR recognizes the need for consumers to control the use and management of personal information. By personal information we mean name, job title, contact information including email address, demographic information such as post code, preferences and interests and also other information relevant to customer survey and/ or offers. We require this information to understand your needs and provide you with a better service, and in particular for the following reasons;\n" +
                        "\n" +
                        "Internal record keeping.\n" +
                        "\n" +
                        "We may use this information to improve our products and services.\n" +
                        "\n" +
                        "We may periodically send promotional emails about new products, special offers or other information which we think you may find interesting using the email address which you have provided.\n" +
                        "\n" +
                        "We may also contact you for market research purposes.\n" +
                        "\n" +
                        "Security:\n" +
                        "\n" +
                        "MRR takes reasonable steps to ensure that your information is protected. Our website has stringent security measures in place to protect the loss, misuse and alteration of the information under our control.\n" +
                        "\n" +
                        "Cookies:\n" +
                        "\n" +
                        "A ‘cookie’ is a small piece of information stored by a web server on a web browser so it can be later read back from the browser. Cookies are useful for enabling the browser to remember information specific to a given user. We use traffic log cookies to identify which pages are being used. This helps us analyze data about webpage traffic and improve our website in order to tailor it to customer needs. We only use this information for statistical analysis purposes and then the data is removed from the system.\n" +
                        "\n" +
                        "You can choose to accept or decline cookies. Most web browsers automatically accept cookies, but you can usually modify your browser setting to decline cookies if you prefer. This may prevent you from taking full advantage of the website.\n" +
                        "\n" +
                        "Links to Third Party Website:\n" +
                        "\n" +
                        "Our website may contain links to other websites. We do not provide any personally indentifiable customer information to these advertisers or third party web sites. We do not have any access to or control over cookies or other features that they may use, and the information practices of these advertisers and third party websites are not covered by this Privacy Notice. Please contact them directly for more information about their privacy practice.\n" +
                        "\n" +
                        "Controlling your Personal Information:\n" +
                        "\n" +
                        "You may choose to restrict the collection or use of your personal information in the following ways:\n" +
                        "\n" +
                        "whenever you are asked to fill in a form on the website, look for the box that you can click to indicate that you do not want the information to be used by anybody for direct marketing purposes\n" +
                        "\n" +
                        "if you have previously agreed to us using your personal information for direct marketing purposes, you may change your mind at any time by writing to or emailing us at sales@mrr.com\n" +
                        "\n" +
                        "We will not sell, distribute or lease your personal information to third parties unless we have your permission or are required by law to do so. We may use your personal information to send you promotional information about third parties which we think you may find interesting if you tell us that you wish this to happen.\n" +
                        "\n" +
                        "You may request details of personal information which we hold about you under the Data Protection Act 1998. A small fee will be payable. If you would like a copy of the information held on you please write to below address.\n" +
                        "\n" +
                        "If you believe that any information we are holding on you is incorrect or incomplete, please write to or email us as soon as possible, at the above address. We will promptly correct any information found to be incorrect.\n" +
                        "\n" +
                        "Compliance: This privacy policy comply ecommerce industry best practice guidelines. If you have any questions or concerns about this policy please feel free to contact us.\n" +
                        "\n" +
                        "Contacting Us:\n" +
                        "\n" +
                        "If there are any questions regarding this privacy policy you may contact us using the information below:\n" +
                        "\n" +
                        "MRR NATURE CURE HOSPITAL\n" +
                        "\n" +
                        "Next to Bharath gas godown, N.H. 48, Solur Village, Magadi Taluk, Ramanagara Dist., Karnataka, India.\n" +
                        "\n" +
                        "Phone : 080 2268 2900</b></font>"));
                existbuilder1.setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkBox.setChecked(true);
                    }
                });
                existbuilder1.setNeutralButton("Decline", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkBox.setSelected(false);
                    }
                });
                AlertDialog existalert1 = existbuilder1.create();
                existalert1.show();
            }
        });

    }

    private void Registerdetails() {
        FirstName = editText_first_name.getText().toString();
        if (!editText_first_name.getText().toString().equals("") && FirstName.length() > 3 ) {
            LastName = editText_last_name.getText().toString();
            if (!editText_last_name.getText().toString().equals("")) {
                Email = editText_email.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (!editText_email.getText().toString().equals("") && Email.matches(emailPattern)) {
                    Mobile = editText_mobile.getText().toString();
                    if (!editText_mobile.getText().toString().equals("") && Mobile.length() >= 10) {
                        Address = editText_address.getText().toString();
                        if (!editText_address.getText().toString().equals("") && Address.length() > 5) {
                            City = editText_city.getText().toString();
                            if (!editText_city.getText().toString().equals("")) {
                                State = editText_state.getText().toString();
                                if (!editText_state.getText().toString().equals("")) {
                                    Country = editText_country.getText().toString();
                                    if (!editText_country.getText().toString().equals("")) {
                                        Password = editText_password.getText().toString();
                                        if (!editText_password.getText().toString().equals("") && Password.length() >= 4 & Password.length() < 20) {
                                            Confirm_Password = editText_confirm_pass.getText().toString();
                                            if (!editText_confirm_pass.getText().toString().equals("")) {
                                                if (Password.equals(Confirm_Password)) {
                                                    if (checkBox.isChecked()) {
                                                        ConnectingTask.LoginData login = task.new LoginData(FirstName, LastName, Email, Mobile, Address, City, Country, Password, Confirm_Password, detailsvalue, Access_token, Zone);
                                                        login.execute();
                                                        dialog = ProgressDialog.show(RegisterActivity.this, "", "Registering please wait..", true);
                                                        dialog.setCancelable(true);
                                                        mythread = null;
                                                        Runnable runnable = new LoginTimer();
                                                        mythread = new Thread(runnable);
                                                        mythread.start();
                                                    } else {
                                                        checkBox.setError("Please Check Terms and Condition");
                                                    }
                                                } else {
                                                    editText_confirm_pass.setError("Confirm Password is not Matching");
                                                }
                                            } else {
                                                editText_confirm_pass.setError("Enter Confirm Password");
                                            }
                                        } else {
                                            editText_password.setError("Enter Password should be between 4 to 20 ");
                                        }
                                    } else {
                                        editText_country.setError("Enter Country Name");
                                    }
                                } else {
                                    editText_state.setError("Enter State Name");
                                }
                            } else {
                                editText_city.setError("Enter City Name");
                            }
                        } else {
                            editText_address.setError("Enter Address should be more than 5 characters");
                        }
                    } else {
                        editText_mobile.setError("Enter valid Mobile number");
                    }
                } else {
                    editText_email.setError("Enter the Correct Email");
                }
            } else {
                editText_last_name.setError("Enter Last Name");
            }
        } else {
            editText_first_name.setError("Enter First Name should be more than 3 Characters");
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
                    if (detailsvalue.isRegisterSuccess()) {
                        detailsvalue.setRegisterSuccess(false);
                        dialog.dismiss();
                       //Date_Added=detailsvalue.getDate_added();
                        Intent intent = new Intent(RegisterActivity.this, ContentActivity.class);
                        intent.putExtra("username", FirstName);
                        intent.putExtra("userlast", LastName);
                        intent.putExtra("useremail", Email);
                        intent.putExtra("usermobile", Mobile);
                       // intent.putExtra("date_added", Date_Added);
                        startActivity(intent);
                        Toast.makeText(RegisterActivity.this, "Registration Success & Welcome To MRR ", Toast.LENGTH_SHORT).show();
                        finish();
/*
                        Intent intent = new Intent();
                        intent.putExtra("email", Email);
                        intent.putExtra("password", Password);
                        setResult(RESULT_OK, intent);
                        Toast.makeText(RegisterActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                        finish();*/
                        mythread.interrupt();
                    }
                    if (detailsvalue.isRegisterError()) {
                        detailsvalue.setRegisterError(false);
                        dialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Email is already registerd", Toast.LENGTH_SHORT).show();
                        mythread.interrupt();
                    }

                    if (detailsvalue.isRegisterFailure()) {
                        detailsvalue.setRegisterFailure(false);
                        dialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Registration Failure", Toast.LENGTH_SHORT).show();
                        mythread.interrupt();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
