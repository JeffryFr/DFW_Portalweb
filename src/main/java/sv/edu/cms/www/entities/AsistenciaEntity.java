package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "asistencia", schema = "portalcms", catalog = "")
public class AsistenciaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idAsistencia", nullable = false)
    private int idAsistencia;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado", nullable = false)
    private EstadoasistenciaEntity estadoasistenciaByIdEstado;
    @ManyToOne
    @JoinColumn(name = "idClase", referencedColumnName = "idClase", nullable = false)
    private ClaseEntity claseByIdClase;
    @ManyToOne
    @JoinColumn(name = "idAlumno", referencedColumnName = "idAlumno", nullable = false)
    private AlumnoEntity alumnoByIdAlumno;

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
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

        AsistenciaEntity that = (AsistenciaEntity) o;

        if (idAsistencia != that.idAsistencia) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAsistencia;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }

    public EstadoasistenciaEntity getEstadoasistenciaByIdEstado() {
        return estadoasistenciaByIdEstado;
    }

    public void setEstadoasistenciaByIdEstado(EstadoasistenciaEntity estadoasistenciaByIdEstado) {
        this.estadoasistenciaByIdEstado = estadoasistenciaByIdEstado;
    }

    public ClaseEntity getClaseByIdClase() {
        return claseByIdClase;
    }

    public void setClaseByIdClase(ClaseEntity claseByIdClase) {
        this.claseByIdClase = claseByIdClase;
    }

    public AlumnoEntity getAlumnoByIdAlumno() {
        return alumnoByIdAlumno;
    }

    public void setAlumnoByIdAlumno(AlumnoEntity alumnoByIdAlumno) {
        this.alumnoByIdAlumno = alumnoByIdAlumno;
    }
}
