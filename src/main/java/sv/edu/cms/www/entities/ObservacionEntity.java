package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "observacion", schema = "portalcms", catalog = "")
public class ObservacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idObservacion", nullable = false)
    private int idObservacion;
    @Basic
    @Column(name = "observacion", nullable = false, length = 200)
    private String observacion;
    @OneToMany(mappedBy = "observacionByIdObservacion")
    private Collection<DiarioEntity> diariosByIdObservacion;
    @ManyToOne
    @JoinColumn(name = "idCategoria", referencedColumnName = "idCategoria", nullable = false)
    private CategoriaEntity categoriaByIdCategoria;

    public int getIdObservacion() {
        return idObservacion;
    }

    public void setIdObservacion(int idObservacion) {
        this.idObservacion = idObservacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObservacionEntity that = (ObservacionEntity) o;

        if (idObservacion != that.idObservacion) return false;
        if (observacion != null ? !observacion.equals(that.observacion) : that.observacion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idObservacion;
        result = 31 * result + (observacion != null ? observacion.hashCode() : 0);
        return result;
    }

    public Collection<DiarioEntity> getDiariosByIdObservacion() {
        return diariosByIdObservacion;
    }

    public void setDiariosByIdObservacion(Collection<DiarioEntity> diariosByIdObservacion) {
        this.diariosByIdObservacion = diariosByIdObservacion;
    }

    public CategoriaEntity getCategoriaByIdCategoria() {
        return categoriaByIdCategoria;
    }

    public void setCategoriaByIdCategoria(CategoriaEntity categoriaByIdCategoria) {
        this.categoriaByIdCategoria = categoriaByIdCategoria;
    }
}
