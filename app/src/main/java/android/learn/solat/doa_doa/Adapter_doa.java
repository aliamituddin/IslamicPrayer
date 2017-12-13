package android.learn.solat.doa_doa;

import android.content.Context;
import android.content.Intent;
import android.learn.solat.R;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by User on 12/11/2017.
 */

public class Adapter_doa extends RecyclerView.Adapter <Adapter_doa.viewholderdoa> {

    private Context context;

    @Override
    public Adapter_doa.viewholderdoa onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doa, parent, false);
        return new viewholderdoa(view);
    }

    @Override
    public void onBindViewHolder(Adapter_doa.viewholderdoa holder, final int position) {
        holder.tvdoa.setText(Data.nama[position]);
        final int pos = Data.pos[position];
        holder.tvdoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, lihatdoa.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pos", pos);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Data.nama.length;
    }

    public class viewholderdoa extends RecyclerView.ViewHolder{

        public final TextView tvdoa;

        public viewholderdoa(View itemView) {
            super(itemView);
            this.tvdoa = itemView.findViewById(R.id.tvdoa);
        }
    }
}
