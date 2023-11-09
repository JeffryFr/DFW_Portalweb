package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "periodo", schema = "portalcms")
@NamedQueries({
        @NamedQuery(name="PeriodoEntity.searchPerYear", query="SELECT p FROM PeriodoEntity p WHERE FUNCTION('YEAR', p.fechaInicio) = :anio AND FUNCTION('YEAR', p.fechaFin) = :anio")
})
public class PeriodoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPeriodo", nullable = false)
    private int idPeriodo;
    @Basic
    @Column(name = "periodo", nullable = false, length = 20)
    private String periodo;
    @Basic
    @Column(name = "fechaInicio", nullable = false)
    private Date fechaInicio;
    @Basic
    @Column(name = "fechaFin", nullable = false)
    private Date fechaFin;
    @OneToMany(mappedBy = "periodoByIdPeriodo")
    private Collection<EvaluacionEntity> evaluacionsByIdPeriodo;

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PeriodoEntity that = (PeriodoEntity) o;

        if (idPeriodo != that.idPeriodo) return false;
        if (periodo != null ? !periodo.equals(that.periodo) : that.periodo != null) return false;
        if (fechaInicio != null ? !fechaInicio.equals(that.fechaInicio) : that.fechaInicio != null) return false;
        if (fechaFin != null ? !fechaFin.equals(that.fechaFin) : that.fechaFin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPeriodo;
        result = 31 * result + (periodo != null ? periodo.hashCode() : 0);
        result = 31 * result + (fechaInicio != null ? fechaInicio.hashCode() : 0);
        result = 31 * result + (fechaFin != null ? fechaFin.hashCode() : 0);
        return result;
    }

    public Collection<EvaluacionEntity> getEvaluacionsByIdPeriodo() {
        return evaluacionsByIdPeriodo;
    }

    public void setEvaluacionsByIdPeriodo(Collection<EvaluacionEntity> evaluacionsByIdPeriodo) {
        this.evaluacionsByIdPeriodo = evaluacionsByIdPeriodo;
    }
}
