package boletimescolar.info.boletimelavamosnos.sqlite.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import boletimescolar.info.boletimelavamosnos.sqlite.constants.DataConstants;
import boletimescolar.info.boletimelavamosnos.sqlite.constants.ExamesConstants;
import boletimescolar.info.boletimelavamosnos.sqlite.constants.NotasConstants;

/**
 * Created by Norb7492 on 07/10/2016.
 */

public class SqliteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "escola.DB";
    private static final int DB_VERSION = 1;

    public SqliteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        try {
            sqLiteDatabase.execSQL(NotasConstants.buildTable());
            sqLiteDatabase.execSQL(ExamesConstants.buildTable());
            sqLiteDatabase.execSQL(DataConstants.buildTable());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NotasConstants.buildTable());
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ExamesConstants.buildTable());
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DataConstants.buildTable());

    }
}
