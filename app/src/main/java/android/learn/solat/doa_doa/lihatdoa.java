package android.learn.solat.doa_doa;

import android.learn.solat.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class lihatdoa extends AppCompatActivity {

    private ImageView imgDoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihatdoa);
        this.imgDoa = (ImageView) findViewById(R.id.imgDoa);

        Bundle bundle = getIntent().getExtras();
        int pos = bundle.getInt("pos");
        imgDoa.setImageResource(Data.picturePath[pos]);
    }
}
