package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "categoria", schema = "portalcms", catalog = "")
public class CategoriaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCategoria", nullable = false)
    private int idCategoria;
    @Basic
    @Column(name = "categoria", nullable = false, length = 20)
    private String categoria;
    @OneToMany(mappedBy = "categoriaByIdCategoria")
    private Collection<ObservacionEntity> observacionsByIdCategoria;

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoriaEntity that = (CategoriaEntity) o;

        if (idCategoria != that.idCategoria) return false;
        if (categoria != null ? !categoria.equals(that.categoria) : that.categoria != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCategoria;
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        return result;
    }

    public Collection<ObservacionEntity> getObservacionsByIdCategoria() {
        return observacionsByIdCategoria;
    }

    public void setObservacionsByIdCategoria(Collection<ObservacionEntity> observacionsByIdCategoria) {
        this.observacionsByIdCategoria = observacionsByIdCategoria;
    }
}
