package in.askdial.mrr.values;

/**
 * Created by cgani on 08-Oct-16.
 */

public class Content {
    String Title, Description, departmenthead, departmentcontent, eventcontent;
    int img, departmentimgs, galleryimgs, eventimgs;
    String  amount,productId;

    public String getAccess_token(String access_token) {
        return Access_token;
    }

    public void setAccess_token(String access_token) {
        Access_token = access_token;
    }

    public String getExpires_in() {
        return Expires_in;
    }

    public void setExpires_in(String expires_in) {
        Expires_in = expires_in;
    }

    public String getToken_type() {
        return Token_type;
    }

    public void setToken_type(String token_type) {
        Token_type = token_type;
    }

    String Access_token,Expires_in,Token_type;
    boolean Token_failure;

    public boolean isToken_failure() {
        return Token_failure;
    }

    public void setToken_failure(boolean token_failure) {
        Token_failure = token_failure;
    }
    /*//You Custom Consumer Key
    public static final String CONSUMER_KEY = "451237";
    //You Custom Consumer SECRET
    public static final String CONSUMER_SECRET = "789567";
    //Your Base URL for the site
    public static final String BASE_URL = "http://www.mrrnaturecure.com/";

    public static final String REQUEST_URL 		= BASE_URL + "oauth/initiate";
    public static final String ACCESS_URL 		= BASE_URL + "oauth/token";
    public static final String AUTHORIZE_URL 	= BASE_URL + "oauth/authorize";
    public static final String API_REQUEST 		= BASE_URL + "index.php?route=feed/rest_api/gettoken&grant_type=client_credentials";

    public static final String PRODUCT_API_REQUEST 		=   BASE_URL+"index.php?route=rest/register/register";

    public static final String ENCODING 		= "UTF-8";

    public static final String OAUTH_CALLBACK_URL = "http://localhost/";*/


    String First_name;
    String Last_name;
    String Email;
    String Mobile;
    String City;
    String Country;
    String Address;

    public String getDate_added() {
        return Date_added;
    }

    public void setDate_added(String date_added) {
        Date_added = date_added;
    }

    String Date_added;

    public int getOrder_ID() {
        return Order_ID;
    }

    public void setOrder_ID(int order_ID) {
        Order_ID = order_ID;
    }

    int Order_ID;

    String UserAlreadyLogged;
    String NoMatchUser;

    public String getRegisterationError() {
        return RegisterationError;
    }

    public void setRegisterationError(String registerationError) {
        RegisterationError = registerationError;
    }

    String RegisterationError;

    private boolean RegisterSuccess;
    private boolean RegisterFailure;

    private boolean LoginSuccess;
    private boolean LoginFailure;
    private boolean LoginNoMatch;
    private boolean LogoutSuccess;
    private boolean cartSuccess;
    private boolean cartFailure;
    private boolean orderIDSuccess;
    private boolean ordeIDFailure;
    private boolean deleteCartSuccess;
    private boolean deleteCartFailure;

    public boolean isOrderIDSuccess() {
        return orderIDSuccess;
    }

    public void setOrderIDSuccess(boolean orderIDSuccess) {
        this.orderIDSuccess = orderIDSuccess;
    }

    public boolean isOrdeIDFailure() {
        return ordeIDFailure;
    }

    public void setOrdeIDFailure(boolean ordeIDFailure) {
        this.ordeIDFailure = ordeIDFailure;
    }

    public boolean isDeleteCartSuccess() {
        return deleteCartSuccess;
    }

    public void setDeleteCartSuccess(boolean deleteCartSuccess) {
        this.deleteCartSuccess = deleteCartSuccess;
    }

    public boolean isDeleteCartFailure() {
        return deleteCartFailure;
    }

    public void setDeleteCartFailure(boolean deleteCartFailure) {
        this.deleteCartFailure = deleteCartFailure;
    }

    public boolean isCartSuccess() {
        return cartSuccess;
    }

