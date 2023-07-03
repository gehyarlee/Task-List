package com.example.cat_project;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdepter extends RecyclerView.Adapter<TaskAdepter.ViewHolder> {
    List<Taskholder> task;
    Context context;
    DatabaseHelper databaseHelper;

    public TaskAdepter(List<Taskholder> task, Context context){
        this.task = task;
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_task_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdepter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Taskholder taskholder = task.get(position);
        holder.textViewID.setText(String.valueOf(taskholder.getId()));
        holder.editText_Title.setText(taskholder.getTitle());
        holder.editText_Descrition.setText(taskholder.getDescription());

        holder.button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringTitle = holder.editText_Title.getText().toString();
                String stringDescription = holder.editText_Descrition.getText().toString();

                databaseHelper.updateTask(new Taskholder(taskholder.getId(), stringTitle, stringDescription));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.button_delate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.delete(taskholder.getId());
                task.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return task.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText editText_Title;
        EditText editText_Descrition;
        Button button_edit;
        Button button_delate;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            textViewID = itemView.findViewById(R.id.listid);
            editText_Title = itemView.findViewById(R.id.title);
            editText_Descrition = itemView.findViewById(R.id.description);
            button_edit = itemView.findViewById(R.id.btnEdit);
            button_delate = itemView.findViewById(R.id.btnDelete);
        }
    }
}
