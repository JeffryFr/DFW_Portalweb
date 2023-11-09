package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "clase", schema = "portalcms")
@NamedQueries({
        @NamedQuery(name="ClaseEntity.findAll", query="SELECT c FROM ClaseEntity c"),
        @NamedQuery(name="ClaseEntity.findByIdClase", query="SELECT c FROM ClaseEntity c WHERE c.idClase = :idclase"),
        @NamedQuery(name="ClaseEntity.findBySalon", query="SELECT c FROM ClaseEntity c WHERE c.salonByIdSalon = :idsalon"),
        @NamedQuery(name="ClaseEntity.compare", query="SELECT c FROM ClaseEntity c WHERE c.salonByIdSalon = :idsalon AND c.maestromateriaByIdMateriaMaestro.materiaByIdMateria = :materia")
})
public class ClaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idClase", nullable = false)
    private int idClase;
    @Basic
    @Column(name = "codigoClase", nullable = false, length = 10)
    private String codigoClase;
    @OneToMany(mappedBy = "claseByIdClase")
    private Collection<AsistenciaEntity> asistenciasByIdClase;
    @ManyToOne
    @JoinColumn(name = "idMateriaMaestro", referencedColumnName = "idMaestroMateria", nullable = false)
    private MaestromateriaEntity maestromateriaByIdMateriaMaestro;
    @ManyToOne
    @JoinColumn(name = "idSalon", referencedColumnName = "idSalon", nullable = false)
    private SalonEntity salonByIdSalon;
    @OneToMany(mappedBy = "claseByIdClase")
    private Collection<EvaluacionEntity> evaluacionsByIdClase;
    @OneToMany(mappedBy = "claseByIdClase")
    private Collection<HorarioEntity> horariosByIdClase;

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public String getCodigoClase() {
        return codigoClase;
    }

    public void setCodigoClase(String codigoClase) {
        this.codigoClase = codigoClase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClaseEntity clase = (ClaseEntity) o;

        if (idClase != clase.idClase) return false;
        if (codigoClase != null ? !codigoClase.equals(clase.codigoClase) : clase.codigoClase != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idClase;
        result = 31 * result + (codigoClase != null ? codigoClase.hashCode() : 0);
        return result;
    }

    public Collection<AsistenciaEntity> getAsistenciasByIdClase() {
        return asistenciasByIdClase;
    }

    public void setAsistenciasByIdClase(Collection<AsistenciaEntity> asistenciasByIdClase) {
        this.asistenciasByIdClase = asistenciasByIdClase;
    }

    public MaestromateriaEntity getMaestromateriaByIdMateriaMaestro() {
        return maestromateriaByIdMateriaMaestro;
    }

    public void setMaestromateriaByIdMateriaMaestro(MaestromateriaEntity maestromateriaByIdMateriaMaestro) {
        this.maestromateriaByIdMateriaMaestro = maestromateriaByIdMateriaMaestro;
    }

    public SalonEntity getSalonByIdSalon() {
        return salonByIdSalon;
    }

    public void setSalonByIdSalon(SalonEntity salonByIdSalon) {
        this.salonByIdSalon = salonByIdSalon;
    }

    public Collection<EvaluacionEntity> getEvaluacionsByIdClase() {
        return evaluacionsByIdClase;
    }

    public void setEvaluacionsByIdClase(Collection<EvaluacionEntity> evaluacionsByIdClase) {
        this.evaluacionsByIdClase = evaluacionsByIdClase;
    }

    public Collection<HorarioEntity> getHorariosByIdClase() {
        return horariosByIdClase;
    }

    public void setHorariosByIdClase(Collection<HorarioEntity> horariosByIdClase) {
        this.horariosByIdClase = horariosByIdClase;
    }
}
