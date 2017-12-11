package android.learn.solat.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Fajri on 03/12/2017.
 */

public class Items {

    @SerializedName("data")
    public List<Jadwal> items;

    public List<Jadwal> getItems(){ return items;}

    public void setItems(List<Jadwal> items) { this.items = items; }

    public Items (List<Jadwal> items) {this.items= items;}

}
