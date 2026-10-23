package com.example.todolistapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private final ArrayList<String> daftarTask;

    public TaskAdapter(ArrayList<String> daftarTask) {
        this.daftarTask = daftarTask;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkTask;
        TextView namaTask;
        Button btnEdit, btnHapus;


        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            checkTask = itemView.findViewById(R.id.checkTask);
            namaTask = itemView.findViewById(R.id.namaTask);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnHapus = itemView.findViewById(R.id.btnHapus);
        }
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull TaskViewHolder holder, int position) {

        holder.namaTask.setText(daftarTask.get(position));
    }
    @Override
    public int getItemCount() {
        return daftarTask.size();
    }


}
