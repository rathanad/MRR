package in.askdial.mrr.dataposting;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import in.askdial.mrr.values.Content;

/**
 * Created by Admin on 05-May-17.
 */

public class RecievingData {

    public void RegisterDetails(String result, Content details) {
        Log.d("debug", result);
        try {
            JSONObject jo = new JSONObject(result);
            if (jo != null) {
                boolean success = (boolean) jo.get("success");
                if (success == true) {
                    JSONObject data = jo.getJSONObject("data");
                    String Fname = data.getString("firstname");
                    details.setFirst_name(Fname);
                    String Lname = data.getString("lastname");
                    details.setLast_name(Lname);
                    String Email = data.getString("email");
                    details.setEmail(Email);
                    String Phone = data.getString("telephone");
                    details.setMobile(Phone);
                    String Adress = data.getString("address_1");
                    details.setAddress(Adress);
                    String city = data.getString("city");
                    details.setCity(city);
                    int Customer_id = data.getInt("customer_id");
                    details.setCustomer_id(Customer_id);
                   /* String date_added = data.getString("date_added");
                    details.setDate_added(date_added);*/
                    details.setRegisterSuccess(true);
                } else if (success == false) {
                    JSONObject data = jo.getJSONObject("error");
                    String Registered = data.getString("warning");
                    if (Registered.equals("Warning: E-Mail Address is already registered!")) {
                        details.setRegisterError(true);
                    } else {
                        details.setRegisterFailure(true);
                    }

                }
            /*} else if (success == false) {
                String Ulogged = jo.getString("error");
                if (Ulogged.equals("User already is logged")) {
                    details.setRegisterError(true);
                } else {
                    details.setRegisterFailure(true);
                }
            }*/


           /* } else if (success == false) {
                JSONObject data1 = jo.getJSONObject("error");
                String noMatch = data1.getString("warning");
                details.setRegisterationError(noMatch);
                details.setRegisterFailure(true);
            } else {
                details.setRegisterError(true);
            }*/
                 /*String Response = jo.getString("success");
                if (Response.equals(true)) {
                    details.setRegisterSuccess(true);
                } else if (Response.equals(false)) {
                    details.setRegisterFailure(true);
                }*/
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void LoginDetails(String result, Content details) {
        Log.d("debug", result);
        try {
            JSONObject jo = new JSONObject(result);
            if (jo != null) {
                // boolean success = new Integer(0).equals(jo.get("success"));
                boolean success = (boolean) jo.get("success");
                if (success == true) {
                    JSONObject data = jo.getJSONObject("data");
                    String Fname = data.getString("firstname");
                    details.setFirst_name(Fname);
                    String Lname = data.getString("lastname");
                    details.setLast_name(Lname);
                    String Email = data.getString("email");
                    details.setEmail(Email);
                    String Phone = data.getString("telephone");
                    details.setMobile(Phone);
                    String date_added = data.getString("date_added");
                    details.setDate_added(date_added);
                    details.setLoginSuccess(true);
                } else if (success == false) {
                    String Ulogged = jo.getString("error");
                    if (Ulogged.equals("User already is logged")) {
                        details.setLoginFailure(true);
                    } else {
                        details.setLoginNoMatch(true);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void AccessToken(String result, Content details) {
        Log.d("debug", result);
        try {
            JSONObject jo = new JSONObject(result);
            if (jo != null) {
                String access_token = jo.getString("access_token");
                details = new Content();
                details.setAccess_token(access_token);
                String expires_in = jo.getString("expires_in");
                details.setExpires_in(expires_in);
                String token_type = jo.getString("token_type");
                details.setToken_type(token_type);
                String Response = jo.getString("success");
                if (Response.equals(false)) {
                    details.setToken_failure(true);
                }
                /*if(Response.equals("")){
                    details.setMobile();
                }*/
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void LogoutDetails(String result, Content details) {
        Log.d("debug", result);
        try {
            JSONObject jo1 = new JSONObject(result);
            if (jo1 != null) {
                // boolean success = new Integer(0).equals(jo.get("success"));
                boolean success = (boolean) jo1.get("success");
                if (success == true) {
                    details.setLogoutSuccess(true);
                } else {
                    details.setLogoutFailure(true);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //details of cart
    public void cartDetails(String result, Content details) {
        Log.d("debug", result);
        try {
            JSONObject jo1 = new JSONObject(result);
            if (jo1 != null) {
                // boolean success = new Integer(0).equals(jo.get("success"));
                boolean success = (boolean) jo1.get("success");
                if (success == true) {
                    details.setCartSuccess(true);
                } else {
                    details.setCartFailure(true);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //recieving Order ID after Confirming Cart
    public void RecieveConfirmCart(String result, Content details) {
        Log.d("debug", result);
        try {
            JSONObject jo1 = new JSONObject(result);
            if (jo1 != null) {
                // boolean success = new Integer(0).equals(jo.get("success"));
                boolean success = (boolean) jo1.get("success");
                if (success == true) {
                    JSONObject data = jo1.getJSONObject("data");
                    int OrderId = data.getInt("order_id");
                    details.setOrder_ID(OrderId);
                    details.setOrderIDSuccess(true);
                } else {
                    details.setOrdeIDFailure(true);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //delete Cart
    public void resultEmptyCartDetails(String result, Content details) {
        Log.d("debug", result);
        try {
            JSONObject jo1 = new JSONObject(result);
            if (jo1 != null) {
                // boolean success = new Integer(0).equals(jo.get("success"));
                boolean success = (boolean) jo1.get("success");
                if (success == true) {
                    details.setDeleteCartSuccess(true);
                } else {
                    details.setDeleteCartFailure(true);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
