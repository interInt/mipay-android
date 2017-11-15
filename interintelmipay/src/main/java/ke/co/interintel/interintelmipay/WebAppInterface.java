package ke.co.interintel.interintelmipay;

import android.app.Activity;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {
    Activity activity;

    /**
     * Instantiate the interface and set the context
     */
    WebAppInterface(Activity c) {
        activity = c;
    }

    /**
     * Show a toast from the web page
     */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(activity, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void onWindowEvent(String res) {

        Intent returnIntent = new Intent();
        returnIntent.putExtra("message", res);
        activity.setResult(Activity.RESULT_OK, returnIntent);
        activity.finish();

    }
}
