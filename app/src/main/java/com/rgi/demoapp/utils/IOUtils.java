package com.rgi.demoapp.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.android.material.snackbar.Snackbar;
import com.rgi.demoapp.R;
import com.rgi.demoapp.app.BookApp;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class IOUtils {

    private static final String LOG_TAG = "LOG";
    public static final String SHARED_PREF_NAME = "BookApp";
    private static String TAG = "IOUtils";
    private static ProgressDialog proDialog = null;
    static DecimalFormat df2 = new DecimalFormat(".##");
    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    public static final int CAPTURE_IMAGE_REQUEST = 124;


    private static List<Address> addresses = new ArrayList<>();
    public static String regexGSTIN = "^([0]{1}[1-9]{1}|[1-2]{1}[0-9]{1}|[3]{1}[0-7]{1})([a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}[1-9a-zA-Z]{1}[zZ]{1}[0-9a-zA-Z]{1})+$";


    public static boolean is_marshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }



    @SuppressWarnings("deprecation")
    public static void mySnackBar(Context mContext, String message, View view) {

        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                .setAction("", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setDuration(Snackbar.LENGTH_SHORT)
                .setActionTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        // Change the text message color
        (snackbar.getView()).getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        (snackbar.getView()).getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        View mySbView = snackbar.getView();
        mySbView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.black));


        TextView textView = mySbView.findViewById(com.google.android.material.R.id.snackbar_text);
        // We can apply the property of TextView
        textView.setTextColor(mContext.getResources().getColor(R.color.white));
        textView.setSingleLine(false);
        textView.setTextSize(14f);
        textView.setGravity(Gravity.CENTER);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        snackbar.show();
    }


    @SuppressWarnings("deprecation")
    public static void mySnackBarInternet(Context mContext, View view) {
        Snackbar.make(view, mContext.getString(R.string.msg_internet_connection), Snackbar.LENGTH_LONG)
                .setActionTextColor(mContext.getResources().getColor(R.color.black))
                .show();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void startLoadingView(Context context) {
        try {
            if (context != null) {
                if (proDialog == null)
                    proDialog = ProgressDialog.show(context, "", "", true, false);

                proDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
                //  proDialog.setProgressDrawable(context.getDrawable(R.drawable.progress_drawable));
                proDialog.setContentView(R.layout.progress_loader);
                proDialog.getWindow().setGravity(Gravity.BOTTOM);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopLoadingView() {
        try {
            if (proDialog != null)
                proDialog.dismiss();
            proDialog = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startLoadingPleaseWait(Context mContext) {
        try {

            if (mContext != null) {
                if (proDialog == null)
                    proDialog = ProgressDialog.show(mContext, null, mContext.getString(R.string.msg_please_Wait));
                proDialog.setCancelable(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startLoading(Context context, String message) {
        try {
            if (context != null) {
                if (proDialog == null)
                    proDialog = ProgressDialog.show(context, null, message);
                proDialog.setCancelable(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopLoading() {
        try {
            if (proDialog != null)
                proDialog.dismiss();
            proDialog = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isInternetPresent(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            @SuppressLint("MissingPermission") NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }


    public static boolean isInternetConnectivity(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkinfo = connectivity.getActiveNetworkInfo();

        return networkinfo == null ? false : true;
    }

    public static void hideKeyBoard(Context context, EditText myEditText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(myEditText.getWindowToken(), 0);
    }

    public static void hideKeyBoard(Context context, AutoCompleteTextView autoCompleteTextView) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(autoCompleteTextView.getWindowToken(), 0);
    }

    public static void hideKeyBoard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (((Activity) context).getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void myToast(String msg, Context context) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }


    public static void loadImage(Context context, ImageView view, String url, int default_thumb_book_detail) {

        Glide.with(context)
                .load(BookApp.cache.readString(context, Constant.URL,"")+"/"+url)
                .placeholder(default_thumb_book_detail)
                .error(default_thumb_book_detail)
                .into(view);
    }


    public static boolean checkPermissions(Context context, String permission) {
        int permissionState = ActivityCompat.checkSelfPermission(context,
                /*Manifest.permission.ACCESS_FINE_LOCATION*/permission);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean checkNotNullEmpty(String param1) {
        return (param1 != null && !param1.isEmpty());
    }


    private static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    public static boolean isConnected(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnected());
    }

    public static void showSnackBar(Activity context, String msg) {
        //CommonUtils.hideKeyboard(context);
        View parentLayout = context.findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(parentLayout, msg, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(context.getResources().getColor(R.color.darkgreen));
        snackbar.show();
    }
    public static void errorShowSnackBar(Activity context, String msg) {
        //CommonUtils.hideKeyboard(context);
        View parentLayout = context.findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(parentLayout, msg, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(context.getResources().getColor(R.color.red));
        snackbar.show();
    }

    /**
     * Alert message
     *
     * @param mContext
     * @param title
     * @param message
     */
    public static void showAlertDialog(Context mContext, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(mContext.getString(R.string.str_ok), (dialog, which) -> {
                    // continue with delete
                    dialog.cancel();
                });
        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
        dialog.show();

        //.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
    }

    public static String getAppVersion(Context context) {
        String version = null;
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }

    public static void showKeyBoard(Context context ) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
    }



}
