package ggc.apresentaoandroid;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

import ggc.apresentaoandroid.Util.Utilidades;
import ggc.apresentaoandroid.Util.VerificarInternet;

public class PrincipalActivity extends AppCompatActivity {
    private DrawerLayout        mDrawerLayout;
    private NavigationView      mNavigationView;
    private ActionBar           mActionBar;
    private FragmentManager     mFragmentManager;
    private static Toolbar      mToolbar;
    private VerificarInternet   internetStatus = new VerificarInternet();
    private static TextView     header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constantes.setActivity(this);
        Fresco.initialize(this);
        setContentView(R.layout.activity_principal);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        findViewById(R.id.conteudo).setVisibility(View.VISIBLE);

        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.conteudo, new HomeFragments()).commit();

        carregarNavegacao();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Colocar algo aqui", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        statusConexao(this);
        registerReceiver(internetStatus, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override
    protected void onPause() {
        unregisterReceiver(internetStatus);
        super.onPause();
    }


    public static boolean statusConexao(Context _context) {
        if (!Utilidades.Conectado(Constantes.getActivity())) {
            mToolbar.setTitle(_context.getString(R.string.cabecalho_sem_conexao));
            mToolbar.setTitleTextColor(_context.getResources().getColor(R.color.accentColor));
            return false;
        } else {
            mToolbar.setTitle(_context.getString(R.string.app_name));
            mToolbar.setTitleTextColor(_context.getResources().getColor(R.color.white));
            return true;
        }
    }
    private void carregarNavegacao(){
        setSupportActionBar(mToolbar);

        mActionBar = getSupportActionBar();
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        mActionBar.setDisplayHomeAsUpEnabled(true);

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        if (mNavigationView != null) {
            setupDrawerContent(mNavigationView);
        }

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }

                mDrawerLayout.closeDrawers();
                switch (item.getItemId()) {

                    case R.id.nav_home:  //0
                        findViewById(R.id.conteudo).setVisibility(View.VISIBLE);
                        mFragmentManager.beginTransaction().replace(R.id.conteudo, new HomeFragments()).commit();
                        return true;
                    case R.id.nav_sobrenos: //1
                        findViewById(R.id.conteudo).setVisibility(View.VISIBLE);
                        mFragmentManager.beginTransaction().replace(R.id.conteudo, new SobreNosFragment()).commit();
                        return true;
                    case R.id.nav_servicos: //2
                        Context context = mDrawerLayout.getContext();
                        Intent intent = new Intent(context, ServicosActivity.class);
                        startActivityForResult(intent, 0);
                        return true;
                    case R.id.nav_contato:
                        Toast.makeText(getApplicationContext(), "Sites", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Constantes.setActivity(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        findViewById(R.id.conteudo).setVisibility(View.VISIBLE);
        fragmentManager.beginTransaction().replace(R.id.conteudo, new HomeFragments()).commit();
        mNavigationView.getMenu().getItem(0).setChecked(true);

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
