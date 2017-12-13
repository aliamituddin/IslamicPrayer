package android.learn.solat.asmaulhusna_qibla;

import android.content.Intent;
import android.learn.solat.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<String> name,meaning,transliteration;
    AsmaulHusnaAdapter adapter;
    private RecyclerView rv_asmaulHusna;
    private CardView arah_kiblat,asmaul_husna,doa_doa;
    FragmenAsmaulHusna fa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        arah_kiblat = (CardView)findViewById(R.id.card_view_arah_kiblat);
        asmaul_husna = (CardView)findViewById(R.id.card_view_asmaul_husna);
        doa_doa = (CardView)findViewById(R.id.card_view_doa_doa);
        asmaul_husna.setOnClickListener(this);
        arah_kiblat.setOnClickListener(this);
        doa_doa.setOnClickListener(this);


//        Uri uri  = NetworkUtils.buildAsmaulHusna();
//        new NetworkTask().execute(uri);
//
//        rv_asmaulHusna = (RecyclerView)findViewById(R.id.rv_asmaulhusna);
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        rv_asmaulHusna.setHasFixedSize(true);
//
//        // use a linear layout manager
//       LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
//        rv_asmaulHusna.setLayoutManager(mLayoutManager);
//        adapter = new AsmaulHusnaAdapter(this);
//
//
//
//        // specify an adapter (see also next example)
//        rv_asmaulHusna.setLayoutManager(mLayoutManager);
//
//        rv_asmaulHusna.setAdapter(adapter);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_view_arah_kiblat:
                Intent intent = new Intent(this,Main2Activity.class);
                startActivity(intent);
                break;
            case R.id.card_view_asmaul_husna:
                Intent intent1 = new Intent(this,FragmenActivity.class);
                startActivity(intent1);
                break;
            case R.id.card_view_doa_doa:
                Intent intent2 = new Intent(this, android.learn.solat.doa_doa.MainActivity.class);
                startActivity(intent2);
                break;

        }
    }
//    private ArrayList<String > getName(String json){
//        ArrayList<String> hasil = new ArrayList<>();
//        try {
//            JSONObject dataJson = new JSONObject(json);
//            JSONArray dataArray = dataJson.getJSONArray("data");
//                for (int i=0;i<dataArray.length();i++){
//                    JSONObject nameItem = dataArray.getJSONObject(i);
//                    String nama = nameItem.getString("name");
//                    hasil.add(nama);
//                }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return hasil;
//    }
//    private ArrayList<String> getTransliteration(String json){
//        ArrayList<String> hasil = new ArrayList<>();
//        try {
//            JSONObject dataJson = new JSONObject(json);
//            JSONArray dataArray = dataJson.getJSONArray("data");
//            for (int i=0;i<dataArray.length();i++){
//                JSONObject nameItem = dataArray.getJSONObject(i);
//                String nama = nameItem.getString("transliteration");
//                hasil.add(nama);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return hasil;
//    }
//    private ArrayList<String> getMeaning(String json){
//        ArrayList<String> hasil = new ArrayList<>();
//        try {
//            JSONObject dataJson = new JSONObject(json);
//            JSONArray dataArray = dataJson.getJSONArray("data");
//
//            for (int i=0;i<dataArray.length();i++){
//                JSONObject nameItem = dataArray.getJSONObject(i);
//                JSONObject a = nameItem.getJSONObject("en");
//                String nama = a.getString("meaning");
//                hasil.add(nama);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return hasil;
//    }
//
//
//
//    class NetworkTask extends AsyncTask<Uri,Void,String >{
//
//        @Override
//        protected String doInBackground(Uri... uris) {
//            URL AsmaulHusnaURL = null;
//
//            Uri uri = uris[0];
//            try {
//                AsmaulHusnaURL = new URL(uri.toString());
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//            if(AsmaulHusnaURL!=null){
//                try {
//                    return NetworkUtils.getResponseFromHttpUrl(AsmaulHusnaURL);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            name = getName(s);
//            meaning = getMeaning(s);
//            transliteration = getTransliteration(s);
//            adapter.swapData(transliteration,meaning,name);
//            adapter.notifyDataSetChanged();
//
//        }
//    }
}
