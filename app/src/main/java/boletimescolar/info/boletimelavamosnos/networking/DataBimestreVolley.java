package boletimescolar.info.boletimelavamosnos.networking;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import boletimescolar.info.boletimelavamosnos.model.domain.BimestreAnoDomain;
import boletimescolar.info.boletimelavamosnos.sqlite.adapter.SqliteDataAdapter;

/**
 * Created by Norb7492 on 14/11/2016.
 */

public class DataBimestreVolley extends Request<JSONObject> {


    private Response.Listener<JSONObject> response;
    private Context ctx;


    public DataBimestreVolley(int method, String url,  Context ctx,Response.Listener<JSONObject> response, Response.ErrorListener listener) {
        super(method, url, listener);
        this.response = response;
        this.ctx = ctx;
    }



    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {




            String js = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            JSONObject jsonObject = new JSONObject(js);
            JSONArray loginArray = jsonObject.getJSONArray("anos");

            for (int i = 0; i < loginArray.length(); i++) {
                JSONObject array = loginArray.getJSONObject(i);

                BimestreAnoDomain b = new BimestreAnoDomain();

                b.setData(array.getString("ANO"));

                SqliteDataAdapter sqliteDataAdapter = new SqliteDataAdapter(ctx);
                sqliteDataAdapter.adicionarData(b);


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
