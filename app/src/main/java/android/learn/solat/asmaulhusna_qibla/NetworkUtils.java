package android.learn.solat.asmaulhusna_qibla;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Aditya on 12/9/2017.
 */

public class NetworkUtils {

    public static final String BASE_URL ="http://api.aladhan.com/";

    public static Uri buildAsmaulHusna(){
        Uri.Builder asmaulHusna = new Uri.Builder();
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendPath("asmaAlHusna")
                .build();
        return  uri;
    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
