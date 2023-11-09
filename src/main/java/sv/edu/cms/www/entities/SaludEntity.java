package sv.edu.cms.www.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "salud", schema = "portalcms", catalog = "")
@NamedQueries({
        @NamedQuery(name="SaludEntity.getByAlumno", query="SELECT s FROM SaludEntity s WHERE s.alumnoByIdAlumno.idAlumno = :idalumno")
})
public class SaludEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idSalud", nullable = false)
    private int idSalud;
    @Basic
    @Column(name = "enfermedad", nullable = false, length = -1)
    private String enfermedad;
    @Basic
    @Column(name = "alergias", nullable = false, length = -1)
    private String alergias;
    @Basic
    @Column(name = "medicamentos", nullable = false, length = -1)
    private String medicamentos;
    @ManyToOne
    @JoinColumn(name = "idAlumno", referencedColumnName = "idAlumno", nullable = false)
    private AlumnoEntity alumnoByIdAlumno;

    public int getIdSalud() {
        return idSalud;
    }

    public void setIdSalud(int idSalud) {
        this.idSalud = idSalud;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaludEntity that = (SaludEntity) o;

        if (idSalud != that.idSalud) return false;
        if (enfermedad != null ? !enfermedad.equals(that.enfermedad) : that.enfermedad != null) return false;
        if (alergias != null ? !alergias.equals(that.alergias) : that.alergias != null) return false;
        if (medicamentos != null ? !medicamentos.equals(that.medicamentos) : that.medicamentos != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSalud;
        result = 31 * result + (enfermedad != null ? enfermedad.hashCode() : 0);
        result = 31 * result + (alergias != null ? alergias.hashCode() : 0);
        result = 31 * result + (medicamentos != null ? medicamentos.hashCode() : 0);
        return result;
    }

    public AlumnoEntity getAlumnoByIdAlumno() {
        return alumnoByIdAlumno;
    }

    public void setAlumnoByIdAlumno(AlumnoEntity alumnoByIdAlumno) {
        this.alumnoByIdAlumno = alumnoByIdAlumno;
    }
}
