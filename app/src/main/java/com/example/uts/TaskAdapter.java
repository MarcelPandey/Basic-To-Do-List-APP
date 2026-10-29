package com.example.uts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.graphics.Color;
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    public interface OnTaskChangeListener {
        void onCheckChanged(int position);
    }

    private final ArrayList<Task> listTask;
    private final OnTaskChangeListener listener;

    public TaskAdapter(ArrayList<Task> listTask, OnTaskChangeListener listener) {
        this.listTask = listTask;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = listTask.get(position);
        holder.namaTask.setText(task.getNamaTask());
        holder.checkTask.setChecked(task.isFinished());

        if ("Prioritas".equalsIgnoreCase(task.getPriority())) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFCDD2")); // Merah muda
        } else if ("Medium".equalsIgnoreCase(task.getPriority())) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFE0B2")); // Oranye muda
        } else if ("Santai".equalsIgnoreCase(task.getPriority())) {
            holder.itemView.setBackgroundColor(Color.parseColor("#C8E6C9")); // Hijau muda
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT); // Default
        }
    }

    @Override
    public int getItemCount() {
        return listTask.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkTask;
        TextView namaTask;

        TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            checkTask = itemView.findViewById(R.id.checkTask);
            namaTask = itemView.findViewById(R.id.namaTask);

            checkTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION && listener != null) {
                        listener.onCheckChanged(pos);
                    }
                }
            });
        }
    }
}