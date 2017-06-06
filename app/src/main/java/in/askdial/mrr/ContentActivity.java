package in.askdial.mrr;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;

import java.util.Arrays;
import java.util.List;

import in.askdial.mrr.adapter.Ingredient;
import in.askdial.mrr.adapter.Recipe;
import in.askdial.mrr.adapter.RecipeAdapter;
import in.askdial.mrr.dataposting.ConnectingTask;
import in.askdial.mrr.fragments.Departments;
import in.askdial.mrr.fragments.UserDetailsFragment;
import in.askdial.mrr.values.Content;
import in.askdial.mrr.values.FunctionCalls;

public class ContentActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    String Token_type = "", Access_token = "", Expires_in = "", userName, userLastname,userMobile,userEmail,userDateAdded;
    private RecipeAdapter mAdapter;
    DrawerLayout drawer;
    int expandedPosition = -1, checkposition = -1;
    FunctionCalls functionCalls;
    ConnectingTask connectingTask;
    Content detailsValue;
    Button logout;
    TextView tv_User_name;
    static ProgressDialog dialog = null;
    Thread mythread;
    boolean doubleBackToExitPressedOnce = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        functionCalls=new FunctionCalls();
        connectingTask=new ConnectingTask();
        detailsValue=new Content();

        settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        editor = settings.edit();

        Intent i=getIntent();
        Bundle bnd = i.getExtras();

        userName = bnd.getString("username");
        userLastname=bnd.getString("userlast");
        userMobile=bnd.getString("useremail");
        userEmail=bnd.getString("usermobile");
        userDateAdded=bnd.getString("date_added");
        tv_User_name= (TextView) findViewById(R.id.tv_User_name);


        Access_token = settings.getString("Access_token", "");
        Token_type = settings.getString("Token_type", "");
        Expires_in = settings.getString("Expires_in", "");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        Userview();

        tv_User_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserDetailsFragment fragment = new UserDetailsFragment();
                Bundle bundle = new Bundle();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                bundle.putString("userName", userName);
                bundle.putString("userlast",userLastname);
                bundle.putString("useremail",userMobile);
                bundle.putString("usermobile",userEmail);
               // bundle.putString("date_added",userDateAdded);
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.container_main, fragment).addToBackStack(null).commit();
                drawer.closeDrawer(GravityCompat.START);

                /*UserDetailsFragment fragment = new UserDetailsFragment();
                Bundle bundle = new Bundle();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                bundle.putString("userName", userName);
                bundle.putString("userlast",userLastname);
                bundle.putString("useremail",userMobile);
                bundle.putString("usermobile",userEmail);
                bundle.putString("date_added",userDateAdded);
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.container_main, fragment).addToBackStack(null).commit();*/
            }
        });
        logout= (Button) findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(functionCalls.isInternetOn(ContentActivity.this)){
                    AlertDialog.Builder existbuilder1 = new AlertDialog.Builder(ContentActivity.this);
                    existbuilder1.setTitle("LOGOUT");
                    existbuilder1.setCancelable(false);
                    existbuilder1.setMessage(Html.fromHtml("<font color='#000000'><b>Sure you want to logout?</b></font>"));
                    existbuilder1.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        ConnectingTask.Logout login = connectingTask.new Logout(Access_token, detailsValue);
                        login.execute();
                        mythread = null;
                        Runnable runnable = new ContentActivity.LoginTimer();
                        mythread = new Thread(runnable);
                        mythread.start();
                        }
                    });
                    existbuilder1.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog existalert1 = existbuilder1.create();
                    existalert1.show();
                }
            }
        });
        Ingredient company_profile = new Ingredient("Company Profile");
        Ingredient vision_mission = new Ingredient("Vision Mission");
        Ingredient management = new Ingredient("Management");
        Ingredient director = new Ingredient("Director Message");
        Ingredient facilities = new Ingredient("Facilities");
        Ingredient boardofmembers= new Ingredient("Board of Members");
        Ingredient history = new Ingredient("History");
        Ingredient department = new Ingredient("Departments");
        Ingredient treatments = new Ingredient("Treatments Offer");
        Ingredient accomodation = new Ingredient("Accomodations");
        Ingredient program = new Ingredient("Programs");
        Ingredient gallery = new Ingredient("Gallery");
        Ingredient events = new Ingredient("Events & News");
        Ingredient carrer = new Ingredient("Carrer Opportunities");
        Ingredient broucher = new Ingredient("E-Broucher");
        Ingredient contact = new Ingredient("Contact Us");

        Recipe treatment = new Recipe("Treatment", Arrays.asList(history, department, treatments));
        Recipe accomodate = new Recipe("Accomodations", Arrays.asList(accomodation));
        Recipe programs = new Recipe("Program & Packages", Arrays.asList(program));
        Recipe media = new Recipe("Media", Arrays.asList(gallery, events, carrer, broucher));
        Recipe about_us = new Recipe("About Us", Arrays.asList(company_profile, boardofmembers, vision_mission, management, director, facilities));
        Recipe contact_us = new Recipe("Contact Us", Arrays.asList(contact));
        final List<Recipe> recipes = Arrays.asList(treatment, accomodate, programs, media, about_us, contact_us);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.navrecyclerview);
        mAdapter = new RecipeAdapter(this, recipes, GravityCompat.START, drawer, ContentActivity.this, toolbar);
        mAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onListItemExpanded(int position) {
            }

            @Override
            public void onListItemCollapsed(int position) {
            }
        });

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        switchContent(new Departments(), toolbar);

    }

    private void Userview() {

        tv_User_name.setText(userName);
    }

    /*@Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }*/
    /*@Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if (getFragmentManager().getBackStackEntryCount()>0 && !drawer.isDrawerOpen(GravityCompat.START)) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
            if(getFragmentManager().getBackStackEntryCount() == 0) {
            if(functionCalls.isInternetOn(ContentActivity.this)){
                AlertDialog.Builder existbuilder1 = new AlertDialog.Builder(ContentActivity.this);
                existbuilder1.setTitle("User Permission");
                existbuilder1.setCancelable(false);
                existbuilder1.setMessage(Html.fromHtml("<font color='#000000'><b>Sure you want to Exit?</b></font>"));
                existbuilder1.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        *//*ConnectingTask.Logout login = connectingTask.new Logout(Access_token, detailsValue);
                        login.execute();
                        mythread = null;
                        Runnable runnable = new ContentActivity.LoginTimer();
                        mythread = new Thread(runnable);
                        mythread.start();*//*
                    }
                });
                existbuilder1.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog existalert1 = existbuilder1.create();
                existalert1.show();
            }
        }

        }
    }
*/
    /*@Override
    public void onBackPressed() {
        //Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }else if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if (getFragmentManager().getBackStackEntryCount()>0 && !drawer.isDrawerOpen(GravityCompat.START)) {
            getFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this,"Please click BACK again to exit.", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;

                }
            }, 2000);
        } else {
            super.onBackPressed();
            return;
        }
    }*/

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }else if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            System.exit(0);

                        }
                    })
                    .setNeutralButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                           // dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;

                }
            }, 500);
        }else{
            super.onBackPressed();
            return;
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
                    if (detailsValue.isLogoutSuccess()) {
                        detailsValue.setLogoutSuccess(false);
                        Intent intent = new Intent(ContentActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(ContentActivity.this, "Logout Success", Toast.LENGTH_SHORT).show();
                        finish();
                        mythread.interrupt();
                    }
                    if (detailsValue.isLogoutFailure()) {
                        detailsValue.setLogoutFailure(false);
                        Toast.makeText(ContentActivity.this, "Logout Failure", Toast.LENGTH_SHORT).show();
                        mythread.interrupt();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void switchContent(Fragment fragment, Toolbar toolbar) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_main, fragment);
        ft.commit();
        toolbar.setTitle("Company Profile");
    }

}
