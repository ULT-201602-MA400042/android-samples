package androidcourse.listviewexample.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidcourse.listviewexample.Models.Person;
import androidcourse.listviewexample.R;

/**
 * Created by DevAdmin on 20/08/2016.
 */
public class PersonAdapter extends ArrayAdapter<Person> {
    private ImageView personImage;
    private TextView personName;
    private TextView personDob;
    private Context context;
    private int resource;
    private Person[] people;

    public PersonAdapter(Context context, int resource, Person[] people) {
        super(context, resource, people);
        this.context = context;
        this.resource = resource;
        this.people = people;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // assign the view we are converting to a local variable
        View row = convertView;
        if (row == null) {
            // first check to see if the view is null. if so, we have to inflate it.
            // to inflate it basically means to render, or show, the view.
            // Get the LayoutInflator from Activity/Context
            // Instantiates a layout XML file into its corresponding View objects
            LayoutInflater inflater = LayoutInflater.from(context);
            // Inflate the row template with the xml layout passed to this Adapter
            row = inflater.inflate(resource, parent, false);
        }
        personImage = (ImageView)row.findViewById(R.id.imageId);
        personName = (TextView)row.findViewById(R.id.nameId);
        personDob = (TextView)row.findViewById(R.id.dobId);

        Person p = people[position];
        personName.setText(p.getName());
        personDob.setText(p.getDobAsString());
        //get id for the image based on female or male
        int resId = context.getResources().getIdentifier(p.getImageName(), "drawable", context.getPackageName());
        personImage.setImageResource(resId);
        return row;
    }
}










