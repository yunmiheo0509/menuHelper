package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuListActivity extends AppCompatActivity {
    private ArrayList<MenuListData> menuListData;
    private RecyclerAdapter recyclerAdapter;
    private int count = -1;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        Intent intent = getIntent();
        String[] myStrings = intent.getStringArrayExtra("menuarray");
        textView = findViewById(R.id.tv_menuname);

        String add_array = "";
        for (int i = 0; i < myStrings.length; i++) {
            add_array += myStrings[i] + ",";
//            menuListData.add(new MenuListData(array[i]));
        }
        System.out.println(myStrings);

        RecyclerView recyclerView = findViewById(R.id.rv_menulist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        menuListData = new ArrayList<>();

        recyclerAdapter = new RecyclerAdapter(menuListData);
        recyclerView.setAdapter(recyclerAdapter);

//        recyclerAdapter.notifyDataSetChanged();
//        textView.setText(add_array);


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu_list);
//
//        ListView listView = findViewById(R.id.lv_menulist);
//
//        final ArrayList<String> list = new ArrayList<>();
//
//        Intent intent = getIntent();
//        String[] myStrings = intent.getStringArrayExtra("menuarray");
//
//        String add_array = "";
//        for (int i = 0; i < myStrings.length; i++) {
//            add_array += myStrings[i] + ",";
////            menuListData.add(new MenuListData(array[i]));
//        }
//
//        for (int i = 0; i < myStrings.length; i++) {
//            list.add(myStrings[i]);
//            System.out.println(list.get(i));
//        }
//
//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                this, android.R.layout.simple_list_item_1, list
//        );
//
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MenuListActivity.this, "Click!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}