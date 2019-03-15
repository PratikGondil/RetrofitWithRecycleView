package com.example.pratikg.androidpractice.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.pratikg.androidpractice.R;
import com.example.pratikg.androidpractice.interfaces.OnClickItemListner;
import com.example.pratikg.androidpractice.objects.Datum;
import com.example.pratikg.androidpractice.utils.SettingPreference;

import java.util.ArrayList;
import java.util.List;

public class StudentRecycleAdapter extends RecyclerView.Adapter<StudentRecycleAdapter.RecyclerViewHolders> {

    private List<Datum> itemList;
    private Context context;
    String packageName;
    Fragment fragment;
    List<Integer> postionArray = new ArrayList<>();
    OnClickItemListner onClickItemListner;


    public StudentRecycleAdapter(Context context, List<Datum> itemList, Fragment fragment, OnClickItemListner onClickItemListener) {
        this.itemList = itemList;
        this.context = context;
        this.fragment = fragment;
        this.onClickItemListner = onClickItemListener;
        packageName = context.getPackageName();
    }


    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_list, null);

        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolders holder, final int position) {

        holder.txtStudentName.setText(itemList.get(position).getName());
        holder.txtCity.setText(itemList.get(position).getCity());
        holder.txtMobileNo.setText(itemList.get(position).getMobile());

        postionArray = SettingPreference.loadStudentFromPref(context);
        if (!postionArray.isEmpty()) {
            for (int i = 0; i < postionArray.size(); i++) {

                int pos = postionArray.get(i);
                if (pos == position) {
                    holder.cdIsVerified.setChecked(true);
                }


            }
        }

        holder.cdIsVerified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItemListner.onClickItem(holder, postionArray, position);

            }
        });


    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }


    public class RecyclerViewHolders extends RecyclerView.ViewHolder {


        private TextView txtStudentNameText;
        private TextView txtStudentName;
        private TextView txtMobileNoText;
        private TextView txtMobileNo;
        private TextView txtCityText;
        private TextView txtCity;
        private TextView txtIsVerfied;
        public CheckBox cdIsVerified;


        public RecyclerViewHolders(View itemView) {
            super(itemView);
            txtStudentNameText = (TextView) itemView.findViewById(R.id.txtStudentNameText);
            txtStudentName = (TextView) itemView.findViewById(R.id.txtStudentName);
            txtMobileNoText = (TextView) itemView.findViewById(R.id.txtMobileNoText);
            txtMobileNo = (TextView) itemView.findViewById(R.id.txtMobileNo);
            txtCityText = (TextView) itemView.findViewById(R.id.txtCityText);
            txtCity = (TextView) itemView.findViewById(R.id.txtCity);
            txtIsVerfied = (TextView) itemView.findViewById(R.id.txtIsVerfied);
            cdIsVerified = (CheckBox) itemView.findViewById(R.id.cdIsVerified);


        }


    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}