package ir.blockify.activities;

import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

import de.dlyt.yanndroid.oneui.layout.AboutPage;
import ir.blockify.R;
import ir.blockify.base.OneUiThemeActivity;

public class AboutActivity extends OneUiThemeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mUseAltTheme = true;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        AboutPage about_page = findViewById(R.id.about_page);

        ((MaterialButton) findViewById(R.id.about_btn1)).setOnClickListener(v -> about_page.setUpdateState(AboutPage.LOADING));
        ((MaterialButton) findViewById(R.id.about_btn2)).setOnClickListener(v -> about_page.setUpdateState(AboutPage.NO_UPDATE));
        ((MaterialButton) findViewById(R.id.about_btn3)).setOnClickListener(v -> about_page.setUpdateState(AboutPage.UPDATE_AVAILABLE));
        ((MaterialButton) findViewById(R.id.about_btn4)).setOnClickListener(v -> about_page.setUpdateState(AboutPage.NOT_UPDATEABLE));
        ((MaterialButton) findViewById(R.id.about_btn5)).setOnClickListener(v -> about_page.setUpdateState(AboutPage.NO_CONNECTION));
    }
}