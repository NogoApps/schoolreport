package boletimescolar.info.boletimelavamosnos.model.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

/**
 * Created by Norb7492 on 08/09/2016.
 */
public class AlunoShared {

    Context ctx;

    public AlunoShared(Context ctx){


        this.ctx = ctx;


    }



    //Ve se aluno esta logado
    public String checkAluno(){


        SharedPreferences sharedPreferences = ctx.getSharedPreferences("alunoInfo", Context.MODE_PRIVATE);
        String p_nome = sharedPreferences.getString("p_nome", "");

        return p_nome;

    }


    public void addAluno(long id_aluno, String p_nome, String s_nome){

        SharedPreferences sharedPreferences = ctx.getSharedPreferences("alunoInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("id_aluno", id_aluno);
        editor.putString("p_nome", p_nome);
        editor.putString("s_nome", s_nome);

        editor.apply();



    }

    public static void getUserName(Context context,TextView p_nome){

        SharedPreferences sharedPreferences = context.getSharedPreferences("alunoInfo", Context.MODE_PRIVATE);
        p_nome.setText(sharedPreferences.getString("p_nome", ""));







    }

    //Pega o id do usuário

    public static long getIdUser(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("alunoInfo", Context.MODE_PRIVATE);
        long id = sharedPreferences.getLong("id_aluno", 0);


        return id;



    }

    public static void delete(Context context){

        SharedPreferences settings = context.getSharedPreferences("alunoInfo", Context.MODE_PRIVATE);
        settings.edit().clear().commit();
    }




}
