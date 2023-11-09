package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "alumno", schema = "portalcms")
@NamedQueries({
        @NamedQuery(name="AlumnoEntity.getAll", query="SELECT a FROM AlumnoEntity a"),
        //NamedQueries usado en login
        @NamedQuery(name="AlumnoEntity.verifyByCodigoAlumno", query="SELECT a.codigoAlumno FROM AlumnoEntity a WHERE a.codigoAlumno = :codigoAlumno"),
        @NamedQuery(name="AlumnoEntity.verifyByContra", query="SELECT a FROM AlumnoEntity a WHERE a.contra = :contra AND a.codigoAlumno = :codigoAlumno")
})

public class AlumnoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idAlumno", nullable = false)
    private int idAlumno;
    @Basic
    @Column(name = "codigoAlumno", nullable = false, length = 6)
    private String codigoAlumno;
    @Basic
    @Column(name = "nombres", nullable = false, length = 50)
    private String nombres;
    @Basic
    @Column(name = "apellidos", nullable = false, length = 50)
    private String apellidos;
    @Basic
    @Column(name = "fechaNacimiento", nullable = false)
    private Date fechaNacimiento;
    @Basic
    @Column(name = "genero", nullable = false, length = 10)
    private String genero;
    @Basic
    @Column(name = "grupoSanguineo", nullable = false, length = 5)
    private String grupoSanguineo;
    @Basic
    @Column(name = "responsable", nullable = false, length = 100)
    private String responsable;
    @Basic
    @Column(name = "telefono", nullable = false, length = 8)
    private String telefono;
    @Basic
    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;
    @Basic
    @Column(name = "contra", nullable = false, length = 100)
    private String contra;
    @ManyToOne
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado", nullable = false)
    private EstadoEntity estadoByIdEstado;
    @OneToMany(mappedBy = "alumnoByIdAlumno")
    private Collection<AsistenciaEntity> asistenciasByIdAlumno;
    @OneToMany(mappedBy = "alumnoByIdAlumno")
    private Collection<DiarioEntity> diariosByIdAlumno;
    @OneToMany(mappedBy = "alumnoByIdAlumno")
    private Collection<NotaEntity> notasByIdAlumno;
    @OneToMany(mappedBy = "alumnoByIdAlumno")
    private Collection<PermisoEntity> permisosByIdAlumno;
    @OneToMany(mappedBy = "alumnoByIdAlumno")
    private Collection<SalonalumnoEntity> salonalumnosByIdAlumno;
    @OneToMany(mappedBy = "alumnoByIdAlumno")
    private Collection<SaludEntity> saludsByIdAlumno;

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getCodigoAlumno() {
        return codigoAlumno;
    }

    public void setCodigoAlumno(String codigoAlumno) {
        this.codigoAlumno = codigoAlumno;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

        AlumnoEntity that = (AlumnoEntity) o;

        if (idAlumno != that.idAlumno) return false;
        if (codigoAlumno != null ? !codigoAlumno.equals(that.codigoAlumno) : that.codigoAlumno != null) return false;
        if (nombres != null ? !nombres.equals(that.nombres) : that.nombres != null) return false;
        if (apellidos != null ? !apellidos.equals(that.apellidos) : that.apellidos != null) return false;
        if (fechaNacimiento != null ? !fechaNacimiento.equals(that.fechaNacimiento) : that.fechaNacimiento != null)
            return false;
        if (genero != null ? !genero.equals(that.genero) : that.genero != null) return false;
        if (grupoSanguineo != null ? !grupoSanguineo.equals(that.grupoSanguineo) : that.grupoSanguineo != null)
            return false;
        if (responsable != null ? !responsable.equals(that.responsable) : that.responsable != null) return false;
        if (telefono != null ? !telefono.equals(that.telefono) : that.telefono != null) return false;
        if (direccion != null ? !direccion.equals(that.direccion) : that.direccion != null) return false;
        if (contra != null ? !contra.equals(that.contra) : that.contra != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAlumno;
        result = 31 * result + (codigoAlumno != null ? codigoAlumno.hashCode() : 0);
        result = 31 * result + (nombres != null ? nombres.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (fechaNacimiento != null ? fechaNacimiento.hashCode() : 0);
        result = 31 * result + (genero != null ? genero.hashCode() : 0);
        result = 31 * result + (grupoSanguineo != null ? grupoSanguineo.hashCode() : 0);
        result = 31 * result + (responsable != null ? responsable.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (contra != null ? contra.hashCode() : 0);
        return result;
    }

    public EstadoEntity getEstadoByIdEstado() {
        return estadoByIdEstado;
    }

    public void setEstadoByIdEstado(EstadoEntity estadoByIdEstado) {
        this.estadoByIdEstado = estadoByIdEstado;
    }

    public Collection<AsistenciaEntity> getAsistenciasByIdAlumno() {
        return asistenciasByIdAlumno;
    }

    public void setAsistenciasByIdAlumno(Collection<AsistenciaEntity> asistenciasByIdAlumno) {
        this.asistenciasByIdAlumno = asistenciasByIdAlumno;
    }

    public Collection<DiarioEntity> getDiariosByIdAlumno() {
        return diariosByIdAlumno;
    }

    public void setDiariosByIdAlumno(Collection<DiarioEntity> diariosByIdAlumno) {
        this.diariosByIdAlumno = diariosByIdAlumno;
    }

    public Collection<NotaEntity> getNotasByIdAlumno() {
        return notasByIdAlumno;
    }

    public void setNotasByIdAlumno(Collection<NotaEntity> notasByIdAlumno) {
        this.notasByIdAlumno = notasByIdAlumno;
    }

    public Collection<PermisoEntity> getPermisosByIdAlumno() {
        return permisosByIdAlumno;
    }

    public void setPermisosByIdAlumno(Collection<PermisoEntity> permisosByIdAlumno) {
        this.permisosByIdAlumno = permisosByIdAlumno;
    }

    public Collection<SalonalumnoEntity> getSalonalumnosByIdAlumno() {
        return salonalumnosByIdAlumno;
    }

    public void setSalonalumnosByIdAlumno(Collection<SalonalumnoEntity> salonalumnosByIdAlumno) {
        this.salonalumnosByIdAlumno = salonalumnosByIdAlumno;
    }

    public Collection<SaludEntity> getSaludsByIdAlumno() {
        return saludsByIdAlumno;
    }

    public void setSaludsByIdAlumno(Collection<SaludEntity> saludsByIdAlumno) {
        this.saludsByIdAlumno = saludsByIdAlumno;
    }
}
