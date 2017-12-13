package android.learn.solat.doa_doa;

import android.learn.solat.R;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private Fragment_doa fragment_doa;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        this.fragment_doa = new Fragment_doa();
        this.fm = this.getSupportFragmentManager();
        fm.beginTransaction().add(R.id.isifragment, fragment_doa, "log").commit();

    }

}