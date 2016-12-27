package com.example.lab2.sqliteclasssample;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.lab2.sqliteclasssample.Database.DataBaseHandler;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DataBaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DataBaseHandler(this);
        insertContacts();

//        LEARNING ASYNC TASK
//        final TextView textView = (TextView) findViewById(R.id.text);
//        String s = "Hello ";
//
//        new AsyncTask<String,Void,String>() {
//
//            @Override
//            protected String doInBackground(String... params) {
//
//                String s = params[0];
//                s = s + "test";
//                return s;
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                textView.setText(s);
//            }
//
//        }.execute(s);

        /**
         * CRUD Operations
         * */
        // Inserting Contacts
//        Log.d("Insert: ", "Inserting ..");
//        db.addContact(new Contact("Ravi", "9100000000"));
//        db.addContact(new Contact("Srinivas", "9199999999"));
//        db.addContact(new Contact("Tommy", "9522222222"));
//        db.addContact(new Contact("Karthik", "9533333333"));
//
//        // Reading all contacts
//        Log.d("Reading: ", "Reading all contacts..");
//        List<Contact> contacts = db.getAllContacts();
//
//        for (Contact cn : contacts) {
//            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
//            // Writing Contacts to log
//            Log.d("Name: ", log);
//        }
    }

    private void insertContacts() {
        Contact contact = new Contact(0,"test","123456-");
        for (int i = 0; i <10; i++) {
            contact._name = contact._name + i;
            contact._phone_number = contact._phone_number + i;
            long id = db.addContact(contact);
            Log.i(TAG, "insertContacts : " + id);

            Contact contact1 = db.getContact(id);
            Log.i(TAG, "read : " + contact1._name);
            db.addContact(contact);
        }
    }
}