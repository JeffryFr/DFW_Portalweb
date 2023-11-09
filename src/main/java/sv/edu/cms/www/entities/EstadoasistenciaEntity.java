package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "estadoasistencia", schema = "portalcms", catalog = "")
public class EstadoasistenciaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idEstado", nullable = false)
    private int idEstado;
    @Basic
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;
    @OneToMany(mappedBy = "estadoasistenciaByIdEstado")
    private Collection<AsistenciaEntity> asistenciasByIdEstado;

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

        EstadoasistenciaEntity that = (EstadoasistenciaEntity) o;

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

    public Collection<AsistenciaEntity> getAsistenciasByIdEstado() {
        return asistenciasByIdEstado;
    }

    public void setAsistenciasByIdEstado(Collection<AsistenciaEntity> asistenciasByIdEstado) {
        this.asistenciasByIdEstado = asistenciasByIdEstado;
    }
}
