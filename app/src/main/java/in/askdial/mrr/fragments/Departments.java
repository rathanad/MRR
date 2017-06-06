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
import in.askdial.mrr.adapter.DepartmentAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Departments extends Fragment {
    int[] imgs;
    String[] heads, contents;
    ArrayList<Content> arrayList;
    RecyclerView departmentview;
    RecyclerView.LayoutManager layoutManager;
    DepartmentAdapter adapter;

    public Departments() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_departments, container, false);
        getActivity().setTitle("Departments");

        imgs = new int[]{R.drawable.acupuncture, R.drawable.mrr_hydro_therapy, R.drawable.mrr_manipulative_therapy, R.drawable.mrr_diet_therapy,
                R.drawable.mrr_physiotherapy, R.drawable.mrr_aroma_therapy, R.drawable.therapy_yoga, R.drawable.mrr_meditation};
        heads = getResources().getStringArray(R.array.departmentsheadlist);
        contents = getResources().getStringArray(R.array.departmentscontentlist);
        arrayList = new ArrayList<>();

        for (int i = 0; i < imgs.length; i++) {
            Content content = new Content(heads[i], /*contents[i],*/ imgs[i]);
            arrayList.add(content);
        }

        departmentview = (RecyclerView) view.findViewById(R.id.departmentsview);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        adapter = new DepartmentAdapter(arrayList, Departments.this);

        departmentview.setHasFixedSize(true);
        departmentview.setLayoutManager(layoutManager);
        departmentview.setAdapter(adapter);

        return view;
    }

}
