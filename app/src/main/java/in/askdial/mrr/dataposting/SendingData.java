package in.askdial.mrr.dataposting;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import in.askdial.mrr.values.FunctionCalls;

/**
 * Created by Admin on 05-May-17.
 */

public class SendingData {
    String BASE_URL = DataRestAPI.BASE_URL2;
    String BASE_URL1 = DataRestAPI.BASE_URL11;
    FunctionCalls functionCalls = new FunctionCalls();
    String Ac_token;

    public String PostLogin(String FirstName, String Lastname, String Email, String Mobile, String Address, String City, String Country, String Password, String Confirm_Password, String Access_Token,int State) {
        String responsestr = "";
        Ac_token = Access_Token;
        int agree=1, country_ID=99;
        // HashMap<String, String> datamap = new HashMap<>();
        JSONObject datamap = new JSONObject();
        try {
            datamap.put("firstname", FirstName);
            datamap.put("lastname", Lastname);
            datamap.put("email", Email);
            datamap.put("telephone", Mobile);
            datamap.put("address_1", Address);
            datamap.put("city", City);
            datamap.put("zone_id",State);
            datamap.put("country_id", country_ID);
            datamap.put("password", Password);
            datamap.put("confirm", Confirm_Password);
            datamap.put("agree", agree);

            responsestr = UrlPostAccessTokenConnection("register/register", datamap);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responsestr;
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

    private String UrlGetConnection(String Get_Url) throws IOException {
        String response = "";
        functionCalls.LogStatus("Connecting URL: " + BASE_URL + Get_Url);
        URL url = new URL(BASE_URL + Get_Url);
        functionCalls.LogStatus("URL Get Connection 1");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        functionCalls.LogStatus("URL Get Connection 2");
        conn.setReadTimeout(15000);
        functionCalls.LogStatus("URL Get Connection 3");
        conn.setConnectTimeout(15000);
        functionCalls.LogStatus("URL Get Connection 4");
        int responseCode = conn.getResponseCode();
        functionCalls.LogStatus("URL Get Connection 5");
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            functionCalls.LogStatus("URL Get Connection 6");
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            functionCalls.LogStatus("URL Get Connection 7");
            while ((line = br.readLine()) != null) {
                response += line;
            }
            functionCalls.LogStatus("URL Get Connection 8");
        } else {
            response = "";
            functionCalls.LogStatus("URL Get Connection 9");
        }
        functionCalls.LogStatus("URL Get Connection Response: " + response);
        return response;
    }

    public String Login_check(String Email, String Password, String Access_token) {
        String response = "";
        Ac_token = Access_token;
        JSONObject datamap = new JSONObject();
        try {
            datamap.put("email", Email);
            datamap.put("password", Password);
            response = UrlPostAccessTokenConnection("login/login", datamap);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    //logout From APP
    public String getLogout(String AccessToken) {
        String response = "";
        Ac_token = AccessToken;
        JSONObject datamap = new JSONObject();
        try {
            datamap.put("logout", "");
            response = UrlPostAccessTokenConnection("logout/logout", datamap);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }

    //add to cart
    public String addToCart(int Product_id, int quantity,String Name, String Date,String Access_Token,String select, String dateField) {
        String responsestr = "";
        Ac_token = Access_Token;
        JSONObject datamap = new JSONObject();
        try {
            datamap.put("product_id", Product_id);
            datamap.put("quantity",quantity);
            JSONObject datamap1=new JSONObject();
            datamap1.put(select, Name);
            datamap1.put(dateField, Date);
            datamap.put("option",datamap1);

            responsestr = UrlPostAccessTokenConnection("cart/cart", datamap);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responsestr;
    }

    //Confirm Cart
    public String getOrdeConfirm(String AccessToken) {
        String response = "";
        Ac_token = AccessToken;
        JSONObject datamap = new JSONObject();
        try {
            datamap.put("confirm", "");
            response = UrlPostAccessTokenConnection("simple_confirm/confirm", datamap);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }
//Delete Cart(Empty Cart)
    public String reqToEmptyCart(String AccessToken) {
        String response = "";
        Ac_token = AccessToken;
        JSONObject datamap = new JSONObject();
        try {
            datamap.put("empty", "");
            response = UrlDeleteAccessTokenConnection("cart/emptycart", datamap);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String UrlPostAccessTokenConnection(String Post_Url, JSONObject datamap) throws IOException {
        String response = "";
        functionCalls.LogStatus("Connecting URL: " + BASE_URL1 + Post_Url);
        URL url = new URL(BASE_URL1 + Post_Url);
        functionCalls.LogStatus("URL Connection 1");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + String.valueOf(Ac_token));
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
        conn.setRequestProperty("Authorization", "Bearer " + String.valueOf(Ac_token));
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

