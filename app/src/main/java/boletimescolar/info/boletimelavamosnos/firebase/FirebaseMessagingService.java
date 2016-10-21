package boletimescolar.info.boletimelavamosnos.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

import boletimescolar.info.boletimelavamosnos.R;
import boletimescolar.info.boletimelavamosnos.view.activities.MainActivity;

/**
 * Created by Norb7492 on 14/10/2016.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService  {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(this);
        noBuilder.setContentTitle("Boletim Escolar");
        noBuilder.setContentText(remoteMessage.getNotification().getBody());
        noBuilder.setAutoCancel(true);
        noBuilder.setSmallIcon(R.mipmap.ic_launcher);
        noBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,noBuilder.build());

    }


}
