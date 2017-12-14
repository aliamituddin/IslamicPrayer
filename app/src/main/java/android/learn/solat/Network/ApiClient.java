package android.learn.solat.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Fajri on 03/12/2017.
 */

public class ApiClient {

    public static final String BASE_URL = "http://wahidganteng.ga/process/api/73ee6669460d3220e9aac187f945c7d9/";
    public static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }
}
