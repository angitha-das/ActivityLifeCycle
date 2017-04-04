package com.example.angithadas.activitylifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    EditText editText;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toast.makeText(this,"ON CREATE_SECOND", Toast.LENGTH_SHORT).show();

        textView = (TextView) findViewById(R.id.text_view);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.edit_text);

        Intent intent = getIntent();
        if(intent != null) {
            s = intent.getStringExtra("key");
            textView.setText(s);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"ON START_SECOND", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"ON RESUME_SECOND", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"ON PAUSE_SECOND", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this,"ON RESTART_SECOND", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"ON STOP_SECOND", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"ON DESTROY_SECOND", Toast.LENGTH_SHORT).show();
    }

    public void ok(View view){
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("key1",s+" Welcome");
        startActivity(i);
    }

    public void bundle(View view){
        Bundle b = new Bundle();
        b.putString("name","Angitha");
        b.putLong("phoneNumber",1234567890);
        b.putDouble("age", 22);
        b.putBoolean("married",false);

        Intent in = new Intent(this, MainActivity.class);
        in.putExtras(b);
        startActivity(in);
    }
    public void submit(View view){
        String message = editText.getText().toString();
        Intent intent=new Intent();
        intent.putExtra("MESSAGE",message);
        setResult(2,intent);
        finish();
    }
}
