package com.example.de11;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar);
    }

    // ham truyen album sang activity_main
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.album, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.item1){
            Toast.makeText(this, "Chuc nang nay chua duoc cap nhat", Toast.LENGTH_SHORT).show();
        }
        if (id== R.id.item2){
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        }
        if (id==R.id.item3){
            Toast.makeText(this, "Chuc nang nay chua duoc cap nhat", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}