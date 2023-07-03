package com.example.cat_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewTask extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<Taskholder> taskAdepterList = databaseHelper.getTaskList();

        if (taskAdepterList.size() >0){
            TaskAdepter taskAdepter = new TaskAdepter(taskAdepterList, ViewTask.this);
            recyclerView.setAdapter(taskAdepter);
        }else {
            Toast.makeText(this, "There are no task available", Toast.LENGTH_SHORT).show();
        }
    }
}