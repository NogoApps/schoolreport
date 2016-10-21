package boletimescolar.info.boletimelavamosnos.model.domain;

/**
 * Created by Norb7492 on 13/09/2016.
 */
public class ProvaDomain {


    private long id_prova;
    private double nota;
    private int materia;
    private int faltas;
    private int bimestre;

    public ProvaDomain() {
    }


    public ProvaDomain(long id_prova, int materia, double nota, int faltas, int bimestre) {
        this.id_prova = id_prova;
        this.materia = materia;
        this.nota = nota;
        this.faltas = faltas;
        this.bimestre = bimestre;
    }

    public long getId_prova() {
        return id_prova;
    }

    public void setId_prova(long id_prova) {
        this.id_prova = id_prova;
    }




    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getMateria() {
        return materia;
    }

    public void setMateria(int materia) {
        this.materia = materia;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public int getBimestre() {
        return bimestre;
    }

    public void setBimestre(int bimestre) {
        this.bimestre = bimestre;
    }
}
