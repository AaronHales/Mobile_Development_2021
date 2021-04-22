package com.example.myexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.*;
import android.content.DialogInterface;
import android.view.View;
import android.widget.*;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button)findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimeDialog();
                timePicker.show(getFragmentManager(), "Time");
//                DialogFragment datePicker = new DateDialog();
//                datePicker.show(getFragmentManager(), "Date");
//                DialogFragment listFrag = new AlertDialogList();
//                listFrag.show(getFragmentManager(), "list dialog");
//                DialogFragment newFragment = new MyAlertDialog();
//                newFragment.show(getFragmentManager(), "dialog");
            }
        });
    }

    public static class TimeDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR);
            int min = c.get(Calendar.MINUTE);
            int sec = c.get(Calendar.SECOND);

            return new TimePickerDialog(getActivity(), this, hour, min, false);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            MainActivity main = (MainActivity)getActivity();
            TextView text = (TextView)main.findViewById(R.id.textView1);
            text.setText(hourOfDay + ":" + minute);
        }
    }

    public static class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            MainActivity main = (MainActivity)getActivity();
            TextView text = (TextView)main.findViewById(R.id.textView1);
            text.setText(year + "/" + month + "/" + dayOfMonth);
        }
    }

    public static class AlertDialogList extends DialogFragment {
        String[] tips = {"10%", "15%", "20%", ".02¢"};
        boolean[] checked = {false, true, true, false};

        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("How much do you want to tip?");
            // Use setItems for single choice list
            // Use setSingleChoiceItems for radio button list
            // Use setMultiChoiceItems for multiple choice list
//            builder.setItems(tips,
            builder.setSingleChoiceItems(tips, 2,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            String tip = tips[id];
                            MainActivity mainActivity = (MainActivity)getActivity();
                            TextView text = (TextView)mainActivity.findViewById(R.id.textView1);
                            text.setText("I will tip " + tip);
                        }
                    });
                    builder.setPositiveButton("Submit",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    return;
                                }
                            });
            return builder.create();
        }
    }

    public static class AlertDialog2 extends DialogFragment {

        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("This is another alert dialog");
            builder.setCancelable(true);

            builder.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity myActivity = (MainActivity)getActivity();
                            TextView myView = (TextView)myActivity.findViewById(R.id.textView1);
                            myView.setText("Yes it is another alert dialog");
                        }
                    });

            return builder.create();
        }
    }

    public static class MyAlertDialog extends DialogFragment {

        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setMessage("Do you hate when things like this pop up?");
            builder.setCancelable(false);

            builder.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            // Do whatever yes should do
                            MainActivity myActivity = (MainActivity)getActivity();
                            TextView myView = (TextView)myActivity.findViewById(R.id.textView1);
                            myView.setText("You clicked YES");
                        }
                    });

            builder.setNegativeButton("NOT YES",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            // Do whatever no should do
                            MainActivity myActivity = (MainActivity)getActivity();
                            TextView myView = (TextView)myActivity.findViewById(R.id.textView1);
                            myView.setText("You clicked NOT YES");
                        }
                    });

            builder.setNeutralButton("I DON'T CARE",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
//                            MainActivity myActivity = (MainActivity)getActivity();
//                            TextView myView = (TextView)myActivity.findViewById(R.id.textView1);
//                            myView.setText("You clicked I DON'T CARE");
                            DialogFragment newFragment = new AlertDialog2();
                            newFragment.show(getFragmentManager(), "dialog");
                        }
                    });

            return builder.create();
        }

//        public class PositiveResponse implements DialogInterface.OnClickListener{
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        }

    }

}