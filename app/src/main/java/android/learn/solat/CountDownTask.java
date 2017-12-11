package android.learn.solat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.learn.solat.model.Jadwal;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Fajri on 05/12/2017.
 */

public class CountDownTask extends AsyncTask<Void, Long, Void> {
    private final NotificationManager notificationManager;
    TextView tvJam,tvMenit,tvDetik,tvJadwalTujuan;
    long nowHour,nowMinute,nowSecond;
    String [] d1,d2,d3,d4,d5;
    long subuh,zuhur,ashar,maghrib,isya,nowmilis,toSubuh,toZuhur,toAshar,toMaghrib,toIsya;
    String status="",jamStatus="";
    Context context;
    private Notification myNotification;


    public CountDownTask(TextView tvJadwalTujuan, TextView tvJam, TextView tvMenit, TextView tvDetik, Jadwal data, Context context) {
        this.tvJam = tvJam;
        this.tvMenit = tvMenit;
        this.tvDetik = tvDetik;
        this.tvJadwalTujuan=tvJadwalTujuan;
        this.context = context;
        d1 = data.getShubuh().split(":");
        d2 = data.getDzuhur().split(":");
        d3 = data.getAshr().split(":");
        d4 = data.getMaghrib().split(":");
        d5 = data.getIsya().split(":");
        subuh = (Integer.parseInt(d1[0])*60*60*1000)+ (Integer.parseInt(d1[1])*60*1000);
        zuhur = (Integer.parseInt(d2[0])*60*60*1000)+ (Integer.parseInt(d2[1])*60*1000);
        ashar = (Integer.parseInt(d3[0])*60*60*1000)+ (Integer.parseInt(d3[1])*60*1000);
        maghrib = (Integer.parseInt(d4[0])*60*60*1000)+ (Integer.parseInt(d4[1])*60*1000);
        isya = (Integer.parseInt(d5[0])*60*60*1000)+ (Integer.parseInt(d5[1])*60*1000);
        this.notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }


    @Override
    protected Void doInBackground(Void... voids) {
        try{
            while(true) {
                this.status="";
                Calendar rightnow = Calendar.getInstance();

                nowHour = rightnow.get(Calendar.HOUR_OF_DAY)*60;
                nowMinute = (nowHour+ rightnow.get(Calendar.MINUTE)) * 60 * 1000;
                nowSecond = 60 - rightnow.get(Calendar.SECOND);
                nowmilis = (nowHour)+(nowMinute);
                if(nowmilis<=(subuh+300000)){
                    this.status="Subuh";
                    toSubuh = subuh-nowmilis;
                    publishProgress(toSubuh,nowSecond);
                }
                else if(nowmilis<=(zuhur+300000)){
                    this.status="Zuhur";
                    toZuhur = zuhur-nowmilis;
                    publishProgress(toZuhur,nowSecond);
                }
                else if(nowmilis<=(ashar+300000)){
                    this.status="Ashar";
                    toAshar = ashar-nowmilis;
                    publishProgress(toAshar,nowSecond);
                }
                else if(nowmilis<=(maghrib+300000)){
                    this.status="Maghrib";
                    toMaghrib = maghrib-nowmilis;
                    publishProgress(toMaghrib,nowSecond);
                }
                else if(nowmilis<=(isya+300000)){
                    this.status="Isya";
                    toIsya = isya-nowmilis;
                    publishProgress(toIsya,nowSecond);
                }
                else if (nowmilis>=(isya+300000)) {
                    this.status = "Isya";
                    publishProgress(toIsya, nowSecond);
                    this.cancel(true);
                }
                Thread.sleep(999);
            }

        }catch (Exception e) {}
        return null;
    }

    @Override
    protected void onProgressUpdate(Long... values) {
        super.onProgressUpdate(values);
        switch(this.status){
            case "Subuh":
                tvJadwalTujuan.setText(status);
                break;
            case "Zuhur":
                tvJadwalTujuan.setText(status);
                break;
            case "Ashar":
                tvJadwalTujuan.setText(status);
                break;
            case "Maghrib":
                tvJadwalTujuan.setText(status);
                break;
            case "Isya":
                tvJadwalTujuan.setText(status);
                break;
        }


        if(values[0]>0) {
            tvJam.setText(((values[0] / 3600000)) + " Jam");
            tvMenit.setText(((values[0]-((values[0] / 3600000)*3600000)) /60000) + " Menit");
            tvDetik.setText(values[1] + " Detik");
        }else{
            tvJam.setText("");
            tvMenit.setText("Saat Ini "+this.status);
            setNotif(status,d1[0]+":"+d1[1]);
            tvDetik.setText("");
        }
    }

    protected void setNotif(String status,String jamStatus){
        this.myNotification = new NotificationCompat.Builder(context)
                .setContentTitle("Waktu "+status+" Telah Tiba")
                .setContentText(jamStatus)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTimeoutAfter(900000)
                .setAutoCancel(true)
                .build();

        this.notificationManager.notify(11,this.myNotification);

    }
}
