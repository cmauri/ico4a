package divstar.ico4a.demo.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.danielnilsson9.colorpickerview.view.ColorPickerView;

import org.apmem.tools.layouts.FlowLayout;

import java.net.MalformedURLException;
import java.net.URL;

import divstar.ico4a.demo.R;
import divstar.ico4a.demo.listeners.LoadOnClickListener;
import divstar.ico4a.demo.tasks.DownloadImageTask;

public class TestIconActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_icon);
        findViewById(R.id.testLayout).setTag(-1, Color.BLACK);

        ((ColorPickerView) findViewById(R.id.iconBackgroundColor)).setOnColorChangedListener(new ColorPickerView.OnColorChangedListener() {
            @Override
            public void onColorChanged(int newColor) {
                refreshIconBackgrounds(newColor);
            }
        });

        Button loadDefaultIcons = (Button) findViewById(R.id.loadDefaultIcons);
        loadDefaultIcons.setOnClickListener(new LoadOnClickListener((FlowLayout) findViewById(R.id.testLayout), new Integer[] {R.raw.ico4a, R.raw.test_icon}));

        Button loadFromUri = (Button) findViewById(R.id.loadFromUri);
        loadFromUri.setOnClickListener(
            new LoadOnClickListener(
                (FlowLayout) findViewById(R.id.testLayout),
                ((EditText) findViewById(R.id.uriToLoadFrom))
            )
        );
    }

    private void refreshIconBackgrounds(int newColor) {
        // every LinearLayout is considered a wrapper for an icon
        FlowLayout layout = (FlowLayout) findViewById(R.id.testLayout);
        layout.setTag(-1, newColor);
        int children = layout.getChildCount();
        for (int index = 0; index < children; index++) {
            View childAtIndex = layout.getChildAt(index);
            if (childAtIndex instanceof LinearLayout) {
                LinearLayout wrapper = (LinearLayout) childAtIndex;
                wrapper.getChildAt(0).setBackgroundColor(newColor);
            }
        }
    }
}
