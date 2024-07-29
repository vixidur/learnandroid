package com.example.fiftyproject;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
public class MainActivity2 extends AppCompatActivity {
    Button btnShowJSON, btnThem, btnSua, btnXoa, btnPlay, btnStop;
    TextView tvjson;
    ListView lv;
    private ArrayList<Person> personList;
    private ArrayAdapter<Person> adapter;
    private Person person;
    EditText etNameSong, etNameSinger;
    RadioGroup rg;
    private String[] arr = {
            "[\n" +
                    "  {\n" +
                    "    \"HoTen\": \"Tran Van Chien\",\n" +
                    "    \"Tuoi\": 24\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"HoTen\": \"Pham Tuan Vu\",\n" +
                    "    \"Tuoi\": 24\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"HoTen\": \"Hoang Van Hung\",\n" +
                    "    \"Tuoi\": 24\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"HoTen\": \"Vo Quoc Viet\",\n" +
                    "    \"Tuoi\": 24\n" +
                    "  }\n" +
                    "]"
    };
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        btnShowJSON = findViewById(R.id.btnShowJSON);
        btnShowJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadJSON();
            }
        });

        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.noinaycoanh);
        btnPlay = findViewById(R.id.btnPlay);
        btnStop = findViewById(R.id.btnStop);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.pause();
            }
        });
        // code hien thi ra listview
        personList = new ArrayList<>();
        personList.add(new Person("Noi nay co anh", "Son Tung Mtp"));
        personList.add(new Person("Dung lam trai tim anh dau", "Son Tung Mtp"));
        personList.add(new Person("There's no one at all", "Son Tung Mtp"));
        personList.add(new Person("Making My Way", "Son Tung Mtp"));

        adapter = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1, personList);
        lv = findViewById(R.id.lv);
        lv.setAdapter(adapter);

        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
        etNameSong = findViewById(R.id.nameSong);
        etNameSinger = findViewById(R.id.nameSinger);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameSong = etNameSong.getText().toString();
                String nameSinger = etNameSinger.getText().toString();
                person = new Person(nameSong, nameSinger);
                personList.add(person);
                etNameSong.setText("");
                etNameSinger.setText("");
                showAlert("Them thanh cong!");
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                person = personList.get(i);
                // noi nay co anh - son tung mtp
                String[] tach = person.toString().split(" - ");
                if(tach.length == 2){
                    etNameSong.setText(tach[0]);
                    etNameSinger.setText(tach[1]);
                }else{
                    etNameSong.setText("");
                    etNameSinger.setText("");
                }
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nameSong = etNameSong.getText().toString();
                        String nameSinger = etNameSinger.getText().toString();
                        person.setNameSong(nameSong);
                        person.setNameSinger(nameSinger);
                        adapter.notifyDataSetChanged();
                        etNameSong.setText("");
                        etNameSinger.setText("");
                        showAlert("Sua thanh cong!");
                    }
                });
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        personList.remove(person);
                        adapter.notifyDataSetChanged();
                        etNameSong.setText("");
                        etNameSinger.setText("");
                        showAlert("Xoa thanh cong!");
                    }
                });
            }
        });
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeFile();
            }
        });
    }
    private void writeFile() {
        String jsonData = arr[0];
        String fileName = "data.json";

        // Use internal storage
        File file = new File(getFilesDir(), fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(jsonData);
            Toast.makeText(this, "File saved successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("MainActivity", "Error writing file", e);
            Toast.makeText(this, "Error writing file", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadJSON(){
        try {
            InputStream is = getAssets().open("thongtin.json");
            int size = is.available();
            byte[] b = new byte[size];
            is.read(b);
            is.close();

            String json = new String(b, StandardCharsets.UTF_8);
            JSONArray jsa = new JSONArray(json);
            int age;
            String name;

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < jsa.length(); i++){
                JSONObject jso = jsa.getJSONObject(i);
                age = jso.getInt("Tuoi");
                name = jso.getString("HoTen");

                sb.append(String.format("Ho ten: %s\nTuoi: %s\n",name,age));
                tvjson = findViewById(R.id.tvJSON);
                tvjson.setText(sb);
            }

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(String message){
        new AlertDialog.Builder(this).setMessage(message).setPositiveButton("Close", null).show();
    }
}