package boletimescolar.info.boletimelavamosnos.firebase;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import boletimescolar.info.boletimelavamosnos.model.sharedpreferences.AlunoShared;
import boletimescolar.info.boletimelavamosnos.model.sharedpreferences.TokenShared;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Request;


/**
 * Created by Norb7492 on 14/10/2016.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();


        TokenShared.addToken(token,this);




    }







}
