package comswatisingh0960.github.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Swati on 9/22/2016.
 */

public class TodoAdapter extends ArrayAdapter<ToDo> {

    public TodoAdapter(Context context, ArrayList<ToDo> todoTasks) {
        super(context, 0, todoTasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ToDo todoTask = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_task, parent, false);
        }
        // Lookup view for data population
        TextView taskName = (TextView) convertView.findViewById(R.id.taskName);
        TextView status = (TextView) convertView.findViewById(R.id.status);
        // Populate the data into the template view using the data object
        taskName.setText(todoTask.getTaskName());
        status.setText(todoTask.getStatus());
        // Return the completed view to render on screen
        return convertView;
    }
}