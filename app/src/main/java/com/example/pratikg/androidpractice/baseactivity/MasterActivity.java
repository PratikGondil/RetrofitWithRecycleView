package com.example.pratikg.androidpractice.baseactivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.example.pratikg.androidpractice.R;

import java.io.UnsupportedEncodingException;

public class MasterActivity extends AppCompatActivity {

    public static ProgressDialog dialog = null;
    public static boolean isLoggedIn = false;

    // to show progress and stop
    public static void showProgressDialog(Context context, boolean isShow) {

        try {
            if (dialog == null && isShow) {
                dialog = new ProgressDialog(context);
                dialog.setMessage("Loading..");
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            } else {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                    dialog = null;
                }
            }
        } catch (Exception e) {
            Log.e("", Log.getStackTraceString(e));
        }

    }

    // to stop progress
    public static void stopProgress() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }
        } catch (Exception e) {
            Log.e("", Log.getStackTraceString(e));
        }
    }

    public void replaceFragment(Fragment fragment) {
        try {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.container, fragment).commitAllowingStateLoss();
            ft.commit();
        } catch (Exception e) {
            Log.e("", Log.getStackTraceString(e));
        }
    }


    public static String convertToBase64(String text) {
        byte[] data = new byte[0];

        try {
            data = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e("", Log.getStackTraceString(e));
        }
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);
        base64 = base64.replace("\n", "").replace("\r", "");

        return base64;
    }

    public void replaceFragmentSignUp(Fragment fragment) {
        try {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            //ft.addToBackStack(fragment.getClass().getName());
            ft.replace(R.id.container, fragment);
            ft.commit();
        } catch (Exception e) {
            Log.e("", Log.getStackTraceString(e));
        }
    }
}
