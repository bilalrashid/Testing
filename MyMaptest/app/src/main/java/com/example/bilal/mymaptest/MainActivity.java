package com.example.bilal.mymaptest;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notify("You've received new message","kkkkkkkk",222);
            }
        });
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                String Text = "location is: " +

                        "Latitud = " + location.getLatitude() +

                        "Longitud = " + location.getLongitude();
                Location loc1 = new Location("A");


                loc1.setLatitude(33.65617377274548);
                loc1.setLongitude(73.01566243171692);
                double distance = loc1.distanceTo(location);
                TextView txtv=(TextView)findViewById(R.id.loc);
                txtv.setText(""+distance);


                if(distance < 500)
                    Notify("You've received new message","kkkkkkkk",456);
                //double distance=calculateDistance(loc1.getLongitude(),loc1.getLatitude(),loc2.getLongitude(),loc2.getLatitude());
                //Toast.makeText(getApplicationContext(), "distance: " + distance, Toast.LENGTH_LONG).show();
            }

            private double calculateDistance(double fromLong, double fromLat,
                                             double toLong, double toLat) {
                double d2r = Math.PI / 180;
                double dLong = (toLong - fromLong) * d2r;
                double dLat = (toLat - fromLat) * d2r;
                double a = Math.pow(Math.sin(dLat / 2.0), 2) + Math.cos(fromLat * d2r)
                        * Math.cos(toLat * d2r) * Math.pow(Math.sin(dLong / 2.0), 2);
                double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                double d = 6367000 * c;
                return (d);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
              //  Toast.makeText(getApplicationContext(), "status", Toast.LENGTH_LONG).show();
            }

            public void onProviderEnabled(String provider) {
                Toast.makeText(getApplicationContext(), "Enabled", Toast.LENGTH_LONG).show();
            }

            public void onProviderDisabled(String provider) {
                Toast.makeText(getApplicationContext(), "Disabled", Toast.LENGTH_LONG).show();
            }
        };

// Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000*2, 1, locationListener);
    }
    private void Notify(String notificationTitle, String notificationMessage,int id){



//Building the Notification
        Intent notificationIntent = new Intent(this,NotificationView.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,notificationIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.logo1);
        builder.setTicker("Fast University is nearby!");

        long when = System.currentTimeMillis();
        builder.setContentTitle("Fast University is nearby");
        builder.setContentText("Time to learn about notifications!");
        builder.setContentIntent(pendingIntent).setWhen(when).setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.fast));
        builder.setSubText("Tap to view documentation about notifications.");
        builder.setNumber(3);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        String[] events = new String[6];
        events[0] = new String("This is first line....");
        events[1] = new String("This is second line...");
        events[2] = new String("This is third line...");
        events[3] = new String("This is 4th line...");
        events[4] = new String("This is 5th line...");
        events[5] = new String("This is 6th line...");

        // Sets a title for the Inbox style big view
        inboxStyle.setBigContentTitle("Big Title Details:");

        // Moves events into the big view
        for (int i=0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }

      //  builder.setStyle(inboxStyle);
        builder.setVibrate(new long[]{1000, 1000});
        builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);

        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
      /*  NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        @SuppressWarnings("deprecation")

        Notification notification ;
        Intent notificationIntent = new Intent(this,NotificationView.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,notificationIntent, 0);

        long when = System.currentTimeMillis();
        notification = new Notification.Builder(getApplicationContext())
                .setContentTitle(notificationTitle)
                .setContentText(
                        notificationMessage).setSmallIcon( R.drawable.bike)
                .setContentIntent(pendingIntent).setWhen(when).setAutoCancel(true)
                .build();
        notificationManager.notify(9999, notification);*/
    }

    private void turnGPSOn(){
        Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
        intent.putExtra("enabled", true);
        getApplicationContext().sendBroadcast(intent);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
       // Notify("You've received new message", "kkkkkkkk", 267);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Notify("You've received new message","kkkkkkkk",22);
            Location loc1=new Location("A");
            Location loc2=new Location("B");
            loc1.setLatitude(33.678084074533096);
            loc1.setLongitude(73.0217108130455);
            loc2.setLatitude(33.67887198243047);
            loc2.setLongitude(73.02321821451187);
            double distance=loc2.distanceTo(loc1);
          //  turnGPSOn();
            //double distance=calculateDistance(loc1.getLongitude(),loc1.getLatitude(),loc2.getLongitude(),loc2.getLatitude());
            Toast.makeText(getApplicationContext(), "distance: " + distance, Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
