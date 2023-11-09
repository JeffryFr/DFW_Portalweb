package sv.edu.cms.www.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "salonalumno", schema = "portalcms", catalog = "")
@NamedQueries({
        @NamedQuery(name="SalonalumnoEntity.getByAlumno", query="SELECT sa FROM SalonalumnoEntity sa WHERE sa.alumnoByIdAlumno.idAlumno = :idalumno AND sa.year = :anio"),
        @NamedQuery(name="SalonalumnoEntity.getBySalon", query="SELECT sa FROM SalonalumnoEntity sa WHERE sa.salonByIdSalon.idSalon = :idsalon")
})
public class SalonalumnoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idSalonAlumno", nullable = false)
    private int idSalonAlumno;
    @Basic
    @Column(name = "year", nullable = false)
    private int year;
    @ManyToOne
    @JoinColumn(name = "idAlumno", referencedColumnName = "idAlumno", nullable = false)
    private AlumnoEntity alumnoByIdAlumno;
    @ManyToOne
    @JoinColumn(name = "idSalon", referencedColumnName = "idSalon", nullable = false)
    private SalonEntity salonByIdSalon;

    public int getIdSalonAlumno() {
        return idSalonAlumno;
    }

    public void setIdSalonAlumno(int idSalonAlumno) {
        this.idSalonAlumno = idSalonAlumno;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalonalumnoEntity that = (SalonalumnoEntity) o;

        if (idSalonAlumno != that.idSalonAlumno) return false;
        if (year != that.year) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSalonAlumno;
        result = 31 * result + year;
        return result;
    }

    public AlumnoEntity getAlumnoByIdAlumno() {
        return alumnoByIdAlumno;
    }

    public void setAlumnoByIdAlumno(AlumnoEntity alumnoByIdAlumno) {
        this.alumnoByIdAlumno = alumnoByIdAlumno;
    }

    public SalonEntity getSalonByIdSalon() {
        return salonByIdSalon;
    }

    public void setSalonByIdSalon(SalonEntity salonByIdSalon) {
        this.salonByIdSalon = salonByIdSalon;
    }
}
