package ir.servicea.app;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import ir.servicea.R;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;

public class Utils {
    public static MaterialShowcaseView createCustomShowcaseView(Activity activity, View target, String contentText, String dismissText) {
        MaterialShowcaseView showcaseView = new MaterialShowcaseView.Builder(activity)
                .setTarget(target)
                .setDismissText(dismissText)
                .setDismissOnTouch(true)
                .setContentText(contentText)
                .setDelay(500)
                .build();

        customizeTextSize(showcaseView);
        return showcaseView;
    }

    public static void customizeTextSize(MaterialShowcaseView showcaseView) {
        TextView contentTextView = showcaseView.findViewById(R.id.tv_content);
        TextView dismissTextView = showcaseView.findViewById(R.id.tv_dismiss);
        if (contentTextView != null) {
            contentTextView.setTextSize(16);
            contentTextView.setGravity(Gravity.CENTER);
            contentTextView.setTextColor(Color.WHITE); // Set text color if needed
            dismissTextView.setGravity(Gravity.CENTER);
            dismissTextView.setTextSize(18); // Set your desired text size here
        }
    }
}
