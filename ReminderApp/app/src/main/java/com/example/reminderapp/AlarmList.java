package com.example.reminderapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AlarmList extends ListActivity {

    // Create an array of all AlarmKeeper objects
    ArrayList<AlarmKeeper> myAlarmKeepers = new ArrayList<AlarmKeeper>();

    // Create an array of all Alarm names
    ArrayList<String> myAlarmDisplayStrings = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // populate memory arrays from alarms saved to disk
        loadAlarms();

        // Re-populate the display list of alarms
        buildAlarmDisplayStrings();
        ListView lv = (ListView)getListView();
        registerForContextMenu(lv);
    }

    public void buildAlarmDisplayStrings() {
        // Clear the strings array
        myAlarmDisplayStrings.clear();
        // If we do not have any alarms set
        if (myAlarmKeepers.isEmpty()) {
            myAlarmDisplayStrings.add("You have no alarms set");
        }
        else {
            // Put each alarm name in the display string array
            for (AlarmKeeper alarm: myAlarmKeepers) {
                myAlarmDisplayStrings.add(alarm.alarmName);
            }
        }

        // Create an ArrayAdapter using our new display strings array
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, myAlarmDisplayStrings);

        // Set the ArrayAdapter to our list
        setListAdapter(myAdapter);
    }

    public void loadAlarms() {
        // All file operations must be placed in a try/catch block
        try {
            // Open the alarms.txt file and place it in an InputStreamReader object
            InputStreamReader isr = new InputStreamReader(openFileInput("alarms.txt"));

            // Place the contents in a BufferedReader object to make reading easier
            BufferedReader buffreader = new BufferedReader(isr);

            // Read in the data, line-by-line
            String line = buffreader.readLine();

            // While we still have valid data
            while (line != null) {
                StringTokenizer st = new StringTokenizer(line, ",");

                AlarmKeeper newAlarm = new AlarmKeeper();
                newAlarm.alarmName = st.nextToken();
                newAlarm.alarmDay = Integer.parseInt(st.nextToken());
                newAlarm.alarmMonth = Integer.parseInt(st.nextToken());
                newAlarm.alarmYear = Integer.parseInt(st.nextToken());
                newAlarm.alarmHour = Integer.parseInt(st.nextToken());
                newAlarm.alarmMinute = Integer.parseInt(st.nextToken());
                newAlarm.alarmDesc = st.nextToken();
                newAlarm.setReqCode(Integer.parseInt(st.nextToken()));

                myAlarmKeepers.add(newAlarm);

                // Read in the next line in the file
                line = buffreader.readLine();
            }

            // Close the BufferedReader object
            buffreader.close();
        }
        catch (Exception e) {
            // file error
            Toast t = Toast.makeText(this, "Something went wrong.\nError Code:\n" + e, Toast.LENGTH_LONG);
            t.show();
        }
    }

    public void saveAlarms() {
        // File operations must be done in a try/catch block
        try {
            // Open the file and assign it to an output stream writer
            OutputStreamWriter osr = new OutputStreamWriter(openFileOutput("alarms.txt",  MODE_PRIVATE));

            // Figure out which character is an endline on the current device
            String endLine = System.getProperty("line.separator");

            for (int i = 0; i < myAlarmKeepers.size(); i++) {
                AlarmKeeper alarm = myAlarmKeepers.get(i);
                osr.write(alarm.alarmName + ",");
                osr.write(alarm.alarmDay+ ",");
                osr.write(alarm.alarmMonth + ",");
                osr.write(alarm.alarmYear + ",");
                osr.write(alarm.alarmHour + ",");
                osr.write(alarm.alarmMinute + ",");
                osr.write(alarm.alarmDesc + ",");
                osr.write(alarm.getReqCode() + ",");
                osr.write(endLine);
            }

            // Make sure all data has been sent out to the file
            osr.flush();

            // Close the file
            osr.close();
        }
        catch (Exception e) {
            // file error
            Toast t = Toast.makeText(this, "Something went wrong.\nError Code:\n" + e, Toast.LENGTH_LONG);
            t.show();
        }
    }

    // This method is completed by the student
    // This method is called when a sure clicks on a menu item
    public boolean onOptionsItemSelected(MenuItem item) {
        // If the user clicked the "Add" button
        if (item.getItemId() == R.id.actionAdd) {
            // Create a new Intent to move to the AddAlarm screen
            Intent addIntent = new Intent(this, AddAlarm.class);

            // Add blank information to tell the AddAlarm screen
            // that we aer adding a new alarm
            addIntent.putExtra("alarm", "");
            addIntent.putExtra("index", -1);

            //Start the new Activity
            startActivityForResult(addIntent, 0);
        }
        return super.onOptionsItemSelected(item);
    }

    // This method is completed by the student
    // This method is called when the program need to create a Context menu
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.contextmenu, menu);
    }

    // This method is completed by the student
    // This method is called when the sure clicks on a context menu item
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.actionDelete) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
            int index = info.position;
            if (index >= 0 && index < myAlarmKeepers.size()) {
                AlarmKeeper alarm = myAlarmKeepers.get(index);
                alarm.cancelAlarm(this);
                myAlarmKeepers.remove(index);
                myAlarmDisplayStrings.remove(index);
                saveAlarms();
                buildAlarmDisplayStrings();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    // This method is completed by the student
    // This method is called when the program needs to create an ActionBar menu
    public boolean onCreateOptionsMenu (Menu menu) {
        // Get the MenuInflater object from the Android system
        MenuInflater mi = getMenuInflater();
        // "inflate" the menu using our actionbar_menu layout XML
        mi.inflate(R.menu.actionbar, menu);
        return true; // all done!
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        if ((position >= 0) && (position < myAlarmKeepers.size())) {
            // If the user clicks on an existing item, create a String
            // out of the alarm information and pass it in a new Intent
            // to the AddAlarm screen
            AlarmKeeper alarm = myAlarmKeepers.get(position);
            Intent editIntent = new Intent(this, AddAlarm.class);
            editIntent.putExtra("alarm", alarm.buildString());
            editIntent.putExtra("index", position);
            startActivityForResult(editIntent, 0);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // If there is Intent data returned to us
        if (data != null) {
            // this screen launched by response from AddAlarm screen

            // If the Intent has Extra data
            Bundle extras = data.getExtras();
            if (extras != null) {
                // Get the Alarm values and the index value
                String  alarmString = extras.getString("alarm");
                int index = extras.getInt("index");

                // If we have valid alarm values
                if (alarmString != null) {
                    // The alarm has changed, so we need a new AlarmKeeper object
                    AlarmKeeper changedAlarm = new AlarmKeeper();

                    // Copy the alarm values into our new AlarmKeeper
                    changedAlarm.fromString(alarmString);
                    if (index >= 0) {
                        // Update existing alarm in our memory arrays
                        myAlarmKeepers.set(index, changedAlarm);
                        changedAlarm.cancelAlarm(this);
                    }
                    else {
                        // Add new alarm to our memory arrays
                        index = myAlarmKeepers.size();  // This new alarm's index is the last slot in the array
                        myAlarmKeepers.add(changedAlarm);
                    }
                    changedAlarm.setAlarm(this);

                    saveAlarms();   // save modified list to disk
                }
            }
        }
        // now, regardless of whether or not this screen was launched by the user
        // or as a result of a new or updated alarm edit, we need to re-populate the
        // display list
        buildAlarmDisplayStrings();
    }

}