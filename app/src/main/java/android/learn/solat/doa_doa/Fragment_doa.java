package android.learn.solat.doa_doa;

import android.learn.solat.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by User on 12/11/2017.
 */

public class Fragment_doa extends Fragment {

    private View view;
    private RecyclerView rvdoa;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_doa,container, false);
        this.rvdoa = (RecyclerView)view.findViewById(R.id.rvdoa);
        Adapter_doa adapter_doa = new Adapter_doa();
        rvdoa.setAdapter(adapter_doa);
        rvdoa.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}

