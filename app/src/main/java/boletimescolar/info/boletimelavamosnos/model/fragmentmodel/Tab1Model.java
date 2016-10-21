package boletimescolar.info.boletimelavamosnos.model.fragmentmodel;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import boletimescolar.info.boletimelavamosnos.R;
import boletimescolar.info.boletimelavamosnos.controler.networkcheckthread.LoadToSqlite;
import boletimescolar.info.boletimelavamosnos.controler.volleythread.ProvasVolleyThread;
import boletimescolar.info.boletimelavamosnos.model.domain.MediaDomain;
import boletimescolar.info.boletimelavamosnos.model.domain.ProvaDomain;
import boletimescolar.info.boletimelavamosnos.model.ips.Ip;
import boletimescolar.info.boletimelavamosnos.model.sharedpreferences.AlunoShared;
import boletimescolar.info.boletimelavamosnos.singleton.VolleySingleton;
import boletimescolar.info.boletimelavamosnos.sqlite.adapter.SqliteNotasAdapter;
import boletimescolar.info.boletimelavamosnos.view.adapters.RecyclerViewAdapter;
import boletimescolar.info.boletimelavamosnos.view.adapters.RecylerViewAdapter2;

/**
 * Created by Norb7492 on 13/09/2016.
 */
public class Tab1Model {


    private Context ctx;
    private Map<String, String> params;
    private ProvaDomain provaDomain = new ProvaDomain();
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecylerViewAdapter2 recyclerViewAdapter2;
    private List<ProvaDomain> provaArray;
    private SqliteNotasAdapter notasAdapter;
    private List<MediaDomain> mediaArray;
    private ProgressBar progressBar;




    public Tab1Model(List<ProvaDomain> provaArray, List<MediaDomain> mediaArray, Context ctx, RecyclerViewAdapter recyclerViewAdapter,RecylerViewAdapter2 recyclerViewAdapter2,RecyclerView recyclerView, RecyclerView recyclerView2,ProgressBar progressBar) {
        this.ctx = ctx;
        this.recyclerViewAdapter = recyclerViewAdapter;
        this.recyclerViewAdapter2 = recyclerViewAdapter2;
        this.provaArray = provaArray;
        this.notasAdapter = new SqliteNotasAdapter(ctx);
        this.mediaArray = mediaArray;
        this.recyclerView = recyclerView;
        this.recyclerView2 = recyclerView2;
        this.progressBar = progressBar;


    }




    //Buscar as provas
    public void provaFetch(final int bimestre) {

        params = new HashMap<String, String>();
        params.put("acao", "prova");
        params.put("id_aluno", String.valueOf(AlunoShared.getIdUser(ctx)));

        Log.d("MyLog", "Chamou aqui");
        progressBar.setVisibility(View.VISIBLE);



        ProvasVolleyThread provasVolleyThread = new ProvasVolleyThread(ctx,provaArray, recyclerViewAdapter, provaDomain, Request.Method.POST, Ip.ip, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                listarNotasWhere(bimestre,provaArray);
                calcularMedia(provaArray,mediaArray);

                progressBar.setVisibility(View.GONE);
//                recyclerViewAdapter.showArray();
//                recyclerViewAdapter.notifyDataSetChanged();
//                calcularMedia(provaArray,mediaArray);




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("MyLog", String.valueOf(error));


            }

        }

        );
        VolleySingleton.getInstance(ctx).addToRequestQueue(provasVolleyThread);


    }




    //Cálculo do bagulho


    public void calcularMedia(List<ProvaDomain> provaArray,List<MediaDomain> mediaArray){

        mediaArray.clear();




        Log.d("Arrays", "------Prova Array--------");
        for(ProvaDomain provaDomain : provaArray) {
            Log.d("Arrays", String.valueOf(provaDomain.getMateria()));
        }


        for(ProvaDomain provaDomain : provaArray){
            MediaDomain mediaDomain = new MediaDomain();
            if(provaDomain.getNota() < 5){



                double media = 10 - provaDomain.getNota();

                mediaDomain.setMateria(provaDomain.getMateria());

                mediaDomain.setMedia(media);






                mediaArray.add(mediaDomain);







            }


        }




        recyclerView2.setAdapter(recyclerViewAdapter2);

        recyclerViewAdapter2.notifyDataSetChanged();

    }




    //Sqlite manooooo

    public void listarNotas(List<ProvaDomain> provaArray){


        Toast.makeText(ctx, "sqlite called", Toast.LENGTH_SHORT).show();

        provaArray.clear();

        notasAdapter.openDB();

        Cursor cursor = notasAdapter.listarCompras();


        while (cursor.moveToNext()) {


            ProvaDomain provaDomain = new ProvaDomain(cursor.getLong(0), cursor.getInt(1), cursor.getDouble(2), cursor.getInt(3), cursor.getInt(4));



            provaArray.add(provaDomain);


        }

        if (!(provaArray.size() < 1)) {


            recyclerViewAdapter.notifyDataSetChanged();
        }



        notasAdapter.closeDB();



    }

    public void listarNotasWhere(int bimestre,List<ProvaDomain> provaArray) {

        notasAdapter.listarComprasWhere(bimestre, provaArray, recyclerViewAdapter,recyclerView);

        if(provaArray.isEmpty()) {

            Toast.makeText(ctx, R.string.sem_provas, Toast.LENGTH_LONG).show();

        }



    }


    //Ver se recyclerView esta vazio

    public void recyclerViewCheck(TextView preciso_tirar, TextView nao_tem) {

        preciso_tirar.setVisibility(View.GONE);
        nao_tem.setVisibility(View.VISIBLE);
    }

}
