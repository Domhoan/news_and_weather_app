package com.example.news_and_weather_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeFragment extends Fragment {
    ListView listView;
    ArrayList<Item> listItem;
    ItemAdapter itemAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView = view.findViewById( R.id.idHome );
        listItem = new ArrayList<>(  );
        Bundle bundle = getArguments();
        String urlRSS = bundle.getString( "urlRSS" );
        new ReadRSS().execute( urlRSS );
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent , View view , int position , long id) {
                Object object = listView.getItemAtPosition( position );
                Item item = (Item) object;
                Intent intent = new Intent( getActivity(), DisplayWeb.class );
                intent.putExtra( "link",item.getLink() );
                startActivity( intent );
            }
        } );
        return view;
    }

    private class ReadRSS extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder(  );
            try {
                URL url = new URL( strings[0] );
                InputStreamReader inputStreamReader = new InputStreamReader( url.openConnection().getInputStream() );
                BufferedReader bufferedReader = new BufferedReader( inputStreamReader );
                String line = "";
                while ((line = bufferedReader.readLine())!=null){
                    content.append( line );
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute( s );
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument( s );
            NodeList nodeList = document.getElementsByTagName( "item" );
            NodeList nodeListDes = document.getElementsByTagName( "description" );
            for(int i = 0; i < nodeList.getLength(); i++){
                Item item = new Item();
                Element element = (Element) nodeList.item( i );
                String title = parser.getValue( element, "title" );
                String description = nodeListDes.item( i+1 ).getTextContent();
                Pattern p = Pattern.compile( "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>" );
                Matcher matcher = p.matcher( description );
                if(matcher.find()){
                    item.setImage( matcher.group(1) );
                }
                String pubDate = parser.getValue( element, "pubDate" );
                String link = parser.getValue( element, "link" );
                item.setTitle( title );
                item.setPubDate( pubDate );
                item.setLink( link );
                listItem.add( item );
            }
            itemAdapter= new ItemAdapter(getContext(), android.R.layout.simple_list_item_1,listItem);
            listView.setAdapter( itemAdapter );
        }
    }
}
