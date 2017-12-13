package android.learn.solat.Network;

import android.learn.solat.model.ItemKota;
import android.learn.solat.model.Items;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Fajri on 03/12/2017.
 */

public interface ApiInterface {

    @GET("jadwal-sholat?")
    Call<Items> getJadwalSholat(@Query("idk") String idKota, @Query("bln") String bulan, @Query("thn") String tahun);

    @GET("jadwal-sholat/get-kota")
    Call<ItemKota> getKota();

}
