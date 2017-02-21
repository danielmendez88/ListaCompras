package app.com.listacompras.activity_class;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import app.com.listacompras.R;

/**
 * Created by daniel on 19/02/2017.
 */

public class Profile_activity extends AppCompatActivity {
    //declare values
    private Toolbar tb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        //toolbar
        tb = (Toolbar) this.findViewById(R.id.tool_bar_profile);
        setSupportActionBar(tb);
        //set the padding to match the status Bar height
        //tb.setPadding(0, getStatusBarHeight(), 0, 0);
        //getApply();
        //here We get Support and display home enabled
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        setSupportActionBar(tb);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * A method to find height of the status bar
     */
    private int getStatusBarHeight()
    {
        int result = 0;

        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0){
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;

    }

    /**
     * method to apply changes in status bar
     */
    private void getApply()
    {
        SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this);
        //enable status bar int
        systemBarTintManager.setStatusBarTintEnabled(true);
        //enable navigation bar int
        systemBarTintManager.setNavigationBarTintEnabled(true);
        //set the transparent color of the status bar, 20% darker
        systemBarTintManager.setTintColor(Color.parseColor("#20000000"));
    }
}
