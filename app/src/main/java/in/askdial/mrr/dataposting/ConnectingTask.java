package in.askdial.mrr.dataposting;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.view.View;

import in.askdial.mrr.values.Content;

/**
 * Created by Admin on 05-May-17.
 */

public class ConnectingTask {
    SendingData sendingData = new SendingData();
    RecievingData receivingData = new RecievingData();

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show, Context context, final View mProgressBar) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = context.getResources().getInteger(android.R.integer.config_shortAnimTime);

            mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressBar.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    public class LoginData extends AsyncTask<String, String, String> {
        String result = "", First_Name, Last_name, email,Mobile,Address,City,Country,Password,Confirm_Password;
        int state;
        Content details;
        String Ac_Token;

        public LoginData(String FirstName, String Lastname, String Email, String Mobile, String Address,String City,String Country, String Password,String Confirm_Password, Content detail, String Access_token,int State) {
            this.First_Name = FirstName;
            this.Last_name = Lastname;
            this.email = Email;
            this.Mobile=Mobile;
            this.Address=Address;
            this.City=City;
            state=State;
            this.Country=Country;
            this.Password=Password;
            this.Confirm_Password=Confirm_Password;
            this.details = detail;
            Ac_Token=Access_token;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                result = sendingData.PostLogin(First_Name, Last_name,email,Mobile,Address,City,Country,Password,Confirm_Password,Ac_Token,state);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("debug", "Result: " + result);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            receivingData.RegisterDetails(result, details);
        }
    }

    public class Login_user extends AsyncTask<String, String, String> {
        String Email, Password, result = "", Acs_tkn;
        Context context;
        Content detailsValue;

        public Login_user( String email,String password,Context context, Content detailsValue,String Access_token) {
            Email = email;
            Password = password;
            this.context = context;
            this.detailsValue = detailsValue;
            Acs_tkn=Access_token;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                String aAcs_tkn= /*Token_type +*/ Acs_tkn;
                result = sendingData.Login_check(Email, Password,aAcs_tkn);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            receivingData.LoginDetails(result, detailsValue);
        }
    }

    public class Logout extends AsyncTask<String, String, String> {
        String  result = "";
        String Ac_token;
        Content detailsValue;

        public Logout( String Access_tkn, Content detailsValue) {
            Ac_token=Access_tkn;
            this.detailsValue = detailsValue;

        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                result = sendingData.getLogout(Ac_token);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            receivingData.LogoutDetails(result, detailsValue);
        }
    }

    //add to cart

    public class AddToCart extends AsyncTask<String, String, String> {
        String  result = "";
        String Access_token, Name,time,Select,dateF;
        int prodd_id, qquantity;
        Content detailsValue;

        public AddToCart( Content DetailsValue,int prod_id, int quantity, String name, String date, String access_token, String select,String DateF) {
            this.detailsValue = DetailsValue;
            Access_token=access_token;
            prodd_id=prod_id;
            qquantity=quantity;
            Name=name;
            time=date;
            Select=select;
            dateF=DateF;
        }
        @Override
        protected String doInBackground(String... strings) {
            try {
                result = sendingData.addToCart(prodd_id,qquantity,Name,time,Access_token,Select,dateF);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            receivingData.cartDetails(result, detailsValue);
        }
    }

    //Confirm Cart

    public class ConfirmCart extends AsyncTask<String, String, String> {
        String  result = "";
        String Ac_token;
        Content detailsValue;

        public ConfirmCart( String Access_tkn, Content detailsValue) {
            Ac_token=Access_tkn;
            this.detailsValue = detailsValue;

        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                result = sendingData.getOrdeConfirm(Ac_token);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            receivingData.RecieveConfirmCart(result, detailsValue);
        }
    }

    //Empty Cart

    public class CancelCart extends AsyncTask<String, String, String> {
        String  result = "";
        String Ac_token;
        Content detailsValue;

        public CancelCart( String Access_tkn, Content detailsValue) {
            Ac_token=Access_tkn;
            this.detailsValue = detailsValue;

        }
        @Override
        protected String doInBackground(String... strings) {
            try {
                result = sendingData.reqToEmptyCart(Ac_token);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            receivingData.resultEmptyCartDetails(result, detailsValue);
        }
    }

   /* public class SendAccessToken extends AsyncTask<String, String, String> {
        String  result = "";
        String Access_token;
        Content detailsValue;

        public SendAccessToken( String access_token) {
            Access_token=access_token;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
               // result = sendingData.putAccessToken_check(Access_token);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            receivingData.AccessToken(result, detailsValue);
        }
    }
*/
}
