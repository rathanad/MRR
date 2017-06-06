package in.askdial.mrr.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.askdial.mrr.adapter.AccoAdapter;
import in.askdial.mrr.values.Content;
import in.askdial.mrr.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Accomadations extends Fragment {
    RecyclerView accomadation_view;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Content> arrayList;

    String[] Title, Description, amount, product_id;

    Content content;

    public Accomadations() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.accomodations, container, false);
        getActivity().setTitle("Accommodations");


        Title = getResources().getStringArray(R.array.acco_titles);
        Description = getResources().getStringArray(R.array.acco_descrip);
        amount=getResources().getStringArray(R.array.amount);
        product_id=getResources().getStringArray(R.array.productId);



        arrayList = new ArrayList<>();
        for (int i = 0; i < Title.length; i++) {
            content = new Content(Title[i], Description[i], amount[i],product_id[i]);
            arrayList.add(content);
        }

        accomadation_view = (RecyclerView) view.findViewById(R.id.accomadation_list);
        layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        adapter = new AccoAdapter(arrayList, this);

        accomadation_view.setHasFixedSize(true);
        accomadation_view.setLayoutManager(layoutManager);
        accomadation_view.setAdapter(adapter);

        return view;
    }

}
