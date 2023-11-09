package sv.edu.cms.www.managedbeans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import sv.edu.cms.www.entities.*;
import sv.edu.cms.www.models.MaestroModel;
import sv.edu.cms.www.utils.JsfUtil;
import java.util.List;
import java.util.Random;

@ManagedBean
@RequestScoped
public class MaestroBean {
    MaestroModel maestroModel = new MaestroModel();
    private MaestroEntity maestro;
    private MaestroEntity maestroSeleccionado;
    private SalonEntity salon;
    private RolEntity rol = new RolEntity();
    private EstadoEntity estado;
    private int estadoSeleccionado;
    private Boolean rolSeleccinado = false;
    private int rolSeleccionado;

    public MaestroBean() {
        salon = new SalonEntity();
        maestro = new MaestroEntity();
    }

    public MaestroModel getMaestroModel() {
        return maestroModel;
    }
    public void setMaestroModel(MaestroModel maestroModel) {
        this.maestroModel = maestroModel;
    }

    public MaestroEntity getMaestro() {
        return maestro;
    }
    public void setMaestro(MaestroEntity maestro) {
        this.maestro = maestro;
    }

    public Boolean getRolSeleccinado() {
        return rolSeleccinado;
    }
    public void setRolSeleccinado(Boolean rolSeleccinado) {
        this.rolSeleccinado = rolSeleccinado;
    }

    public void setEstado(EstadoEntity estado) {
        this.estado = estado;
    }
    public EstadoEntity getEstado() {
        return estado;
    }

    public void setEstadoSeleccionado(int estadoSeleccionado) {
        this.estadoSeleccionado = estadoSeleccionado;
    }
    public int getEstadoSeleccionado() {
        return estadoSeleccionado;
    }

    public int getRolSeleccionado() {
        return rolSeleccionado;
    }
    public void setRolSeleccionado(int rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public MaestroEntity getMaestroSeleccionado() {
        return maestroSeleccionado;
    }
    public void setMaestroSeleccionado(MaestroEntity maestroSeleccionado) {
        this.maestroSeleccionado = maestroSeleccionado;
    }

    //ANDRESS
    public RolEntity getRol() {
        return rol;
    }
    public void setRol(RolEntity rol){
        this.rol = rol;
    }

    //LISTAR MAESTROS
    public List<MaestroEntity> getListaMaestro(){
        return maestroModel.maestrosLista();
    }
    public List<MaestroEntity> getListaOnlyMaestro(){
        return maestroModel.onlyMaestrosList();
    }

    //GUARDAR MAESTRO
    public void guardarMaestro(){

        if(maestro.getCodigoMaestro().isEmpty()){
            estado = maestroModel.obtenerEstado(3);
            maestro.setEstadoByIdEstado(estado);

            if(rolSeleccinado){
                rol = maestroModel.obtenerRol(1);
            }else{
                rol = maestroModel.obtenerRol(2);
            }
            maestro.setRolByIdRol(rol);

            maestro.setEstadoByIdEstado(maestroModel.obtenerEstado(1));
            maestro.setCodigoMaestro(this.obtenerCarnet());
            maestro.setContra(maestro.getCodigoMaestro());

            if(maestroModel.insertMaestro(maestro) != 1){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ocurrio un problema", "Intentalo más tarde."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario registrado exitosamente", "Los datos han sido registrados en el sistema."));
            }
        }else{
            estado = maestroModel.obtenerEstado(estadoSeleccionado);
            maestro.setEstadoByIdEstado(estado);
            if(rolSeleccionado == 1){
                rol = maestroModel.obtenerRol(1);
            }else{
                rol = maestroModel.obtenerRol(2);
            }

            maestro.setRolByIdRol(rol);
            maestro.setEstadoByIdEstado(maestroModel.obtenerEstado(estadoSeleccionado));

            if(maestroModel.modificarMaestro(maestro) != 1){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ocurrio un problema", "Intentalo más tarde."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario modificado exitosamente", "Los datos han sido modificados en el sistema."));
            }
        }
        PrimeFaces.current().ajax().update("form:messages");

        maestro = new MaestroEntity();
        rol = new RolEntity();
        rolSeleccinado = false;
        rolSeleccionado = 0;
        estadoSeleccionado = 0;

    }

    //ELIMINAR
    public void eliminarMaestro(){
        String codigo = JsfUtil.getRequest().getParameter("codigo");

        if (maestroModel.eliminarMaestro(Integer.parseInt(codigo))== 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ocurrio un problema", "Intentalo más tarde."));
        }else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Maestro eliminado exitosamente", "Los datos han sido modificados en el sistema."));
        }
        PrimeFaces.current().ajax().update("form:messages");

        maestro = new MaestroEntity();
        rol = new RolEntity();
        rolSeleccinado = false;
        rolSeleccionado = 0;
        estadoSeleccionado = 0;
    }

    //CAMBIAR CONTRASEÑA
    public void desactivarAlumno(){
        String codigo= JsfUtil.getRequest().getParameter("codigo");
        maestroSeleccionado = maestroModel.obtenerMaestro(Integer.parseInt(codigo));
        maestroSeleccionado.setEstadoByIdEstado(maestroModel.obtenerEstado(2));
        if (maestroModel.modificarMaestro(maestroSeleccionado) != 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ocurrio un problema", "Intentalo más tarde."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario desactivado", "Los datos han sido modificados en el sistema."));
        }
        PrimeFaces.current().ajax().update("form:messages");

        maestroSeleccionado = new MaestroEntity();
    }

    //OBTENER MAESTRO
    public void obtenerMaestro() {
        String codigo= JsfUtil.getRequest().getParameter("codigo");
        maestro = maestroModel.obtenerMaestro(Integer.parseInt(codigo));
        switch (maestro.getRolByIdRol().getIdRol()){
            case 1:
                rolSeleccionado = 1;
            break;
            case 2:
                rolSeleccionado = 2;
            break;
        }
        estadoSeleccionado = maestro.getEstadoByIdEstado().getIdEstado();
    }

    //LIMPIAR
    public void limpiar(){
        maestro = new MaestroEntity();
        rolSeleccinado = false;
    }

    //OPERACIONES EXTRA
    private String obtenerCarnet(){
        String carnet;

        switch (rol.getRol()){
            case "Director":
                carnet = "DIR";
            break;
            case "Maestro":
                carnet = "MTR";
            break;
            default:
                carnet = "MTR";
        }

        Random rand = new Random();
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int numeroAleatorio = rand.nextInt(10);
            resultado.append(numeroAleatorio);
        }
        String cadenaConcatenada = resultado.toString();

        return carnet+cadenaConcatenada;
    }
}
