package boletimescolar.info.boletimelavamosnos.sqlite.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import boletimescolar.info.boletimelavamosnos.model.domain.ExameDomain;
import boletimescolar.info.boletimelavamosnos.model.domain.ProvaDomain;
import boletimescolar.info.boletimelavamosnos.sqlite.constants.ExamesConstants;
import boletimescolar.info.boletimelavamosnos.sqlite.constants.NotasConstants;
import boletimescolar.info.boletimelavamosnos.sqlite.helper.SqliteHelper;

/**
 * Created by Norb7492 on 20/10/2016.
 */

public class SqliteExamesAdapter {

    Context ctx;
    SQLiteDatabase db;
    SqliteHelper helper;




    public SqliteExamesAdapter(Context ctx) {
        this.ctx = ctx;
        helper = new SqliteHelper(ctx);


    }

    public SqliteExamesAdapter openDB() {

        try {
            db = helper.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public void closeDB(){

        try {
            helper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public long adicionarExames(ExameDomain exameDomain) {


        ContentValues cv = new ContentValues();

        openDB();


        cv.put(ExamesConstants.getMATERIA(), exameDomain.getMateria());
        cv.put(ExamesConstants.getDATA(), exameDomain.getData());
        cv.put(ExamesConstants.getBIMESTRE(), exameDomain.getBimestre());


        long id =  db.insert(ExamesConstants.getTABLE(), null, cv);


        closeDB();
        return id;

    }

    public Cursor listarExames() {


        String[] columns={ExamesConstants.getID(), ExamesConstants.getMATERIA(), ExamesConstants.getDATA(),ExamesConstants.getBIMESTRE()};

        return db.query(ExamesConstants.getTABLE(), columns,null,null,null,null,null,null);

    }

    public boolean  notasIsEmpty() {

        boolean flag;
        String quString = "select exists(select 1 from " + ExamesConstants.getTABLE()  + ");";

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(quString, null);
        cursor.moveToFirst();
        int count= cursor.getInt(0);
        if (count ==1) {
            flag =  false;
        } else {
            flag = true;
        }
        cursor.close();
        db.close();

        return flag;
    }

    public void apagarTudo(){
        openDB();
        db.execSQL("DELETE FROM " + ExamesConstants.getTABLE());
        db.execSQL("delete from sqlite_sequence where name= '" + ExamesConstants.getTABLE() + "';");




        closeDB();
    }
}
