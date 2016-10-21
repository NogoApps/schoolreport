package boletimescolar.info.boletimelavamosnos.controler.networkcheckthread;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import android.os.Handler;
import android.os.Message;
import boletimescolar.info.boletimelavamosnos.model.domain.ProvaDomain;
import boletimescolar.info.boletimelavamosnos.model.fragmentmodel.Tab1Model;
import boletimescolar.info.boletimelavamosnos.sqlite.adapter.SqliteNotasAdapter;

/**
 * Created by Norb7492 on 17/10/2016.
 */

public class LoadToSqlite extends Thread {

    private Context ctx;
    private SqliteNotasAdapter sqliteAdapter;
    private Tab1Model tab1Model;
    private List<ProvaDomain> provaArray;


    public LoadToSqlite(Context ctx,SqliteNotasAdapter sqliteAdapter,Tab1Model tab1Model, List<ProvaDomain> provaArray) {
        this.ctx = ctx;
        this.sqliteAdapter = sqliteAdapter;
        this.tab1Model = tab1Model;
        this.provaArray = provaArray;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tab1Model.listarNotas(provaArray);
        }
    };

    @Override
    public void run() {
        final boolean check = sqliteAdapter.notasIsEmpty();
        while(check == true){


            Log.d("Thread", "Waiting");


        }

        handler.sendEmptyMessage(0);





    }
}
