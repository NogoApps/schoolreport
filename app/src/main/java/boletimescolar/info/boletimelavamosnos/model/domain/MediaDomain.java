package boletimescolar.info.boletimelavamosnos.model.domain;

/**
 * Created by Norb7492 on 07/10/2016.
 */

public class MediaDomain {

    private int materia;
    private double media;


    public MediaDomain() {
    }

    public MediaDomain(int materia, double media) {
        this.materia = materia;
        this.media = media;
    }

    public int getMateria() {
        return materia;
    }

    public void setMateria(int materia) {
        this.materia = materia;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }
}
