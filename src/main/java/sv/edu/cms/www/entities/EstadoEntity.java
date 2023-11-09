package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "estado", schema = "portalcms", catalog = "")
public class EstadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idEstado", nullable = false)
    private int idEstado;
    @Basic
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;
    @OneToMany(mappedBy = "estadoByIdEstado")
    private Collection<AlumnoEntity> alumnosByIdEstado;
    @OneToMany(mappedBy = "estadoByIdEstado")
    private Collection<MaestroEntity> maestrosByIdEstado;

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstadoEntity that = (EstadoEntity) o;

        if (idEstado != that.idEstado) return false;
        if (estado != null ? !estado.equals(that.estado) : that.estado != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEstado;
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        return result;
    }

    public Collection<AlumnoEntity> getAlumnosByIdEstado() {
        return alumnosByIdEstado;
    }

    public void setAlumnosByIdEstado(Collection<AlumnoEntity> alumnosByIdEstado) {
        this.alumnosByIdEstado = alumnosByIdEstado;
    }

    public Collection<MaestroEntity> getMaestrosByIdEstado() {
        return maestrosByIdEstado;
    }

    public void setMaestrosByIdEstado(Collection<MaestroEntity> maestrosByIdEstado) {
        this.maestrosByIdEstado = maestrosByIdEstado;
    }
}
