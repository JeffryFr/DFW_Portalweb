package sv.edu.cms.www.managedbeans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import sv.edu.cms.www.entities.*;
import sv.edu.cms.www.models.AlumnoModel;
import sv.edu.cms.www.utils.JsfUtil;

import java.security.SecureRandom;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@ManagedBean
@RequestScoped
public class AlumnoBean {
    AlumnoModel alumnoModel = new AlumnoModel();
    private AlumnoEntity alumno;
    private AlumnoEntity alumnoSeleccionado;
    private SaludEntity salud;
    private SaludEntity saludSeleccionada;
    private SalonEntity salon;
    private int salonSeleccionado;
    private int salonalumnoSeleccionado = 0;
    private EstadoEntity estado;
    private SalonalumnoEntity salonalumno;
    private String detalles;
    private static final String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$*+=?";


    public AlumnoBean(){
        alumno = new AlumnoEntity();
        alumnoSeleccionado = new AlumnoEntity();
        salud = new SaludEntity();
        salonalumno = new SalonalumnoEntity();
    }

    public AlumnoEntity getAlumno() {
        return alumno;
    }
    public void setAlumno(AlumnoEntity alumno) {
        this.alumno = alumno;
    }

    public AlumnoEntity getAlumnoSeleccionado() {
        return alumnoSeleccionado;
    }
    public void setAlumnoSeleccionado(AlumnoEntity alumnoSeleccionado) {
        this.alumnoSeleccionado = alumnoSeleccionado;
    }

    public SaludEntity getSalud() {
        return salud;
    }
    public void setSalud(SaludEntity salud) {
        this.salud = salud;
    }

    public EstadoEntity getEstado() {
        return estado;
    }
    public void setEstado(EstadoEntity estado) {
        this.estado = estado;
    }

    public SaludEntity getSaludSeleccionada() {
        return saludSeleccionada;
    }
    public void setSaludSeleccionada(SaludEntity saludSeleccionada) {
        this.saludSeleccionada = saludSeleccionada;
    }

    public void setAlumnoModel(AlumnoModel alumnoModel) {
        this.alumnoModel = alumnoModel;
    }
    public AlumnoModel getAlumnoModel() {
        return alumnoModel;
    }

    public void setSalon(SalonEntity salon) {
        this.salon = salon;
    }
    public SalonEntity getSalon() {
        return salon;
    }

    public void setSalonSeleccionado(int salonSeleccionado) {
        this.salonSeleccionado = salonSeleccionado;
    }
    public int getSalonSeleccionado() {
        return salonSeleccionado;
    }

    public void setSalonalumno(SalonalumnoEntity salonalumno) {
        this.salonalumno = salonalumno;
    }
    public SalonalumnoEntity getSalonalumno() {
        return salonalumno;
    }

    public void setSalonalumnoSeleccionado(int salonalumnoSeleccionado) {
        this.salonalumnoSeleccionado = salonalumnoSeleccionado;
    }
    public int getSalonalumnoSeleccionado() {
        return salonalumnoSeleccionado;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
    public String getDetalles() {
        return detalles;
    }

    //LISTAR ALUMNOS
    public List<AlumnoEntity> getListaAlumnos(){
        return alumnoModel.getAlumno();
    }

    //GUARDAR ALUMNO
    public void guardarAlumno(){
        estado = alumnoModel.obtenerEstado(1);
        alumno.setEstadoByIdEstado(estado);
        salon = alumnoModel.obtenerSalonById(salonSeleccionado);

        this.asignarSalud();

        if(alumno.getCodigoAlumno().isEmpty()){
            alumno.setCodigoAlumno(this.obtenerCarnet(alumno.getApellidos()));
            alumno.setContra(this.generarContrasena());

            salud.setAlumnoByIdAlumno(alumno);
            if (alumnoModel.guardarAlumno(alumno) != 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ocurrio un problema", "Intentalo más tarde."));
            } else {
                alumnoModel.guardarSalonAlumno(salon, alumno);
                alumnoModel.guardarSalud(salud);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Alumno registrado exitosamente", "Los datos han sido registrados en el sistema."));
            }
        }else{
            salud.setAlumnoByIdAlumno(alumno);
            salonalumno.setIdSalonAlumno(salonalumnoSeleccionado);
            salonalumno.setSalonByIdSalon(salon);
            salonalumno.setAlumnoByIdAlumno(alumno);
            if (alumnoModel.editarAlumno(alumno) != 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ocurrio un problema", "Intentalo más tarde."));
            } else {
                alumnoModel.editarSalonAlumno(salonalumno);
                alumnoModel.editarSalud(salud);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Alumno modificado exitosamente", "Los datos han sido modificados en el sistema."));
            }
        }
        PrimeFaces.current().ajax().update("form:messages");

        alumno = new AlumnoEntity();
        estado = new EstadoEntity();
        salud = new SaludEntity();
        salonalumno = new SalonalumnoEntity();
    }

    private void asignarSalud(){
        if(salud.getAlergias().isEmpty()){
            salud.setAlergias("No Aplica");
        }
        if(salud.getEnfermedad().isEmpty()){
            salud.setEnfermedad("No Aplica");
        }
        if(salud.getMedicamentos().isEmpty()){
            salud.setMedicamentos("No Aplica");
        }
    }

    //CAMBIAR CONTRASEÑA
    public void cambiarContra(){
        String codigo= JsfUtil.getRequest().getParameter("codigo");
        alumnoSeleccionado = alumnoModel.obtenerAlumno(Integer.parseInt(codigo));
        alumnoSeleccionado.setContra(this.generarContrasena());
        if (alumnoModel.editarAlumno(alumnoSeleccionado) != 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ocurrio un problema", "Intentalo más tarde."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Alumno modificado exitosamente", "Los datos han sido modificados en el sistema."));
        }
        PrimeFaces.current().ajax().update("form:messages");

