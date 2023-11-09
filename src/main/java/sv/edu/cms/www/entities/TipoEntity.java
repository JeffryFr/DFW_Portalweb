package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "tipo", schema = "portalcms", catalog = "")
public class TipoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idTipo", nullable = false)
    private int idTipo;
    @Basic
    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;
    @OneToMany(mappedBy = "tipoByIdTipo")
    private Collection<EvaluacionEntity> evaluacionsByIdTipo;

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoEntity that = (TipoEntity) o;

        if (idTipo != that.idTipo) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTipo;
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        return result;
    }

    public Collection<EvaluacionEntity> getEvaluacionsByIdTipo() {
        return evaluacionsByIdTipo;
    }

    public void setEvaluacionsByIdTipo(Collection<EvaluacionEntity> evaluacionsByIdTipo) {
        this.evaluacionsByIdTipo = evaluacionsByIdTipo;
    }
}
