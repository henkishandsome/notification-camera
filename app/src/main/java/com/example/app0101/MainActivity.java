package com.example.app0101;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button send_notice,camera;
    private final static int NOTIFY_ID = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send_notice=findViewById(R.id.send_notice);
        send_notice.setOnClickListener(this);
        camera=findViewById(R.id.camera);
        camera.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_notice:
//                NotificationManager manager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                Notification notification =new Notification.Builder(MainActivity.this)
//                        .setContentTitle("this is title")
//                        .setContentText("this is text")
//                        .setWhen(System.currentTimeMillis())
//                        .setSmallIcon(R.mipmap.ic_launcher)
//                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
//                        .build();
//
//                manager.notify(1,notification);
                Notification notification;
                NotificationManager manager;
                manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Intent hangIntent = new Intent(this, MainActivity.class);

                PendingIntent hangPendingIntent = PendingIntent.getActivity(this, 1001, hangIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                String CHANNEL_ID = "1234";//应用频道Id唯一值， 长度若太长可能会被截断，
                String CHANNEL_NAME = "android";//最长40个字符，太长会被截断
                notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setContentTitle("这是一个猫头")
                        .setContentText("点我返回应用")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(hangPendingIntent)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .build();

                //Android 8.0 以上需包添加渠道
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                    NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,
                            CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
                    manager.createNotificationChannel(notificationChannel);
                }
                manager.notify(NOTIFY_ID, notification);
            break;
            case R.id.camera:
                startActivity(new Intent(MainActivity.this,CameraActivity.class));
                break;
            default:
                break;
        }
    }
}