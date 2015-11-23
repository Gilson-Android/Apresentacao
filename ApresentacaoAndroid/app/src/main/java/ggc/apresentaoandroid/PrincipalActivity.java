package ggc.apresentaoandroid;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.Locale;

import ggc.apresentaoandroid.adapters.NavigationDrawerListAdapter;

public class PrincipalActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    private ViewPager mViewPager;

    private Toolbar mtoolbar;

    private ActionBarDrawerToggle mDrawerToogle;

    private ListView mListaDrawer;

    int mPosicaoSelecionada;

    int mUltimaPosicaoDoDrawer;

    //SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_principal);

        FragmentManager fragmentManager = getSupportFragmentManager();

        SobreNosFragment fragment = SobreNosFragment.newInstance();

        fragmentManager.beginTransaction().replace(R.id.conteudo_layout, fragment).commit();

        inicializarToolbar();

        inicializarDrawer();
    }

    public void inicializarToolbar()
    {
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);

        //Define titulo do toolbar
        mtoolbar.setTitle("Apresentação Syshouseapps");

        //Define o icon do drawer no Toolbar
        mtoolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_menu));

        mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDrawerLayout.openDrawer(mListaDrawer);
            }
        });
    }


    public void inicializarDrawer()
    {

        //Definir a posicao selecionada e a ultima do drawer
        mPosicaoSelecionada = 1;

        mUltimaPosicaoDoDrawer  = mPosicaoSelecionada;

        //Pega o DrawerLayout
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);

        //Pega a lista
        mListaDrawer = (ListView) findViewById(R.id.nav_drawer);


        //Criar um toogler para abrir e fechar o drawer ao clicar no icon no toolbar
        mDrawerToogle = new ActionBarDrawerToggle(this,mDrawerLayout,mtoolbar,R.string.drawer_aberto,R.string.drawer_fechad0)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                //pintar a antiga posicao ao normal
                pintarItems(mUltimaPosicaoDoDrawer,false);

                pintarItems(mPosicaoSelecionada,true);

            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);

            }
        };


        mDrawerLayout.setDrawerListener(mDrawerToogle);

        //Definir os items da lista no adapter

        NavigationDrawerListAdapter mDrawerAdapter = new NavigationDrawerListAdapter(this.getApplicationContext());

        mDrawerAdapter.setDrawerHeader("Defina o link de uma imagem aqui para avatar","Gilson Gonçalves","Defina  o link para o backgroudn aqui");
        mDrawerAdapter.addRow(R.drawable.ic_comingsoon,"SOBRE NÓS");
        mDrawerAdapter.addRow(R.drawable.ic_ticket,"SERVIÇOS");
        mDrawerAdapter.addRow(R.drawable.ic_settings,"CONTATO");

        mListaDrawer.setAdapter(mDrawerAdapter);

        mListaDrawer.setOnItemClickListener(new DrawerItemClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void pintarItems(int posicao,boolean isSelected)
    {
        View view = mListaDrawer.getChildAt(posicao);

        View mIconView = view.findViewById(R.id.row_icon);

        Drawable iconDrawable = DrawableCompat.wrap(mIconView.getBackground());

        TextView mRowTextView = (TextView)view.findViewById(R.id.drawer_row_line);

        int color = getResources().getColor(R.color.black);

        if(isSelected==true)
        {
            color = getResources().getColor(R.color.primaryColor);
        }

        mRowTextView.setTextColor(color);

        //muda a cor do drawable para as especificadas
        DrawableCompat.setTint(iconDrawable,color);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {

            if(position!=0)
            {
                mUltimaPosicaoDoDrawer = mPosicaoSelecionada;
                mPosicaoSelecionada = position;
                selectItem(position);
            }
            else
            {
                mDrawerLayout.closeDrawer(mListaDrawer);
            }
        }
    }

    public void selectItem(int position)
    {
        //mViewPager.setCurrentItem(position);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment fr = null;
        switch (position) {
            case 1:
                fr = new SobreNosFragment();
                break;
            case 2:
                fr = new ServicoFragments();
                break;
            case 3:
                fr = new ContatoFragment();
                break;
        }
        fragmentTransaction.replace(R.id.conteudo_layout,fr);
        fragmentTransaction.commit();

        pintarItems(mUltimaPosicaoDoDrawer,false);

        pintarItems(mPosicaoSelecionada,true);

        mDrawerLayout.closeDrawer(mListaDrawer);
    }
}
