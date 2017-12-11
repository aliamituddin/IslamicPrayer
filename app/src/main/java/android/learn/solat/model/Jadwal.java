package android.learn.solat.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajri on 03/12/2017.
 */

public class Jadwal {

    @SerializedName("tanggal")
    public String tanggal;

    @SerializedName("shubuh")
    public String shubuh;

    @SerializedName("terbit")
    public String terbit;

    @SerializedName("dzuhur")
    public String dzuhur;

    @SerializedName("ashr")
    public String ashr;

    @SerializedName("maghrib")
    public String maghrib;

    @SerializedName("isya")
    public String isya;

    public String getTanggal() {
        return tanggal;
    }

    public String getShubuh() {
        return shubuh;
    }

    public String getTerbit() {
        return terbit;
    }

    public String getDzuhur() {
        return dzuhur;
    }

    public String getAshr() {
        return ashr;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public String getIsya() {
        return isya;
    }

    public Jadwal(String tanggal, String shubuh, String terbit, String dzuhur, String ashr, String maghrib, String isya) {
        this.tanggal = tanggal;
        this.shubuh = shubuh;
        this.terbit = terbit;
        this.dzuhur = dzuhur;
        this.ashr = ashr;
        this.maghrib = maghrib;
        this.isya = isya;
    }
}
