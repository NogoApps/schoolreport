package boletimescolar.info.boletimelavamosnos.controler.fragmentcontroller;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import boletimescolar.info.boletimelavamosnos.R;
import boletimescolar.info.boletimelavamosnos.model.domain.BimestreAnoDomain;
import boletimescolar.info.boletimelavamosnos.model.sharedpreferences.AlunoShared;
import boletimescolar.info.boletimelavamosnos.networking.DataBimestreVolley;
import boletimescolar.info.boletimelavamosnos.singleton.VolleySingleton;
import boletimescolar.info.boletimelavamosnos.sqlite.adapter.SqliteDataAdapter;

/**
 * Created by Norb7492 on 14/11/2016.
 */

public class Tab1Controller {





    public void fetchDataSpinner(final Context ctx, final List<BimestreAnoDomain> dataList, final ArrayAdapter<BimestreAnoDomain> spinnerAdapter2, Spinner spinner2){




        DataBimestreVolley bimestreVolley = new DataBimestreVolley(Request.Method.GET, "http://nogoapps.com/appEscolarRestApi/public/bimestre/" + String.valueOf(AlunoShared.getIdUser(ctx)), ctx, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                listarNotas(ctx,dataList, spinnerAdapter2);





            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {






            }

        }

        );
        VolleySingleton.getInstance(ctx).addToRequestQueue(bimestreVolley);


    }



    public List<BimestreAnoDomain> oQueEscolher(Context ctx, ArrayAdapter<BimestreAnoDomain> spinnerAdapter2, Spinner spinner2){

        SqliteDataAdapter sqliteDataAdapter = new SqliteDataAdapter(ctx);

        List<BimestreAnoDomain> dataList = new ArrayList<>();

        final boolean check = sqliteDataAdapter.dataIsEmpty();
        if (check == true) {
            fetchDataSpinner(ctx, dataList, spinnerAdapter2, spinner2);





            return dataList;


        } else {




            listarNotas(ctx, dataList,spinnerAdapter2);



            return dataList;

        }




    }

    //Sqlite manooooo

    public void listarNotas(Context ctx, List<BimestreAnoDomain> dataList,ArrayAdapter<BimestreAnoDomain> spinnerAdapter2){

        SqliteDataAdapter sqliteDataAdapter = new SqliteDataAdapter(ctx);


        sqliteDataAdapter.openDB();

        Cursor cursor = sqliteDataAdapter.listarData();


        while (cursor.moveToNext()) {


            BimestreAnoDomain b = new BimestreAnoDomain();


            b.setData(cursor.getString(0));

            dataList.add(b);



        }





        sqliteDataAdapter.closeDB();



    }





}
