package androidcourse.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidcourse.myapplication.Models.Car;
import androidcourse.myapplication.R;

/**
 * Created by DevAdmin on 24/08/2016.
 */
public class CarAdapter extends ArrayAdapter<Car> {
    private Context _context;
    private List<Car> _cars;
    private int _resource;

    //resource is the layout for the row
    public CarAdapter(Context context, int resource, List<Car> objects) {
        super(context, resource, objects);
        _cars = objects;
        _context = context;
        _resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row==null){
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(_resource, null);
        }
        TextView make = (TextView)row.findViewById(R.id.makeTxt);
        TextView model = (TextView)row.findViewById(R.id.modelTxt);

        Car c = _cars.get(position);
        make.setText(c.getMake().toString());
        model.setText(c.getModel().toString());
        return row;
    }
}
