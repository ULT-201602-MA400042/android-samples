package androidcourse.simplelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Juan on 19/08/2016.
 */
public class PersonAdapter extends ArrayAdapter<Person> {

    private Context mContext;
    private int mResource;
    private Person[] mPeople;

    public PersonAdapter(Context context, int resource, Person[] people) {
        super(context, resource, people);
        mContext = context;
        mPeople = people;
        mResource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView; // lets name convertView row, as its easier to understand

        // Get the LayoutInflater from Activity/Context
        // Instantiates a layout XML file into its corresponding View objects
        LayoutInflater inflater = LayoutInflater.from(mContext);
        // Inflate the row template with the xml layout passed to this Adapter
        row = inflater.inflate(mResource, parent, false);
        // ensure attachToRoot is false otherwise you may get an exception:
        // java.lang.UnsupportedOperationException: addView(View, LayoutParams)
        //is not supported in AdapterView

        // Get the View elements in the row template

        // NOTE: we must use row.findViewById() as we are in a class that is not an Activity
        ImageView coverImage = (ImageView) row.findViewById(R.id.imageView);
        TextView personName = (TextView) row.findViewById(R.id.textView);


        // Get the Person object for the current position this method is requesting
        Person p = mPeople[position];

        // Get the id of the drawable (image) for the person
        int resId = mContext.getResources().getIdentifier(p.getImageName(), "drawable", mContext.getPackageName());
        // name -> The file name of the resource (no extensions required!)
        // defType -> The type of the resource
        // defPackage -> The package where the resource lives

        // Now lets set the View elements with the values for the person
        coverImage.setImageResource(resId);
        personName.setText(p.getName());

        // Finally we return the row (View) so the ListView can display it :)
        return row;
    }
}
