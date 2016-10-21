package boletimescolar.info.boletimelavamosnos.model.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

/**
 * Created by Norb7492 on 14/10/2016.
 */

public class TokenShared {

    Context ctx;



    public static void addToken(String token,Context ctx){

        SharedPreferences sharedPreferences = ctx.getSharedPreferences("tokenInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);

        editor.apply();

    }


    public static String getToken(Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences("tokenInfo", Context.MODE_PRIVATE);


        return sharedPreferences.getString("token", "");








    }


    public static void RegisterToken(Context ctx, String register){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences("tokenRegister", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("register", register);

        editor.apply();



    }

    public static String getRegister(Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences("tokenRegister", Context.MODE_PRIVATE);


        return sharedPreferences.getString("register", "");








    }

    public static void delete(Context context){

        SharedPreferences settings = context.getSharedPreferences("tokenRegister", Context.MODE_PRIVATE);
        settings.edit().clear().commit();
        SharedPreferences settings2 = context.getSharedPreferences("tokenInfo", Context.MODE_PRIVATE);
        settings2.edit().clear().commit();
    }



}
