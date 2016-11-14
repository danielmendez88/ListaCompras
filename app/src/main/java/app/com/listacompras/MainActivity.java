package app.com.listacompras;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import app.com.listacompras.fragments.Frgone;

public class MainActivity extends AppCompatActivity {

    //declare variables
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    //declare an Array to add icons
    private int[] tabIcons ={
            R.drawable.ic_console,
            R.drawable.ic_key_variant,
            R.drawable.ic_lock_open
    };
    private String[] names_ ={
            "Autenticación",
            "Token",
            "Credenciales"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set variable ToolBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //here We get Support and display home enabled
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //set viewPager
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        //in this case setUpViewPager work like a method for setup the fragments in the activity
        setupViewPager(viewPager);

        //add tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons()
    {
        for (int j = 0; j < 3 ; j++)
        {
            tabLayout.getTabAt(j).setIcon(tabIcons[j]);
        }
    }

    private void setupViewPager(ViewPager viewPager)
    {
        AdaptadorPager adapter = new AdaptadorPager(getSupportFragmentManager());
        for (int i= 0; i < 3; i++)
        {adapter.addFragment(new Frgone(), names_[i]);}
        viewPager.setAdapter(adapter);
    }

    //create another class inside this one
    class AdaptadorPager extends FragmentPagerAdapter{
        //add a list to fill with fragments
        private final List<Fragment> myFragmentList = new ArrayList<>();
        private final List<String> myFragmenttitle = new ArrayList<>();
        @Override
        public Fragment getItem(int position) {
            return myFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return myFragmentList.size();
        }

        public AdaptadorPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return myFragmenttitle.get(position);
        }
        public void addFragment(Fragment fragmento, String titulo)
        {
            myFragmentList.add(fragmento);
            myFragmenttitle.add(titulo);
        }
    }
}
