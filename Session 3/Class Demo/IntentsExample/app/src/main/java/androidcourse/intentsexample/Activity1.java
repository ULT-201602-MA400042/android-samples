package androidcourse.intentsexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity1 extends AppCompatActivity {

    private Button login;
    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        login = (Button)findViewById(R.id.loginBtnId);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = (EditText)findViewById(R.id.nameId);
                //create intent to open welcome activity
                Intent openWelcome = new Intent(Activity1.this,WelcomeScreen.class);
                openWelcome.putExtra("name",name.getText().toString());
                startActivity(openWelcome);
            }
        });


    }
}
