package divstar.ico4a.demo.tasks;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apmem.tools.layouts.FlowLayout;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by divStarHQ on 11.03.2016.
 */
public class TaskHelper {
    public static void onPostExecute(WeakReference<View> target, List<Bitmap> images) {
        if (images != null && images.size() > 0 && target.get() != null) {
            // clear layout
            FlowLayout layout = (FlowLayout) target.get();
            layout.removeAllViewsInLayout();

            for (int index = 0; index < images.size(); index++) {
                LinearLayout wrapper = new LinearLayout(target.get().getContext());
                wrapper.setOrientation(LinearLayout.VERTICAL);
                wrapper.setPadding(4, 4, 4, 4);

                ImageView imageView = new ImageView(target.get().getContext());
                imageView.setImageBitmap(images.get(index));
                imageView.setBackgroundColor((Integer) layout.getTag(-1));
                wrapper.addView(imageView);

                TextView textView = new TextView(target.get().getContext());
                textView.setText("Image " + index);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 10f);
                wrapper.addView(textView);

                layout.addView(wrapper);
            }
        } else {
            Log.e("IBView", "onPostExecute: images == null");
            if (target.get() != null) {
                Toast.makeText(target.get().getContext(), "No image was returned!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
