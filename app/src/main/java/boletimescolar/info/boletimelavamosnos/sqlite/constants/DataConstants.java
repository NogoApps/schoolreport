package boletimescolar.info.boletimelavamosnos.sqlite.constants;

/**
 * Created by Norb7492 on 14/11/2016.
 */

public class DataConstants {


    private final static String ID = "id";
    private final static String ANO = "ano";
    private final static String TABLE = "ANO";

    public static String buildTable(){


        StringBuilder builder = new StringBuilder();
        builder.append("Create Table ");
        builder.append(TABLE);
        builder.append(" (");
        builder.append(ID);
        builder.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        builder.append(ANO);
        builder.append(" TEXT NOT NULL);");



        return builder.toString();

    }

    public static String getID() {
        return ID;
    }


    public static String getANO() {
        return ANO;

    }

    public static String getTABLE() {
        return TABLE;
    }
}
