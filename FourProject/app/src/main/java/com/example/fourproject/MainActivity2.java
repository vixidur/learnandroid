package com.example.fourproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity {
    ListView lv;
    private ArrayList<String> arrList;
    ArrayAdapter<String> adapter;
    EditText newName;
    Button addNewName;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);


        arrList = new ArrayList<>(Arrays.asList(
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
                "Do Duy Thanh\nSDT: 0123456789"
        ));


        lv = findViewById(R.id.listview);
        adapter = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1, arrList);
        lv.setAdapter(adapter);

        // click vào dòng
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showAlertEditandRemove(i);
            }
        });



        // them sinh vien
        newName = findViewById(R.id.etAddNewName);
        addNewName = findViewById(R.id.btnAddNewName);
        addNewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName2 = newName.getText().toString().trim();
                arrList.add(newName2 + "\nSDT: 0934277416");
                adapter.notifyDataSetChanged();
                newName.setText("");
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar);
    }


    private void Alert(String mess) {
        new AlertDialog.Builder(this).setMessage(mess).setPositiveButton("OK", null).show();
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

    private void showAlertEditandRemove(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Item");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText editName = new EditText(this);
        editName.setHint("Name");
        editName.setText(arrList.get(position).split("\n")[0]);
        layout.addView(editName);

        final EditText editPhone = new EditText(this);
        editPhone.setHint("Phone");
        editPhone.setText(arrList.get(position).split("\n")[1].split(": ")[1]);
        layout.addView(editPhone);

        builder.setView(layout);

        builder.setPositiveButton("Xóa", (dialog, which) -> {
            arrList.remove(position); // Xóa item được chọn
            adapter.notifyDataSetChanged(); // Thông báo Adapter về thay đổi dữ liệu
            Toast.makeText(MainActivity2.this, "Item đã được xóa", Toast.LENGTH_SHORT).show();
        });

        builder.setNeutralButton("Sửa", (dialog, which) -> {
            String newName = editName.getText().toString().trim();
            String newPhone = editPhone.getText().toString().trim();
            if (!newName.isEmpty() && !newPhone.isEmpty()) {
                arrList.set(position, newName + "\nSDT: " + newPhone); // Cập nhật tên và số điện thoại mới
                adapter.notifyDataSetChanged(); // Thông báo Adapter về thay đổi dữ liệu
                Toast.makeText(MainActivity2.this, "Item đã được sửa", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Đóng", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}