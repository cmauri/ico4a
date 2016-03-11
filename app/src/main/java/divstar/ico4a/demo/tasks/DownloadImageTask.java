package divstar.ico4a.demo.tasks;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apmem.tools.layouts.FlowLayout;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import divstar.ico4a.codec.ico.ICODecoder;

/**
 * Created by divStarHQ on 28.02.2016.
 */
public class DownloadImageTask extends AsyncTask<URL, Void, List<Bitmap>> {
    private WeakReference<View> target;

    public DownloadImageTask(View target) {
        this.target = new WeakReference<View>(target);
    }

    public static void copyStream(InputStream input, OutputStream output)
            throws IOException {
        byte[] buffer = new byte[1024]; // Adjust if you want
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
        output.flush();
    }

    @Override
    protected List<Bitmap> doInBackground(URL... urls) {
        List<Bitmap> images = null;

        try {
            HttpURLConnection urlConnection = (HttpURLConnection) urls[0].openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0");
            urlConnection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            urlConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            urlConnection.setRequestProperty("Accept-Encoding", "identity");

            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream faviconStream = urlConnection.getInputStream();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                copyStream(faviconStream, baos);

                faviconStream.close();
                urlConnection.disconnect();

                images = ICODecoder.read(new ByteArrayInputStream(baos.toByteArray()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return images;
    }

    @Override
    protected void onPostExecute(List<Bitmap> images) {
        TaskHelper.onPostExecute(target, images);
    }
}
