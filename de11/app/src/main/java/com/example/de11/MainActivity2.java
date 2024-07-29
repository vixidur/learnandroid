package com.example.de11;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    // Step 1: khai bao lop
    private AlbumSinger as;

    // Step 2: khai bao mang va adapter, listview, button them
    private ArrayList<AlbumSinger> singerList;
    private ArrayAdapter<AlbumSinger> adt;
    ListView lv;
    Button btnAdd;
    EditText et_namesinger, et_namesong;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        // Step3: gán biến btnAdd, et = findViewById();
        btnAdd = findViewById(R.id.them);
        et_namesinger = findViewById(R.id.etNameSinger);
        et_namesong = findViewById(R.id.etNameSong);


        //Step 4: su dung singerList de hien thi ra ListView
        singerList = new ArrayList<>();
        singerList.add(new AlbumSinger("Noi nay co anh","Son Tung MTP"));
        singerList.add(new AlbumSinger("Dung lam trai tim anh dau","Son Tung MTP"));

        //Step 5: su dung Adapter de chuyen doi ArrayList sang ListView
        adt = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1, singerList);

        //Step 6: hien thi ra ListView
        lv = findViewById(R.id.lv);
        lv.setAdapter(adt);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameSong = et_namesong.getText().toString();
                String nameSinger = et_namesinger.getText().toString();
                as = new AlbumSinger(nameSong, nameSinger);
                singerList.add(as);
                adt.notifyDataSetChanged();
                et_namesong.setText("");
                et_namesinger.setText("");
                Toast.makeText(MainActivity2.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
            }
        });

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
        if(id == R.id.item4){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}