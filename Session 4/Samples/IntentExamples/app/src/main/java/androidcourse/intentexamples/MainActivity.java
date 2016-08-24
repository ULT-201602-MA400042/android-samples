package androidcourse.intentexamples;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Camera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void Maps(View view) {
        Uri location = Uri.parse("geo:0,0?q=ultimo+tafe,+sydney,+ultimo");
        Intent intent = new Intent(Intent.ACTION_VIEW, location);


        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void Dial(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + "011111111"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void Browse(View view) {
        Uri webpage = Uri.parse("http://www.android.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void Alarm(View view) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, "Wake up!")
                .putExtra(AlarmClock.EXTRA_HOUR, 5)
                .putExtra(AlarmClock.EXTRA_MINUTES, 12);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void Activity2(View view) {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    public void Activity2ForResult(View view) {
        String message = "Hello from activity 1";
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("message", message);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String x = data.getStringExtra("response");
            Toast.makeText(MainActivity.this, x, Toast.LENGTH_LONG).show();
        }
    }
}
