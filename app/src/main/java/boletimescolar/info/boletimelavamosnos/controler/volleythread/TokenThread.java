package boletimescolar.info.boletimelavamosnos.controler.volleythread;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import boletimescolar.info.boletimelavamosnos.model.activitymodel.MainActivityModel;
import boletimescolar.info.boletimelavamosnos.model.sharedpreferences.TokenShared;
import boletimescolar.info.boletimelavamosnos.view.activities.MainActivity;

/**
 * Created by Norb7492 on 14/10/2016.
 */

public class TokenThread extends Request<JSONObject> {


    private Response.Listener<JSONObject> response;
    private Map<String, String> params;
    private Context context;

    public TokenThread(Context context, int method, String url,Map<String, String> params,Response.Listener<JSONObject> response,Response.ErrorListener listener) {
        super(method, url, listener);
        this.context = context;
        this.response = response;
        this.params = params;
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
