package boletimescolar.info.boletimelavamosnos.sqlite.constants;

/**
 * Created by Norb7492 on 20/10/2016.
 */

public class ExamesConstants {


    private final static String ID = "id";
    private final static String MATERIA = "materia";
    private final static String BIMESTRE = "bimestre";
    private final static String DATA = "data";
    private final static String TABLE = "EXAMES";

    public static String buildTable(){


        StringBuilder builder = new StringBuilder();
        builder.append("Create Table ");
        builder.append(TABLE);
        builder.append(" (");
        builder.append(ID);
        builder.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        builder.append(MATERIA);
        builder.append(" INTEGER NOT NULL, ");
        builder.append(DATA);
        builder.append(" TEXT NOT NULL, ");
        builder.append(BIMESTRE);
        builder.append(" INTEGER NOT NULL);");



        return builder.toString();

    }


    public static String getID() {
        return ID;
    }

    public static String getTABLE() {
        return TABLE;
    }

    public static String getDATA() {
        return DATA;
    }

    public static String getBIMESTRE() {
        return BIMESTRE;
    }

    public static String getMATERIA() {
        return MATERIA;
    }
}
