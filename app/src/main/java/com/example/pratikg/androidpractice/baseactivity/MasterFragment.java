package com.example.pratikg.androidpractice.baseactivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.pratikg.androidpractice.R;
import com.example.pratikg.androidpractice.utils.Utils;

/**
 * Created by pratikg on 3/7/18.
 */

public class MasterFragment extends android.support.v4.app.Fragment {
    public static ProgressDialog dialog = null;
    public static ProgressDialog progressDialog = null;

    public static void showProgress(Context context) {
        try {
            if (dialog == null) {
                dialog = new ProgressDialog(context);
                dialog.setMessage("Processing...\nPlease Wait");
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        } catch (Exception e) {
            Log.e("", Log.getStackTraceString(e));
            //As per sonar lint printstackTrace change to Log
        }
    }
    public static void showProgress(Context context, String message) {
        try {
            if (dialog == null) {
                dialog = new ProgressDialog(context);
                dialog.setMessage(message);
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        } catch (Exception e) {
            Utils.e("Exception ", String.valueOf(e));//As per sonar lint printstackTrace change to Log
        }
    }

    /**
     * replace container
     *
     * @param fragment
     */
    public void replaceCurrentFrament(android.support.v4.app.Fragment fragment) {
        try {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.container, fragment);
            ft.commit();
        } catch (Exception e) {

        }
    }

    public static void stopProgress() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }
        } catch (Exception e) {
            Utils.e("Exception ", String.valueOf(e));//As per sonar lint printstackTrace change to Log
        }
    }
}
