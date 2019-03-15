package com.example.pratikg.androidpractice.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pratikg.androidpractice.interfaces.OnGetResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;




/**
 * Created by pravina on 26/6/18.
 */

public class Utils {
    private static String NullTag="null";

    public static void setResponse(OnGetResponse onGetResponse, String response) {
        if (onGetResponse != null && response != null) {
            if (response.contains(AppConstants.ERROR_INVALID_CREDENTIALS_RES)) {
                onGetResponse.onGetResponse(response, true);
            } else if (response.contains(AppConstants.ERROR_INVALID_TOKEN_RES)) {
                onGetResponse.onGetResponse(response, true);
            } else if (response.contains(AppConstants.ERROR_GENERIC_ERROR)) {
                onGetResponse.onGetResponse(response, true);
            } else if (response.contains(AppConstants.NETWORK_TIMEOUT_ERROR)) {
                onGetResponse.onGetResponse(response, true);
            }else
                onGetResponse.onGetResponse(response, false);
        } else {
            /*if (dialog != null)
                stopProgress();*/
        }

    }
    public static StringRequest getTimeoutApplied(StringRequest stringRequest) {
        stringRequest = (StringRequest) stringRequest.setRetryPolicy(new
                DefaultRetryPolicy(60 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return stringRequest;
    }public static void e(String tag, String msg) {
        if (!TextUtils.isEmpty(tag) && !TextUtils.isEmpty(msg) && !NullTag.equalsIgnoreCase(tag) && !NullTag.equalsIgnoreCase(msg)) {
            Log.e(tag, msg);
        }
    }
    public static void v(String tag, String msg) {
        if (!TextUtils.isEmpty(tag) && !TextUtils.isEmpty(msg) && !NullTag.equalsIgnoreCase(tag) && !NullTag.equalsIgnoreCase(msg)) {
            Log.v(tag, msg);
        }
    }
    public static String getVolleyError(VolleyError error, Context context) {

        String errorToReturn = "";
        if (context != null && error != null) {
           // stopProgress();
            NetworkResponse errorRes = error.networkResponse;

            if (error instanceof TimeoutError) {
                //errorToReturn = context.getString(R.string.TimeoutError);
                errorToReturn = createErrorObject(errorToReturn);
            } else if (error instanceof NoConnectionError) {

                if (errorRes == null) {
                    errorToReturn = AppConstants.NO_NETWORK;
                    errorToReturn = createErrorObject(errorToReturn);
                } else if (!errorRes.toString().isEmpty() && errorRes.data != null) {
                    switch (errorRes.statusCode) {

                        case 400:
                        case 403:
                        case 404:
                            errorToReturn = AppConstants.NO_NETWORK;
                            errorToReturn = createErrorObject(errorToReturn);
                            break;

                        case 0:
                        case 401:
                        default:
                            errorToReturn = AppConstants.ERROR_INVALID_TOKEN_RES;
                            errorToReturn = createErrorObject(errorToReturn);
                            break;

                    }
                }
            }



            else if (error instanceof NetworkError) {
                errorToReturn = AppConstants.NO_NETWORK;
                errorToReturn = createErrorObject(errorToReturn);
            } else if (error instanceof ParseError) {
                //errorToReturn = context.getString(R.string.ParseError);
                errorToReturn = createErrorObject(errorToReturn);
            } else if (error instanceof AuthFailureError) {
                //errorToReturn = context.getString(R.string.AuthFailureError);
                errorToReturn = createErrorObject(errorToReturn);
            } else {
                if (errorRes != null && errorRes.data != null) {
                    try {
                        errorToReturn = new String(errorRes.data, "UTF-8");
                        if (errorToReturn.contains("Generic error")) {
                            errorToReturn = createErrorObject(errorToReturn);
                        }

                    } catch (UnsupportedEncodingException e) {
                        Log.v("Error Message",e.getMessage());
                    }

                }

            }

        } else {
            errorToReturn = createErrorObject(errorToReturn);// creating empty error
        }
        return errorToReturn;
    } public static String createErrorObject(String response) {
        String error = "";
        try {
            JSONObject jMainObj = new JSONObject();

            JSONObject jErrorObj = new JSONObject();

            if (response.contains("NO NETWORK"))
                jErrorObj.put("errorCode", "1143");
            else
                jErrorObj.put("errorCode", "0");
            jErrorObj.put("errorMessage", response);
            jMainObj.put("error", jErrorObj);

           // Utils.v("error object ", response);

            error = jMainObj.toString();
        } catch (JSONException e) {
            Log.v("Error Message",e.getMessage());

        }


        return error;
    }


}
