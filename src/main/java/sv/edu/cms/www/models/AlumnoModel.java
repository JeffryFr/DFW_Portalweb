package sv.edu.cms.www.models;

import sv.edu.cms.www.entities.*;
import sv.edu.cms.www.utils.JpaUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class AlumnoModel {
    //OBTENER ALUMNOS
    public List<AlumnoEntity> getAlumno(){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("AlumnoEntity.getAll");
            List<AlumnoEntity> alumno = consulta.getResultList();
            em.close();

            if(!alumno.isEmpty()){
                return alumno;
            }else{
                return null;
            }
        } catch (Exception e){
            em.close();
            return null;
        }
    }

    public List<SalonEntity> getSalones(){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("SalonEntity.getAll");
            List<SalonEntity> salon = consulta.getResultList();
            em.close();

            if(!salon.isEmpty()){
                return salon;
            }else{
                return null;
            }
        } catch (Exception e){
            em.close();
            return null;
        }
    }

    //GUARDAR ALUMNO
    public int guardarAlumno(AlumnoEntity alumno){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try{
            tran.begin();
            em.persist(alumno);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int guardarSalud(SaludEntity salud){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try{
            tran.begin();
            em.persist(salud);
            tran.commit();
            return 1;
        }catch (Exception e){
            em.close();
            return 0;
        }
    }

    public int guardarSalonAlumno(SalonEntity salon, AlumnoEntity alumno){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        int anoActual = Calendar.getInstance().get(Calendar.YEAR);
        try{
            SalonalumnoEntity salonalumno = new SalonalumnoEntity();
            salonalumno.setAlumnoByIdAlumno(alumno);
            salonalumno.setSalonByIdSalon(salon);
            salonalumno.setYear(anoActual);

            tran.begin();
            em.persist(salonalumno);
            tran.commit();
            return 1;
        }catch (Exception e){
            em.close();
            return 0;
        }
    }

    //MODIFICAR ALUMNO
    public int editarAlumno(AlumnoEntity alumno) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.merge(alumno); //Actualizando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int editarSalud(SaludEntity salud) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.merge(salud); //Actualizando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int editarSalonAlumno(SalonalumnoEntity salon) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        int anoActual = Calendar.getInstance().get(Calendar.YEAR);
        salon.setYear(anoActual);
        try {
            tran.begin();//Iniciando transacción
            em.merge(salon); //Actualizando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    //ELIMINAR ALUMNO
    public int eliminarAlumno(int codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;
        try {
            //Recuperando el objeto a eliminar
            AlumnoEntity est = em.find(AlumnoEntity.class, codigo);
            if (est != null) {
                EntityTransaction tran = em.getTransaction();
                tran.begin();//Iniciando transacción
                em.remove(est);//Borrando la instancia
                tran.commit();//Confirmando la transacción
                filasBorradas = 1;
            }
            em.close();
            return filasBorradas;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int eliminarSalud(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;
        try {
            SaludEntity salud = this.obtenerSalud(id);
            //Recuperando el objeto a eliminar
            SaludEntity est = em.find(SaludEntity.class, salud.getIdSalud());
            if (est != null) {
                EntityTransaction tran = em.getTransaction();
                tran.begin();//Iniciando transacción
                em.remove(est);//Borrando la instancia
                tran.commit();//Confirmando la transacción
                filasBorradas = 1;
            }
            em.close();
            return filasBorradas;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    //OBTENER DATOS
    public AlumnoEntity obtenerAlumno(int id){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            AlumnoEntity alumno = em.find(AlumnoEntity.class, id);
            em.close();
            return alumno;
        }catch (Exception e){
            em.close();
            return null;
        }
    }

    public SaludEntity obtenerSalud(int alumno){
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createNamedQuery("SaludEntity.getByAlumno");
            consulta.setParameter("idalumno", alumno);
            List<SaludEntity> salud = consulta.getResultList();
            em.close();

            if (!salud.isEmpty()) {
                return salud.get(0);
            } else {
                return null;
            }
        }catch (Exception e){
            System.out.println(e);
            em.close();
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close(); // Asegúrate de que el EntityManager se cierre en cualquier caso
            }
        }
    }

    public SalonalumnoEntity obtenerSalon(int alumno){
        EntityManager em = JpaUtil.getEntityManager();
        int añoActual = Calendar.getInstance().get(Calendar.YEAR);
        try {
            Query consulta = em.createNamedQuery("SalonalumnoEntity.getByAlumno");
            consulta.setParameter("idalumno", alumno);
            consulta.setParameter("anio", añoActual);
            List<SalonalumnoEntity> salon = consulta.getResultList();
            em.close();

            if (!salon.isEmpty()) {
                return salon.get(salon.size() - 1);
            } else {
                return null;
            }
        }catch (Exception e){
            System.out.println(e);
            em.close();
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close(); // Asegúrate de que el EntityManager se cierre en cualquier caso
            }
        }
    }

    public EstadoEntity obtenerEstado(int id){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            EstadoEntity estado = em.find(EstadoEntity.class, id);
            em.close();
            return estado;
        }catch (Exception e){
            em.close();
            return null;
        }
    }

    public SalonEntity obtenerSalonById(int id){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            SalonEntity salon = em.find(SalonEntity.class, id);
            em.close();
            return salon;
        }catch (Exception e){
            em.close();
            return null;
        }
    }

    public SalonalumnoEntity obtenerSalonAlumno(int id){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            SalonalumnoEntity salonalumno = em.find(SalonalumnoEntity.class, id);
            em.close();
            return salonalumno;
        }catch (Exception e){
            em.close();
            return null;
        }
    }

    //LOGIN
    public Boolean verificarAlumno(String codigo){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("AlumnoEntity.verifyByCodigoAlumno");
            consulta.setParameter("codigoAlumno", codigo);
            String alumno = consulta.getSingleResult().toString();
            em.close();

            if(!alumno.isEmpty()){
                return true;
            }else{
                return false;
            }
        } catch (Exception e){
            em.close();
            return false;
        }
    }

    public AlumnoEntity iniciarAlumno(String codigo, String contra){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("AlumnoEntity.verifyByContra");
            consulta.setParameter("codigoAlumno", codigo);
            consulta.setParameter("contra", contra);
            List<AlumnoEntity> lista = consulta.getResultList();

            if (!lista.isEmpty()) {
                AlumnoEntity alumno = lista.get(0);
                return alumno;
            }else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e);
            em.close();
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close(); // Asegúrate de que el EntityManager se cierre en cualquier caso
            }
        }
    }
}
