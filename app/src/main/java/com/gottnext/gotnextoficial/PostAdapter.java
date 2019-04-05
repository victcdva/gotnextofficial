package com.gottnext.gotnextoficial;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PostAdapter extends BaseAdapter {

//atributes
    private ArrayList<PostPerson> data;
    private Activity activity;
    private LayoutInflater inflater;

    public PostAdapter(Activity activity, ArrayList<PostPerson> data){
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater)this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){return this.data.size();}
    @Override
    public Object getItem(int position){return position;}
    @Override
    public long getItemId(int position){return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //View
        View view = convertView;
        if (view == null) view = inflater.inflate(R.layout.post_layout, null);
        //controls
        ImageView imagenPost = view.findViewById(R.id.ImagenPost);
        TextView textPost = view.findViewById(R.id.TextPost);
        ImageView ImagenPerson = view.findViewById(R.id.ImagenPersonPost);
        TextView textPerson = view.findViewById(R.id.TextPersonPost);

        PostPerson pp = this.data.get(position);

        imagenPost.setImageDrawable(pp.getImage());
        textPost.setText(pp.getPost());
        textPerson.setText(pp.getName() + " " + pp.getLastName());
        ImagenPerson.setImageDrawable(pp.getImagenPerson());

        return view;
    }


}
