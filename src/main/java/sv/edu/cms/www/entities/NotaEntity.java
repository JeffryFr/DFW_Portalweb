package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "nota", schema = "portalcms", catalog = "")
public class NotaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idNota", nullable = false)
    private int idNota;
    @Basic
    @Column(name = "nota", nullable = false, precision = 0)
    private int nota;
    @Basic
    @Column(name = "observacion", nullable = false, length = 100)
    private String observacion;
    @Basic
    @Column(name = "ingresoNota", nullable = false)
    private Date ingresoNota;
    @ManyToOne
    @JoinColumn(name = "idAlumno", referencedColumnName = "idAlumno", nullable = false)
    private AlumnoEntity alumnoByIdAlumno;
    @ManyToOne
    @JoinColumn(name = "idEvaluacion", referencedColumnName = "idEvaluacion", nullable = false)
    private EvaluacionEntity evaluacionByIdEvaluacion;

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getIngresoNota() {
        return ingresoNota;
    }

    public void setIngresoNota(Date ingresoNota) {
        this.ingresoNota = ingresoNota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotaEntity that = (NotaEntity) o;

        if (idNota != that.idNota) return false;
        if (nota != that.nota) return false;
        if (observacion != null ? !observacion.equals(that.observacion) : that.observacion != null) return false;
        if (ingresoNota != null ? !ingresoNota.equals(that.ingresoNota) : that.ingresoNota != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idNota;
        result = 31 * result + nota;
        result = 31 * result + (observacion != null ? observacion.hashCode() : 0);
        result = 31 * result + (ingresoNota != null ? ingresoNota.hashCode() : 0);
        return result;
    }

    public AlumnoEntity getAlumnoByIdAlumno() {
        return alumnoByIdAlumno;
    }

    public void setAlumnoByIdAlumno(AlumnoEntity alumnoByIdAlumno) {
        this.alumnoByIdAlumno = alumnoByIdAlumno;
    }

    public EvaluacionEntity getEvaluacionByIdEvaluacion() {
        return evaluacionByIdEvaluacion;
    }

    public void setEvaluacionByIdEvaluacion(EvaluacionEntity evaluacionByIdEvaluacion) {
        this.evaluacionByIdEvaluacion = evaluacionByIdEvaluacion;
    }
}
