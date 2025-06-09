package ir.servicea.app;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * @author haniye94 .
 * @since on 6/8/2025.
 */
public class CustomFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String token) {
        Log.d("messaging", "onNewToken: " + token);
        super.onNewToken(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage message) {
        super.onMessageReceived(message);
        Log.d("messaging", "onMessageReceived: " + message.getMessageId() + ", " + message.getSentTime());
        Log.d("messaging", message.getData().toString());
        // Handle the received message
        if (message.getNotification() != null) {
            String title = message.getNotification().getTitle();
            String body = message.getNotification().getBody();
            // Show notification or handle as needed
        }
    }

}
