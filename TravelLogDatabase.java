package com.example.sprint2portion;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class TravelLogDatabase {

    private static TravelLogDatabase instance;
    private List<TravelLog> travelLogs;

    // Private constructor to prevent direct instantiation
    private TravelLogDatabase(Context context) {
        travelLogs = new ArrayList<>();
        prepopulateData();
    }

    // Singleton pattern to return the only instance
    public static synchronized TravelLogDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new TravelLogDatabase(context.getApplicationContext());
        }
        return instance;
    }

    private void prepopulateData() {
        travelLogs.add(new TravelLog("Paris", "2023-05-01", "2023-05-07", "0 days planned"));
        travelLogs.add(new TravelLog("New York", "2023-08-10", "2023-08-15", "0 days planned"));
    }

    public List<TravelLog> getTravelLogs() {
        return travelLogs;
    }

    public void addTravelLog(TravelLog log) {
        travelLogs.add(log);
    }
}
