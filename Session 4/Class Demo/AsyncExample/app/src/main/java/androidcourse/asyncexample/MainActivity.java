package androidcourse.asyncexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button start;
    private TextView progress;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.startBtn);
        bar = (ProgressBar)findViewById(R.id.progressBar);
        progress = (TextView) findViewById(R.id.progressLbl);
        //create on click listener
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask task = new MyTask();
                task.execute(1000);

            }
        });
    }

    public void DisplayToast(View v) {
        Toast.makeText(MainActivity.this, "You clicked me", Toast.LENGTH_SHORT).show();
    }

    class MyTask extends AsyncTask<Integer,Integer,String>{

        @Override
        protected String doInBackground(Integer... params) {
            for (int i = 1; i < 11; i++) {
                try {
                    Thread.sleep(params[0]);
                } catch (InterruptedException e) {
                }
                publishProgress(i);
            }
            return "I'm done - task completed";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            bar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String message) {
            progress.setText(message);
        }

        @Override
        protected void onPreExecute() {
            bar.setVisibility(View.VISIBLE);
        }
    }
}
