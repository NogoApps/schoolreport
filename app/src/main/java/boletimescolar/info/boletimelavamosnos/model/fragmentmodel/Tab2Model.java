package boletimescolar.info.boletimelavamosnos.model.fragmentmodel;

import android.content.Context;
import android.database.Cursor;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.List;

import boletimescolar.info.boletimelavamosnos.networking.ExamesVolleyThread;
import boletimescolar.info.boletimelavamosnos.model.domain.ExameDomain;
import boletimescolar.info.boletimelavamosnos.singleton.VolleySingleton;
import boletimescolar.info.boletimelavamosnos.sqlite.adapter.SqliteExamesAdapter;
import boletimescolar.info.boletimelavamosnos.view.adapters.RecyclerViewAdapter3;

/**
 * Created by Norb7492 on 20/10/2016.
 */

public class Tab2Model {

    private Context ctx;
    private SqliteExamesAdapter sqliteExamesAdapter;
    private List<ExameDomain> exameArray;
    private RecyclerViewAdapter3 recyclerViewAdapter3;




    public Tab2Model(Context ctx, List<ExameDomain> exameArray,RecyclerViewAdapter3 recyclerViewAdapter3) {
        this.ctx = ctx;
        this.sqliteExamesAdapter = new SqliteExamesAdapter(ctx);
        this.exameArray = exameArray;
        this.recyclerViewAdapter3 = recyclerViewAdapter3;

    }

    //Buscar as provas
    public void exameFetch() {








        ExamesVolleyThread examesVolleyThread = new ExamesVolleyThread(ctx, Request.Method.GET, "http://nogoapps.com/appEscolarRestApi/public/exames", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                listarNotas();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {




            }

        }

        );
        VolleySingleton.getInstance(ctx).addToRequestQueue(examesVolleyThread);


    }

    public void listarNotas(){






        sqliteExamesAdapter.openDB();

        Cursor cursor = sqliteExamesAdapter.listarExames();


        while (cursor.moveToNext()) {


            ExameDomain exameDomain = new ExameDomain(cursor.getLong(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3));



            exameArray.add(exameDomain);


        }

        if (!(exameArray.size() < 1)) {



        }

        recyclerViewAdapter3.notifyDataSetChanged();
        sqliteExamesAdapter.closeDB();



    }
}
