package br.com.ladelicedata;

import android.content.Context;
import android.net.ConnectivityManager;

public class ConstantMethod {

    public static boolean verifyConnection(Context context) {
        boolean connected;
        ConnectivityManager conectivtyManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            connected = true;
        } else {
            connected = false;
        }

        return connected;
    }

}
