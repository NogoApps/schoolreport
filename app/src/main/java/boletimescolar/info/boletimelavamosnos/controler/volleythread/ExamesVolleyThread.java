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

import boletimescolar.info.boletimelavamosnos.model.domain.ExameDomain;
import boletimescolar.info.boletimelavamosnos.sqlite.adapter.SqliteExamesAdapter;

/**
 * Created by Norb7492 on 20/10/2016.
 */

public class ExamesVolleyThread extends Request<JSONObject> {

    private Response.Listener<JSONObject> response;
    private Map<String, String> params;

    private Context context;


    public ExamesVolleyThread(Context context, int method, String url, Map<String, String> params, Response.Listener<JSONObject> response, Response.ErrorListener listener) {
        super(method, url, listener);
        this.params = params;
        this.response = response;
        this.context = context;

    }

    @Override
    public Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {



            String js = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            JSONObject jsonObject = new JSONObject(js);
            JSONArray loginArray = jsonObject.getJSONArray("exames");

            for (int i = 0; i < loginArray.length(); i++) {
                JSONObject array = loginArray.getJSONObject(i);

                ExameDomain exameDomain = new ExameDomain();
                Log.d("Exames", "ExamesLoop");

                exameDomain.setId_exame(Long.valueOf(array.getString("ID_EXAME")));
                exameDomain.setBimestre(Integer.valueOf(array.getString("BIMESTRE")));
                exameDomain.setMateria(Integer.valueOf(array.getString("MATERIA")));
                exameDomain.setData(array.getString("DATA"));




                SqliteExamesAdapter sqliteExamesAdapter = new SqliteExamesAdapter(context);

                sqliteExamesAdapter.adicionarExames(exameDomain);


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