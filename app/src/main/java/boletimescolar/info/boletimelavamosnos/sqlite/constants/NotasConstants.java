package boletimescolar.info.boletimelavamosnos.sqlite.constants;

/**
 * Created by Norb7492 on 07/10/2016.
 */

public class NotasConstants {


    private final static String ID = "id";
    private final static String MATERIA = "materia";
    private final static String NOTAS = "notas";
    private final static String FALTAS = "faltas";
    private final static String BIMESTRE = "bimestre";
    private final static String DATA = "data";
    private final static String TABLE = "NOTAS";




    public static String buildTable(){


        StringBuilder builder = new StringBuilder();
        builder.append("Create Table ");
        builder.append(TABLE);
        builder.append(" (");
        builder.append(ID);
        builder.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        builder.append(MATERIA);
        builder.append(" INTEGER NOT NULL, ");
        builder.append(NOTAS);
        builder.append(" REAL NOT NULL, ");
        builder.append(FALTAS);
        builder.append(" INTEGER NOT NULL, ");
        builder.append(BIMESTRE);
        builder.append(" INTEGER NOT NULL, ");
        builder.append(DATA);
        builder.append(" TEXT NOT NULL);");



        return builder.toString();

    }


    public static String getDATA() {
        return DATA;
    }

    public static String getID() {
        return ID;
    }

    public static String getMATERIA() {
        return MATERIA;
    }

    public static String getNOTAS() {
        return NOTAS;
    }

    public static String getFALTAS() {
        return FALTAS;
    }

    public static String getTABLE() {
        return TABLE;
    }

    public static String getBIMESTRE() {
        return BIMESTRE;
    }
}
