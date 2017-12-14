package android.learn.solat.kalender;

import android.learn.solat.R;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private View view;
    private FragmentManager fm;
    private Button btnHijriah;
    private Button btnMasehi;
    FragmentHijriah fragH = new FragmentHijriah();
    FragmentMasehi fragM = new FragmentMasehi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kalender);

        this.btnHijriah = (Button) this.findViewById(R.id.button1);
        btnHijriah.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FragmentManager fm=getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.fragment2,new FragmentHijriah()).addToBackStack("")
                        .commit();
            }
        });

        this.btnMasehi = (Button) this.findViewById(R.id.button2);
        btnMasehi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FragmentManager fm=getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.fragment2,new FragmentMasehi()).addToBackStack("")
                        .commit();
            }
        });
    }

}
