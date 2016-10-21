package boletimescolar.info.boletimelavamosnos.controler.volleythread;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import boletimescolar.info.boletimelavamosnos.model.domain.ProvaDomain;
import boletimescolar.info.boletimelavamosnos.sqlite.adapter.SqliteNotasAdapter;
import boletimescolar.info.boletimelavamosnos.view.adapters.RecyclerViewAdapter;


/**
 * Created by Norb7492 on 13/09/2016.
 */
public class ProvasVolleyThread extends Request<JSONObject> {

    private Response.Listener<JSONObject> response;
    private Map<String, String> params;
    private ProvaDomain provaDomain;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<ProvaDomain> provaArray;
    private Context context;


    public ProvasVolleyThread(Context context, List<ProvaDomain> provaArray, RecyclerViewAdapter recyclerViewAdapter, ProvaDomain provaDomain, int method, String url, Map<String, String> params, Response.Listener<JSONObject> response, Response.ErrorListener listener) {
        super(method, url, listener);
        this.params = params;
        this.response = response;
        this.provaDomain = provaDomain;
        this.recyclerViewAdapter = recyclerViewAdapter;
        this.provaArray = provaArray;
        this.context = context;
        Log.d("TesteLog", "Chamado");
    }

    @Override
    public Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        Log.d("TesteLog", "Chamado");
        try {



            Log.d("TesteLog", "Chamado");
            String js = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            JSONObject jsonObject = new JSONObject(js);
            JSONArray loginArray = jsonObject.getJSONArray("provas");

            for (int i = 0; i < loginArray.length(); i++) {
                JSONObject array = loginArray.getJSONObject(i);

                ProvaDomain provaDomain2 = new ProvaDomain();


                provaDomain2.setId_prova(Long.valueOf(array.getString("ID_PROVA")));
                provaDomain2.setNota(Double.valueOf(array.getString("NOTA")));
                provaDomain2.setMateria(Integer.valueOf(array.getString("MATERIA")));
                provaDomain2.setFaltas(Integer.valueOf(array.getString("FALTAS")));
                provaDomain2.setBimestre(Integer.valueOf(array.getString("BIMESTRE")));





                SqliteNotasAdapter sqliteNotasAdapter = new SqliteNotasAdapter(context);

                sqliteNotasAdapter.adicionarCompras(provaDomain2);





            }
            return (Response.success(new JSONObject(js), HttpHeaderParser.parseCacheHeaders(response)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        this.response.onResponse(response);
    }
}





