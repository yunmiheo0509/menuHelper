package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
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
import java.util.List;

public class MenuListActivity extends AppCompatActivity {
//    private List<MenuListData> menuListData;
//    private RecyclerAdapter recyclerAdapter;
//    private int count = -1;
//
//    TextView textView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu_list);
//
//        Intent intent = getIntent();
//        String[] myStrings = intent.getStringArrayExtra("menuarray");
//        textView = findViewById(R.id.tv_menuname);
//
//        //전달 테스트
////        for (int i = 0; i < myStrings.length; i++) {
////            System.out.println(myStrings[i]);
////        }
//
//        menuListData = new ArrayList<>();
////        String add_array = "";
////        for (int i = 0; i < myStrings.length; i++) {
//////            add_array += myStrings[i] + ",";
////            System.out.println(myStrings[i]);
////            menuListData.add(new MenuListData(myStrings[i]));
////        }
////        System.out.println(myStrings);
//
//        RecyclerView recyclerView = findViewById(R.id.rv_menulist);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        menuListData.add(new MenuListData(myStrings[0]));
//        menuListData.add(new MenuListData(myStrings[1]));
//        menuListData.add(new MenuListData(myStrings[2]));
//
//        recyclerAdapter = new RecyclerAdapter(this, menuListData);
//        recyclerView.setAdapter(recyclerAdapter);
//
////        recyclerView.addItemDecoration(
////                new DividerItemDecoration(this, linearLayoutManager.getOrientation()));
////        recyclerView.setLayoutManager(linearLayoutManager);
//
////        recyclerAdapter.notifyDataSetChanged();
////        textView.setText(add_array);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        ListView listView = findViewById(R.id.lv_menulist);

        List<String> data = new ArrayList<>();

        Intent intent = getIntent();
        String[] myStrings = intent.getStringArrayExtra("menuarray");

//        String add_array = "";
//        for (int i = 0; i < myStrings.length; i++) {
////            menuListData.add(new MenuListData(myStrings[i]));
//        }

//        for (int i = 0; i < myStrings.length; i++) {
////            menuListData.add(new MenuListData(myStrings[i]));
//            list.add(myStrings[i]);
////            System.out.println(list.get(i));
//        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_list_item_1, data
        );

        listView.setAdapter(arrayAdapter);

        for (int i = 0; i < myStrings.length; i++) {
            String add = myStrings[i];
            data.add(add);
        }

        arrayAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MenuListActivity.this, MenuDescription.class);
                startActivity(intent);
            }
        });
    }
}