    public void setCartSuccess(boolean cartSuccess) {
        this.cartSuccess = cartSuccess;
    }

    public boolean isCartFailure() {
        return cartFailure;
    }

    public void setCartFailure(boolean cartFailure) {
        this.cartFailure = cartFailure;
    }




    public boolean isLogoutSuccess() {
        return LogoutSuccess;
    }

    public void setLogoutSuccess(boolean logoutSuccess) {
        LogoutSuccess = logoutSuccess;
    }

    public boolean isLogoutFailure() {
        return LogoutFailure;
    }

    public void setLogoutFailure(boolean logoutFailure) {
        LogoutFailure = logoutFailure;
    }

    private boolean LogoutFailure;

    public int getCustomer_id() {
        return Customer_id;
    }

    public void setCustomer_id(int customer_id) {
        Customer_id = customer_id;
    }

    int Customer_id;

    public boolean isLoginNoMatch() {
        return LoginNoMatch;
    }

    public void setLoginNoMatch(boolean loginNoMatch) {
        LoginNoMatch = loginNoMatch;
    }

    public boolean isLoginSuccess() {
        return LoginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        LoginSuccess = loginSuccess;
    }

    public String getUserAlreadyLogged() {
        return UserAlreadyLogged;
    }

    public void setUserAlreadyLogged(String userAlreadyLogged) {
        UserAlreadyLogged = userAlreadyLogged;
    }

    public String getNoMatchUser() {
        return NoMatchUser;
    }

    public void setNoMatchUser(String noMatchUser) {
        NoMatchUser = noMatchUser;
    }
    public boolean isLoginFailure() {
        return LoginFailure;
    }

    public void setLoginFailure(boolean loginFailure) {
        LoginFailure = loginFailure;
    }

    public boolean isLoginError() {
        return LoginError;
    }

    public void setLoginError(boolean loginError) {
        LoginError = loginError;
    }

    private boolean LoginError;

    public boolean isRegisterSuccess() {
        return RegisterSuccess;
    }

    public void setRegisterSuccess(boolean registerSuccess) {
        RegisterSuccess = registerSuccess;
    }

    public boolean isRegisterFailure() {
        return RegisterFailure;
    }

    public void setRegisterFailure(boolean registerFailure) {
        RegisterFailure = registerFailure;
    }

    public boolean isRegisterError() {
        return RegisterError;
    }

    public void setRegisterError(boolean registerError) {
        RegisterError = registerError;
    }

    private boolean RegisterError;

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }

    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String last_name) {
        Last_name = last_name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Content() {
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Content(String title, String description, String Amount, String productI) {
        Title = title;
        Description = description;
        amount=Amount;
        productId=productI;


    }

    public Content(String departmenthead, /*String departmentcontent,*/ int departmentimgs) {
        this.departmenthead = departmenthead;
        /*this.departmentcontent = departmentcontent;*/
        this.departmentimgs = departmentimgs;
    }

    public Content(int eventimgs, String eventcontent) {
        this.eventimgs = eventimgs;
        this.eventcontent = eventcontent;
    }

    public Content(int galleryimgs) {
        this.galleryimgs = galleryimgs;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getImg() {
        return this.img;
    }

    public String getDepartmenthead() {
        return departmenthead;
    }

    public void setDepartmenthead(String departmenthead) {
        this.departmenthead = departmenthead;
    }

    public String getDepartmentcontent() {
        return departmentcontent;
    }

    public void setDepartmentcontent(String departmentcontent) {
        this.departmentcontent = departmentcontent;
    }

    public int getDepartmentimgs() {
        return departmentimgs;
    }

    public void setDepartmentimgs(int departmentimgs) {
        this.departmentimgs = departmentimgs;
    }

    public int getGalleryimgs() {
        return galleryimgs;
    }

    public void setGalleryimgs(int galleryimgs) {
        this.galleryimgs = galleryimgs;
    }

    public String getEventcontent() {
        return eventcontent;
    }

    public int getEventimgs() {
        return eventimgs;
    }



}
