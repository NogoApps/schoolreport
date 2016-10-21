package boletimescolar.info.boletimelavamosnos.model.domain;

/**
 * Created by Norb7492 on 08/09/2016.
 */
public class AlunoDomain {

    private long id_aluno;
    private String p_nome;
    private String s_nome;


    public AlunoDomain() {
    }

    public long getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(long id_aluno) {
        this.id_aluno = id_aluno;
    }

    public String getS_nome() {
        return s_nome;
    }

    public void setS_nome(String s_nome) {
        this.s_nome = s_nome;
    }

    public String getP_nome() {
        return p_nome;
    }

    public void setP_nome(String p_nome) {
        this.p_nome = p_nome;
    }
}
