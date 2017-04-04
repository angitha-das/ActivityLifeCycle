package com.example.angithadas.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    EditText editText;
    EditText editText1;
    String s1;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this,"ON CREATE_MAIN", Toast.LENGTH_SHORT).show();

        editText = (EditText) findViewById(R.id.edit_text);
        editText1 = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.text);

        Intent intent = getIntent();
        if(intent != null) {
            s1 = intent.getStringExtra("key1");
            textView.setText(s1);
        }

        Intent in = getIntent();
        if(in != null) {
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
        Toast.makeText(this,"ON START_MAIN", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"ON RESUME_MAIN", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"ON PAUSE_MAIN", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this,"ON RESTART_MAIN", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"ON STOP_MAIN", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"ON DESTROY_MAIN", Toast.LENGTH_SHORT).show();
    }

    public void clicked(View view){
        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("key",editText.getText().toString());
        startActivity(i);
    }

    public void start_activity_for_result(View view){
        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("MESSAGE",editText1.getText().toString());
        startActivityForResult(i,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            String message=data.getStringExtra("MESSAGE");
            editText1.setText(message);
        }
    }
}
