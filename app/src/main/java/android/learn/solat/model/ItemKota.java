package android.learn.solat.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Fajri on 13/12/2017.
 */

public class ItemKota {
    @SerializedName("data")
    public List<Kota> itemKota;

    public List<Kota> getItemKota(){ return itemKota;}

    public void setItemKota(List<Kota> itemKota) { this.itemKota = itemKota; }

    public ItemKota(List<Kota> itemKota) {this.itemKota= itemKota;}

}
