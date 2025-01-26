package com.example.save;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recyclerview extends AppCompatActivity {



    RecyclerView recyclerView;
    ArrayList<Class_save> arrayList = new ArrayList<>();
    Db_save db_Save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recyclerview);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db_Save = new Db_save(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Cursor cursor = db_Save.showData();

        while (cursor.moveToNext()){

            arrayList.add(new Class_save(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(0)));

        }


        Array_Adapter adepter = new Array_Adapter(arrayList,this);
        recyclerView.setAdapter(adepter);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Recyclerview.this,MainActivity.class));
        super.onBackPressed();


    }

}