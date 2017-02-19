package app.com.listacompras.activity_class;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.com.listacompras.MainActivity;
import app.com.listacompras.R;

/**
 * Created by daniel on 17/02/2017.
 */

public class Welcome extends AppCompatActivity {
    private ViewPager vp;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button next, skip;
    private MyViewPagerAdapter myViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }

        setContentView(R.layout.welcome);

        /**
         * casting the buttons
         */
        vp =(ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        next = (Button) findViewById(R.id.btn_next);
        skip = (Button) findViewById(R.id.skip);
        //casting the buttons end

        /**
         * layouts of all sliders
         */
        layouts = new int[]{
                R.layout.slide,
                R.layout.slide2
        };

        //adding bottom dots
        addBottomDots(0);

        //Making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        vp.setAdapter(myViewPagerAdapter);
        vp.addOnPageChangeListener(viewpagerPageChangeListener);

        skip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                lauchHomeScreen();
            }
        });

        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //checking for last page
                //if last page home screen will be launched
                int current = getItem(+1);
                if(current < layouts.length)
                {
                    //move to next screen
                    vp.setCurrentItem(current);
                }
                else {
                    lauchHomeScreen();
                }
            }
        });
    }

    /**
     * add Bottom dots
     */
    private void addBottomDots(int currentPage)
    {
        dots = new TextView[layouts.length];
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        // don´t have idea what remove the views
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++)
        {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    /**
     * get Item
     */
    private int getItem(int i)
    {
        return vp.getCurrentItem() + i;
    }

    /**
     * launch home screen
     */
    private void lauchHomeScreen()
    {
        startActivity(new Intent(Welcome.this, MainActivity.class));
        finish();
    }

    ViewPager.OnPageChangeListener viewpagerPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            if (position == layouts.length -1)
            {
                //last page make button text to GOT IT
                next.setText(getString(R.string.start));
                skip.setVisibility(View.GONE);
            }else{
                //still pages are left
                next.setText(getString(R.string.next));
                skip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * making notification bar transparent
     */
    private void changeStatusBarColor(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Window window  = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View Pager Adapter for populate the viewpager
     */
    public class MyViewPagerAdapter extends PagerAdapter{
        private LayoutInflater layoutInflater;
        public MyViewPagerAdapter()
        {

        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
             container.removeView(view);
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }
}
