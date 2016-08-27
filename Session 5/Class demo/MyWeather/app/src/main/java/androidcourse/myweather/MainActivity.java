package androidcourse.myweather;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText city;
    private TextView temp;
    private String API_KEY = "1098ea3ca980ea6232af4ed104200829";
    private String URL_LINK = "http://api.openweathermap.org/data/2.5/weather?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button search = (Button)findViewById(R.id.button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetTemp task = new GetTemp();
                city = (EditText)findViewById(R.id.editText);
                String query = URL_LINK+city.getText().toString()+"&appid="+API_KEY;
                task.execute(query);
            }
        });


    }

    class GetTemp extends AsyncTask<String,Void,String>
    {
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection c = null;
            try {
                URL u = new URL(params[0]);
                c = (HttpURLConnection) u.openConnection();
                c.setRequestMethod("GET");
                c.setConnectTimeout(8000);
                c.connect();
                int status = c.getResponseCode();
                switch (status) {
                    case 200:
                    case 201:
                        BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line);
                        }
                        br.close();
                        return sb.toString();
                }
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (c != null) {
                    c.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //parse json result from doinbackground
            if(s!=null){
                try {
                    JSONObject object = new JSONObject(s);
                    JSONObject main = object.getJSONObject("main");
                    double tempVal = main.getDouble("temp");

                    temp = (TextView)findViewById(R.id.currentTemp);
                    temp.setText(tempVal+"");




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
