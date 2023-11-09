package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "rol", schema = "portalcms")
@NamedQueries({
        @NamedQuery(name="RolEntity.getAll", query="SELECT r FROM RolEntity r")
})
public class RolEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idRol", nullable = false)
    private int idRol;
    @Basic
    @Column(name = "Rol", nullable = false, length = 20)
    private String rol;
    @OneToMany(mappedBy = "rolByIdRol")
    private Collection<MaestroEntity> maestrosByIdRol;

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolEntity rolEntity = (RolEntity) o;

        if (idRol != rolEntity.idRol) return false;
        if (rol != null ? !rol.equals(rolEntity.rol) : rolEntity.rol != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRol;
        result = 31 * result + (rol != null ? rol.hashCode() : 0);
        return result;
    }

    public Collection<MaestroEntity> getMaestrosByIdRol() {
        return maestrosByIdRol;
    }

    public void setMaestrosByIdRol(Collection<MaestroEntity> maestrosByIdRol) {
        this.maestrosByIdRol = maestrosByIdRol;
    }
}
