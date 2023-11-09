package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "estadocaso", schema = "portalcms", catalog = "")
public class EstadocasoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idEstadoCaso", nullable = false)
    private int idEstadoCaso;
    @Basic
    @Column(name = "Estado", nullable = false, length = 20)
    private String estado;
    @OneToMany(mappedBy = "estadocasoByIdEstadoCaso")
    private Collection<CasoEntity> casosByIdEstadoCaso;

    public int getIdEstadoCaso() {
        return idEstadoCaso;
    }

    public void setIdEstadoCaso(int idEstadoCaso) {
        this.idEstadoCaso = idEstadoCaso;
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

        EstadocasoEntity that = (EstadocasoEntity) o;

        if (idEstadoCaso != that.idEstadoCaso) return false;
        if (estado != null ? !estado.equals(that.estado) : that.estado != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEstadoCaso;
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        return result;
    }

    public Collection<CasoEntity> getCasosByIdEstadoCaso() {
        return casosByIdEstadoCaso;
    }

    public void setCasosByIdEstadoCaso(Collection<CasoEntity> casosByIdEstadoCaso) {
        this.casosByIdEstadoCaso = casosByIdEstadoCaso;
    }
}
