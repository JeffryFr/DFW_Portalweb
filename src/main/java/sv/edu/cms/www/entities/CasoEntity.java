package sv.edu.cms.www.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "caso", schema = "portalcms", catalog = "")
public class CasoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCaso", nullable = false)
    private int idCaso;
    @Basic
    @Column(name = "idEvaluacion", nullable = false)
    private int idEvaluacion;
    @Basic
    @Column(name = "idAlumno", nullable = false)
    private int idAlumno;
    @Basic
    @Column(name = "concepto", nullable = false, length = 100)
    private String concepto;
    @ManyToOne
    @JoinColumn(name = "idMaestro", referencedColumnName = "idMaestro", nullable = false)
    private MaestroEntity maestroByIdMaestro;
    @ManyToOne
    @JoinColumn(name = "idEstadoCaso", referencedColumnName = "idEstadoCaso", nullable = false)
    private EstadocasoEntity estadocasoByIdEstadoCaso;

    public int getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(int idCaso) {
        this.idCaso = idCaso;
    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CasoEntity that = (CasoEntity) o;

        if (idCaso != that.idCaso) return false;
        if (idEvaluacion != that.idEvaluacion) return false;
        if (idAlumno != that.idAlumno) return false;
        if (concepto != null ? !concepto.equals(that.concepto) : that.concepto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCaso;
        result = 31 * result + idEvaluacion;
        result = 31 * result + idAlumno;
        result = 31 * result + (concepto != null ? concepto.hashCode() : 0);
        return result;
    }

    public MaestroEntity getMaestroByIdMaestro() {
        return maestroByIdMaestro;
    }

    public void setMaestroByIdMaestro(MaestroEntity maestroByIdMaestro) {
        this.maestroByIdMaestro = maestroByIdMaestro;
    }

    public EstadocasoEntity getEstadocasoByIdEstadoCaso() {
        return estadocasoByIdEstadoCaso;
    }

    public void setEstadocasoByIdEstadoCaso(EstadocasoEntity estadocasoByIdEstadoCaso) {
        this.estadocasoByIdEstadoCaso = estadocasoByIdEstadoCaso;
    }
}
