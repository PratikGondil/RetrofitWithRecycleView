package com.example.pratikg.androidpractice.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.pratikg.androidpractice.R;
import com.example.pratikg.androidpractice.baseactivity.MasterActivity;
import com.example.pratikg.androidpractice.fragments.StudentListFragment;

import java.util.HashMap;


public class MainActivity extends MasterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        replaceFragmentSignUp(new StudentListFragment());
    }

    @Override
    public void onBackPressed() {
        exitByBackKey();
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to exit application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();

    }
}
