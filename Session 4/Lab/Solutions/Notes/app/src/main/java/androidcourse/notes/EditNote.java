package androidcourse.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import androidcourse.notes.Models.Note;

public class EditNote extends AppCompatActivity {
    private Note noteToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        //obtaining the note from main activity
        Intent intent = getIntent();
        if (intent.hasExtra("note to edit")) {
            noteToEdit = (Note) intent.getSerializableExtra("note to edit");
        }
        //updating the UI with the note info
        EditText title = (EditText) findViewById(R.id.title_edit);
        title.setText(noteToEdit.getTitle());
        EditText content = (EditText) findViewById(R.id.note_info_edit);
        content.setText(noteToEdit.getContent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.SaveNote)
        {
            String title = ((EditText) findViewById(R.id.title_edit)).getText().toString();
            String content = ((EditText) findViewById(R.id.note_info_edit)).getText().toString();
            //updating the note
            noteToEdit.setTitle(title);
            noteToEdit.setContent(content);
            //responding to the request from main activity
            Intent editNote = new Intent();
            editNote.putExtra("note", noteToEdit);
            setResult(RESULT_OK, editNote);
            finish();
        }
        return true;
    }
}
