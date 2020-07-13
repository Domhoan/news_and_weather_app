package com.example.news_and_weather_app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {
    ArrayList<Item> listItem;
    Context context;
    public ItemAdapter(Context context , int simple_list_item_1 , ArrayList<Item> listItem) {
        this.context = context;
        this.listItem = listItem;
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Item getItem(int position) {
        return listItem.get( position );
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position , View convertView , ViewGroup parent) {
        View viewItem;
        if (convertView == null) {
            viewItem = View.inflate(parent.getContext(), R.layout.item_layout, null);
        } else {
            viewItem = convertView;
        }
        Item item = getItem( position );
        ImageView imageView = viewItem.findViewById( R.id.image );
        Picasso.with( parent.getContext() ).load(item.getImage()).into(imageView);
        ((TextView) viewItem.findViewById( R.id.title)).setText(String.format(item.getTitle()));
        ((TextView) viewItem.findViewById( R.id.time)).setText(String.format(item.getPubDate()));
        return viewItem;
    }
}
