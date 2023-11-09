package sv.edu.cms.www.models;

import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;
import sv.edu.cms.www.entities.*;
import sv.edu.cms.www.utils.JpaUtil;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class MaestroModel {
    //LISTAR DATOS
    public List<MaestroEntity> maestrosLista(){
        EntityManager em = JpaUtil.getEntityManager();
        try{

            Query consulta = em.createNamedQuery("MaestroEntity.getAll");
            List<MaestroEntity> listado = consulta.getResultList();
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

    public List<MaestroEntity> maestroListaValidada(int usuario){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("MaestroEntity.getAllExceptAdmin");
            consulta.setParameter("usuario", usuario);
            List<MaestroEntity> lista = consulta.getResultList();
            em.close();

            if(!lista.isEmpty()){
                return lista;
            }else{
                return null;
            }
        }catch (Exception e){
            em.close();
            return null;
        }
    }

    public List<MaestroEntity> onlyMaestrosList(){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("MaestroEntity.getAllMaestros");
            List<MaestroEntity> list = consulta.getResultList();
            em.close();

            if(!list.isEmpty()){
                return list;
            }else{
                return null;
            }
        }catch (Exception e){
            em.close();
            return null;
        }
    }

    //GUARDAR MAESTRO
    public int insertMaestro(MaestroEntity maestro) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.persist(maestro); //Guardando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    //ACTUALIZAR DATOS
    public int modificarMaestro(MaestroEntity maestro){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try{
            tran.begin();//Iniciando transacción
            em.merge(maestro); //Actualizando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    //ELIMINAR DATOS
    public int eliminarMaestro(int codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;
        try {
            //Recuperando el objeto a eliminar
            MaestroEntity est = em.find(MaestroEntity.class, codigo);
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
    public MaestroEntity obtenerMaestro(int codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a travez del metodo find
            MaestroEntity maestro = em.find(MaestroEntity.class, codigo);
            em.close();
            return maestro;
        } catch (Exception e) {
            em.close();
            return null;
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
    public RolEntity obtenerRol(int id){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            RolEntity rol = em.find(RolEntity.class, id);
            em.close();
            return rol;
        }catch (Exception e){
            em.close();
            return null;
        }
    }

    //METODOS PARA LOGIN
    public Boolean verificarMaestro(String codigo){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("MaestroEntity.verifyByCodigoMaestro");
            consulta.setParameter("codigoMaestro", codigo);
            String maestro = consulta.getSingleResult().toString();
            em.close();

            if(!maestro.isEmpty()){
                return true;
            }else{
                return false;
            }
        } catch (Exception e){
            em.close();
            return false;
        }
    }

    public MaestroEntity iniciarMaestro(String codigo, String contra){
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Query consulta = em.createNamedQuery("MaestroEntity.verifyByContra");
            consulta.setParameter("codigoMaestro", codigo);
            consulta.setParameter("contra", contra);
            List<MaestroEntity> lista = consulta.getResultList();

            if (!lista.isEmpty()) {
                MaestroEntity maestro = lista.get(0);
                return maestro;
            }else{
                return null;
            }
        } catch (Exception e){
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
