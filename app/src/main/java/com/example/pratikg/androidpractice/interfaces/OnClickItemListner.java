package com.example.pratikg.androidpractice.interfaces;

import com.example.pratikg.androidpractice.adapters.StudentRecycleAdapter;

import java.util.List;

public interface OnClickItemListner {

    void onClickItem(StudentRecycleAdapter.RecyclerViewHolders holder, List<Integer> postionArray, int position);

}
