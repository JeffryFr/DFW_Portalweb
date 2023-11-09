package sv.edu.cms.www.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "dia", schema = "portalcms", catalog = "")
public class DiaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idDia", nullable = false)
    private int idDia;
    @Basic
    @Column(name = "dia", nullable = false, length = 20)
    private String dia;
    @OneToMany(mappedBy = "diaByIdDia")
    private Collection<HorarioEntity> horariosByIdDia;

    public int getIdDia() {
        return idDia;
    }

    public void setIdDia(int idDia) {
        this.idDia = idDia;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiaEntity diaEntity = (DiaEntity) o;

        if (idDia != diaEntity.idDia) return false;
        if (dia != null ? !dia.equals(diaEntity.dia) : diaEntity.dia != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDia;
        result = 31 * result + (dia != null ? dia.hashCode() : 0);
        return result;
    }

    public Collection<HorarioEntity> getHorariosByIdDia() {
        return horariosByIdDia;
    }

    public void setHorariosByIdDia(Collection<HorarioEntity> horariosByIdDia) {
        this.horariosByIdDia = horariosByIdDia;
    }
}
