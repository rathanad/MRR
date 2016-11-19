package in.askdial.mrr.adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import in.askdial.mrr.Content;
import in.askdial.mrr.R;
import in.askdial.mrr.fragments.Gallery;
import in.askdial.mrr.fragments.MasterFragment;

/**
 * Created by Admin on 15-Oct-16.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {
    ArrayList<Content> arrayList;
    Gallery gallery;

    public GalleryAdapter(ArrayList<Content> arrayList, Gallery gallery) {
        this.arrayList = arrayList;
        this.gallery = gallery;
    }

    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_card, parent, false);
        GalleryViewHolder viewHolder = new GalleryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GalleryViewHolder holder, int position) {
        Content content = arrayList.get(position);
        holder.galleryimage.setImageResource(content.getGalleryimgs());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class GalleryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView galleryimage;

        public GalleryViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            galleryimage = (ImageView) itemView.findViewById(R.id.galleryimage);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Content content = arrayList.get(pos);
            int image = content.getGalleryimgs();
            MasterFragment fragment = new MasterFragment();
            Bundle bundle = new Bundle();
            FragmentTransaction fragmentTransaction = gallery.getActivity().getSupportFragmentManager().beginTransaction();
            bundle.putInt("image", image);
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.container_main, fragment).addToBackStack(null).commit();
        }
    }
}
