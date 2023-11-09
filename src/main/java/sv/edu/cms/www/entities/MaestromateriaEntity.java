package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "maestromateria", schema = "portalcms")
@NamedQueries({
        @NamedQuery(name = "MaestromateriaEntity.getAll", query = "SELECT mm FROM MaestromateriaEntity mm"),
        @NamedQuery(name = "MaestromateriaEntity.getById", query = "SELECT mm FROM MaestromateriaEntity mm WHERE mm.idMaestroMateria = :idmm"),
        @NamedQuery(name = "MaestromateriaEntity.getByMaestro", query = "SELECT mm FROM MaestromateriaEntity mm WHERE mm.maestroByIdMaestro = :maestro"),
        @NamedQuery(name = "MaestromateriaEntity.getByMaestroAndMateria", query = "SELECT mm FROM MaestromateriaEntity mm WHERE mm.maestroByIdMaestro = :maestro AND mm.materiaByIdMateria = :materia"),
        @NamedQuery(name = "MaestromateriaEntity.getByMaestroAndMateriaLabel", query = "SELECT mm FROM MaestromateriaEntity mm WHERE mm.maestroByIdMaestro = :maestro AND mm.materiaByIdMateria.materia = :materia")
})
public class MaestromateriaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idMaestroMateria", nullable = false)
    private int idMaestroMateria;
    @OneToMany(mappedBy = "maestromateriaByIdMateriaMaestro")
    private Collection<ClaseEntity> clasesByIdMaestroMateria;
    @ManyToOne
    @JoinColumn(name = "idMateria", referencedColumnName = "idMateria", nullable = false)
    private MateriaEntity materiaByIdMateria;
    @ManyToOne
    @JoinColumn(name = "idMaestro", referencedColumnName = "idMaestro", nullable = false)
    private MaestroEntity maestroByIdMaestro;

    public int getIdMaestroMateria() {
        return idMaestroMateria;
    }

    public void setIdMaestroMateria(int idMaestroMateria) {
        this.idMaestroMateria = idMaestroMateria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaestromateriaEntity that = (MaestromateriaEntity) o;

        if (idMaestroMateria != that.idMaestroMateria) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idMaestroMateria;
    }

    public Collection<ClaseEntity> getClasesByIdMaestroMateria() {
        return clasesByIdMaestroMateria;
    }

    public void setClasesByIdMaestroMateria(Collection<ClaseEntity> clasesByIdMaestroMateria) {
        this.clasesByIdMaestroMateria = clasesByIdMaestroMateria;
    }

    public MateriaEntity getMateriaByIdMateria() {
        return materiaByIdMateria;
    }

    public void setMateriaByIdMateria(MateriaEntity materiaByIdMateria) {
        this.materiaByIdMateria = materiaByIdMateria;
    }

    public MaestroEntity getMaestroByIdMaestro() {
        return maestroByIdMaestro;
    }

    public void setMaestroByIdMaestro(MaestroEntity maestroByIdMaestro) {
        this.maestroByIdMaestro = maestroByIdMaestro;
    }
}
