package in.askdial.mrr.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.askdial.mrr.Content;
import in.askdial.mrr.R;
import in.askdial.mrr.adapter.EventAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Events extends Fragment {
    int[] event_imgs;
    String[] event_content;
    RecyclerView event_view;
    RecyclerView.LayoutManager layoutManager;
    EventAdapter adapter;
    ArrayList<Content> arrayList;

    public Events() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        getActivity().setTitle("Events");

        event_imgs = new int[]{R.drawable.events_1, R.drawable.events_2, R.drawable.events_3, R.drawable.events_4, R.drawable.events_5};
        event_content = getResources().getStringArray(R.array.events_list);

        arrayList = new ArrayList<>();
        for (int i = 0; i < event_imgs.length; i++) {
            Content content = new Content(event_imgs[i], event_content[i]);
            arrayList.add(content);
        }

        event_view = (RecyclerView) view.findViewById(R.id.events_view);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        adapter = new EventAdapter(arrayList);

        event_view.setHasFixedSize(true);
        event_view.setLayoutManager(layoutManager);
        event_view.setAdapter(adapter);

        return view;
    }

}
