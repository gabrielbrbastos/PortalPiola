package entidades;

import java.sql.Time;
import java.sql.Date;

public class Disciplina {
    private Integer id;
    private String nome;
    private String turno;


    public Disciplina(Integer id, String nome, String turno) {
        this.id = id;
        this.nome = nome;
        this.turno = turno;
    }

    public Disciplina(String nome, String turno) {
        this.nome = nome;
        this.turno = turno;

    }

    public Disciplina() {
        
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
