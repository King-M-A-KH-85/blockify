package ir.blockify.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;

import ir.blockify.Application;
import ir.blockify.R;
import ir.blockify.preference.WindowPreference;

public class MaterialYouActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    private MaterialToolbar materialToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setupWindow();
//        materialToolbar = findViewById(R.id.mt);
//        materialToolbar.setNavigationOnClickListener((v) -> finish());
//        materialToolbar.setOnMenuItemClickListener(this);
//
//        throw new RuntimeException("test");
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_edit:
//                AppUtils.showToast(this, "Edit");
//                break;
//            case R.id.menu_heart:
//                AppUtils.showToast(this, "Heart");
//                break;
//            case R.id.menu_more:
//                AppUtils.showToast(this, "More");
//                break;
//        }
        return false;
    }

    private void setupWindow() {
        new WindowPreference(this).applyEdgeToEdgePreference(getWindow(), getColor(R.color.colorSurface));
        Application.addSystemWindowInsetToPadding(getWindow().getDecorView(), false, true, false, true);
    }
}
