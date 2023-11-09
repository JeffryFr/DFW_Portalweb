package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "salon", schema = "portalcms")
@NamedQueries({
        @NamedQuery(name="SalonEntity.getAll", query="SELECT s FROM SalonEntity s"),
        @NamedQuery(name="SalonEntity.getPerYear", query="SELECT s FROM SalonEntity s WHERE s.codigoSalon LIKE :anio"),
        @NamedQuery(name="SalonEntity.findByCodigo", query="SELECT s FROM SalonEntity s WHERE s.idSalon = :codigosalon"),
        @NamedQuery(name="SalonEntity.findByOrientador", query="SELECT s FROM SalonEntity s WHERE s.maestroByOrientador.idMaestro = :codigomaestro")
})
public class SalonEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idSalon", nullable = false)
    private int idSalon;
    @Basic
    @Column(name = "codigoSalon", nullable = false, length = 4)
    private String codigoSalon;
    @Basic
    @Column(name = "grado", nullable = false)
    private int grado;
    @Basic
    @Column(name = "seccion", nullable = false, length = 1)
    private String seccion;
    @OneToMany(mappedBy = "salonByIdSalon")
    private Collection<ClaseEntity> clasesByIdSalon;
    @ManyToOne
    @JoinColumn(name = "orientador", referencedColumnName = "idMaestro", nullable = false)
    private MaestroEntity maestroByOrientador;
    @OneToMany(mappedBy = "salonByIdSalon")
    private Collection<SalonalumnoEntity> salonalumnosByIdSalon;

    public int getIdSalon() {
        return idSalon;
    }

    public void setIdSalon(int idSalon) {
        this.idSalon = idSalon;
    }

    public String getCodigoSalon() {
        return codigoSalon;
    }

    public void setCodigoSalon(String codigoSalon) {
        this.codigoSalon = codigoSalon;
    }

    public int getGrado() {
        return grado;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalonEntity that = (SalonEntity) o;

        if (idSalon != that.idSalon) return false;
        if (grado != that.grado) return false;
        if (codigoSalon != null ? !codigoSalon.equals(that.codigoSalon) : that.codigoSalon != null) return false;
        if (seccion != null ? !seccion.equals(that.seccion) : that.seccion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSalon;
        result = 31 * result + (codigoSalon != null ? codigoSalon.hashCode() : 0);
        result = 31 * result + grado;
        result = 31 * result + (seccion != null ? seccion.hashCode() : 0);
        return result;
    }

    public Collection<ClaseEntity> getClasesByIdSalon() {
        return clasesByIdSalon;
    }

    public void setClasesByIdSalon(Collection<ClaseEntity> clasesByIdSalon) {
        this.clasesByIdSalon = clasesByIdSalon;
    }

    public MaestroEntity getMaestroByOrientador() {
        return maestroByOrientador;
    }

    public void setMaestroByOrientador(MaestroEntity maestroByOrientador) {
        this.maestroByOrientador = maestroByOrientador;
    }

    public Collection<SalonalumnoEntity> getSalonalumnosByIdSalon() {
        return salonalumnosByIdSalon;
    }

    public void setSalonalumnosByIdSalon(Collection<SalonalumnoEntity> salonalumnosByIdSalon) {
        this.salonalumnosByIdSalon = salonalumnosByIdSalon;
    }
}
