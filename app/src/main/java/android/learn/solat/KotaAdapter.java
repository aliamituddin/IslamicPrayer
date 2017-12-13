package android.learn.solat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.learn.solat.model.Kota;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Fajri on 13/12/2017.
 */

public class KotaAdapter extends RecyclerView.Adapter<KotaAdapter.RowHolder> {
    private ArrayList<Kota> listKota;
    private LayoutInflater inflater;
    SharedPreferences pref;
    private String fileName = "learn.android.Solat.sharedPref";
    Context context;

    public KotaAdapter(Context context, ArrayList<Kota> listKota) {
        this.context=context;
        this.inflater = LayoutInflater.from(context);
        this.listKota = listKota;
    }


    @Override
    public KotaAdapter.RowHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // meng-inflate View sebagai holder dari XML
        View holder = this.inflater.inflate(R.layout.item_kota,
                parent, false);

        return new RowHolder(holder);
    }

    @Override
    public void onBindViewHolder(KotaAdapter.RowHolder holder, int position) {

        final Kota k = this.listKota.get(position);

        // mapping
        holder.tvNama.setText(k.getNama_kota());
        holder.tvId.setText(k.getId());

        // ketika melakukan mapping view holder dengan elemen data tunggal
        // simpan index-nya untuk digunakan di lain waktu
        holder.position = position;
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
                pref.edit()
                        .putString("id", k.getId())
                        .putString("kota", k.getNama_kota())
                        .apply();
                ((PilihKota)context).onBackPressed();

            }
        });

    }

    @Override
    public int getItemCount() {
        return this.listKota.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {

        private TextView tvId;
        private TextView tvNama;
        public LinearLayout linearLayout;
        public int position;

        public RowHolder(View itemView) {

            super(itemView);

            this.tvNama = (TextView) itemView.findViewById(R.id.tvNama);
            this.tvId = (TextView) itemView.findViewById(R.id.tvId);
            linearLayout= (LinearLayout) itemView.findViewById(R.id.layout_kota);

        }

    }
}
