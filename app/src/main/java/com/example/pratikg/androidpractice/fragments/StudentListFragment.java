package com.example.pratikg.androidpractice.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pratikg.androidpractice.R;
import com.example.pratikg.androidpractice.activities.MainActivity;
import com.example.pratikg.androidpractice.adapters.StudentRecycleAdapter;
import com.example.pratikg.androidpractice.baseactivity.MasterFragment;
import com.example.pratikg.androidpractice.interfaces.OnClickItemListner;
import com.example.pratikg.androidpractice.objects.ObjStudent;
import com.example.pratikg.androidpractice.utils.APIClient;
import com.example.pratikg.androidpractice.utils.APIInterface;
import com.example.pratikg.androidpractice.utils.SettingPreference;
import com.example.pratikg.androidpractice.utils.SimpleDividerItemDecoration;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class StudentListFragment extends MasterFragment implements OnClickItemListner {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerView;
    private String mParam1;
    private String mParam2;
    View view;
    APIInterface apiInterface;
    private LinearLayoutManager lLayout;
    StudentRecycleAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_test, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        initApiCAll();
    }


    /*
        Calling the Api here
     */
    private void initApiCAll() {
        showProgress(getActivity());

        Call<ObjStudent> call = apiInterface.doGetListResources();
        call.enqueue(new Callback<ObjStudent>() {
            @Override
            public void onResponse(Call<ObjStudent> call, Response<ObjStudent> response) {
                stopProgress();

                ObjStudent objStudent = response.body();

                if(objStudent.getResult().equalsIgnoreCase("Success")) {
                    Log.v("Response", response.toString());
                    recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
                    lLayout = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(lLayout);
                     adapter = new StudentRecycleAdapter(getActivity(), objStudent.getData(), StudentListFragment.this,StudentListFragment.this);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<ObjStudent> call, Throwable t) {

            }


        });


    }


    @Override
    public void onClickItem(StudentRecycleAdapter.RecyclerViewHolders holder, List<Integer> postionArray, int position) {

        if(holder.cdIsVerified.isChecked()) {
            postionArray.add(position);
        }else {

            for (int i = 0; i <postionArray.size() ; i++) {
                if(postionArray.get(i).equals(position)) {
                    postionArray.remove(i);
                }
            }


        }

        SettingPreference.addStudentToPref(getActivity(), postionArray,position);

    }


}
