package com.example.fourproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        String arr[] = {
                "Tran Van Chien\nSDT: 0862587229",
                "Le Thien Nguyen\nSDT: 0123456789",
                "Nguyen Xuan Thuong\nSDT: 0123456789",
                "To Hoang Vu\nSDT: 0123456789",
                "Pham Tuan Vu\nSDT: 0123456789",
                "Vo Quoc Viet\nSDT: 0123456789",
                "Le Thuy Linh\nSDT: 0123456789",
                "Pham Tran Anh\nSDT: 0123456789",
                "Nguyen Phuc Thinh\nSDT: 0123456789",
                "Nguyen Thi Quynh Nga\nSDT: 0123456789",
                "Hoang Van Hung\nSDT: 0123456789",
                "Do Duy Thanh\nSDT: 0123456789",
        };
        lv = findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1, arr);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showAlert("Day la: " + arr[i]);
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
        if(id == R.id.item3) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlert(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}