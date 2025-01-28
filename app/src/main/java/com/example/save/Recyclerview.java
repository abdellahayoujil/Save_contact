package com.example.save;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recyclerview extends AppCompatActivity {

    EditText edName;
    RecyclerView recyclerView;
    ArrayList<Class_save> arrayList = new ArrayList<>();
    Db_save db_Save;
    Array_Adapter adapter;
    TextView emptyView; // Add this

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recyclerview);

        edName = findViewById(R.id.edName);
        recyclerView = findViewById(R.id.recyclerView);
        emptyView = findViewById(R.id.emptyView); // Initialize empty view

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db_Save = new Db_save(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        adapter = new Array_Adapter(arrayList,this);
        recyclerView.setAdapter(adapter);

        loadAllData(); // Load initial data
        checkIfEmpty(); // Check if data is empty

        // Add TextWatcher for search functionality
        edName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void checkIfEmpty() {
        if (arrayList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

    private void loadAllData() {
        arrayList.clear();
        Cursor cursor = db_Save.showData();
        while (cursor.moveToNext()) {
            arrayList.add(new Class_save(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(0)
            ));
        }
        cursor.close();
        adapter.notifyDataSetChanged();
        checkIfEmpty(); // Check after loading data
    }

    private void searchData(String searchText) {
        arrayList.clear();
        Cursor cursor = db_Save.searchByName(searchText);
        while (cursor.moveToNext()) {
            arrayList.add(new Class_save(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(0)
            ));
        }
        cursor.close();
        adapter.notifyDataSetChanged();
        checkIfEmpty(); // Check after searching
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(Recyclerview.this,MainActivity.class));
        super.onBackPressed();
    }
}