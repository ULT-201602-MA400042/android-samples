package androidcourse.intentsexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItem extends AppCompatActivity {

    private EditText item;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        button = (Button)findViewById(R.id.addBtnId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = (EditText)findViewById(R.id.itemId);
                Intent i = getIntent();
                i.putExtra("newItem",item.getText().toString());
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}
