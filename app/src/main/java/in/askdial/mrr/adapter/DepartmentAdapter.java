package in.askdial.mrr.adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.askdial.mrr.values.Content;
import in.askdial.mrr.R;
import in.askdial.mrr.fragments.Departments;
import in.askdial.mrr.fragments.MasterFragment;

/**
 * Created by Admin on 15-Oct-16.
 */

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.DepartmentViewHolder> {
    ArrayList<Content> arrayList;
    Departments departments;

    public DepartmentAdapter(ArrayList<Content> arrayList, Departments departments) {
        this.arrayList = arrayList;
        this.departments = departments;
    }

    @Override
    public DepartmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.departments_contents_card, parent, false);
        DepartmentViewHolder viewHolder = new DepartmentViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DepartmentViewHolder holder, int position) {
        Content content = arrayList.get(position);
        holder.tvhead.setText(content.getDepartmenthead());
        /*holder.tvcontent.setText(content.getDepartmentcontent());*/
        holder.departmentimage.setImageResource(content.getDepartmentimgs());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class DepartmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvhead/*, tvcontent*/;
        ImageView departmentimage;

        public DepartmentViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvhead = (TextView) itemView.findViewById(R.id.departmenthead);
            /*tvcontent = (TextView) itemView.findViewById(R.id.departmentcontent);*/
            departmentimage = (ImageView) itemView.findViewById(R.id.departmentimage);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Content content = arrayList.get(pos);
            int image = content.getDepartmentimgs();
            String departmenthead = content.getDepartmenthead();
            MasterFragment fragment = new MasterFragment();
            Bundle bundle = new Bundle();
            FragmentTransaction fragmentTransaction = departments.getActivity().getSupportFragmentManager().beginTransaction();
            bundle.putInt("image", image);
            bundle.putInt("pos", pos);
            bundle.putString("head", departmenthead);
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.container_main, fragment).addToBackStack(null).commit();
        }
    }
}
