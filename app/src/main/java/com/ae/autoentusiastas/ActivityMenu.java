package com.ae.autoentusiastas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityMenu extends AppCompatActivity {

    private Button btnSite, btnForm, btnList, btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnSite = findViewById(R.id.btnSite);
        btnProfile = findViewById(R.id.btnProfile);
        btnForm = findViewById(R.id.btnForm);
        btnList = findViewById(R.id.btnList);

        btnForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityForm.class));
                finish();
            }
        });
    }
}
