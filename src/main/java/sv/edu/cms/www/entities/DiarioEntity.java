package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "diario", schema = "portalcms", catalog = "")
public class DiarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idDiario", nullable = false)
    private int idDiario;
    @Basic
    @Column(name = "nota", nullable = false, length = 200)
    private String nota;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "idMaestro", referencedColumnName = "idMaestro", nullable = false)
    private MaestroEntity maestroByIdMaestro;
    @ManyToOne
    @JoinColumn(name = "idAlumno", referencedColumnName = "idAlumno", nullable = false)
    private AlumnoEntity alumnoByIdAlumno;
    @ManyToOne
    @JoinColumn(name = "idObservacion", referencedColumnName = "idObservacion", nullable = false)
    private ObservacionEntity observacionByIdObservacion;

    public int getIdDiario() {
        return idDiario;
    }

    public void setIdDiario(int idDiario) {
        this.idDiario = idDiario;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiarioEntity that = (DiarioEntity) o;

        if (idDiario != that.idDiario) return false;
        if (nota != null ? !nota.equals(that.nota) : that.nota != null) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDiario;
        result = 31 * result + (nota != null ? nota.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }

    public MaestroEntity getMaestroByIdMaestro() {
        return maestroByIdMaestro;
    }

    public void setMaestroByIdMaestro(MaestroEntity maestroByIdMaestro) {
        this.maestroByIdMaestro = maestroByIdMaestro;
    }

    public AlumnoEntity getAlumnoByIdAlumno() {
        return alumnoByIdAlumno;
    }

    public void setAlumnoByIdAlumno(AlumnoEntity alumnoByIdAlumno) {
        this.alumnoByIdAlumno = alumnoByIdAlumno;
    }

    public ObservacionEntity getObservacionByIdObservacion() {
        return observacionByIdObservacion;
    }

    public void setObservacionByIdObservacion(ObservacionEntity observacionByIdObservacion) {
        this.observacionByIdObservacion = observacionByIdObservacion;
    }
}
