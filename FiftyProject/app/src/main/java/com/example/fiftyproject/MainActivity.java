package com.example.fiftyproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Button btnAdd, btnEdit, btnRemove;
    EditText etHoten, etCCCD;
    private ArrayList<CongDan> arrList;
    private ArrayAdapter<CongDan> adapter;
    ListView lv;
    private CongDan cd;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        arrList = new ArrayList<>();
        arrList.add(new CongDan("Tran Van Chien", "0312456789"));
        arrList.add(new CongDan("Pham Tuan Vu", "0312456789"));
        lv = findViewById(R.id.lv);
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arrList);
        lv.setAdapter(adapter);

        //them sinh vien
        btnAdd = findViewById(R.id.btnAdd);
        etHoten = findViewById(R.id.et_Hoten);
        etCCCD = findViewById(R.id.et_CCCD);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoTen = etHoten.getText().toString();
                String cccd = etCCCD.getText().toString();
                cd = new CongDan(hoTen, cccd);
                arrList.add(cd);
                adapter.notifyDataSetChanged();
                etHoten.setText("");
                etCCCD.setText("");
            }
        });


        //sua sinh vien
        btnEdit = findViewById(R.id.btnEdit);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                cd = arrList.get(i);
                String[] parts = cd.toString().split(" - ");
                if (parts.length == 2) {
                    etHoten.setText(parts[0]);
                    etCCCD.setText(parts[1]);
                } else {
                    etHoten.setText("");
                    etCCCD.setText("");
                }

                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String newHoTen = etHoten.getText().toString();
                        String newCccd = etCCCD.getText().toString();
                        cd.setHoten(newHoTen);
                        cd.setCccd(newCccd);
                        adapter.notifyDataSetChanged();
                        etHoten.setText("");
                        etCCCD.setText("");
                    }
                });

                // xoa
                btnRemove = findViewById(R.id.btnRemove);
                btnRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        arrList.remove(cd);
                        adapter.notifyDataSetChanged();
                        etHoten.setText("");
                        etCCCD.setText("");
                    }
                });
            }
        });
    }
}