package androidcourse.notes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import androidcourse.notes.Adapters.NotesAdapter;
import androidcourse.notes.Models.Note;

public class NotesList extends AppCompatActivity {
    private static final int ADD_NOTE_REQUEST = 1;
    private static final int EDIT_NOTE_REQUEST = 2;
    private ArrayList<Note> notes = new ArrayList<Note>();
    private NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        final ListView notesListView = (ListView) findViewById(R.id.listView);
        adapter = new NotesAdapter(this, R.layout.note_row, notes);
        notesListView.setAdapter(adapter);

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //get the selected note from the list
                Note note = notes.get(position);
                //if note is password protected
                if (note.getPassword() != null) {
                    displayPinPrompt(note);
                } else {
                    editNoteIntent(note);
                }
            }
        });



        //listener for add NoteImage
        ImageView addNoteImg = (ImageView) findViewById(R.id.addNoteImg);
        addNoteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create an intent
                Intent addNoteIntent = new Intent(getBaseContext(), AddNote.class);
                startActivityForResult(addNoteIntent, ADD_NOTE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_NOTE_REQUEST) {
            if (resultCode == RESULT_OK) {
                //get the new note from the intent
                Note newNote = (Note) data.getSerializableExtra("note");
                notes.add(newNote);
                //notify the adapter and
                // update the listView adapter with the new Note
                adapter.notifyDataSetChanged();
            }
        } else if (requestCode == EDIT_NOTE_REQUEST) {
            if (resultCode == RESULT_OK) {
                //get the edited note from the intent
                Note updatedNote = (Note) data.getSerializableExtra("note");
                updateNotesList(updatedNote);
                //notify the adapter and
                // update the listView
                adapter.notifyDataSetChanged();
            }
        }
    }


    private void editNoteIntent(Note note) {
        Intent editNoteIntent = new Intent(NotesList.this, EditNote.class);
        editNoteIntent.putExtra("note to edit", note);
        startActivityForResult(editNoteIntent, EDIT_NOTE_REQUEST);
    }

    private void displayPinPrompt(final Note note) {
        View layout = getLayoutInflater().inflate(R.layout.pin_prompt_layout, null);
        final EditText password1 = (EditText) layout.findViewById(R.id.pwd1);
        final TextView error = (TextView) layout.findViewById(R.id.TextView_PwdProblem);

        password1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                String strPass1 = password1.getText().toString();
                //validate if password is correct
                if (!strPass1.equals(note.getPassword())) {
                    error.setText("Invalid Password");
                    error.setTextColor(Color.RED);
                }
                else
                {
                    error.setText("Valid Password");
                    error.setTextColor(Color.GREEN);
                }
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(NotesList.this);
        builder.setView(layout);
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String strPassword1 = password1.getText().toString();
                if (strPassword1.equals(note.getPassword())) {
                    editNoteIntent(note);
                }
            }
        });
        AlertDialog passwordDialog = builder.create();
        passwordDialog.show();
    }


    private void updateNotesList(Note updatedNote) {
        Note note = null;
        for (Note n : notes) {
            if (updatedNote.getId() == n.getId()) {
                note = n;
                break;
            }
        }
        int pos = notes.indexOf(note);
        notes.set(pos, updatedNote);
    }

}
