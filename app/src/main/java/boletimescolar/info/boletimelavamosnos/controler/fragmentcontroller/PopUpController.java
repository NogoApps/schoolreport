package boletimescolar.info.boletimelavamosnos.controler.fragmentcontroller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import boletimescolar.info.boletimelavamosnos.model.domain.ProvaDomain;
import boletimescolar.info.boletimelavamosnos.model.sharedpreferences.AlunoShared;
import boletimescolar.info.boletimelavamosnos.networking.ProvasAnterioresVolley;
import boletimescolar.info.boletimelavamosnos.networking.ProvasVolleyThread;
import boletimescolar.info.boletimelavamosnos.singleton.VolleySingleton;
import boletimescolar.info.boletimelavamosnos.view.adapters.RecyclerViewAdapter;

/**
 * Created by Norb7492 on 14/11/2016.
 */

public class PopUpController {


    public void fetchProvas(final List<ProvaDomain> provasList, Context ctx, String bimestre, String ano, final RecyclerView recyclerView, final RecyclerViewAdapter recyclerViewAdapter){




        ProvasAnterioresVolley provasVolleyThread = new ProvasAnterioresVolley(ctx,provasList,Request.Method.GET, "http://nogoapps.com/appEscolarRestApi/public/anterior/"+String.valueOf(AlunoShared.getIdUser(ctx)) +
                "/"+ bimestre + "/" + ano,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                recyclerView.setAdapter(recyclerViewAdapter);
                recyclerViewAdapter.notifyDataSetChanged();


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
}
