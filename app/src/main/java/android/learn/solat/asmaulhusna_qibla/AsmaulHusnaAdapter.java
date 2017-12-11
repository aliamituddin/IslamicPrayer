package android.learn.solat.asmaulhusna_qibla;

import android.content.Context;
import android.learn.solat.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Aditya on 12/9/2017.
 */

public class AsmaulHusnaAdapter extends RecyclerView.Adapter<AsmaulHusnaViewHolder> {
    private final Context mContext;
    private ArrayList<String> name;
    private ArrayList<String> arti;
    private ArrayList<String> arab;
    private ArrayList<String> no;

    public AsmaulHusnaAdapter(Context mContext){
        this.mContext = mContext;
    }
    public void swapData(ArrayList<String> name,ArrayList<String> arti,ArrayList<String> arab,ArrayList<String> no){
        this.name = name;
        this.arti = arti;
        this.arab = arab;
        this.no = no;

    }


    @Override
    public AsmaulHusnaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_view,parent,false);

        return new AsmaulHusnaViewHolder(view);


    }

    @Override
    public void onBindViewHolder(AsmaulHusnaViewHolder holder, int position) {
        holder.no.setText(no.get(position)+".");
        holder.tv_arab.setText(arab.get(position));
        holder.tv_arti.setText(arti.get(position));
        holder.tv_nama.setText(name.get(position));
    }

    @Override
    public int getItemCount() {
        if(arab==null){
            return 0;
        }
        return arab.size();

    }
}
