package com.example.chapter27examples;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.net.*;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.webbttn) {
            Uri uri = Uri.parse("http://www.CompuScholar.com");
            Intent viewIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(viewIntent);
        }
        else if (id == R.id.contactsbttn) {
            Toast myMessage = Toast.makeText(getApplicationContext(), "Į̴̤̠̙̱̜̜̫̜̬̟̝͐̒̀̈͊̊͑̈͗̃́͒̆͛̈̀͛̊́̋̈́̈́̒̃̒͆̍̈́̒́̒̇̊̍͗́̃͛̒̐͌͛̌͐̽̈̓͛̂̉̏̏̓̀͘͘̚͘͘̚̕͜͝͝͝͠ ̴̢̢̛̛̛̛̭͇̦̜͈͓̳̤̞̖̗͙̬̘̠̮̤͚̻̱̤̱̓̈́̊͒̌̈̀̾͂́̋͗̀̐̿͋̇̈̀̍̑̇̏͂̑̄͒̈̓̃̊̔͆̈̽̈̑̄̉̓̍̚͘͘͘̕͘͜͝͠͠ͅF̷̨̨̢̛̛̛͙̳͙̭̮̳͈̤̬̠̣̟͓̳̩̹̰̈́̂̾͒̔̈́̈́̐̾̉̀͊́̄̍̃͑͑̊̎̈̏͊̒̇̇̈̍͑̔̏̌̇̽̀̂̐͊͛̉̾́̃̓̆̀͐̈́̌͘̕͘̚̚͘͜͠͝͠͠͠͝ͅͅO̵̡̢̧̢̯̯͖̗̖̝̝̱̤̜͕̘̼̝̻̭̞̻̭̠̦͎͙̤̮̤̳͍̜̭̿͆̐̈͐͊̈́̐͌̔̐̕͜͜ͅͅU̶̢̨̨̨̦̰̻͕̥͈̪̤͙̱̝͈̠̬͚̫̰͖͚̦͖̪̟͉̠̟̞͚͒̌̀̊̃̐͛̾̽̾̔̑̂̇̚ͅͅN̵̢̧̢̧̞͇̫̬̬̘͈̼̩͇͍̺͍̤̟̜̗̲͎͙̹̥̙̙͉͔̤͇̪̦͕̣̞͉͌́̽̿̃̍̈͒̿̿̾̈́̅̕͜͠Ḓ̵̨̢̡͔̯̜̭͋͐̏̈́̏͘͝͝ ̷̧̧̨̢̛̮̠̙͖̹̱͔̟͚̳̦̣̼̣̞͇͈͎̦̤̙̘͈̭̤̖̲͍͆̓̍̂̍̒́͆̒̆̅̈́̀͐̅̉͒͐͋̇͂̀̓͑͗͛͑̉̍̓̏̊̀̄́̎̎̓̾̈́͑̾̀͘͘̕̕͜͝Ý̴̡̡̢̨̫̗͖̤̻͇͚̰̬̙̖̪̘͔̥̟̲͇̱̻̭̹̱̝̭̣̻̭͇͎̝̜̒̃͜ͅǪ̵̢̻̼͔̺̹͖͚͙̬̰̜͔͉̯͈̻̮̪͚͕̙͚̼͊̀͗̏̍̃̊̈͑͋͋̇͒̂͗̕̕̚̕ͅU̶͖̣̳͙̮̮͔̰̜͍̬̙͆̀̒̊͠", Toast.LENGTH_LONG);
            myMessage.setGravity(Gravity.RIGHT, 0, 0);
            myMessage.show();

            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        }
        else if (id == R.id.phonebttn) {
            Uri uri = Uri.parse("tel:1-435-841-0628");
            Intent viewIntent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(viewIntent);
        }
    }
}