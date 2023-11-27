package com.taras_overmind.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.R.layout;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.taras_overmind.myapplication.ContactModel;
import com.taras_overmind.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity2 extends AppCompatActivity {
    Button all_contacts_btn, selected_contacts_btn;
    ListView lv_contactsList;
    ArrayAdapter contactsArrayAdapter;

    Pattern pattern = Pattern.compile(".*7$", Pattern.CASE_INSENSITIVE);
    Matcher matcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        all_contacts_btn = findViewById(R.id.all_contacts_btn);
        selected_contacts_btn = findViewById(R.id.selected_contacts_btn);
        lv_contactsList = findViewById(R.id.lv_contactsList);

        all_contacts_btn.setOnClickListener(view -> {

            contactsArrayAdapter = new ArrayAdapter<>(MainActivity2.this, layout.simple_list_item_1, getPhoneContactsList());
            lv_contactsList.setAdapter(contactsArrayAdapter);
        });

        selected_contacts_btn.setOnClickListener(view -> {

            contactsArrayAdapter = new ArrayAdapter<ContactModel>(MainActivity2.this, layout.simple_list_item_1 , getSortedContactsList());
            lv_contactsList.setAdapter(contactsArrayAdapter);

        });

    }

    private List<ContactModel> getPhoneContactsList(){

        List<ContactModel> returnList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity2.this, new String[] {Manifest.permission.READ_CONTACTS}, 0);
        }

        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        Cursor cursor = contentResolver.query(uri, null, null, null, null);

        String contactName = null;
        String contactNumber = null;
        String contactAddress = null;

        while (cursor.moveToNext()) {
             contactName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
             contactNumber = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));

            // Query for postal addresses for the current contact
            Uri postalUri = ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI;
            Cursor postalCursor = contentResolver.query(postalUri, null, ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID + " = ?",
                    new String[]{String.valueOf(cursor.getLong(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)))}, null);

             contactAddress = null;
            while (postalCursor.moveToNext()) {
                contactAddress = postalCursor.getString(postalCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS));
            }
            postalCursor.close();

            String contactMail = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.ADDRESS));

            ContactModel contact = new ContactModel(contactName, contactNumber, contactAddress);
            returnList.add(contact);

            // Log contact details
            Log.i("CONTACT_PROVIDER", "Contact Name: " + contactName + " ContactPhone: " + contactNumber + " ContactAddress: " + contactAddress + " ContactMail: " + contactMail);
        }

        cursor.close();
        return returnList;
    }

    private List<ContactModel> getSortedContactsList(){

        List<ContactModel> returnList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity2.this, new String[] {Manifest.permission.READ_CONTACTS}, 0);
        }

        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        Cursor cursor = contentResolver.query(uri, null, null, null, null);

        String contactName = null;
        String contactNumber = null;
        String contactAddress = null;


        while (cursor.moveToNext()) {
             contactName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
             contactNumber = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));

            // Query for postal addresses for the current contact
            Uri postalUri = ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI;
            Cursor postalCursor = contentResolver.query(postalUri, null, ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID + " = ?",
                    new String[]{String.valueOf(cursor.getLong(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)))}, null);

             contactAddress = null;
            while (postalCursor.moveToNext()) {
                contactAddress = postalCursor.getString(postalCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS));
            }
            postalCursor.close();

            String contactMail = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.ADDRESS));

            // Apply sorting logic (you may need to adjust this part based on your sorting criteria)
            matcher = pattern.matcher(contactNumber);
            if (matcher.find()) {
                ContactModel contact = new ContactModel(contactName, contactNumber, contactAddress);
                returnList.add(contact);
            }

            // Log contact details
            Log.i("CONTACT_PROVIDER", "Contact Name: " + contactName + " ContactPhone: " + contactNumber + " ContactAddress: " + contactAddress + " ContactMail: " + contactMail);
        }

        cursor.close();
        return returnList;
    }
}