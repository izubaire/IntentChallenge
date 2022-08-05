package com.example.intentchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateContact extends AppCompatActivity implements View.OnClickListener{

    EditText etName, etNumber, etAddress, etWeb;
    ImageView ivHappy, ivOk, ivSad;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        etAddress = findViewById(R.id.etAddress);
        etWeb = findViewById(R.id.etWeb);

        ivHappy = findViewById(R.id.ivHappy);
        ivOk = findViewById(R.id.ivOk);
        ivSad = findViewById(R.id.ivSad);

        ivSad.setOnClickListener(this);
        ivHappy.setOnClickListener(this);
        ivOk.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        if (etName.getText().toString().isEmpty() || etNumber.getText().toString().isEmpty()
                || etAddress.getText().toString().isEmpty() || etWeb.getText().toString().isEmpty() ){
            Toast.makeText(this, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra("name", etName.getText().toString().trim());
            intent.putExtra("number", etNumber.getText().toString().trim());
            intent.putExtra("address", etAddress.getText().toString().trim());
            intent.putExtra("web", etWeb.getText().toString().trim());

            if (view.getId() == R.id.ivHappy){
                intent.putExtra("mood", "happy");
            } else if (view.getId() == R.id.ivSad){
                intent.putExtra("mood","sad");
            } else {
                intent.putExtra("mood", "ok");
            }
            setResult(RESULT_OK, intent);
            CreateContact.this.finish();
        }
    }
}