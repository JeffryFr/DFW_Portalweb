package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "evaluacion", schema = "portalcms", catalog = "")
public class EvaluacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idEvaluacion", nullable = false)
    private int idEvaluacion;
    @Basic
    @Column(name = "concepto", nullable = false, length = 200)
    private String concepto;
    @Basic
    @Column(name = "fechaEntrega", nullable = false)
    private Date fechaEntrega;
    @Basic
    @Column(name = "ingresoNota", nullable = false)
    private Date ingresoNota;
    @ManyToOne
    @JoinColumn(name = "idClase", referencedColumnName = "idClase", nullable = false)
    private ClaseEntity claseByIdClase;
    @ManyToOne
    @JoinColumn(name = "idPeriodo", referencedColumnName = "idPeriodo", nullable = false)
    private PeriodoEntity periodoByIdPeriodo;
    @ManyToOne
    @JoinColumn(name = "idTipo", referencedColumnName = "idTipo", nullable = false)
    private TipoEntity tipoByIdTipo;
    @OneToMany(mappedBy = "evaluacionByIdEvaluacion")
    private Collection<NotaEntity> notasByIdEvaluacion;

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getIngresoNota() {
        return ingresoNota;
    }

    public void setIngresoNota(Date ingresoNota) {
        this.ingresoNota = ingresoNota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EvaluacionEntity that = (EvaluacionEntity) o;

        if (idEvaluacion != that.idEvaluacion) return false;
        if (concepto != null ? !concepto.equals(that.concepto) : that.concepto != null) return false;
        if (fechaEntrega != null ? !fechaEntrega.equals(that.fechaEntrega) : that.fechaEntrega != null) return false;
        if (ingresoNota != null ? !ingresoNota.equals(that.ingresoNota) : that.ingresoNota != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEvaluacion;
        result = 31 * result + (concepto != null ? concepto.hashCode() : 0);
        result = 31 * result + (fechaEntrega != null ? fechaEntrega.hashCode() : 0);
        result = 31 * result + (ingresoNota != null ? ingresoNota.hashCode() : 0);
        return result;
    }

    public ClaseEntity getClaseByIdClase() {
        return claseByIdClase;
    }

    public void setClaseByIdClase(ClaseEntity claseByIdClase) {
        this.claseByIdClase = claseByIdClase;
    }

    public PeriodoEntity getPeriodoByIdPeriodo() {
        return periodoByIdPeriodo;
    }

    public void setPeriodoByIdPeriodo(PeriodoEntity periodoByIdPeriodo) {
        this.periodoByIdPeriodo = periodoByIdPeriodo;
    }

    public TipoEntity getTipoByIdTipo() {
        return tipoByIdTipo;
    }

    public void setTipoByIdTipo(TipoEntity tipoByIdTipo) {
        this.tipoByIdTipo = tipoByIdTipo;
    }

    public Collection<NotaEntity> getNotasByIdEvaluacion() {
        return notasByIdEvaluacion;
    }

    public void setNotasByIdEvaluacion(Collection<NotaEntity> notasByIdEvaluacion) {
        this.notasByIdEvaluacion = notasByIdEvaluacion;
    }
}
