package elsaghier.developer.com.capstoneproject.Models;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by ELSaghier on 1/30/2018.
 */

public class ProgressDialogClass {
    private static ProgressDialog mProgressDialog;

    public static void showProgressDialog(Context context, String tittle, String message) {
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setTitle(tittle);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    public static void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }
}
