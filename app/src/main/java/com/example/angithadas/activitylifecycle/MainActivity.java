package com.example.angithadas.activitylifecycle;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText editText1;
    EditText editText2;
    EditText phone;
    String s1;
    TextView textView;
    ImageView imageView;
    TextView textViewfilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);

        Toast.makeText(this, "ON CREATE_MAIN", Toast.LENGTH_SHORT).show();

        textViewfilter = (TextView) findViewById(R.id.filterText);
        editText = (EditText) findViewById(R.id.edit_text);
        editText1 = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        textView = (TextView) findViewById(R.id.text);
        phone = (EditText) findViewById(R.id.phone);
        imageView = (ImageView) findViewById(R.id.image);


        Intent intent_implicit = getIntent();
        String action = intent_implicit.getAction();
        if (!action.equals(Intent.ACTION_VIEW)) {
            throw new RuntimeException("Should not happen");
        }
        Uri data = intent_implicit.getData();

        try {
            URL  url = new URL(data.getScheme(), data.getHost(), data.getPath());
            editText2.setText(url.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //======================================
        Intent intent = getIntent();
        if (intent != null) {
            s1 = intent.getStringExtra("key1");
            textView.setText(s1);
        }

        Intent in = getIntent();
        if (in != null) {
            Bundle b = in.getExtras();
            if (b != null) {
                String nameString = b.getString("name");
                long phoneNumberLong = b.getLong("phoneNumber");
                String phoneNumberString = Long.toString(phoneNumberLong);
                double ageDouble = b.getDouble("age");
                String ageString = Double.toString(ageDouble);
                boolean isMarriedBoolean = b.getBoolean("married");

                textView.setText("Name : " + nameString + "\n" +
                        "Phone number : " + phoneNumberString + "\n" + "Age : " + ageString
                        + "\n" + "Is married : " + isMarriedBoolean);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "ON START_MAIN", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "ON RESUME_MAIN", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "ON PAUSE_MAIN", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "ON RESTART_MAIN", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "ON STOP_MAIN", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "ON DESTROY_MAIN", Toast.LENGTH_SHORT).show();
    }

    public void clicked(View view) {
        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("key", editText.getText().toString());
        startActivity(i);
    }

    public void start_activity_for_result(View view) {
        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("MESSAGE", editText1.getText().toString());
        startActivityForResult(i, 1);
    }

    public void implicit(View view) {
        Intent implicit_intent = new Intent(Intent.ACTION_VIEW);
        implicit_intent.setData(Uri.parse(editText2.getText().toString()));
        startActivity(implicit_intent);
    }

    public void readContact(View view) {
        Intent readContact = new Intent();
        readContact.setAction(Intent.ACTION_VIEW);
        readContact.setData(ContactsContract.Contacts.CONTENT_URI);
        startActivity(readContact);
    }

    public void makeCall(View view) {
        String uri = "tel:" + phone.getText();
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        startActivity(callIntent);
    }

    public void sendSms(View view){
        Intent sms=new Intent();
        sms.setAction(Intent.ACTION_VIEW);
        sms.setData(Uri.parse("smsto:"+phone.getText().toString()));
        startActivity(sms);
    }

    public void camera(View view){
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(cameraIntent, 3);
    }

    public void gallery(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            String message=data.getStringExtra("MESSAGE");
            editText1.setText(message);
        }else if (requestCode == 2 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else  if (requestCode == 3 && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }
}
