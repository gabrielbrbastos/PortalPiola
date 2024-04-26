package entidades;

import entidades.Disciplina;
import java.sql.Date;
import java.sql.Time;

public class Horario extends Disciplina{

    private String diadeaula;
    private String horainicio;
    private String horafim;
    private String professor;

    public Horario(Integer id, String nome, String turno, String professor, String diadeaula, String horainicio, String horafim) {
        super(id, nome, turno);
        this.diadeaula = diadeaula;
        this.horainicio = horainicio;
        this.horafim = horafim;
        this.professor = professor;
    }

    public Horario(String nome, String turno, String professor, String diadeaula, String horainicio, String horafim) {
        super(nome, turno);
        this.diadeaula = diadeaula;
        this.horainicio = horainicio;
        this.horafim = horafim;
        this.professor = professor;
    }
    
    public Horario(){
    }

    public String getDiadeaula() {
        return diadeaula;
    }

    public void setDiadeaula(String diadeaula) {
        this.diadeaula = diadeaula;
    }

    public String getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(String horainicio) {
        this.horainicio = horainicio;
    }

    public String getHorafim() {
        return horafim;
    }

    public void setHorafim(String horafim) {
        this.horafim = horafim;
    }

    /**
     * @return the professor
     */
    public String getProfessor() {
        return professor;
    }

    /**
     * @param professor the professor to set
     */
    public void setProfessor(String professor) {
        this.professor = professor;
    }



}