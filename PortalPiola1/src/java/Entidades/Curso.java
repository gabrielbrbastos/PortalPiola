package entidades;

public class Curso {

    private Integer id;
    private String nome;
    private String turno;

    public Curso(Integer id, String nome, String turno) {
        super();
        this.id = id;
        this.nome = nome;
        this.turno = turno;
    }

    public Curso(String nome, String turno) {
        super();
        this.nome = nome;
        this.turno = turno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

}
