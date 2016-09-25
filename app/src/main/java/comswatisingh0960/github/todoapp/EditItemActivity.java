package comswatisingh0960.github.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        String task = getIntent().getStringExtra("task");
        pos = getIntent().getIntExtra("pos", 200);
        EditText editText = (EditText) findViewById(R.id.editItem);
        editText.append(task);
    }

    public void onSave(View view) {
        EditText editText = (EditText) findViewById(R.id.editItem);
        Intent i = new Intent();
        i.putExtra("todoItemSaved", editText.getText().toString());
        i.putExtra("positionSaved", pos);
        setResult(RESULT_OK, i);
        finish();
    }
}

