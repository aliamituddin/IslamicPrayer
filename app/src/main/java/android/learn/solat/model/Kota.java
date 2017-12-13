package android.learn.solat.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajri on 13/12/2017.
 */

public class Kota {

    @SerializedName("id")
    public String id;

    @SerializedName("nama_kota")
    public String nama_kota;


    public Kota(String id, String nama_kota) {
        this.id = id;
        this.nama_kota = nama_kota;
    }

    public String getId() {
        return id;
    }

    public String getNama_kota() {
        return nama_kota;
    }
}
