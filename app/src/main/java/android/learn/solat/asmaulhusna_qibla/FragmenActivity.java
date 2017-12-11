package android.learn.solat.asmaulhusna_qibla;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.learn.solat.R;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FragmenActivity extends AppCompatActivity implements View.OnClickListener {
    FragmentManager fm;
    FragmenAsmaulHusna fa1;
    FragmenAsmaulHusna2 fa2;
    private Button btn_1;
    private Button btn_2;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmen);

        fa1 = new FragmenAsmaulHusna();
        fa2 = new FragmenAsmaulHusna2();

        this.fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.linear_fragment,fa1)
                .commit();
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);

        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);



    }
    private int getMatColor(String typeColor)
    {
        int returnColor = Color.BLACK;
        int arrayId = getResources().getIdentifier("mdcolor_" + typeColor, "colors", getApplicationContext().getPackageName());

        if (arrayId != 0)
        {
            TypedArray colors = getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.BLACK);
            colors.recycle();
        }
        return returnColor;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_1:
                fm.beginTransaction().replace(R.id.linear_fragment,fa1).commit();
                break;
            case R.id.btn_2:
                fm.beginTransaction().replace(R.id.linear_fragment,fa2).commit();
                break;
        }

    }
}
