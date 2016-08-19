package androidcourse.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import androidcourse.notes.Adapters.NotesAdapter;
import androidcourse.notes.Models.Note;

public class NotesList extends AppCompatActivity {
    private ArrayList<Note> notes = new ArrayList<Note>() {{

        add(new Note("Note 1", "This is an example of my first note"));
        add(new Note("Note 2", "This is an example of my second note"));
        add(new Note("Note 3", "This is an example of my third note", "123"));
        add(new Note("Note 4", "This is an example of my fourth note"));
        add(new Note("Note 5", "This is an example of my fifth note", "123"));
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        final ListView notesListView = (ListView) findViewById(R.id.listView);
        NotesAdapter adapter = new NotesAdapter(this, R.layout.note_row, notes);
        notesListView.setAdapter(adapter);

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(NotesList.this, notes.get(position).getTitle().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
