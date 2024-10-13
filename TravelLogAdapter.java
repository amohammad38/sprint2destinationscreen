package com.example.sprint2portion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TravelLogAdapter extends ArrayAdapter<TravelLog> {

    public TravelLogAdapter(Context context, List<TravelLog> travelLogs) {
        super(context, 0, travelLogs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the view is being reused, otherwise inflate a new view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the current travel log item
        TravelLog travelLog = getItem(position);

        // Find the views in the list_item.xml layout
        TextView locationTextView = convertView.findViewById(R.id.item_title);
        TextView durationTextView = convertView.findViewById(R.id.item_subtitle);

        // Populate the data into the views
        if (travelLog != null) {
            locationTextView.setText(travelLog.getLocation());
            durationTextView.setText(travelLog.getDuration());
        }

        return convertView;
    }
}
