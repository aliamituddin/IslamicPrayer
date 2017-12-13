package android.learn.solat;

import android.learn.solat.Network.ApiClient;
import android.learn.solat.Network.ApiInterface;
import android.learn.solat.model.ItemKota;
import android.learn.solat.model.Items;
import android.learn.solat.model.Jadwal;
import android.learn.solat.model.Kota;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilihKota extends AppCompatActivity {
    ArrayList<Kota> listKota = new ArrayList<Kota>();
    KotaAdapter kotaAdapter;
    RecyclerView rvKota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_kota);
        rvKota = (RecyclerView) this.findViewById(R.id.rvListKota);
        // buat BookAdapter
        this.kotaAdapter = new KotaAdapter(this, listKota);

        // set BookAdapter sebagai adapter untuk RecyclerView
        this.rvKota.setAdapter(kotaAdapter);

        // set layout manager yang mengelola tampilan recyclerView
        // dengan: LinearLayoutManager
        rvKota.setLayoutManager(new LinearLayoutManager(this));

        // ngambil data dari api lalu menampilkannya


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ItemKota> call = apiService.getKota();

        call.enqueue(new Callback<ItemKota>() {
            @Override
            public void onResponse(Call<ItemKota> call, Response<ItemKota> response) {
                List<Kota> kota= response.body().getItemKota();
                loadData(kota);

            }

            @Override
            public void onFailure(Call<ItemKota> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return false;
        }

    }

    private void loadData(List<Kota> kota) {
        for (Kota data: kota) {
                listKota.add(data);
                this.kotaAdapter.notifyDataSetChanged();
        }
    }
}
