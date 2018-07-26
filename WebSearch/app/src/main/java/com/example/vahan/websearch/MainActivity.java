package com.example.vahan.websearch;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.url);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String urlString = String.valueOf(editText.getText());
        if (urlString.substring(0, 8).equals(getString(R.string.https))
                || urlString.substring(0, 7).equals(getString(R.string.http))) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
            startActivity(intent);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Invalid URL!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
