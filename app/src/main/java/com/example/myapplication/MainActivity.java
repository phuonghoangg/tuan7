package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<com.example.myapplication.City> listCity;
    private ListView listView;
    private Button btnsave;
    com.example.myapplication.DataCity dataCity;
    EditText editText;
    ArrayList nameList;
    ArrayList idList;
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataCity = new com.example.myapplication.DataCity(this,"citydb.sqlite",null,1);
//        dataCity = new DataCity(this,"citydb.sqlite",null,1);
        dataCity.addCity(new com.example.myapplication.City("Ho Chi Minh"));
        editText = findViewById(R.id.id_nhapcity);
        btnsave = findViewById(R.id.btn_save);
        nameList = new ArrayList();

        listView = findViewById(R.id.idListview);
        listCity = new ArrayList<>();
        listCity.add(new com.example.myapplication.City("Hồ Chí Minh"));
        listCity.add(new com.example.myapplication.City("Hà Nội"));
        com.example.myapplication.adapterCity adapter = new com.example.myapplication.adapterCity(this, R.layout.city_item, listCity);
        listView.setAdapter(adapter);


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataCity.addCity(new com.example.myapplication.City(editText.getText().toString()));
                getNameList();
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
    private ArrayList   getNameList(){

        nameList.clear();
        idList.clear();
        for (Iterator iterator = dataCity.getAll().iterator(); iterator.hasNext(); ) {
            com.example.myapplication.City city = (com.example.myapplication.City) iterator.next();

            nameList.add(city.getNameCity());
            idList.add(city.getId());
        }
        return nameList;
    }
}