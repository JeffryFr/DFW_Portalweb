package sv.edu.cms.www.models;

import sv.edu.cms.www.entities.*;
import sv.edu.cms.www.utils.JpaUtil;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import sv.edu.cms.www.models.SalonModel;

public class ClaseModel {
    //LISTAR CLASES
    public List<ClaseEntity> obtenerClases(int salon){
        SalonModel salonModel = new SalonModel();
        EntityManager em = JpaUtil.getEntityManager();
        SalonEntity listaSalon = salonModel.getSalonByCodigo(salon);
        try{
            Query consulta;
            if(salon == 0){
                consulta = em.createNamedQuery("ClaseEntity.findAll");
            }else{
                consulta = em.createNamedQuery("ClaseEntity.findBySalon");
                consulta.setParameter("idsalon", listaSalon);
            }
            List<ClaseEntity> clase = consulta.getResultList();
            em.close();

            if(!clase.isEmpty()){
                return clase;
            }else{
                return null;
            }
        } catch (Exception e){
            em.close();
            return null;
        }
    }

    public List<ClaseEntity> listarAllClases(){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("ClaseEntity.findAll");
            List<ClaseEntity> clases = consulta.getResultList();
            em.close();

            if(!clases.isEmpty()){
                return clases;
            }else{
                return null;
            }
        }catch ( Exception e){
            em.close();
            return null;
        }
    }

    public ClaseEntity obtenerByIdClase(int codigo){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("ClaseEntity.findByIdClase");
            consulta.setParameter("idclase", codigo);
            List<ClaseEntity> clase = consulta.getResultList();
            em.close();

            if(!clase.isEmpty()){
                return clase.get(0);
            }else{
                return null;
            }
        }catch(Exception e){
            em.close();
            return null;
        }
    }

    public int verificarClase(SalonEntity salon, MaestromateriaEntity maestroMateria){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            MateriaEntity materia = maestroMateria.getMateriaByIdMateria();

            Query consulta;
            consulta = em.createNamedQuery("ClaseEntity.compare");
            consulta.setParameter("codigosalon", salon);
            consulta.setParameter("materia", materia);
            List<ClaseEntity> clase = consulta.getResultList();
            em.close();

            if(!clase.isEmpty()){
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e){
            em.close();
            return 0;
        }
    }

    //GUARDAR DATOS
    public int guardarClase(ClaseEntity clase){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try{
            tran.begin();
            em.persist(clase);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int guardarEvaluacion(EvaluacionEntity evaluacion){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try{
            tran.begin();
            em.persist(evaluacion);
            tran.commit();
            em.close();
            return 1;
        }catch (Exception e){
            em.close();
            return 0;
        }
    }

    public int editarClase(ClaseEntity clase){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try{
            tran.begin();
            em.merge(clase);
            tran.commit();
            em.close();
            return 1;
        }catch(Exception e){
            em.close();
            return 0;
        }
    }

    //ELIMINAR CLASE
    public int eliminarClase(int codigo){
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;
        try{
            ClaseEntity clase = em.find(ClaseEntity.class, codigo);
            if (clase != null) {
                EntityTransaction tran = em.getTransaction();
                tran.begin();//Iniciando transacción
                em.remove(clase);//Borrando la instancia
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

    //LISTAR PERIODOS
    public List<PeriodoEntity> listarPeriodos(int anio){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("PeriodoEntity.searchPerYear");
            consulta.setParameter("anio", anio);
            List<PeriodoEntity> listado = consulta.getResultList();
            em.close();

            if(!listado.isEmpty()){
                return listado;
            }else{
                return null;
            }
        }catch (Exception e){
            em.close();
            return null;
        }
    }

    //OBTENER TIPOS DE EVALUACION
    public TipoEntity getTipo(int codigo){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            TipoEntity datos = em.find(TipoEntity.class, codigo);
            em.close();

            if(!(datos == null)){
                return datos;
            }else{
                return null;
            }
        }catch (Exception e){
            em.close();
            return null;
        }
    }
}
