package in.askdial.mrr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;

import java.util.Arrays;
import java.util.List;

import in.askdial.mrr.adapter.Ingredient;
import in.askdial.mrr.adapter.Recipe;
import in.askdial.mrr.adapter.RecipeAdapter;
import in.askdial.mrr.fragments.Accomadations;
import in.askdial.mrr.fragments.CompanyProfile;
import in.askdial.mrr.fragments.Departments;
import in.askdial.mrr.fragments.Director;
import in.askdial.mrr.fragments.Facilities;
import in.askdial.mrr.fragments.Gallery;
import in.askdial.mrr.fragments.Management;
import in.askdial.mrr.fragments.MasterFragment;
import in.askdial.mrr.fragments.Vision_Mission;

public class ContentActivity extends AppCompatActivity {
    private RecipeAdapter mAdapter;
    DrawerLayout drawer;
    int expandedPosition = -1, checkposition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

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

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void switchContent(Fragment fragment, Toolbar toolbar) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_main, fragment);
        ft.commit();
        toolbar.setTitle("Company Profile");
    }
}
