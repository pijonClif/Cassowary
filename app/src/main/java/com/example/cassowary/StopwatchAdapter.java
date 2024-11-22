package com.example.cassowary;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StopwatchAdapter extends RecyclerView.Adapter<StopwatchAdapter.StopwatchViewHolder> {

    private List<Stopwatch> stopwatchList;
    private Handler handler = new Handler();
    private Runnable updateRunnable;

    public StopwatchAdapter(List<Stopwatch> stopwatchList) {
        this.stopwatchList = stopwatchList;
        startAutoUpdate();
    }

    @NonNull
    @Override
    public StopwatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stopwatch_item, parent, false);
        return new StopwatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StopwatchViewHolder holder, int position) {
        Stopwatch stopwatch = stopwatchList.get(position);

        // Set the stopwatch name and time
        holder.nameTextView.setText(stopwatch.getName());
        holder.timeTextView.setText(stopwatch.getTime());
        holder.startPauseButton.setText(stopwatch.isRunning() ? "Pause" : "Start");

        holder.startPauseButton.setOnClickListener(v -> {
            if (stopwatch.isRunning()) {
                stopwatch.pause();
            } else {
                stopwatch.start();
            }
            notifyItemChanged(position);
        });

        holder.resetButton.setOnClickListener(v -> {
            stopwatch.reset();
            notifyItemChanged(position);
        });

        // Handle "Remove" button click
        holder.removeButton.setOnClickListener(v -> {
            stopwatchList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, stopwatchList.size());
        });
    }

    public static class StopwatchViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, timeTextView;
        Button startPauseButton, resetButton, removeButton;

        public StopwatchViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            startPauseButton = itemView.findViewById(R.id.startPauseButton);
            resetButton = itemView.findViewById(R.id.resetButton);
            removeButton = itemView.findViewById(R.id.removeButton);
        }
    }


    @Override
    public int getItemCount() {
        return stopwatchList.size();
    }

    private void startAutoUpdate() {
        updateRunnable = new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(updateRunnable);
    }
}