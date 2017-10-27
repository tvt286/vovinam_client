package vn.tranty.vovinam_client.mics;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.tranty.vovinam_client.R;

/**
 * Created by TRUC-SIDA on 10/19/2017.
 */

public class Utils {

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void showToast(String message, Activity context) {
        LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.my_toast, (ViewGroup) context.findViewById(R.id.toast_layout_root));
        TextView text = (TextView) layout.findViewById(R.id.tv_toast);
        text.setText(message);
        Toast toast = new Toast(context.getApplicationContext());
        toast.setGravity(Gravity.BOTTOM,0,80);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
