package sv.edu.cms.www.managedbeans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import sv.edu.cms.www.entities.*;
import sv.edu.cms.www.models.MaestroModel;
import sv.edu.cms.www.models.MaestroMateriaModel;
import sv.edu.cms.www.utils.JsfUtil;
import java.util.List;
import java.util.Random;

@ManagedBean
@RequestScoped
public class MaestroMateriaBean {
    MaestroMateriaModel maestroMateriaModel = new MaestroMateriaModel();
    MaestroModel maestroModelo = new MaestroModel();
    private MaestromateriaEntity maestromateria;
    private MaestroEntity maestroGet;
    private int materiaSeleccionada;
    private List<MaestromateriaEntity> lista;
    private boolean materias = false;
    private int usuario;
    private int eliminarMM;

    public MaestroMateriaBean(){
        maestromateria = new MaestromateriaEntity();
    }

    public boolean getMaterias(){
        return this.materias;
    }
    public void setMaterias(boolean materias) {
        this.materias = materias;
    }

    public MaestroMateriaModel getMaestroMateriaModel() {
        return maestroMateriaModel;
    }
    public void setMaestroMateriaModel(MaestroMateriaModel maestroMateriaModel) {
        this.maestroMateriaModel = maestroMateriaModel;
    }

    public void setMaestromateria(MaestromateriaEntity maestromateria) {
        this.maestromateria = maestromateria;
    }
    public MaestromateriaEntity getMaestromateria() {
        return maestromateria;
    }

    public void setLista(List<MaestromateriaEntity> lista) {
        this.lista = lista;
    }
    public List<MaestromateriaEntity> getLista() {
        return lista;
    }

    public void setMaestroGet(MaestroEntity maestroGet) {
        this.maestroGet = maestroGet;
    }
    public MaestroEntity getMaestroGet() {
        return maestroGet;
    }

    public int getMateriaSeleccionada() {
        return materiaSeleccionada;
    }
    public void setMateriaSeleccionada(int materiaSeleccionada) {
        this.materiaSeleccionada = materiaSeleccionada;
    }

    public int getUsuario() {
        return usuario;
    }
    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getEliminarMM() {
        return eliminarMM;
    }
    public void setEliminarMM(int eliminarMM) {
        this.eliminarMM = eliminarMM;
    }

    //Listado de maestro
    public void agregarMaterias(){
        String codigo= JsfUtil.getRequest().getParameter("codigo");
        maestroGet = maestroModelo.obtenerMaestro(Integer.parseInt(codigo));
        lista = maestroMateriaModel.maestroMateriaLista(maestroGet);
        usuario = maestroGet.getIdMaestro();
        materias = true;
    }

    public void guardarMaestroMateria(){
        if(maestroMateriaModel.verificarMaestroMateria(maestroModelo.obtenerMaestro(usuario), maestroMateriaModel.obtenerMateria(materiaSeleccionada)) >= 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El maestro ya imparte esta materia", "No se pudo realisar la operación"));
        }else {
            maestromateria.setMaestroByIdMaestro(maestroModelo.obtenerMaestro(usuario));
            maestromateria.setMateriaByIdMateria(maestroMateriaModel.obtenerMateria(materiaSeleccionada));
            if (maestroMateriaModel.insertMaestroMateria(maestromateria) != 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un problema", "Intentalo más tarde."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El maestro ahora impartira una nueva materia", "Los datos han sido registrados en el sistema."));
            }
        }
        PrimeFaces.current().ajax().update("form:messages");

        maestroGet = maestroModelo.obtenerMaestro(maestroModelo.obtenerMaestro(usuario).getIdMaestro());
        lista = maestroMateriaModel.maestroMateriaLista(maestroGet);
        usuario = maestroModelo.obtenerMaestro(usuario).getIdMaestro();
        materias = true;
    }

    //RESOLVER
    public void eliminarMaestroMateria(){
        eliminarMM = Integer.parseInt(JsfUtil.getRequest().getParameter("eliminado"));
        if (maestroMateriaModel.eliminarMaestroMateria(eliminarMM)==0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ocurrio un problema", "Intentalo más tarde."));
        }else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El maestro ya no impartira esta materia", "Los datos han sido modificadis en el sistema."));
        }
        PrimeFaces.current().ajax().update("form:messages");
    }

    public void Regresar(){
        materias = false;
    }
}
