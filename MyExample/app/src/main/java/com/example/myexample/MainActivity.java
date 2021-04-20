package com.example.myexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.app.*;
import android.content.DialogInterface;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button)findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new MyAlertDialog();
                newFragment.show(getFragmentManager(), "dialog");
            }
        });

    }

    public static class MyAlertDialog extends DialogFragment {

        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setMessage("Do you hate when things like this pop up?");
            builder.setCancelable(false);


            builder.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            // Do whatever yes should do
                            MainActivity myActivity = (MainActivity)getActivity();
                            TextView myView = (TextView)myActivity.findViewById(R.id.textView1);
                            myView.setText("You clicked yes");
                        }
                    });
            builder.setNegativeButton("Not yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            // Do whatever no should do
                            MainActivity myActivity = (MainActivity)getActivity();
                            TextView myView = (TextView)myActivity.findViewById(R.id.textView1);
                            myView.setText("You clicked not yes");
                        }
                    });
            builder.setNeutralButton("IDC",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity myActivity = (MainActivity)getActivity();
                            TextView myView = (TextView)myActivity.findViewById(R.id.textView1);
                            myView.setText("You clicked IDC");
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