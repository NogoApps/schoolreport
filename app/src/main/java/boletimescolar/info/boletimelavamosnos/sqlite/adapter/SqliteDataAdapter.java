package boletimescolar.info.boletimelavamosnos.sqlite.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import boletimescolar.info.boletimelavamosnos.model.domain.BimestreAnoDomain;
import boletimescolar.info.boletimelavamosnos.model.domain.ExameDomain;
import boletimescolar.info.boletimelavamosnos.sqlite.constants.DataConstants;
import boletimescolar.info.boletimelavamosnos.sqlite.constants.ExamesConstants;
import boletimescolar.info.boletimelavamosnos.sqlite.helper.SqliteHelper;

/**
 * Created by Norb7492 on 14/11/2016.
 */

public class SqliteDataAdapter {

    Context ctx;
    SQLiteDatabase db;
    SqliteHelper helper;

    public SqliteDataAdapter(Context ctx) {
        this.ctx = ctx;
        helper = new SqliteHelper(ctx);


    }

    public SqliteDataAdapter openDB() {

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


    public long adicionarData(BimestreAnoDomain b) {


        Log.d("Data sqlite", "Chamou Aqui");

        ContentValues cv = new ContentValues();

        openDB();


        cv.put(DataConstants.getTABLE(), b.getData());


        long id =  db.insert(DataConstants.getTABLE(), null, cv);


        closeDB();
        return id;

    }

    public Cursor listarData() {


        String[] columns={DataConstants.getANO()};

        return db.query(DataConstants.getTABLE(), columns,null,null,null,null,null,null);

    }

    public boolean  dataIsEmpty() {

        boolean flag;
        String quString = "select exists(select 1 from " + DataConstants.getTABLE()  + ");";

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
        db.execSQL("DELETE FROM " + DataConstants.getTABLE());
        db.execSQL("delete from sqlite_sequence where name= '" + DataConstants.getTABLE() + "';");




        closeDB();
    }

}
