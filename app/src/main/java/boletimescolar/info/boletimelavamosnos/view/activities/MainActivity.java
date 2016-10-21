package boletimescolar.info.boletimelavamosnos.view.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import boletimescolar.info.boletimelavamosnos.R;
import boletimescolar.info.boletimelavamosnos.controler.networkcheckthread.LoadToSqlite;
import boletimescolar.info.boletimelavamosnos.controler.networkcheckthread.Networkcheck;
import boletimescolar.info.boletimelavamosnos.controler.networkcheckthread.TokeCheckThread;
import boletimescolar.info.boletimelavamosnos.controler.volleythread.TokenThread;
import boletimescolar.info.boletimelavamosnos.model.activitymodel.MainActivityModel;
import boletimescolar.info.boletimelavamosnos.model.sharedpreferences.AlunoShared;
import boletimescolar.info.boletimelavamosnos.model.sharedpreferences.TokenShared;
import boletimescolar.info.boletimelavamosnos.sqlite.adapter.SqliteExamesAdapter;
import boletimescolar.info.boletimelavamosnos.sqlite.adapter.SqliteNotasAdapter;
import boletimescolar.info.boletimelavamosnos.view.adapters.ViewPagerAdapter;
import boletimescolar.info.boletimelavamosnos.view.tabs.SlidingTabLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    // Declaring Your View and Variables

    private Toolbar toolbar;
    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;
    private CharSequence Titles[] = {"Notas", "Agenda"};
    private int Numboftabs = 2;


    @BindView(R.id.nome_txt_toolbar)
    TextView nome_toobar;
    @BindView(R.id.internet)
     TextView internet;



    //Model
    MainActivityModel mainActivityModel;



    //Threads
    private Networkcheck networkcheck;
    private TokeCheckThread tokenTokeCheckThread;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setElevation(0);


        instanceCrap();
        setNamesToolbar();


        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.white);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);



        verSeTokenEstaRegistrado();

        networkcheck.start();



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        networkcheck.setFlag(true);
    }

    public void instanceCrap() {


        ButterKnife.bind(this);
        mainActivityModel = new MainActivityModel(MainActivity.this);
        networkcheck = new Networkcheck(this, internet);
        tokenTokeCheckThread = new TokeCheckThread(this,mainActivityModel);



    }


    public void setNamesToolbar() {

        AlunoShared.getUserName(this, nome_toobar);

    }



    public void verSeTokenEstaRegistrado(){

        if(TokenShared.getRegister(MainActivity.this) == ""){


//            Toast.makeText(this, "Thread chamada", Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, String.valueOf(TokenShared.getRegister(this)), Toast.LENGTH_SHORT).show();
            tokenTokeCheckThread.start();


        }else{

            Log.d("Registrado","Esta registrado");
//            Toast.makeText(this, String.valueOf(TokenShared.getRegister(this)), Toast.LENGTH_SHORT).show();

        }



    }

    @OnClick(R.id.sair_toolbar)
    public synchronized void sairApp(View v){

        SqliteNotasAdapter adapter = new SqliteNotasAdapter(this);
        SqliteExamesAdapter sqliteExamesAdapter = new SqliteExamesAdapter(this);
        adapter.apagarTudo();
        sqliteExamesAdapter.apagarTudo();
        AlunoShared.delete(this);
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();


    }



}
