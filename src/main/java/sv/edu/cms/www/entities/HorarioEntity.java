package sv.edu.cms.www.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "horario", schema = "portalcms", catalog = "")
public class HorarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idHorario", nullable = false)
    private int idHorario;
    @Basic
    @Column(name = "horaInicio", nullable = false)
    private String horaInicio;
    @Basic
    @Column(name = "horaFin", nullable = false)
    private String horaFin;
    @ManyToOne
    @JoinColumn(name = "idClase", referencedColumnName = "idClase", nullable = false)
    private ClaseEntity claseByIdClase;
    @ManyToOne
    @JoinColumn(name = "idDia", referencedColumnName = "idDia", nullable = false)
    private DiaEntity diaByIdDia;

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HorarioEntity that = (HorarioEntity) o;

        if (idHorario != that.idHorario) return false;
        if (horaInicio != null ? !horaInicio.equals(that.horaInicio) : that.horaInicio != null) return false;
        if (horaFin != null ? !horaFin.equals(that.horaFin) : that.horaFin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idHorario;
        result = 31 * result + (horaInicio != null ? horaInicio.hashCode() : 0);
        result = 31 * result + (horaFin != null ? horaFin.hashCode() : 0);
        return result;
    }

    public ClaseEntity getClaseByIdClase() {
        return claseByIdClase;
    }

    public void setClaseByIdClase(ClaseEntity claseByIdClase) {
        this.claseByIdClase = claseByIdClase;
    }

    public DiaEntity getDiaByIdDia() {
        return diaByIdDia;
    }

    public void setDiaByIdDia(DiaEntity diaByIdDia) {
        this.diaByIdDia = diaByIdDia;
    }
}
