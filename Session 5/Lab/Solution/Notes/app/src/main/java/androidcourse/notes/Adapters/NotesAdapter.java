package androidcourse.notes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidcourse.notes.Models.Note;
import androidcourse.notes.R;

/**
 * Created by Juan on 19/08/2016.
 */
public class NotesAdapter extends ArrayAdapter<Note> {

    private Context mContext;
    private List<Note> mNotelist;
    private int mLayoutResourceId;

    private static class ViewHolder {
        TextView title;
        TextView date;
        ImageView img;
        ImageView pwd;
    }

    public NotesAdapter(Context context, int layoutResourceId, List<Note> notelist) {
        super(context, layoutResourceId, notelist);
        mContext = context;
        mNotelist = notelist;
        mLayoutResourceId = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;

        /* There is no view at this position, we create a new one.
       In this case by inflating an xml layout */

        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            rowView = inflater.inflate(mLayoutResourceId, parent, false);
            // configure view holder
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) rowView.findViewById(R.id.title);
            viewHolder.date = (TextView) rowView.findViewById(R.id.lastModified);
            viewHolder.img = (ImageView) rowView.findViewById(R.id.icon);
            viewHolder.pwd = (ImageView) rowView.findViewById(R.id.pwdImg);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        //fill data
        viewHolder.title.setText(mNotelist.get(position).getTitle());
        viewHolder.date.setText(mNotelist.get(position).dateFormatted());
        if (mNotelist.get(position).getPassword() != null) {
            viewHolder.pwd.setImageResource(R.drawable.password);
        } else {
            viewHolder.pwd.setImageDrawable(null);
        }
        return rowView;
    }
}

