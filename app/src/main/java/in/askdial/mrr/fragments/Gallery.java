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

        galleryimgs = new int[]{R.drawable.gallery_1, R.drawable.gallery_2, R.drawable.gallery_3, R.drawable.gallery_4,
                R.drawable.gallery_5, R.drawable.gallery_6, R.drawable.gallery_7, R.drawable.gallery_8, R.drawable.gallery_9,
                R.drawable.gallery_10, R.drawable.gallery_11, R.drawable.gallery_12, R.drawable.gallery_13, R.drawable.gallery_14,
                R.drawable.gallery_15, R.drawable.gallery_16, R.drawable.gallery_17, R.drawable.gallery_18, R.drawable.gallery_19,
                R.drawable.gallery_20};
        arrayList = new ArrayList<>();

        for (int i = 0; i < galleryimgs.length; i++) {
            Content content = new Content(galleryimgs[i]);
            arrayList.add(content);
        }

        galleryview = (RecyclerView) view.findViewById(R.id.galleryview);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        adapter = new GalleryAdapter(arrayList, Gallery.this);

        galleryview.setHasFixedSize(true);
        galleryview.setLayoutManager(layoutManager);
        galleryview.setAdapter(adapter);

        return view;
    }

}
