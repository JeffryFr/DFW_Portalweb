package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "materia", schema = "portalcms")
@NamedQueries({
        @NamedQuery(name = "MateriaEntity.getAll", query = "SELECT m FROM MateriaEntity m")
})
public class MateriaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idMateria", nullable = false)
    private int idMateria;
    @Basic
    @Column(name = "materia", nullable = false, length = 20)
    private String materia;
    @OneToMany(mappedBy = "materiaByIdMateria")
    private Collection<MaestromateriaEntity> maestromateriasByIdMateria;

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MateriaEntity that = (MateriaEntity) o;

        if (idMateria != that.idMateria) return false;
        if (materia != null ? !materia.equals(that.materia) : that.materia != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMateria;
        result = 31 * result + (materia != null ? materia.hashCode() : 0);
        return result;
    }

    public Collection<MaestromateriaEntity> getMaestromateriasByIdMateria() {
        return maestromateriasByIdMateria;
    }

    public void setMaestromateriasByIdMateria(Collection<MaestromateriaEntity> maestromateriasByIdMateria) {
        this.maestromateriasByIdMateria = maestromateriasByIdMateria;
    }
}
