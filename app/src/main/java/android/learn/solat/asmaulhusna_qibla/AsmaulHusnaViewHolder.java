package android.learn.solat.asmaulhusna_qibla;

import android.learn.solat.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Aditya on 12/9/2017.
 */

public class AsmaulHusnaViewHolder extends RecyclerView.ViewHolder {

    public final TextView tv_arab;
    public final TextView tv_nama;
    public final TextView tv_arti;
    public final TextView no;

    public AsmaulHusnaViewHolder(View itemView) {
        super(itemView);

        tv_arab = (TextView)itemView.findViewById(R.id.tv_arab);
        tv_nama = (TextView)itemView.findViewById(R.id.info_text);
        tv_arti = (TextView)itemView.findViewById(R.id.tv_meaning);
        no = (TextView) itemView.findViewById(R.id.textView);
    }
}
