package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {
    Context context;
    ArrayList<MenuListData> menuListData;

//    public RecyclerAdapter(Context context, ArrayList<MenuListData> menuListData) {
//        this.context = context;
//        this.menuListData = menuListData;
//    }

    public RecyclerAdapter(ArrayList<MenuListData> menuListData) {
        this.menuListData = menuListData;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.menulistitem, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.menuName.setText(menuListData.get(position).getMenuName());
//        holder.onBind(menuListData.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    void addItem(MenuListData menuListData) {
    }


    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView menuName;

        ItemViewHolder(View view) {
            super(view);

            menuName = view.findViewById(R.id.tv_menuname);
        }

        void onBind(MenuListData menuListData) {
            menuName.setText(menuListData.getMenuName());
        }

    }
}