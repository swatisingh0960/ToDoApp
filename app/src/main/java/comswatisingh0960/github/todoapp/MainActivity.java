package comswatisingh0960.github.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
        protected ToDoDBHelper db;
        private ArrayList<ToDo> items;
        TodoAdapter todoAdapter;
//        private ArrayAdapter<String> itemsAdapter;
        private ListView lvItems;
        List<ToDo> list;
        private final int EDIT_REQUEST_CODE = 20;
        private static final int REQUEST_CODE = 10;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            db = new ToDoDBHelper(this);
            list= db.getAllTasks();
            lvItems = (ListView) findViewById(R.id.lvItems);
            items = (ArrayList<ToDo>)ToDo.listAll(ToDo.class);
//            items = new ArrayList<String>();
//            readItems();
//            itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
            todoAdapter = new TodoAdapter(this,items);
//            lvItems.setAdapter(itemsAdapter);
            lvItems.setAdapter(todoAdapter);
            /*items.add("Read at 7 AM");
             items.add("Breakfast at 8 AM");*/
            setupListViewListener();
            setupListEditListener();
}

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
//                        items.remove(pos);
//                        itemsAdapter.notifyDataSetChanged();
                        ToDo deletetask = items.remove(pos);
                        deletetask.delete();
                        todoAdapter.notifyDataSetChanged();
//                        writeItems(); // <---- Add this line
                        return true;
                    }
                });
    }
    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        //items = new ArrayList<String>();
        ToDo newTask = new ToDo(itemText,"High", 1);
            db.addTask(newTask);
            Log.d("tasker", "data added");
            etNewItem.setText("");
            items.add(newTask);
            etNewItem.setText("");
            //writeItems(); // <---- Add this line
        }

       private void setupListEditListener() {
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
                intent.putExtra("task", items.get(position).getTaskName());
                intent.putExtra("pos", position);
                startActivityForResult(intent, EDIT_REQUEST_CODE);
            }
        });
    }

   /*     private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            items = new ArrayList<ToDo>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
        }
    }
    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
            @Override
           protected void onActivityResult(int requestCode, int resultCode, Intent i) {
                    // REQUEST_CODE is defined above
                    if (resultCode == RESULT_OK && requestCode == EDIT_REQUEST_CODE) {
                        // Extract name value from result extras
                        String taskname = i.getExtras().getString("todoItemSaved");
                        int position = i.getIntExtra("positionSaved", 200);
//                        ToDo editTask = items.remove(position);
                        items.remove(position);
//                        items.taskname=taskname;
                        ToDo editTask = new ToDo(taskname,"Work in Progress",1);
                        editTask.setTaskName(taskname);
                        editTask.save();
                        items.add(position, editTask);
                        todoAdapter.notifyDataSetChanged();
//                        writeItems();

                    }
    }

}
