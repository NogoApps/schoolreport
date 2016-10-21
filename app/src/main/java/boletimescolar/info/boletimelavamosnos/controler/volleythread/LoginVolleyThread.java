package boletimescolar.info.boletimelavamosnos.controler.volleythread;

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
import java.util.Map;

import boletimescolar.info.boletimelavamosnos.model.domain.AlunoDomain;

/**
 * Created by Norb7492 on 07/09/2016.
 */
public class LoginVolleyThread extends Request<JSONObject> {


    private Response.Listener<JSONObject> response;
    private Map<String, String > params;
    private AlunoDomain alunoDomain;


    public LoginVolleyThread(AlunoDomain alunoDomain, int method, String url, Map<String, String> params, Response.Listener<JSONObject> response, Response.ErrorListener listener) {
        super(method, url, listener);
        this.params = params;
        this.response = response;
        this.alunoDomain = alunoDomain;
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
            JSONArray loginArray = jsonObject.getJSONArray("aluno");

            for (int i = 0; i < loginArray.length(); i++) {
                JSONObject array = loginArray.getJSONObject(i);




                alunoDomain.setId_aluno(Long.valueOf(array.getString("ID_ALUNO")));
                alunoDomain.setP_nome(array.getString("P_NOME"));
                alunoDomain.setS_nome(array.getString("S_NOME"));


                Log.d("TestLog", alunoDomain.getP_nome());

            }
            return(Response.success(new JSONObject(js),HttpHeaderParser.parseCacheHeaders(response)));
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
