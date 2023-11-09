package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "permiso", schema = "portalcms", catalog = "")
public class PermisoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPermiso", nullable = false)
    private int idPermiso;
    @Basic
    @Column(name = "permiso", nullable = false, length = 200)
    private String permiso;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "idAlumno", referencedColumnName = "idAlumno", nullable = false)
    private AlumnoEntity alumnoByIdAlumno;

    public int getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(int idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
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

        PermisoEntity that = (PermisoEntity) o;

        if (idPermiso != that.idPermiso) return false;
        if (permiso != null ? !permiso.equals(that.permiso) : that.permiso != null) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPermiso;
        result = 31 * result + (permiso != null ? permiso.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }

    public AlumnoEntity getAlumnoByIdAlumno() {
        return alumnoByIdAlumno;
    }

    public void setAlumnoByIdAlumno(AlumnoEntity alumnoByIdAlumno) {
        this.alumnoByIdAlumno = alumnoByIdAlumno;
    }
}
