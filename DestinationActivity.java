package com.example.sprint2portion;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class DestinationActivity extends AppCompatActivity {

    private TravelLogAdapter travelLogAdapter;
    private List<TravelLog> travelLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        // Get reference to the list and adapter
        travelLogs = TravelLogDatabase.getInstance(this).getTravelLogs();
        travelLogAdapter = new TravelLogAdapter(this, travelLogs);

        ListView listView = findViewById(R.id.list_travel_logs);
        listView.setAdapter(travelLogAdapter);

        // Set up the button to log new travel
        Button logTravelButton = findViewById(R.id.btn_log_travel);
        logTravelButton.setOnClickListener(v -> showLogTravelDialog());
    }

    private void showLogTravelDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_log_travel);

        EditText travelLocationInput = dialog.findViewById(R.id.travel_location);
        EditText startDateInput = dialog.findViewById(R.id.estimated_start);
        EditText endDateInput = dialog.findViewById(R.id.estimated_end);
        Button submitButton = dialog.findViewById(R.id.btn_submit_travel);

        submitButton.setOnClickListener(v -> {
            String location = travelLocationInput.getText().toString();
            String startDate = startDateInput.getText().toString();
            String endDate = endDateInput.getText().toString();

            if (!location.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                long daysBetween = calculateDaysBetween(startDate, endDate);
                if (daysBetween > 0) {
                    // Directly passing daysBetween + " days" as the duration
                    TravelLog newLog = new TravelLog(location, startDate, endDate, daysBetween + " days");
                    TravelLogDatabase.getInstance(this).addTravelLog(newLog);
                    travelLogs.clear();
                    travelLogs.addAll(TravelLogDatabase.getInstance(this).getTravelLogs());
                    travelLogAdapter.notifyDataSetChanged(); // Update the list
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }

    // Calculate the number of days between two dates
    private long calculateDaysBetween(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        return ChronoUnit.DAYS.between(start, end);
    }

}