        alumnoSeleccionado = new AlumnoEntity();
        detalles = "";
    }

    //ELIMINAR ALUMNO
    public void eliminarAlumno(){
        // Leyendo el parametro enviado desde la vista
        String codigo = JsfUtil.getRequest().getParameter("codigo");

        if(alumnoModel.eliminarSalud(Integer.parseInt(codigo)) == 1){
            if (alumnoModel.eliminarAlumno(Integer.parseInt(codigo)) != 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ocurrio un problema", "Intentalo más tarde."));
            } else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Alumno eliminado exitosamente", "Los datos han sido registrados en el sistema."));
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ocurrio un problema", "Intentalo más tarde."));
        }

        PrimeFaces.current().ajax().update("form:messages");
        alumno = new AlumnoEntity();
        estado = new EstadoEntity();
        salud = new SaludEntity();
        salonalumno = new SalonalumnoEntity();
        detalles="";
    }

    //OBTENER ALUMNO
    public void obtenerAlumno() {
        String codigo= JsfUtil.getRequest().getParameter("codigo");
        alumno = alumnoModel.obtenerAlumno(Integer.parseInt(codigo));
        salud = alumnoModel.obtenerSalud(Integer.parseInt(codigo));
        salonalumnoSeleccionado = alumnoModel.obtenerSalon(Integer.parseInt(codigo)).getIdSalonAlumno();
        detalles = "";
    }

    public void verDetalles(){
        String codigo= JsfUtil.getRequest().getParameter("codigo");
        alumnoSeleccionado = alumnoModel.obtenerAlumno(Integer.parseInt(codigo));
        saludSeleccionada = alumnoModel.obtenerSalud(Integer.parseInt(codigo));
        detalles = "mostrar";
    }

    //LIMPIAR
    public void limpiar(){
        alumno = new AlumnoEntity();
        estado = new EstadoEntity();
        salud = new SaludEntity();
        salonalumno = new SalonalumnoEntity();
    }

    public void Regresar(){
        detalles="";
    }

    //OPERACIONES PARA GUARDAR USUARIO
    public String obtenerCarnet(String apellidos) {
        String[] palabras = apellidos.split(" ");

        String iniciales = "";
        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                iniciales += palabra.charAt(0);
            }
        }

        LocalDate fechaActual = LocalDate.now();
        int year = fechaActual.getYear();
        int ultimosDosDigitos = year % 100;

        Random rand = new Random();
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            int numeroAleatorio = rand.nextInt(10);
            resultado.append(numeroAleatorio);
        }
        String cadenaConcatenada = resultado.toString();

        return iniciales+ultimosDosDigitos+cadenaConcatenada;
    }

    public String generarContrasena() {
        int longitud = 7;
        SecureRandom random = new SecureRandom();
        StringBuilder contrasena = new StringBuilder(longitud);

        for (int i = 0; i < longitud; i++) {
            int indice = random.nextInt(caracteres.length());
            char caracter = caracteres.charAt(indice);
            contrasena.append(caracter);
        }

        return contrasena.toString();
    }
}
