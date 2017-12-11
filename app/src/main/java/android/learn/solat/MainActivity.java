package android.learn.solat;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.learn.solat.model.Items;
import android.learn.solat.model.Jadwal;
import android.learn.solat.Network.ApiClient;
import android.learn.solat.Network.ApiInterface;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvSubuh;
    TextView tvZuhur;
    TextView tvAshar;
    TextView tvMaghrib;
    TextView tvIsya;
    TextView tvKota,tvTanggal;
    Button btnLoadData;
    String formattedDate;
    String []date;
    android.support.v7.widget.Toolbar toolbar;
    String kota,idKota;
    ImageView btSubuh,btZuhur,btAshar,btMaghrib,btIsya;
    boolean nfSubuh=false;
    boolean nfZuhur=false;
    boolean nfAshar=false;
    boolean nfMaghrib=false;
    boolean nfIsya=false;
    Jadwal besok,today;
    private TextView tvJam;
    private TextView tvMenit;
    private TextView tvDetik;
    private TextView tvJadwalTujuan;
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);
        this.notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        kota = "Malang";
        idKota = "142";
        //ambil format tanggal dan bulan saat ini;
            Calendar c = Calendar.getInstance();
            System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        formattedDate = df.format(c.getTime());
        date = formattedDate.split("-");

        this.tvSubuh = findViewById(R.id.tvSubuh);
        this.tvZuhur = findViewById(R.id.tvZuhur);
        this.tvAshar = findViewById(R.id.tvAshar);
        this.tvMaghrib = findViewById(R.id.tvMagrhib);
        this.tvIsya = findViewById(R.id.tvIsya);
        this.tvTanggal = findViewById(R.id.tvTanggal);
        this.tvKota = findViewById(R.id.tvKota);
        this.btSubuh = (ImageView) findViewById(R.id.btSubuh);
        this.btZuhur = (ImageView) findViewById(R.id.btZuhur);
        this.btAshar = (ImageView) findViewById(R.id.btAshar);
        this.btMaghrib = (ImageView) findViewById(R.id.btMaghrib);
        this.btIsya = (ImageView) findViewById(R.id.btIsya);
        this.tvJam  = (TextView) findViewById(R.id.tvJam);
        this.tvMenit  = (TextView) findViewById(R.id.tvMenit);
        this.tvDetik = (TextView) findViewById(R.id.tvDetik);
        this.tvJadwalTujuan = (TextView) findViewById(R.id.tvJadwalTujuan);


        btSubuh.setOnClickListener(this);
        btIsya.setOnClickListener(this);
        btMaghrib.setOnClickListener(this);
        btZuhur.setOnClickListener(this);
        btAshar.setOnClickListener(this);





        // ngambil data dari api lalu menampilkannya
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
    CountDownTask cdTask;

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
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btSubuh :
                if(this.nfSubuh==false){
                    nfSubuh = true;
                    btSubuh.setImageResource(R.drawable.notif_on);
                }
                else if(this.nfSubuh==true){
                    nfSubuh = false;
                    btSubuh.setImageResource(R.drawable.notif_off);
                }
                break;
            case R.id.btZuhur :
                if(nfZuhur==false){
                    nfZuhur = true;
                    btZuhur.setImageResource(R.drawable.notif_on);
                }
                else if(nfZuhur==true){
                    nfZuhur = false;
                    btZuhur.setImageResource(R.drawable.notif_off);
                }
                break;
            case R.id.btAshar :
                if(nfAshar==false){
                    nfAshar = true;
                    btAshar.setImageResource(R.drawable.notif_on);
                }
                else if(nfAshar==true){
                    nfAshar = false;
                    btAshar.setImageResource(R.drawable.notif_off);
                }
                break;
            case R.id.btMaghrib :
                if(nfMaghrib==false){
                    nfMaghrib = true;
                    btMaghrib.setImageResource(R.drawable.notif_on);
                }
                else if(nfMaghrib==true){
                    nfMaghrib = false;
                    btMaghrib.setImageResource(R.drawable.notif_off);
                }
                break;
            case R.id.btIsya :
                if(nfIsya==false){
                    nfIsya = true;
                    btIsya.setImageResource(R.drawable.notif_on);
                }
                else if(nfIsya==true){
                    nfIsya = false;
                    btIsya.setImageResource(R.drawable.notif_off);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cdTask.cancel(true);
    }
}
