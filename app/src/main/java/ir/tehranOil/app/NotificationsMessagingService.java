package ir.tehranOil.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.RemoteMessage;
import com.pusher.pushnotifications.fcm.MessagingService;

import org.json.JSONException;
import org.json.JSONObject;

import ir.tehranOil.activity.MainActivity;

@SuppressLint({"Registered", "MissingFirebaseInstanceTokenRefresh"})
public class NotificationsMessagingService extends MessagingService {
    private static final String TAG = "NDA";
    private NotificationUtils notificationUtils;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "PUSHER: ");
        Log.d(TAG, "From: " + remoteMessage.toString());
        Log.d(TAG, "From:data: " + remoteMessage.getData().toString());
        if (remoteMessage == null)
            return;
        // Check if message contains a notification payload.
       /* if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Notification Body: " + remoteMessage.getNotification().getBody());
            Log.d(TAG, "Notification tag: " + remoteMessage.getNotification().getTag());
            if(remoteMessage.getNotification().getTag() != null){
                G.sendNotif(remoteMessage.getNotification().getTitle() + "", remoteMessage.getNotification().getBody() + "",remoteMessage.getNotification().getTag() + "");
            }else{
                G.sendNotif(remoteMessage.getNotification().getTitle() + "", remoteMessage.getNotification().getBody() + "");
            }
        }*/

        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Notification Body: " + remoteMessage.getNotification().getBody());
            G.sendNotif(remoteMessage.getNotification().getTitle() + "", remoteMessage.getNotification().getBody() + "");
        }
        // Check if message contains a data payload.
//        if (remoteMessage.getData().size() > 0) {
//            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
//            try {
//                JSONObject json = new JSONObject(remoteMessage.getData().toString());
//                handleDataMessage(json);
//            } catch (Exception e) {
//                Log.e(TAG, "Exception: " + e.getMessage());
//            }
//        }
    }

    private void handleNotification(String title, String content) {
        if (NotificationUtils.isAppIsInBackground(getApplicationContext())) {
            // app is in foreground, broadcast the push message
            Intent pushNotification = new Intent(G.PUSH_NOTIFICATION);
            pushNotification.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            pushNotification.putExtra("message", content);
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

            // play notification sound
            showNotificationMessage(getApplicationContext(), title, content, "", pushNotification);
        }

    }

    private void handleDataMessage(JSONObject json) {
        Log.e(TAG, "push json: " + json.toString());
        try {
            JSONObject data = json.getJSONObject("data");
            String title = G.getValue(data, "title");
            String message = G.getValue(data, "message");
            boolean isBackground = data.getBoolean("is_background");
            String imageUrl = G.getValue(data, "image");
            String timestamp = G.getValue(data, "timestamp");
            JSONObject payload = data.getJSONObject("payload");
            Log.i("Status", "AppIsInBackground");
            Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);

            if (TextUtils.isEmpty(imageUrl)) {
                showNotificationMessage(getApplicationContext(), title, message, timestamp, notificationIntent);
            } else {
                showNotificationMessageWithBigImage(getApplicationContext(), title, message, timestamp, notificationIntent, imageUrl);
            }

        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }

    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
    }

}
