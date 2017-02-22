package app.com.listacompras.activity_class;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import app.com.listacompras.R;

/**
 * Created by daniel on 19/02/2017.
 */

public class Profile_activity extends AppCompatActivity implements View.OnClickListener {
    //declare values
    private Toolbar tb;
    private EditText editor_scan;
    private TextInputLayout tokenLayout;
    private Button btnscan, btncancel;
    private TextInputLayout tilt;
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

        //casting the layout widgets
        btnscan = (Button) findViewById(R.id.btnScan);
        btncancel = (Button) findViewById(R.id.cancel);
        //cast
        tilt = (TextInputLayout) findViewById(R.id.TextinputLayout);

        setupFloatingLabelError();

        //button cancel setonclicklistener
        btncancel.setOnClickListener(this);
        btnscan.setOnClickListener(this);
    }

    /**
     * on Back pressed controls the back button
     */

    @Override
    public void onBackPressed() {
        finish();
        Log.d("Close Activity", "onBackPressed: the activity has closed correctly");
    }

    /***
     *
     * @param item - a item that option selected has
     * @return - return the item
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
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

    /**
     * set up Floating Layout Error
     */
    private void setupFloatingLabelError()
    {
        tilt.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 8)
                {
                    tilt.setError(getString(R.string.token_length_required));
                    tilt.setErrorEnabled(true);
                }
                else
                {
                    tilt.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                /**
                 * this will call when stops typing, it will calls after you completely wrote the "word" that is the main difference
                 * we are going to hide soft virtual keyboard
                 */
                if(s.length() > 8)
                {
                    ((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE))
                            .toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.cancel:
                //clean editText
                editor_scan.getText().clear();
                break;
            case R.id.btnScan:

                break;
            default:
                break;
        }
    }
}
