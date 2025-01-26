package com.example.save;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {



    EditText edName,edNumber,edEmail,edAdresse;
    Button addButton;
    Db_save db_save;
    FloatingActionButton floatingActionButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        edName = findViewById(R.id.edName);
        edNumber = findViewById(R.id.edNumber);
        edEmail = findViewById(R.id.edEmail);
        edAdresse = findViewById(R.id.edAd);
        addButton = findViewById(R.id.addBtn);
        floatingActionButton = findViewById(R.id.floatingId);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,Recyclerview.class));
            }
        });

        db_save = new Db_save(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edName.length()>0){

                    db_save.insertData(edName.getText().toString(),edNumber.getText().toString(),edEmail.getText().toString(),edAdresse.getText().toString());

                    edName.setText("");
                    edNumber.setText("");
                    edEmail.setText("");
                    edAdresse.setText("");

                    Toast.makeText(MainActivity.this,"Successfully Save!",Toast.LENGTH_SHORT).show();


                }else {
                    Toast.makeText(MainActivity.this,"Enter at least the name!",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}