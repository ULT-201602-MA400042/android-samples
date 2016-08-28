package androidcourse.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.Date;

import androidcourse.notes.Models.Note;
import io.realm.Realm;

public class EditNote extends AppCompatActivity {
    private Note noteToEdit;
    private Realm _context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        _context = Realm.getDefaultInstance();
        //obtaining the note from main activity
        Intent intent = getIntent();
        if (intent.hasExtra("note to edit")) {
            int id = intent.getIntExtra("note to edit", 0);
            noteToEdit = findNote(id);
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
        if (item.getItemId() == R.id.SaveNote) {
            String title = ((EditText) findViewById(R.id.title_edit)).getText().toString();
            String content = ((EditText) findViewById(R.id.note_info_edit)).getText().toString();
            //updating the note
            updateNote(title, content);
        } else if (item.getItemId() == R.id.DeleteNote) {
            deleteNote();
        }
        //Return to notesList activity
        finish();
        return true;
    }


    private Note findNote(int id) {
        return _context.where(Note.class).equalTo("id", id).findFirst();
    }

    private void updateNote(String title, String content) {
        _context.beginTransaction();
        noteToEdit.setTitle(title);
        noteToEdit.setContent(content);
        noteToEdit.setLastModified(new Date());
        _context.commitTransaction();
    }

    private void deleteNote() {
        _context.beginTransaction();
        noteToEdit.deleteFromRealm();
        _context.commitTransaction();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        _context.close();
    }
}
