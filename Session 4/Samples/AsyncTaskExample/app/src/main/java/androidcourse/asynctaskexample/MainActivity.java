package androidcourse.asynctaskexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView status;
    private Button clickMe;
    private Button start;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = (ProgressBar) findViewById(R.id.progressBar);
        bar.setMax(10);
        clickMe = (Button) findViewById(R.id.clickMeId);
        clickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button is Functional", Toast.LENGTH_SHORT).show();
            }
        });

        status = (TextView) findViewById(R.id.statusd);

        start = (Button) findViewById(R.id.startId);
        //Listener for the button
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask task = new MyTask();
                task.execute(1000);
            }
        });
    }

    class MyTask extends AsyncTask<Integer, Integer, String> {

        //Executed first before the task starts
        @Override
        protected void onPreExecute() {
            status.setText("Task is starting");
        }

        @Override
        protected String doInBackground(Integer... params) {

            //Execute task for 10 seconds
            for (int i = 1; i < 11; i++) {
                try {
                    Thread.sleep(params[0]);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Task Completed";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            bar.setVisibility(View.VISIBLE);
            status.setText("Task Started");
            bar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            status.setText(s);
            start.setText("restart");
        }
    }
}
