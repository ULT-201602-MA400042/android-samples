package course.android.countries;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private ImageButton search;
    private EditText countryName;
    static final String MYURL = "https://restcountries.eu/rest/v1/name/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = (ImageButton) findViewById(R.id.imageButton);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country = ((EditText) findViewById(R.id.countryIn)).getText().toString();
                if (country.contains(" ")) {
                    country = country.replace(" ", "%20");
                }
                new GetCountryInfo().execute(MYURL + country);
            }
        });
    }

    public String readJSONFeed(String link) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(link);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setConnectTimeout(8000);
            c.setAllowUserInteraction(false);
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


    private class GetCountryInfo extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... links) {
            return readJSONFeed(links[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    JSONArray array = new JSONArray(result);
                    JSONObject object = (JSONObject) array.get(0);
                    ((TextView) findViewById(R.id.capitalCity)).setText(object.getString("capital"));
                    JSONArray currencies = object.getJSONArray("currencies");
                    ((TextView) findViewById(R.id.currency)).setText(currencies.get(0).toString());
                    DecimalFormat df = new DecimalFormat("#,###.#");
                    ((TextView) findViewById(R.id.population)).setText(df.format(object.getDouble("population")));
                    ((TextView) findViewById(R.id.region)).setText(object.getString("region"));
                    String countryCode = object.getString("alpha2Code");
                    int resId = getResources().getIdentifier(countryCode.toLowerCase(), "drawable", getPackageName());
                    ((ImageView) findViewById(R.id.flag)).setImageResource(resId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(MainActivity.this, "Invalid Country Name", Toast.LENGTH_LONG).show();
            }
        }
    }


}
