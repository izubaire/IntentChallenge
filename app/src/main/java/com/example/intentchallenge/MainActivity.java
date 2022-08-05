package com.example.intentchallenge;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView ivMood, ivPhone, ivWeb, ivLocation;
    Button btnCreate;
    final int CREATE_CONTACT = 1;
    String name = "", number = "", web = "", address = "", mood = "";

    ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult activityResult) {
                            int result = activityResult.getResultCode();
                            Intent data = activityResult.getData();
                            if (result == RESULT_OK){
                                ivMood.setVisibility(View.VISIBLE);
                                ivPhone.setVisibility(View.VISIBLE);
                                ivWeb.setVisibility(View.VISIBLE);
                                ivLocation.setVisibility(View.VISIBLE);

                                name = data.getStringExtra("name");
                                number = data.getStringExtra("number");
                                web = data.getStringExtra("web");
                                address = data.getStringExtra("address");
                                mood = data.getStringExtra("mood");

                                Log.i("debugCheck", address);
                                Log.i("debugCheck", web);
                                Log.i("debugCheck", name);


                                if (mood.equals("happy")){
                                    ivMood.setImageResource(R.drawable.happy);
                                }else if (mood.equals("sad")){
                                    ivMood.setImageResource(R.drawable.sad);
                                }else {
                                    ivMood.setImageResource(R.drawable.ok);
                                }
                            } else {
//                                Toast.makeText(this, "No Data Passes", Toast.LENGTH_SHORT).show();
                                Toast.makeText(MainActivity.this, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivMood = findViewById(R.id.ivMood);
        ivPhone = findViewById(R.id.ivPhone);
        ivWeb = findViewById(R.id.ivWeb);
        ivLocation = findViewById(R.id.ivLocation);

        btnCreate = findViewById(R.id.btnCreate);

        ivMood.setVisibility(View.GONE);
        ivPhone.setVisibility(View.GONE);
        ivWeb.setVisibility(View.GONE);
        ivLocation.setVisibility(View.GONE);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CreateContact.class);
//                startActivityForResult(intent, CREATE_CONTACT);
                String str = intent.toString();

                activityResultLauncher.launch(intent);
            }
        });

        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });

        ivWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + web));
                startActivity(intent);
            }
        });

        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + address));
                startActivity(intent);
            }
        });

    }
}