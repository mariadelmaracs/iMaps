package com.example.imap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ItemList> itemLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(itemLists, this));

        itemLists.add(new ItemList(R.drawable.basilica, getResources().getString(R.string.b_location)));
        itemLists.add(new ItemList(R.drawable.laguna, getResources().getString(R.string.l_location)));
        itemLists.add(new ItemList(R.drawable.derrumbado, getResources().getString(R.string.d_location)));
        itemLists.add(new ItemList(R.drawable.estadio, getResources().getString(R.string.e_location)));

    }

}