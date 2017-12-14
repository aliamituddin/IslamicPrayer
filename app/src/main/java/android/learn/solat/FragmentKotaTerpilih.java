package android.learn.solat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class FragmentKotaTerpilih extends Fragment {
   private View view;
    private String fileName = "learn.android.Solat.sharedPref";
    private SharedPreferences sharedPreferences;
    private String kota,idKota;
    TextView tvKota,tvIdKota;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = (View) inflater.inflate(R.layout.fragment_kota_terpilih,container,false);
        this.tvKota = view.findViewById(R.id.tvFragKota);
        this.tvIdKota = view.findViewById(R.id.tvFragIdKota);
        this.sharedPreferences = getActivity().getSharedPreferences(this.fileName, Context.MODE_PRIVATE);
        this.kota = this.sharedPreferences.getString("kota","Malang");
        this.idKota = this.sharedPreferences.getString("id","142");
        this.tvKota.setText(this.kota+"");
        this.tvIdKota.setText(this.idKota+"");
        return view;
    }

}
