package divstar.ico4a.demo.listeners;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apmem.tools.layouts.FlowLayout;

import java.net.MalformedURLException;
import java.net.URL;

import divstar.ico4a.demo.tasks.DownloadImageTask;
import divstar.ico4a.demo.tasks.GetResourceImageTask;

/**
 * Created by divStarHQ on 11.03.2016.
 */
public class LoadOnClickListener implements View.OnClickListener {
    private FlowLayout target;
    private EditText uriEditText;
    private Integer[] ids;

    public LoadOnClickListener(FlowLayout target, Integer[] ids) {
        this(target);
        this.uriEditText = null;
        this.ids = ids;
    }

    public LoadOnClickListener(FlowLayout target, EditText uriEditText) {
        this(target);
        this.uriEditText = uriEditText;
        this.ids = null;
    }

    protected LoadOnClickListener(FlowLayout target) {
        this.target = target;
    }

    @Override
    public void onClick(View view) {
        if (uriEditText != null) {
            try {
                new DownloadImageTask(target).execute(new URL(uriEditText.getText().toString()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Toast.makeText(target.getContext(), "The URI you entered is invalid!", Toast.LENGTH_SHORT).show();
            }
        } else if (ids != null) {
            new GetResourceImageTask(target).execute(ids);
        }
    }
}
