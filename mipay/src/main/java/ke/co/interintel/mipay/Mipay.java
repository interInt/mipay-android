package ke.co.interintel.mipay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

import static ke.co.interintel.mipay.Constants.MIPAY_APP;
import static ke.co.interintel.mipay.Constants.MIPAY_HOST;

/**
 * Created by danleyb2 on 10/13/17.
 */

public class Mipay {
    public static final int REQUEST_CODE_MIPAY_PAYMENT = 12;

    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static void checkout(Activity activity, String reference) {
        Intent i;
        String httpUrl = "https://" + MIPAY_HOST + "/checkout/?reference=" + reference;

        if (isAppInstalled(activity, MIPAY_APP)) {
            i = new Intent();
            i.setClassName(MIPAY_APP, MIPAY_APP + ".SplashActivity");
        } else {
            i = new Intent(activity, WebViewActivity.class);
        }

        i.setData(Uri.parse(httpUrl.toString()));


        activity.startActivityForResult(i, REQUEST_CODE_MIPAY_PAYMENT);

    }

    private static String[] toStringA(int[] intArray) {
        String strArray[] = new String[intArray.length];

        for (int i = 0; i < intArray.length; i++)
            strArray[i] = String.valueOf(intArray[i]);

        return strArray;
    }

    public static void order(Activity activity, int[] cartItems) {
        String cartItemsS = TextUtils.join(", ", toStringA(cartItems));
        Intent i;
        String httpUrl = "https://" + MIPAY_HOST + "/order/?cart_items=" + cartItemsS;

        if (isAppInstalled(activity, MIPAY_APP)) {
            i = new Intent();
            i.setClassName(MIPAY_APP, MIPAY_APP + ".SplashActivity");
        } else {
            i = new Intent(activity, WebViewActivity.class);
        }

        i.setData(Uri.parse(httpUrl.toString()));


        activity.startActivityForResult(i, REQUEST_CODE_MIPAY_PAYMENT);

    }

    public static void sale(Activity activity, int productItemID, int quantity) {
        Intent i;
        String httpUrl = "https://" + MIPAY_HOST + "/order/?product_item_id=" + String.valueOf(productItemID) + "\"&quantity=" + String.valueOf(quantity);

        if (isAppInstalled(activity, MIPAY_APP)) {
            i = new Intent();
            i.setClassName(MIPAY_APP, MIPAY_APP + ".SplashActivity");
        } else {
            i = new Intent(activity, WebViewActivity.class);
        }

        i.setData(Uri.parse(httpUrl.toString()));


        activity.startActivityForResult(i, REQUEST_CODE_MIPAY_PAYMENT);

    }
}
