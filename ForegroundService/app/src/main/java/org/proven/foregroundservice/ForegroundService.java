package org.proven.foregroundservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class ForegroundService extends Service {
    public static final String CHANNEL_ID = "ForegroundServiceChannel";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand( Intent intent, int flags, int startId ) {
        String input = intent.getStringExtra( "inputExtra" );
        createNotificationChannel();
        Intent notificationIntent = new Intent( this, MainActivity.class );
        // Funciona amb versions < 31
        // PendingIntent pendingIntent = PendingIntent.getActivity(this,0, notificationIntent, 0);
        PendingIntent pendingIntent = PendingIntent.getActivity( this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE ); // API > 31
        Notification notification = new NotificationCompat.Builder( this, CHANNEL_ID ).setContentTitle( "Foreground Service" ).setContentText( input ).setSmallIcon( R.drawable.ic_launcher_foreground ).setContentIntent( pendingIntent ).build();
        startForeground( 1, notification );
        //do heavy work on a background thread
        //stopSelf();
        return START_NOT_STICKY;
        /*What is the difference between START_STICKY and START_NOT_STICKY
        Both codes are only relevant when the phone runs out of memory and kills the service before it finishes executing.
        START_STICKY tells the OS to recreate the service after it has enough memory and call onStartCommand() again with a null intent.
        START_NOT_STICKY tells the OS to not bother recreating the service again.
        There is also a third code START_REDELIVER_INTENT that tells the OS to recreate the service and redeliver the same intent to onStartCommand().
         */
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind( Intent intent ) {
        return null;
    }

    private void createNotificationChannel() {
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ) {
            NotificationChannel serviceChannel = new NotificationChannel( CHANNEL_ID, "Foreground Service Channel", NotificationManager.IMPORTANCE_DEFAULT );
            NotificationManager manager = getSystemService( NotificationManager.class );
            manager.createNotificationChannel( serviceChannel );
        }
    }
}
