package com.example.fourproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    TextView jsonDemo;
    Button btnShow, btnCheck, btnPlayAudio, btnStop, btnNext, btnBack;
    CheckBox checkTVC;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadJSON();
            }
        });
        btnCheck = findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkTVC = findViewById(R.id.tvc);
                if(checkTVC.isChecked()){
                    showAlert("this is Tran Van Chien");
                }
            }
        });

        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.noinaycoanh);
        btnPlayAudio = findViewById(R.id.btnPlay);
        btnPlayAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });
        btnStop = findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.item1) {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        }
        if(id == R.id.item3) {
            finish();
        }
        return true;
    }

    private void loadJSON(){
        try {
            InputStream inputStream = getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsA = new JSONArray(json);
            JSONArray xe;
            int age;
            String name, country;
            StringBuilder sb = new StringBuilder();
            for(int i=0; i < jsA.length(); i++){
                JSONObject jsO = jsA.getJSONObject(i);
                name = jsO.getString("name");
                country = jsO.getString("country");
                age = jsO.getInt("age");
                xe = jsO.getJSONArray("xe");
                sb.append(String.format("Name: %s \nCountry: %s \nAge: %s \nXe: ",name,country,age,xe));
                jsonDemo = findViewById(R.id.jsonDemo);
                jsonDemo.setText(sb);
            }
        }catch (Exception e){
            Log.e("TAG", "loadJSON: error" + e);
        }
    }

    private void showAlert(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}