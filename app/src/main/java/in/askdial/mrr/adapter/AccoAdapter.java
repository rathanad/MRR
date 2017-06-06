package in.askdial.mrr.adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.askdial.mrr.values.Content;
import in.askdial.mrr.R;
import in.askdial.mrr.fragments.Accomadations;
import in.askdial.mrr.fragments.Accomodation_details;

/**
 * Created by cgani on 08-Oct-16.
 */

public class AccoAdapter extends RecyclerView.Adapter<AccoAdapter.AccoViewHolder> {

    ArrayList<Content> arraylist = new ArrayList<>();
    Accomadations accomadations;

    public AccoAdapter(ArrayList<Content> titlelist, Accomadations accomadations1) {
        this.arraylist = titlelist;
        this.accomadations = accomadations1;
    }

    @Override
    public AccoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.accomodation_card_1, parent, false);
        AccoViewHolder accoViewHolder = new AccoViewHolder(view);
        return accoViewHolder;
    }

    @Override
    public void onBindViewHolder(AccoViewHolder holder, int position) {
        Content content = arraylist.get(position);
        holder.tvtitle.setText(content.getTitle());
        holder.tvdespcription.setText(content.getDescription());
        holder.tvAmount.setText(content.getAmount());
        holder.tv_productId.setText(content.getProductId());
    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    public class AccoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvtitle, tvdespcription,tvAmount, tv_productId;

        public AccoViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvtitle = (TextView) itemView.findViewById(R.id.textView4);
            tvdespcription = (TextView) itemView.findViewById(R.id.textView5);
            tvAmount = (TextView) itemView.findViewById(R.id.textView6);
            tv_productId=(TextView) itemView.findViewById(R.id.tv_productId);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Content details = arraylist.get(pos);
            Accomodation_details fragment = new Accomodation_details();
            Bundle bundle = new Bundle();
            FragmentTransaction fragmentTransaction = accomadations.getActivity().getSupportFragmentManager().beginTransaction();
            bundle.putInt("pos", pos);
            bundle.putString("Title", details.getTitle());
            bundle.putString("Amount", details.getAmount());
            bundle.putString("Product_ID", details.getProductId());
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.container_main, fragment).addToBackStack(null).commit();
        }
    }
}
