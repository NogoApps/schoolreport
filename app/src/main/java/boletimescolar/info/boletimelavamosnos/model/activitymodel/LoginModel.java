package boletimescolar.info.boletimelavamosnos.model.activitymodel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import boletimescolar.info.boletimelavamosnos.R;
import boletimescolar.info.boletimelavamosnos.controler.volleythread.LoginVolleyThread;
import boletimescolar.info.boletimelavamosnos.model.domain.AlunoDomain;
import boletimescolar.info.boletimelavamosnos.model.ips.Ip;
import boletimescolar.info.boletimelavamosnos.model.sharedpreferences.AlunoShared;
import boletimescolar.info.boletimelavamosnos.singleton.VolleySingleton;
import boletimescolar.info.boletimelavamosnos.view.activities.MainActivity;

/**
 * Created by Norb7492 on 08/09/2016.
 */
public class LoginModel {

    private Context ctx;
    private Map<String, String> params;
    private AlunoDomain alunoDomain;
    private AlunoShared alunoShared;
    private ProgressDialog progressDialog;


    public LoginModel(Context ctx,ProgressDialog progressDialog) {

        this.ctx = ctx;
        this.alunoDomain = new AlunoDomain();
        this.alunoShared = new AlunoShared(ctx);
        this.progressDialog = progressDialog;


    }


    //Autenticação Login
    public void loginAut(String raAluno, final Activity activity) {

        params = new HashMap<String, String>();
        params.put("acao", "aluno");
        params.put("ra", raAluno);

        progressDialog = new ProgressDialog(ctx);
        progressDialog.setMessage("Fazendo Login....");
        progressDialog.show();



        LoginVolleyThread loginVolleyThread = new LoginVolleyThread(alunoDomain, Request.Method.POST, Ip.ip, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                progressDialog.dismiss();
                alunoShared.addAluno(alunoDomain.getId_aluno(), alunoDomain.getP_nome(), alunoDomain.getS_nome());

                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
                activity.finish();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                NetworkResponse response = error.networkResponse;
                if (response != null && response.data != null) {
                    switch (response.statusCode) {
                        case 404:
                            Toast.makeText(ctx, R.string.user_not_found, Toast.LENGTH_SHORT).show();
                            break;

                        case 403:
                            Toast.makeText(ctx, R.string.forbidden, Toast.LENGTH_SHORT).show();
                            break;

                    }


                }

            }
        }

        );
        VolleySingleton.getInstance(ctx).addToRequestQueue(loginVolleyThread);


//        RequestQueue queue = Volley.newRequestQueue(ctx);
//        queue.add(loginVolleyThread);
    }


    //Verificar se campos estão preenchidos

    public boolean checkFields(EditText ra) {

        boolean check;

        if (ra.getText().toString().trim().length() == 0) {

            check = false;

        } else {

            check = true;
        }

        return check;

    }

    //Verificar se usuario esta logado
    public void checkAlunoLogged(Activity activity) {

        if (alunoShared.checkAluno().isEmpty()) {


        } else {


            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
            activity.finish();


        }
    }

}
