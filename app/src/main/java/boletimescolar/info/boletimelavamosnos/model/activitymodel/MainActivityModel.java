package boletimescolar.info.boletimelavamosnos.model.activitymodel;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import boletimescolar.info.boletimelavamosnos.networking.TokenThread;
import boletimescolar.info.boletimelavamosnos.model.sharedpreferences.AlunoShared;
import boletimescolar.info.boletimelavamosnos.model.sharedpreferences.TokenShared;
import boletimescolar.info.boletimelavamosnos.singleton.VolleySingleton;

/**
 * Created by Norb7492 on 14/10/2016.
 */

public class MainActivityModel {



    Context ctx;
    private Map<String, String> params;


    public MainActivityModel(Context ctx) {
        this.ctx = ctx;
    }

    public void saveTokeToDatabase(){


        params = new HashMap<String, String>();
        params.put("id_aluno", String.valueOf(AlunoShared.getIdUser(ctx)));
        params.put("token", TokenShared.getToken(ctx));


        TokenThread tokenThread = new TokenThread(ctx, com.android.volley.Request.Method.POST, "http://nogoapps.com/appEscolarRestApi/public/token", params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {







            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("MyLog", String.valueOf(error));



            }

        }

        );
        VolleySingleton.getInstance(ctx).addToRequestQueue(tokenThread);


    }





}





