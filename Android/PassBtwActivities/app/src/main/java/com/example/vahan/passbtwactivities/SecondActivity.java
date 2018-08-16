package com.example.vahan.passbtwactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button;
    private EditText editText;
    private final String MyKey="info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editText=findViewById(R.id.editTextSecond);
        button=findViewById(R.id.sumbitBtnSecond);
        button.setOnClickListener(this);

        Intent intent = getIntent();
        String info = intent.getStringExtra(MyKey);
        editText.setText(info);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra(MyKey,editText.getText().toString());
        startActivity(intent);
    }
}
