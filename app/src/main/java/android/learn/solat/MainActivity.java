package android.learn.solat;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.learn.solat.model.Items;
import android.learn.solat.model.Jadwal;
import android.learn.solat.Network.ApiClient;
import android.learn.solat.Network.ApiInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView tvSubuh;
    TextView tvZuhur;
    TextView tvAshar;
    TextView tvMaghrib;
    TextView tvIsya;
    TextView tvKota,tvTanggal;
    Button btnLoadData;
    String formattedDate;
    String []date;
    String kota,idKota;
    boolean nfSubuh=false;
    boolean nfZuhur=false;
    boolean nfAshar=false;
    boolean nfMaghrib=false;
    boolean nfIsya=false;
    ImageView ivBackground;
    Jadwal besok,today;
    private TextView tvJam;
    private TextView tvMenit;
    private TextView tvDetik;
    private TextView tvJadwalTujuan;
    private NotificationManager notificationManager;
    private String fileName = "learn.android.Solat.sharedPref";
    private SharedPreferences sharedPreferences;
    CountDownTask cdTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);
        this.notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        this.sharedPreferences = this.getSharedPreferences(this.fileName, Context.MODE_PRIVATE);
        kota = this.sharedPreferences.getString("kota","Malang");
        idKota = this.sharedPreferences.getString("id","142");
        //ambil format tanggal dan bulan saat ini;
            Calendar c = Calendar.getInstance();
            System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        formattedDate = df.format(c.getTime());
        date = formattedDate.split("-");
        if(c.get(Calendar.HOUR_OF_DAY)>=18||c.get(Calendar.HOUR_OF_DAY)<=5){
            this.ivBackground = findViewById(R.id.ivBackground);
            this.ivBackground.setImageResource(R.drawable.night);
        }else{
            this.ivBackground = findViewById(R.id.ivBackground);
            this.ivBackground.setImageResource(R.drawable.day);
        }

        this.tvSubuh = findViewById(R.id.tvSubuh);
        this.tvZuhur = findViewById(R.id.tvZuhur);
        this.tvAshar = findViewById(R.id.tvAshar);
        this.tvMaghrib = findViewById(R.id.tvMagrhib);
        this.tvIsya = findViewById(R.id.tvIsya);
        this.tvTanggal = findViewById(R.id.tvTanggal);
        this.tvKota = findViewById(R.id.tvKota);
        this.tvJam  = (TextView) findViewById(R.id.tvJam);
        this.tvMenit  = (TextView) findViewById(R.id.tvMenit);
        this.tvDetik = (TextView) findViewById(R.id.tvDetik);
        this.tvJadwalTujuan = (TextView) findViewById(R.id.tvJadwalTujuan);


    }


    @Override
    protected void onStart() {
        tvSubuh.setText("");
        tvZuhur.setText("");
        tvAshar.setText("");
        tvMaghrib.setText("");
        tvIsya.setText("");
        tvKota.setText("Waiting Data");
        tvJadwalTujuan.setText("Waiting Data");
        tvJam.setText("--");
        tvDetik.setText("--");
        tvMenit.setText("--");
        tvTanggal.setText(formattedDate);
        kota = this.sharedPreferences.getString("kota","Malang");
        idKota = this.sharedPreferences.getString("id","142");
        if(cdTask== null){
        }else{
            cdTask.cancel(true);
        }
        try{// ngambil data dari api lalu menampilkannya
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<Items> call = apiService.getJadwalSholat(idKota, date[1],date[2]);

            call.enqueue(new Callback<Items>() {
                @Override
                public void onResponse(Call<Items> call, Response<Items> response) {
                    List<Jadwal> jadwal = response.body().getItems();
                    loadData(jadwal);
                }

                @Override
                public void onFailure(Call<Items> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
        catch(Exception e){}
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainactivity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.btnKiblat:
                Intent i = new Intent(this, android.learn.solat.asmaulhusna_qibla.MainActivity.class);
                startActivity(i);
                break;
            case R.id.btnPilihKota:
                Intent a = new Intent(this, PilihKota.class);
                startActivity(a);

        }
        return super.onOptionsItemSelected(item);
    }

    private void loadData(List<Jadwal> jadwal) {
        for (Jadwal data: jadwal) {
            if (data.getTanggal().equals(date[0])) {
            tvSubuh.setText(data.getShubuh());
            tvZuhur.setText(data.getDzuhur());
            tvAshar.setText(data.getAshr());
            tvMaghrib.setText(data.getMaghrib());
            tvIsya.setText(data.getIsya());
            tvKota.setText(kota);
            tvTanggal.setText(formattedDate);
            today = data;
            cdTask = new CountDownTask(this.tvJadwalTujuan,this.tvJam,this.tvMenit,this.tvDetik,today,getApplicationContext());
            cdTask.execute();
        }else if (data.getTanggal()==(date[0])+1) {
                besok = data;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cdTask.cancel(true);
    }
}
