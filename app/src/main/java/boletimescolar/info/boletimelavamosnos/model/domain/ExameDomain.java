package boletimescolar.info.boletimelavamosnos.model.domain;

/**
 * Created by Norb7492 on 20/10/2016.
 */

public class ExameDomain {

    private long id_exame;
    private int bimestre;
    private int materia;
    private String data;


    public long getId_exame() {
        return id_exame;
    }

    public ExameDomain(long id_exame, int materia, String data, int bimestre) {
        this.id_exame = id_exame;
        this.materia = materia;
        this.data = data;
        this.bimestre = bimestre;
    }

    public void setId_exame(long id_exame) {
        this.id_exame = id_exame;
    }

    public int getBimestre() {
        return bimestre;
    }

    public void setBimestre(int bimestre) {
        this.bimestre = bimestre;
    }

    public int getMateria() {
        return materia;
    }

    public void setMateria(int materia) {
        this.materia = materia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ExameDomain() {

    }
}
