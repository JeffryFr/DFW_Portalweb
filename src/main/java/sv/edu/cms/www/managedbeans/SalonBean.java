package sv.edu.cms.www.managedbeans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import sv.edu.cms.www.entities.AlumnoEntity;
import sv.edu.cms.www.entities.SalonEntity;
import sv.edu.cms.www.entities.SalonalumnoEntity;
import sv.edu.cms.www.models.SalonModel;
import sv.edu.cms.www.models.AlumnoModel;
import sv.edu.cms.www.models.MaestroModel;
import sv.edu.cms.www.utils.JsfUtil;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@ManagedBean
@RequestScoped
public class SalonBean {
    SalonModel salonModel = new SalonModel();
    private SalonEntity salon;
    private String maestro;
    private String detalles;
    private List<SalonalumnoEntity> alumnos;

    public SalonBean(){
        salon = new SalonEntity();
    }

    public SalonModel getSalonModel() {
        return salonModel;
    }
    public void setSalonModel(SalonModel salonModel) {
        this.salonModel = salonModel;
    }

    public SalonEntity getSalon() {
        return salon;
    }
    public void setSalon(SalonEntity salon) {
        this.salon = salon;
    }

    public String getMaestro() {
        return maestro;
    }
    public void setMaestro(String maestro) {
        this.maestro = maestro;
    }

    public String getDetalles() {
        return detalles;
    }
    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public void setAlumnos(List<SalonalumnoEntity> alumnos) {
        this.alumnos = alumnos;
    }
    public List<SalonalumnoEntity> getAlumnos() {
        return alumnos;
    }

    //LISTAR SALONES
    public List<SalonEntity> getListaSalones(){
        return salonModel.getSalon();
    }
    public List<SalonEntity> getListaSalonesPerYear(){
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int ano = year % 100;
        return salonModel.getSalonPerYear(ano);
    }

    //GUARDAR SALON
    public void guardarSalon(){
        if(salon.getCodigoSalon().isEmpty()){
            if(salonModel.verifyOrientador(Integer.parseInt(maestro)) != 1){
                MaestroModel maestroModel = new MaestroModel();

                LocalDate currentDate = LocalDate.now();
                int year = currentDate.getYear();
                int ano = year % 100;
                String codigo = salon.getGrado()+salon.getSeccion()+ano;

                salon.setMaestroByOrientador(maestroModel.obtenerMaestro(Integer.parseInt(maestro)));
                salon.setCodigoSalon(codigo);
                if(salonModel.guardarSalon(salon) != 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ocurrio un problema", "Intentalo más tarde."));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Salón registrado exitosamente", "Los datos han sido registrados en el sistema."));
                }
                PrimeFaces.current().ajax().update("form:messages");
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"El maestro solo puede orientar un salón", "Intentalo con otro maestro."));
            }
        }else{
            if(salonModel.verifyOrientador(Integer.parseInt(maestro)) != 1){
                MaestroModel maestroModel = new MaestroModel();

                salon.setMaestroByOrientador(maestroModel.obtenerMaestro(Integer.parseInt(maestro)));
                if(salonModel.editarSalon(salon) != 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ocurrio un problema", "Intentalo más tarde."));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Salón modificado exitosamente", "Los datos han sido modificados en el sistema."));
                }
                PrimeFaces.current().ajax().update("form:messages");
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"El maestro solo puede orientar un salón", "Intentalo con otro maestro."));
            }
        }
        salon = new SalonEntity();
    }

    //ELIMINAR DATOS
    public void eliminarSalon(){
        String codigo = JsfUtil.getRequest().getParameter("codigo");

        if(salonModel.eliminarSalon(Integer.parseInt(codigo)) < 1){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ocurrio un problema", "Intentalo más tarde."));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Salón eliminado exitosamente", "Los datos han sido registrados en el sistema."));
        }
        PrimeFaces.current().ajax().update("form:messages");
    }

    //OBTENER SALON
    public void obtenerSalon(){
        String codigo = JsfUtil.getRequest().getParameter("codigo");
        salon = salonModel.getSalonByCodigo(Integer.parseInt(codigo));
    }

    //OPERACIONES EXTRAS
    public int obtenerAnio(String codigoSalon){
        if (codigoSalon != null && codigoSalon.length() >= 2) {
            String anio = codigoSalon.substring(codigoSalon.length() - 2);
            return Integer.parseInt(anio)+2000;
        } else {
            // Manejar el caso en el que la cadena sea nula o no tenga al menos dos caracteres
            // Puedes lanzar una excepción, devolver un valor predeterminado, o hacer algo más según tus necesidades.
            return 0; // Valor predeterminado en caso de error
        }
    }

    public void Regresar(){
        detalles = "";
    }

    public void verAlumnos(){
        String codigo= JsfUtil.getRequest().getParameter("codigo");
        alumnos = salonModel.getAlumnosBySalon(Integer.parseInt(codigo));
        detalles = "mostrar";
    }

    public boolean compararAnios(int anio){
        Year year = Year.now();
        int anoActual = year.getValue();

        if(anio == anoActual){
            return true;
        }else{
            return false;
        }
    }

}
