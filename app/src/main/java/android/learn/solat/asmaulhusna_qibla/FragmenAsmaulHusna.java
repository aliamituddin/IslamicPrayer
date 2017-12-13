package android.learn.solat.asmaulhusna_qibla;


import android.learn.solat.R;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Aditya on 12/10/2017.
 */

public class FragmenAsmaulHusna extends Fragment {

    private View view;

    private ArrayList<String> name,meaning,transliteration,no;
    AsmaulHusnaAdapter adapter;
    private RecyclerView rv_asmaulHusna;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        this.view = (View) inflater.inflate(R.layout.activity_main2,container,false);


        rv_asmaulHusna = (RecyclerView)view.findViewById(R.id.rv_asmaulhusna);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        rv_asmaulHusna.setHasFixedSize(true);

        // use a linear layout manager
       LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv_asmaulHusna.setLayoutManager(mLayoutManager);
        adapter = new AsmaulHusnaAdapter(this.getActivity());



        // specify an adapter (see also next example)
        rv_asmaulHusna.setLayoutManager(mLayoutManager);

        rv_asmaulHusna.setAdapter(adapter);
        Uri uri  = NetworkUtils.buildAsmaulHusna();
        new NetworkTask().execute(uri);
        return view;
    }
    private ArrayList<String > getNo(String json){
        ArrayList<String> hasil = new ArrayList<>();
        try {
            JSONObject dataJson = new JSONObject(json);
            JSONArray dataArray = dataJson.getJSONArray("data");
            for (int i=0;i<45;i++){
                JSONObject nameItem = dataArray.getJSONObject(i);
                String nama = nameItem.getString("number");
                hasil.add(nama);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hasil;
    }
    private ArrayList<String > getName(String json){
        ArrayList<String> hasil = new ArrayList<>();
        try {
            JSONObject dataJson = new JSONObject(json);
            JSONArray dataArray = dataJson.getJSONArray("data");
            for (int i=0;i<45;i++){
                JSONObject nameItem = dataArray.getJSONObject(i);
                String nama = nameItem.getString("name");
                hasil.add(nama);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hasil;
    }
    private ArrayList<String> getTransliteration(String json){
        ArrayList<String> hasil = new ArrayList<>();
        try {
            JSONObject dataJson = new JSONObject(json);
            JSONArray dataArray = dataJson.getJSONArray("data");
            for (int i=0;i<45;i++){
                JSONObject nameItem = dataArray.getJSONObject(i);
                String nama = nameItem.getString("transliteration");
                hasil.add(nama);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hasil;
    }
    private ArrayList<String> getMeaning(String json){
        ArrayList<String> hasil = new ArrayList<>();
        try {
            JSONObject dataJson = new JSONObject(json);
            JSONArray dataArray = dataJson.getJSONArray("data");

            for (int i=0;i<45;i++){
                JSONObject nameItem = dataArray.getJSONObject(i);
                JSONObject a = nameItem.getJSONObject("en");
                String nama = a.getString("meaning");
                hasil.add(nama);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hasil;
    }


    class NetworkTask extends AsyncTask<Uri,Void,String > {

        @Override
        protected String doInBackground(Uri... uris) {
            URL AsmaulHusnaURL = null;

            Uri uri = uris[0];
            try {
                AsmaulHusnaURL = new URL(uri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            if(AsmaulHusnaURL!=null){
                try {
                    return NetworkUtils.getResponseFromHttpUrl(AsmaulHusnaURL);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            name = getName(s);
            meaning = getMeaning(s);
            no = getNo(s);
            transliteration = getTransliteration(s);
            adapter.swapData(transliteration,meaning,name,no);
            adapter.notifyDataSetChanged();

        }
    }

}
