package com.example.menuexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Called then menu bar is first created
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();    // Creates a menuInflater object
        mi.inflate(R.menu.menu, menu);  // Uses menuInflater object to get the menu bar file from R.menu

        return true;
    }

    // Called when item is selected from the menu bar
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.actionDelete) {
            Toast t = Toast.makeText(this, "Ỹ̶͓͈͖̩͍̻́̂̏̐͂̏̈́͝ò̶͓̪̳͆̂͌̈́̋͛͛̎͘u̶͖̿͐̐͆́̄́͌̚ ̶̛͙͈̣̭̞̜͕̻̳̠͂̈̊̓C̵̻̩̰̱̖͆̎͛̎͝ļ̶̰̣͎͌͆͛̈͑̽͒̕͜i̸̹̳̞̟̩̤̽̊̾̈͗̚c̴̥̫̰͕̩̮̤͉̩͕̼͂̅́̓̎͗̀̂ͅk̸̝̤̜̜͓̩̭͚̗̄̓̀͛̓͒̇͌̂̚͜͝é̵̢̨̻̦̞̫͚͍͇̈́̇̊͐̕͝ͅͅd̵̜̯͍͊̋̆͗̂̀̅͘ͅ ̴̩̼̺̞̲̳͍̞̍̆̃̈̐͋̒̈́͒̇͜͠D̴̨̧͖̗͙͚̗̤̄̂́̆̈́͐̎͋͐͘̚͠͝ͅẽ̶̟͉̿̓̑͊̀͝l̴̳̣̽̚ẹ̵̡̡̩̘̽͊̄͋̈̓t̴̛̲̮̤͒̇͑̿̐͛̒̒͗̀͋̎̚ę̸̢̛̯̫̙̜̲̫͓͔͉͍̦̓͋̏", Toast.LENGTH_LONG);  // Make toast message
            t.show();   // Show toast message
        }
        else if (item.getItemId() == R.id.actionEdit) {
            Toast t = Toast.makeText(this, "Y̴̧̠̱̯̤̰͍͍̺̻̩̳͇̋͆͌o̵̡͍̤͍̫̲̩̅̽̏̾̈̐͛̀̐̔̽͐̚͠ͅu̴͖̒͑̓̾́̀͑̉͠ ̸̧̜̙̳̟̙̻̦̫̌͛̿͐̂̅͘͝C̴̦̖̙̪̟̱͇̎͝l̷̛̛͚̳͓̣̫͉̤̰̹̺̆͑̔̾̚͜i̴̢͍͔̫̐͗̀͒̉̒́̏͐̊͌̎c̶̡͎̦̗̬̹̗̠͖͌̏̽́̍̒̓̂̾̃̀̾̆͂͜k̶̢̞̹̓́̇̅̑e̸͕̱̞̙̒͒̄͒̿͛̕̚d̶̡̥̩͉̖͍͕͓̯͛̀̕ ̶̢̛͈̦͓̙̬͇̭̤̳̰̃̓̈̔̀̌̾̀͝Ê̶͈͕͔̦͍͉̳͍͔͚͍͆̇̇͊́̍͗̚͝d̴͈̱̘̖̎̄̇́̔̅̌̅̊͌͝i̷̘̰͂͒̀͝ṭ̸̨̡̧̦̩̩̹̬̠̮̖̭̦̺͚̥̰̣̟̤͉͔̍̊̃̑͂̈́͊̆͐̈́͘͘ͅ", Toast.LENGTH_LONG);   // Make toast message
            t.show();   // Show toast message
        }
        return super.onOptionsItemSelected(item);

    }
}