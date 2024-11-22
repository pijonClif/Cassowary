package com.example.cassowary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Stopwatch> stopwatchList = new ArrayList<>();
    private StopwatchAdapter stopwatchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        stopwatchAdapter = new StopwatchAdapter(stopwatchList);
        recyclerView.setAdapter(stopwatchAdapter);

        findViewById(R.id.addStopwatchButton).setOnClickListener(v -> {
            // Show dialog to input name for the new stopwatch
            showAddStopwatchDialog();
        });
    }

    private void showAddStopwatchDialog() {
        final EditText input = new EditText(MainActivity.this);
        input.setHint("Enter name");

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Add Stopwatch")
                .setMessage("Please enter a name for your new stopwatch.")
                .setView(input)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String stopwatchName = input.getText().toString().trim();
                        if (!stopwatchName.isEmpty()) {
                            stopwatchList.add(new Stopwatch(stopwatchName));
                            stopwatchAdapter.notifyItemInserted(stopwatchList.size() - 1);
                        } else {
                            Toast.makeText(MainActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
