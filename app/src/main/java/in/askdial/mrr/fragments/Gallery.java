package in.askdial.mrr.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.askdial.mrr.values.Content;
import in.askdial.mrr.R;
import in.askdial.mrr.adapter.GalleryAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Gallery extends Fragment {
    RecyclerView galleryview;
    GalleryAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Content> arrayList;
    int[] galleryimgs;


    public Gallery() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        getActivity().setTitle("Gallery");


        galleryimgs = new int[]{ R.drawable.mrr_hospitalfront1, R.drawable.mrr_hospitalfront,  R.drawable.mrr_hospitalfrontgate1, R.drawable.mrr_canteen, R.drawable.gallery11, R.drawable.gallery12, R.drawable.gallery13, R.drawable.gallery14,
                R.drawable.gallery15, R.drawable.gallery16, R.drawable.gallery17, R.drawable.gallery19, R.drawable.gallery18,
                R.drawable.gallery20, R.drawable.gallery21, R.drawable.gallery22, R.drawable.gallery23, R.drawable.gallery24,
                R.drawable.gallery25, R.drawable.gallery26, R.drawable.gallery27, R.drawable.gallery28};
        arrayList = new ArrayList<>();

        for (int i = 0; i < galleryimgs.length; i++) {
            Content content = new Content(galleryimgs[i]);
            arrayList.add(content);
        }

        galleryview = (RecyclerView) view.findViewById(R.id.galleryview);
        layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        adapter = new GalleryAdapter(arrayList, Gallery.this);

        galleryview.setHasFixedSize(true);
        galleryview.setLayoutManager(layoutManager);
        galleryview.setAdapter(adapter);

        return view;
    }


}
