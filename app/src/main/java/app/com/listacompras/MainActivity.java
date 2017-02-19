package app.com.listacompras;


import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.com.listacompras.clases.Eventoqr;
import app.com.listacompras.fragments.Frgone;
import app.com.listacompras.fragments.Frmcredencial;
import app.com.listacompras.fragments.Frmtoken;
import app.com.listacompras.interfaces.CodeScan;

public class MainActivity extends AppCompatActivity implements CodeScan {

    //declare variables
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    String fragmentToken;
    Eventoqr evento;
    /**
     * we declare the values what we have to use for Navigation Drawer
     */
    private DrawerLayout drawerlayout;
    private CoordinatorLayout coordinatorLayout;

    FloatingActionButton FloatingButton;

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
        //inflate coordinator layout
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_main);
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

        /**
         * navigation Drawer
         */
        setSupportActionBar(toolbar);
        initNavigationDrawer();

        //cast floatingbutton
        evento = new Eventoqr(this);
        FloatingButton = (FloatingActionButton) findViewById(R.id.A1);
        //send event to method evento with FloatingButton
        View.OnClickListener l = evento;
        FloatingButton.setOnClickListener(l);
    }

    /***
     * Navigation Drawer Class
     */
    public void initNavigationDrawer()
    {
        //we cast the navigation view
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id)
                {
                    case R.id.home:
                        SnackBack("Casa ya estamos aquí");
                        drawerlayout.closeDrawers();
                        break;
                    case R.id.profile:
                        SnackBack("perfil");
                        drawerlayout.closeDrawers();
                        break;
                    case  R.id.settings:
                        SnackBack("opciones");
                        drawerlayout.closeDrawers();
                        break;
                    case R.id.lgoutmenu:
                        SnackBack("Salir de la aplicación");
                        drawerlayout.closeDrawers();
                        break;

                }
                return true;
            }
        });

        View header = navigationView.getHeaderView(0);
        TextView email = (TextView) header.findViewById(R.id.email);
        email.setText("daniel.mendez@cimat.mx");

        drawerlayout = (DrawerLayout) findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerlayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    /***
     * snack Bar
     */
    private void SnackBack(String texto)
    {
        Snackbar snackbar = Snackbar.make(coordinatorLayout,texto.toString(), Snackbar.LENGTH_LONG)
                .setAction("Intentar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

        /**
         * changing message text color
         */
        snackbar.setActionTextColor(Color.RED);
        // changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);

        //show the snack bar
        snackbar.show();
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
        adapter.addFragment(new Frgone(), names_[0]);
        adapter.addFragment(new Frmtoken(), names_[1]);
        adapter.addFragment(new Frmcredencial(), names_[2]);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void enviarCode(String codigo) {
        FragmentManager fManager = getSupportFragmentManager();
        Frmtoken token = (Frmtoken) fManager.findFragmentById(R.id.frmtoken);
       // token.GetMensaje(codigo);
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

    //generate a void method to get a tag in the fragment we want to send message
    public void setTagFragmentToken(String tag)
    { fragmentToken = tag; }

    public String getTagFragmentToken()
    { return fragmentToken;}

    @Override
    protected void onPause() {
        super.onPause();
    }
}
