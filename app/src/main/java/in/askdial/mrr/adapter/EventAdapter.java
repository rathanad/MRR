package in.askdial.mrr.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.askdial.mrr.values.Content;
import in.askdial.mrr.R;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    ArrayList<Content> arrayList;

    public EventAdapter(ArrayList<Content> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_card, parent, false);
        EventViewHolder viewHolder = new EventViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        Content content = arrayList.get(position);
        holder.eventimg.setImageResource(content.getEventimgs());
        holder.tvcontent.setText(content.getEventcontent());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        ImageView eventimg;
        TextView tvcontent;

        public EventViewHolder(View itemView) {
            super(itemView);
            eventimg = (ImageView) itemView.findViewById(R.id.events_image);
            tvcontent = (TextView) itemView.findViewById(R.id.events_content);
        }
    }
}
