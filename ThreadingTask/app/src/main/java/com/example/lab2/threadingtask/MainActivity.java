package com.example.lab2.threadingtask;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // made a variable static final TAG,
    // final = when do not want others to change its value
    // static = can be accessed by all instances(objects in a class) in a single class, not in any other class.

    // write this tag in log search, then will show regarding to this
    private static final String TAG = "MainActivity";
    // handler associates with thread
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // final, so that can access in other methods as well
        final Button button = (Button) findViewById(R.id.btn1);
        final Button button2 = (Button) findViewById(R.id.btn2);

        // make object of handler
        handler = new Handler();

        // Runnable method implements this task

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            // Change the text of Button through this background thread.
            public void onClick(View v) {
                // we post here, button bcz we want this UI to get the update from thread in background
                // bcz working through handlers in main UI thread is wrong practise
                button.post(new Runnable() {
                    @Override
                    // this implementation is called when task is run
                    public void run() {
                        // info log
                        Log.i(TAG, "test run");
                        Toast.makeText(MainActivity.this, "Without delay, Task perform", Toast.LENGTH_SHORT).show();
                        // UI thread gets the thread from background thread
                        button.setText("Test Button");

                    }
                });
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // for task to perform after some delay, this postDelayed is called.
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "test run after 5 seconds delay");
                        Toast.makeText(MainActivity.this, "With delay, Task perform", Toast.LENGTH_SHORT).show();
                    }
                    // second argument is taken 5000ms, for 5 seconds.
                }, 5000);

                // AsyncTask is made here for tasks to do in background.
                // Void is class here, so V is capital. For null values, it is used.
                // class here bcz can use any data type here.
//                new AsyncTask<Void, Void, Void>() {
//
//                    @Override
//                    protected Void doInBackground(Void... params) {
//                        Log.i(TAG,"This task is performed in Background.");
//                        return null;
//                    }
//
//                    @Override
//                    protected void onPostExecute(Void aVoid) {
//                        super.onPostExecute(aVoid);
//                    }
//
//                    // can write to execute here as well in this line for run the background tasks.
//                }.execute();

                new AsyncTask<A, B, C>() {

                    @Override // doInBackground has A class type argument / object
                    // its return type is C type object and goes in onPostExecute
                    protected C doInBackground(A... params) {
                        // params.length = check length how many
                        return null;
                    }

                    @Override
                    // it gets the values here for updating...
                    protected void onProgressUpdate(B... values) {
                        super.onProgressUpdate(values);
                    }

                    @Override
                    // the C class object is returned here from doInBackground
                    protected void onPostExecute(C c) {
                        super.onPostExecute(c);
                    }

                    // pass A class here in execute
                }.execute(new A());
            }
        });
    }

    // Three empty classes for checking where these are placed in AsyncTask.
    class A {}
    class B {}
    class C {}
}