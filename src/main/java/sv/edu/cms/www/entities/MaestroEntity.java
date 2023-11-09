package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "maestro", schema = "portalcms")
@NamedQueries({
        @NamedQuery(name="MaestroEntity.getAll", query="SELECT m FROM MaestroEntity m"),
        @NamedQuery(name="MaestroEntity.getAllExceptAdmin", query="SELECT m FROM MaestroEntity m WHERE m.idMaestro <> :usuario"),
        @NamedQuery(name="MaestroEntity.getAllMaestros", query="SELECT m FROM MaestroEntity m WHERE m.rolByIdRol.rol = 'Maestro'"),
        //NamedQueries usados en login
        @NamedQuery(name="MaestroEntity.verifyByCodigoMaestro", query="SELECT m.codigoMaestro FROM MaestroEntity m WHERE m.codigoMaestro = :codigoMaestro"),
        @NamedQuery(name="MaestroEntity.verifyByContra", query="SELECT m FROM MaestroEntity m WHERE m.contra = :contra AND m.codigoMaestro = :codigoMaestro")
})
public class MaestroEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idMaestro", nullable = false)
    private int idMaestro;
    @Basic
    @Column(name = "codigoMaestro", nullable = false, length = 6)
    private String codigoMaestro;
    @Basic
    @Column(name = "nombres", nullable = false, length = 50)
    private String nombres;
    @Basic
    @Column(name = "apellidos", nullable = false, length = 50)
    private String apellidos;
    @Basic
    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;
    @Basic
    @Column(name = "telefono", nullable = false, length = 8)
    private String telefono;
    @Basic
    @Column(name = "correo", nullable = false, length = 50)
    private String correo;
    @Basic
    @Column(name = "contra", nullable = false, length = 100)
    private String contra;
    @OneToMany(mappedBy = "maestroByIdMaestro")
    private Collection<CasoEntity> casosByIdMaestro;
    @OneToMany(mappedBy = "maestroByIdMaestro")
    private Collection<DiarioEntity> diariosByIdMaestro;
    @ManyToOne
    @JoinColumn(name = "idRol", referencedColumnName = "idRol", nullable = false)
    private RolEntity rolByIdRol;
    @ManyToOne
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado", nullable = false)
    private EstadoEntity estadoByIdEstado;
    @OneToMany(mappedBy = "maestroByIdMaestro")
    private Collection<MaestromateriaEntity> maestromateriasByIdMaestro;
    @OneToMany(mappedBy = "maestroByOrientador")
    private Collection<SalonEntity> salonsByIdMaestro;

    public int getIdMaestro() {
        return idMaestro;
    }

    public void setIdMaestro(int idMaestro) {
        this.idMaestro = idMaestro;
    }

    public String getCodigoMaestro() {
        return codigoMaestro;
    }

    public void setCodigoMaestro(String codigoMaestro) {
        this.codigoMaestro = codigoMaestro;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaestroEntity maestro = (MaestroEntity) o;

        if (idMaestro != maestro.idMaestro) return false;
        if (codigoMaestro != null ? !codigoMaestro.equals(maestro.codigoMaestro) : maestro.codigoMaestro != null)
            return false;
        if (nombres != null ? !nombres.equals(maestro.nombres) : maestro.nombres != null) return false;
        if (apellidos != null ? !apellidos.equals(maestro.apellidos) : maestro.apellidos != null) return false;
        if (direccion != null ? !direccion.equals(maestro.direccion) : maestro.direccion != null) return false;
        if (telefono != null ? !telefono.equals(maestro.telefono) : maestro.telefono != null) return false;
        if (correo != null ? !correo.equals(maestro.correo) : maestro.correo != null) return false;
        if (contra != null ? !contra.equals(maestro.contra) : maestro.contra != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMaestro;
        result = 31 * result + (codigoMaestro != null ? codigoMaestro.hashCode() : 0);
        result = 31 * result + (nombres != null ? nombres.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (correo != null ? correo.hashCode() : 0);
        result = 31 * result + (contra != null ? contra.hashCode() : 0);
        return result;
    }

    public Collection<CasoEntity> getCasosByIdMaestro() {
        return casosByIdMaestro;
    }

    public void setCasosByIdMaestro(Collection<CasoEntity> casosByIdMaestro) {
        this.casosByIdMaestro = casosByIdMaestro;
    }

    public Collection<DiarioEntity> getDiariosByIdMaestro() {
        return diariosByIdMaestro;
    }

    public void setDiariosByIdMaestro(Collection<DiarioEntity> diariosByIdMaestro) {
        this.diariosByIdMaestro = diariosByIdMaestro;
    }

    public RolEntity getRolByIdRol() {
        return rolByIdRol;
    }

    public void setRolByIdRol(RolEntity rolByIdRol) {
        this.rolByIdRol = rolByIdRol;
    }

    public EstadoEntity getEstadoByIdEstado() {
        return estadoByIdEstado;
    }

    public void setEstadoByIdEstado(EstadoEntity estadoByIdEstado) {
        this.estadoByIdEstado = estadoByIdEstado;
    }

    public Collection<MaestromateriaEntity> getMaestromateriasByIdMaestro() {
        return maestromateriasByIdMaestro;
    }

    public void setMaestromateriasByIdMaestro(Collection<MaestromateriaEntity> maestromateriasByIdMaestro) {
        this.maestromateriasByIdMaestro = maestromateriasByIdMaestro;
    }

    public Collection<SalonEntity> getSalonsByIdMaestro() {
        return salonsByIdMaestro;
    }

    public void setSalonsByIdMaestro(Collection<SalonEntity> salonsByIdMaestro) {
        this.salonsByIdMaestro = salonsByIdMaestro;
    }
}
