package androidcourse.notes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidcourse.notes.Models.Note;

public class AddNote extends AppCompatActivity {

    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        final CheckBox password = (CheckBox) findViewById(R.id.pwdCheckBox);
        assert password != null;
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View layout = getLayoutInflater().inflate(R.layout.pin_layout, null);
                final EditText password1 = (EditText) layout.findViewById(R.id.pwd1);
                final EditText password2 = (EditText) layout.findViewById(R.id.pwd2);
                final TextView error = (TextView) layout.findViewById(R.id.TextView_PwdProblem);
                //Listener for the password checkbox
                password2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    public void afterTextChanged(Editable s) {
                        String strPass1 = password1.getText().toString();
                        String strPass2 = password2.getText().toString();
                        //validate if both passwords are the same
                        if (strPass1.equals(strPass2) && strPass2.length() == 4) {
                            error.setText("Passwords Match");
                            error.setTextColor(Color.GREEN);
                        } else if (strPass2.length() != 4) {
                            error.setText("Password must contain 4 digits");
                            error.setTextColor(Color.RED);
                        } else {
                            error.setText("Passwords do not Match");
                            error.setTextColor(Color.RED);
                        }
                    }
                });
                AlertDialog.Builder builder = new AlertDialog.Builder(AddNote.this);
                builder.setView(layout);
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //if password prompt is cancelled disable checkbox
                        password.setChecked(false);
                        mPassword = null;
                    }
                });
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String strPassword1 = password1.getText().toString();
                        String strPassword2 = password2.getText().toString();
                        if (strPassword1.equals(strPassword2)) {
                            mPassword = strPassword1;
                        }
                    }
                });
                AlertDialog passwordDialog = builder.create();
                passwordDialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.SaveNote) {
            String title = ((EditText) findViewById(R.id.title_add)).getText().toString();
            String contents = ((EditText) findViewById(R.id.title_add)).getText().toString();
            Note note;
            //check if the note has password set.
            if (mPassword == null) {
                note = new Note(title, contents);
            } else {
                note = new Note(title, contents, mPassword);
            }
            //return to NotesList activity
            Intent newNote = new Intent();
            //add new note to the intent
            newNote.putExtra("note", note);
            setResult(RESULT_OK, newNote);
            finish();
        }
        return true;
    }
}

