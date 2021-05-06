package com.example.chapter27examples;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.net.*;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent implicitintent = new Intent();
//        static PendingIntent getActivity(Context context, int requestCode, Intent intent, int flags);
        PendingIntent pendIntent = PendingIntent.getActivity(getApplicationContext(), 0, implicitintent, 0);

        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);    // Set the icon that appears in the status bar with the notification.
        builder.setTicker("Notify!");   // The text that appears in the status bar at the top of the screen when the notification is first displayed.
        builder.setContentTitle("You are Notified!");   // Set the title of the notification.
        builder.setContentText("Į̴̤̠̙̱̜̜̫̜̬̟̝͐̒̀̈͊̊͑̈͗̃́͒̆͛̈̀͛̊́̋̈́̈́̒̃̒͆̍̈́̒́̒̇̊̍͗́̃͛̒̐͌͛̌͐̽̈̓͛̂̉̏̏̓̀͘͘̚͘͘̚̕͜͝͝͝͠ ̴̢̢̛̛̛̛̭͇̦̜͈͓̳̤̞̖̗͙̬̘̠̮̤͚̻̱̤̱̓̈́̊͒̌̈̀̾͂́̋͗̀̐̿͋̇̈̀̍̑̇̏͂̑̄͒̈̓̃̊̔͆̈̽̈̑̄̉̓̍̚͘͘͘̕͘͜͝͠͠ͅF̷̨̨̢̛̛̛͙̳͙̭̮̳͈̤̬̠̣̟͓̳̩̹̰̈́̂̾͒̔̈́̈́̐̾̉̀͊́̄̍̃͑͑̊̎̈̏͊̒̇̇̈̍͑̔̏̌̇̽̀̂̐͊͛̉̾́̃̓̆̀͐̈́̌͘̕͘̚̚͘͜͠͝͠͠͠͝ͅͅO̵̡̢̧̢̯̯͖̗̖̝̝̱̤̜͕̘̼̝̻̭̞̻̭̠̦͎͙̤̮̤̳͍̜̭̿͆̐̈͐͊̈́̐͌̔̐̕͜͜ͅͅU̶̢̨̨̨̦̰̻͕̥͈̪̤͙̱̝͈̠̬͚̫̰͖͚̦͖̪̟͉̠̟̞͚͒̌̀̊̃̐͛̾̽̾̔̑̂̇̚ͅͅN̵̢̧̢̧̞͇̫̬̬̘͈̼̩͇͍̺͍̤̟̜̗̲͎͙̹̥̙̙͉͔̤͇̪̦͕̣̞͉͌́̽̿̃̍̈͒̿̿̾̈́̅̕͜͠Ḓ̵̨̢̡͔̯̜̭͋͐̏̈́̏͘͝͝ ̷̧̧̨̢̛̮̠̙͖̹̱͔̟͚̳̦̣̼̣̞͇͈͎̦̤̙̘͈̭̤̖̲͍͆̓̍̂̍̒́͆̒̆̅̈́̀͐̅̉͒͐͋̇͂̀̓͑͗͛͑̉̍̓̏̊̀̄́̎̎̓̾̈́͑̾̀͘͘̕̕͜͝Ý̴̡̡̢̨̫̗͖̤̻͇͚̰̬̙̖̪̘͔̥̟̲͇̱̻̭̹̱̝̭̣̻̭͇͎̝̜̒̃͜ͅǪ̵̢̻̼͔̺̹͖͚͙̬̰̜͔͉̯͈̻̮̪͚͕̙͚̼͊̀͗̏̍̃̊̈͑͋͋̇͒̂͗̕̕̚̕ͅU̶͆̀̒̊͠");    // Set the description of the notification.
        builder.setContentIntent(pendIntent);   // Add the pending intent to our notification.
        builder.setAutoCancel(true);    // Makes the notification auto-clear when the user clicks on it.

        Notification notify = builder.build();

        NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        nm.notify(0, notify);
    }
}