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
import java.util.ArrayList;
import java.util.List;

import divstar.ico4a.codec.ico.ICODecoder;

/**
 * Created by divStarHQ on 28.02.2016.
 */
public class GetResourceImageTask extends AsyncTask<Integer, Void, List<Bitmap>> {
    private Context context;
    private WeakReference<View> target;

    public GetResourceImageTask(View target) {
        this.context = target.getContext();
        this.target = new WeakReference<View>(target);
    }

    @Override
    protected List<Bitmap> doInBackground(Integer[] ids) {
        List<Bitmap> images = new ArrayList<>();

        try {
            for (Integer id : ids) {
                List<Bitmap> decodedImages = ICODecoder.read(context.getResources().openRawResource(id));
                if (decodedImages != null && decodedImages.size() > 0) {
                    images.addAll(decodedImages);
                }
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
