package in.askdial.mrr.adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

import in.askdial.mrr.R;
import in.askdial.mrr.fragments.Accomadations;
import in.askdial.mrr.fragments.BoardMembersFragment;
import in.askdial.mrr.fragments.Broucher;
import in.askdial.mrr.fragments.Carrer;
import in.askdial.mrr.fragments.CompanyProfile;
import in.askdial.mrr.fragments.Contactus;
import in.askdial.mrr.fragments.Departments;
import in.askdial.mrr.fragments.Director;
import in.askdial.mrr.fragments.Events;
import in.askdial.mrr.fragments.Facilities;
import in.askdial.mrr.fragments.Gallery;
import in.askdial.mrr.fragments.History;
import in.askdial.mrr.fragments.Management;
import in.askdial.mrr.fragments.Programs;
import in.askdial.mrr.fragments.Treatments;
import in.askdial.mrr.fragments.Vision_Mission;

public class IngredientViewHolder extends ChildViewHolder {

    private TextView mIngredientTextView;

    public IngredientViewHolder(View itemView) {
        super(itemView);
        mIngredientTextView = (TextView) itemView.findViewById(R.id.ingredient_textview);
    }

    public void bind(final Ingredient ingredient, final int gravity, final DrawerLayout drawer, final AppCompatActivity activity,
                     final Toolbar toolbar) {
        mIngredientTextView.setText(ingredient.getName());

        mIngredientTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                switch (ingredient.getName()) {
                    case "Company Profile":
                        fragmentTransaction.replace(R.id.container_main, new CompanyProfile()).addToBackStack(null).commit();
                        toolbar.setTitle("Company Profile");
                        break;

                    case "Vision Mission":
                        fragmentTransaction.replace(R.id.container_main, new Vision_Mission()).addToBackStack(null).commit();
                        toolbar.setTitle("Vision Mission");
                        break;

                    case "Management":
                        fragmentTransaction.replace(R.id.container_main, new Management()).addToBackStack(null).commit();
                        toolbar.setTitle("Management");
                        break;

                    case "Director Message":
                        fragmentTransaction.replace(R.id.container_main, new Director()).addToBackStack(null).commit();
                        toolbar.setTitle("Director Message");
                        break;

                    case "Board of Members":
                        fragmentTransaction.replace(R.id.container_main, new BoardMembersFragment()).addToBackStack(null).commit();
                        toolbar.setTitle("Board of Members");
                        break;

                    case "Facilities":
                        fragmentTransaction.replace(R.id.container_main, new Facilities()).addToBackStack(null).commit();
                        toolbar.setTitle("Facilities");
                        break;

                    case "Accomodations":
                        Accomadations accomadations = new Accomadations();
                        Bundle bundle = new Bundle();
                        bundle.putString("start", "Start");
                        accomadations.setArguments(bundle);
                        fragmentTransaction.replace(R.id.container_main, accomadations).addToBackStack(null).commit();
                        toolbar.setTitle("Accomodations");
                        break;

                    case "History":
                        fragmentTransaction.replace(R.id.container_main, new History()).addToBackStack(null).commit();
                        toolbar.setTitle("History");
                        break;

                    case "Departments":
                        fragmentTransaction.replace(R.id.container_main, new Departments()).addToBackStack(null).commit();
                        toolbar.setTitle("Departments");
                        break;

                    case "Treatments Offer":
                        fragmentTransaction.replace(R.id.container_main, new Treatments()).addToBackStack(null).commit();
                        toolbar.setTitle("Treatments Offer");
                        break;

                    case "Programs":
                        fragmentTransaction.replace(R.id.container_main, new Programs()).addToBackStack(null).commit();
                        toolbar.setTitle("Programs");
                        break;

                    case "Gallery":
                        fragmentTransaction.replace(R.id.container_main, new Gallery()).addToBackStack(null).commit();
                        toolbar.setTitle("Gallery");
                        break;

                    case "Carrer Opportunities":
                        fragmentTransaction.replace(R.id.container_main, new Carrer()).addToBackStack(null).commit();
                        toolbar.setTitle("Carrer");
                        break;

                    case "E-Broucher":
                        fragmentTransaction.replace(R.id.container_main, new Broucher()).addToBackStack(null).commit();
                        toolbar.setTitle("E-Broucher");
                        break;

                    case "Events & News":
                        fragmentTransaction.replace(R.id.container_main, new Events()).addToBackStack(null).commit();
                        toolbar.setTitle("Events & News");
                        break;

                    case "Contact Us":
                        fragmentTransaction.replace(R.id.container_main, new Contactus()).addToBackStack(null).commit();
                        toolbar.setTitle("Contact Us");
                        break;
                }
                drawer.closeDrawer(gravity);
            }
        });
    }
}
