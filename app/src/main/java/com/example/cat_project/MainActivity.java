package com.example.cat_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText title, description;
    Button add, view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.txtTitle);
        description = findViewById(R.id.txtDescription);
        add = findViewById(R.id.btnAdd);
        view = findViewById(R.id.btnView);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringTitle = title.getText().toString();
                String stringDescription = description.getText().toString();

                if (stringTitle.length()<=0 || stringDescription.length()<=0){
                    Toast.makeText(MainActivity.this, "All task are require", Toast.LENGTH_SHORT).show();
                }
                else {
                    DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                    Taskholder taskholder = new Taskholder(null, stringTitle, stringDescription);
                    databaseHelper.insertTask(taskholder);
                    Toast.makeText(MainActivity.this, "Task successfully enter", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewTask.class);
                startActivity(intent);
            }
        });
    }
}