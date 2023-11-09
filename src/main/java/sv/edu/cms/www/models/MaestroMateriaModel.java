package sv.edu.cms.www.models;

import sv.edu.cms.www.entities.*;
import sv.edu.cms.www.utils.JpaUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class MaestroMateriaModel {
    //LISTAR DATOS
    public List<MaestromateriaEntity> listarDatosMM(){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("MaestromateriaEntity.getAll");
            List<MaestromateriaEntity> datos = consulta.getResultList();
            em.close();

            if(!datos.isEmpty()){
                return datos;
            }else{
                return null;
            }
        }catch(Exception e){
            em.close();
            return null;
        }
    }

    public List<MaestromateriaEntity> maestroMateriaLista(MaestroEntity maestro){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("MaestromateriaEntity.getByMaestro");
            consulta.setParameter("maestro", maestro);
            List<MaestromateriaEntity> listado = consulta.getResultList();
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
    public List<MateriaEntity> materiaLista(){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("MateriaEntity.getAll");
            List<MateriaEntity> listado = consulta.getResultList();
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

    //GUARDAR MAESTRO
    public int insertMaestroMateria(MaestromateriaEntity maestromateria) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.persist(maestromateria); //Guardando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    //OBTENER DATOS
    public MateriaEntity obtenerMateria(int codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a travez del metodo find
            MateriaEntity materia = em.find(MateriaEntity.class, codigo);
            em.close();
            return materia;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public MaestromateriaEntity obtenerMaestroMateria(MaestroEntity maestro, String materia) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;
        try {
            Query consulta = em.createNamedQuery("MaestromateriaEntity.getByMaestroAndMateria");
            consulta.setParameter("maestro", maestro);
            consulta.setParameter("materia", materia);
            List<MaestromateriaEntity> listado = consulta.getResultList();
            em.close();
            return listado.get(0);
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public MaestromateriaEntity obtenerMaestroMateriaById(int codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;
        try {
            Query consulta = em.createNamedQuery("MaestromateriaEntity.getById");
            consulta.setParameter("idmm", codigo);
            List<MaestromateriaEntity> listado = consulta.getResultList();
            em.close();
            return listado.get(0);
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    //VERIFICAR
    public int verificarMaestroMateria(MaestroEntity maestro, MateriaEntity materia) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;
        try {
            Query consulta = em.createNamedQuery("MaestromateriaEntity.getByMaestroAndMateria");
            consulta.setParameter("maestro", maestro);
            consulta.setParameter("materia", materia);
            List<MaestromateriaEntity> listado = consulta.getResultList();
            em.close();
            filasBorradas = listado.size();
            return filasBorradas;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    //ELIMINAR DATOS
    public int eliminarMaestroMateria(int codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;
        try {
            //Recuperando el objeto a eliminar
            MaestromateriaEntity est = em.find(MaestromateriaEntity.class, codigo);
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
}
