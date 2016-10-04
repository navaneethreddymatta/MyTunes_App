package com.example.navanee.mytunes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by navanee on 03-10-2016.
 */
public class TunesAdapter extends ArrayAdapter<Tune> {

    List<Tune> tunesList;
    Context context;
    int resource;

    public TunesAdapter(Context context, int resource, List<Tune> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.tunesList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource,parent,false);
            holder = new ViewHolder();
            holder.holderText = (TextView) convertView.findViewById(R.id.rowText);
            holder.holderImage = (ImageView) convertView.findViewById(R.id.rowImage);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        Tune item = tunesList.get(position);
        if(item.isMatched()) {
            convertView.setBackgroundColor(Color.GREEN);
        } else {
            convertView.setBackgroundColor(Color.WHITE);
        }
        holder.holderText.setText(item.getTitle());
        if (holder.holderImage != null) {
            Picasso.with(context).load(item.getThumbnail()).into(holder.holderImage);
            //new SetImageInRow(holder.holderImage).execute(item.getThumbnail());
        }
        return convertView;
    }
}
class ViewHolder {
    TextView holderText;
    ImageView holderImage;
}

