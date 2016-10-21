package boletimescolar.info.boletimelavamosnos.sqlite.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import boletimescolar.info.boletimelavamosnos.model.domain.ProvaDomain;
import boletimescolar.info.boletimelavamosnos.sqlite.helper.SqliteHelper;
import boletimescolar.info.boletimelavamosnos.sqlite.constants.NotasConstants;
import boletimescolar.info.boletimelavamosnos.view.adapters.RecyclerViewAdapter;

/**
 * Created by Norb7492 on 07/10/2016.
 */

public class SqliteNotasAdapter {

    Context ctx;
    SQLiteDatabase db;
    SqliteHelper helper;




    public SqliteNotasAdapter(Context ctx) {
        this.ctx = ctx;
        helper = new SqliteHelper(ctx);


    }


    public SqliteNotasAdapter openDB() {

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


    public long adicionarCompras(ProvaDomain provaDomain) {


        ContentValues cv = new ContentValues();

        openDB();


        cv.put(NotasConstants.getMATERIA(), provaDomain.getMateria());
        cv.put(NotasConstants.getNOTAS(), provaDomain.getNota());
        cv.put(NotasConstants.getFALTAS(), provaDomain.getFaltas());
        cv.put(NotasConstants.getBIMESTRE(), provaDomain.getBimestre());


        long id =  db.insert(NotasConstants.getTABLE(), null, cv);


        closeDB();
        return id;

    }

    public Cursor listarCompras() {
        Log.d("Sqlite", "listarComprasChamado");

        String[] columns={NotasConstants.getID(), NotasConstants.getMATERIA(), NotasConstants.getNOTAS(),NotasConstants.getFALTAS(), NotasConstants.getBIMESTRE()};

        return db.query(NotasConstants.getTABLE(), columns,null,null,null,null,null,null);

    }

    public void listarComprasWhere(int bimestre, List<ProvaDomain> provaArray, RecyclerViewAdapter recyclerViewAdapter, RecyclerView recyclerView) {
        openDB();
        provaArray.clear();

        String[] columns={NotasConstants.getID(), NotasConstants.getMATERIA(), NotasConstants.getNOTAS(),NotasConstants.getFALTAS(), NotasConstants.getBIMESTRE()};

        Cursor cursor = db.query(NotasConstants.getTABLE(), columns,NotasConstants.getBIMESTRE()+" = '"+bimestre+"'",null,null,null,null);


        Log.d("Bimestre", String.valueOf(bimestre));

        while (cursor.moveToNext()) {


            ProvaDomain provaDomain = new ProvaDomain(cursor.getLong(0), cursor.getInt(1), cursor.getDouble(2), cursor.getInt(3), cursor.getInt(4));



            provaArray.add(provaDomain);


        }


        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
        db.close();

    }

    public boolean  notasIsEmpty() {

        boolean flag;
        String quString = "select exists(select 1 from " + NotasConstants.getTABLE()  + ");";

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
        db.execSQL("DELETE FROM " + NotasConstants.getTABLE());
        db.execSQL("delete from sqlite_sequence where name= '" + NotasConstants.getTABLE() + "';");




            closeDB();
        }



}
