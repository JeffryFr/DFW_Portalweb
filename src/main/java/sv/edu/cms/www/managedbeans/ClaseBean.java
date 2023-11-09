package sv.edu.cms.www.managedbeans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import sv.edu.cms.www.entities.*;
import sv.edu.cms.www.models.ClaseModel;
import sv.edu.cms.www.models.SalonModel;
import sv.edu.cms.www.models.MaestroMateriaModel;
import sv.edu.cms.www.utils.JsfUtil;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@RequestScoped
public class ClaseBean {
    ClaseModel claseModel = new ClaseModel();
    private ClaseEntity clase;
    private int salon = 0;
    private int SalonForm;
    private int claseEdit;
    private int maestroMateriaForm;
    private int maestroMateriaEdit;

    public ClaseBean(){
        clase = new ClaseEntity();
    }

    public ClaseModel getClaseModel() {
        return claseModel;
    }
    public void setClaseModel(ClaseModel claseModel) {
        this.claseModel = claseModel;
    }

    public ClaseEntity getClase() {
        return clase;
    }
    public void setClase(ClaseEntity clase) {
        this.clase = clase;
    }

    public int getSalon() {
        return salon;
    }
    public void setSalon(int salon) {
        this.salon = salon;
    }

    public int getSalonForm() {
        return SalonForm;
    }
    public void setSalonForm(int salonForm) {
        SalonForm = salonForm;
    }

    public int getMaestroMateriaForm() {
        return maestroMateriaForm;
    }
    public void setMaestroMateriaForm(int maestroMateriaForm) {
        this.maestroMateriaForm = maestroMateriaForm;
    }

    public int getClaseEdit() {
        return claseEdit;
    }
    public void setClaseEdit(int claseEdit) {
        this.claseEdit = claseEdit;
    }

    public int getMaestroMateriaEdit() {
        return maestroMateriaEdit;
    }
    public void setMaestroMateriaEdit(int maestroMateriaEdit) {
        this.maestroMateriaEdit = maestroMateriaEdit;
    }

    public void guardarClase(){
        SalonModel salonModel = new SalonModel();
        SalonEntity salon1 = salonModel.getSalonByCodigo(SalonForm);

        MaestroMateriaModel mmModel = new MaestroMateriaModel();
        MaestromateriaEntity mm1 = mmModel.obtenerMaestroMateriaById(maestroMateriaForm);

        clase.setMaestromateriaByIdMateriaMaestro(mm1);
        clase.setSalonByIdSalon(salon1);

        clase.setCodigoClase(crearCodigo(mm1.getMateriaByIdMateria().getMateria(), salon1.getCodigoSalon()));

        if(claseModel.verificarClase(salon1, mm1) != 1){
            if(claseModel.guardarClase(clase) != 1){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ocurrio un problema", "Intentalo más tarde."));
            } else {
                registrarEvaluaciones(clase);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Clase registrada exitosamente", "Los datos han sido registrados en el sistema."));
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"La materia ya es impartida en el salón", "Intentalo con otra materia."));
        }
        PrimeFaces.current().ajax().update("form:messages");

        SalonForm = 0;
        maestroMateriaForm = 0;
    }

    //EDITAR CLASE
    public void editarClase(){
        ClaseEntity cc1 = claseModel.obtenerByIdClase(claseEdit);

        MaestroMateriaModel mmModel = new MaestroMateriaModel();
        MaestromateriaEntity mm1 = mmModel.obtenerMaestroMateriaById(maestroMateriaEdit);

        if(cc1.getMaestromateriaByIdMateriaMaestro().getMateriaByIdMateria().getMateria().equals(mm1.getMateriaByIdMateria().getMateria())){
            cc1.setMaestromateriaByIdMateriaMaestro(mm1);
            if(claseModel.editarClase(cc1) != 1){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ocurrio un problema", "Intentalo más tarde."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Clase modificado exitosamente", "Los datos han sido modificados en el sistema."));
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El cambio debe ser con una materia igual", ""));
        }
        PrimeFaces.current().ajax().update("form:messages");

        claseEdit=0;
        maestroMateriaEdit=0;
    }

    //ELIMINAR DATO
    public void eliminarClase(){
        String codigo = JsfUtil.getRequest().getParameter("codigo");
        if(claseModel.eliminarClase(Integer.parseInt(codigo)) > 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Clase eliminada exitosamente", "Los datos han sido eliminados en el sistema."));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ocurrio un problema", "Intentalo más tarde."));
        }
        PrimeFaces.current().ajax().update("form:messages");
    }

    //EVALUACIONES
    public void registrarEvaluaciones(ClaseEntity clase){
        int anio = obtenerAnio(clase.getSalonByIdSalon().getCodigoSalon());
        List<PeriodoEntity> periodos = claseModel.listarPeriodos(anio);

        for(PeriodoEntity periodo : periodos){
            List<Date> listaFechas = obtenerFechasEntrega(periodo.getFechaInicio(), periodo.getFechaFin(), 5);

            for (int i = 0; i < listaFechas.size(); i++) {
                Date fecha = listaFechas.get(i);

                EvaluacionEntity test = new EvaluacionEntity();

                test.setClaseByIdClase(clase);
                test.setPeriodoByIdPeriodo(periodo);
                test.setFechaEntrega(fecha);
                test.setIngresoNota(aumentarUnaSemana(fecha));

                switch (i){
                    case 1:
                        test.setTipoByIdTipo(claseModel.getTipo(1));
                        test.setConcepto("Actividad Integradora 1");
                        break;
                    case 3:
                        test.setTipoByIdTipo(claseModel.getTipo(1));
                        test.setConcepto("Actividad Integradora 2");
                        break;
                    case 2:
                        test.setTipoByIdTipo(claseModel.getTipo(2));
                        test.setConcepto("Examen 1");
                        break;
                    case 4:
                        test.setTipoByIdTipo(claseModel.getTipo(2));
                        test.setConcepto("Examen 2");
                        break;
                    case 5:
                        test.setTipoByIdTipo(claseModel.getTipo(3));
                        test.setConcepto("Examen Final");
                        break;
                    default:
                        continue;
                }
                claseModel.guardarEvaluacion(test);
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Las evaluaciones de esta clase se han creado con exito", "Los datos han sido modificados en el sistema."));
    }

    //OPERACIONES EXTRA
    public String crearCodigo(String materia, String salon){
        String[] palabras = materia.split(" ");

        if (palabras.length == 1) {
            // Si solo hay una palabra, tomamos sus primeras tres letras
            if (materia.length() >= 3) {
                return materia.substring(0, 3)+salon;
            } else {
                return materia+salon;
            }
        } else {
            // Si hay múltiples palabras, tomamos la inicial de cada palabra
            StringBuilder resultado = new StringBuilder();

            for (String palabra : palabras) {
                if (!palabra.isEmpty()) {
                    resultado.append(palabra.charAt(0));
                }
            }

            return resultado.toString()+salon;
        }
    }

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

    //Obtener entregas de tareas
    public static List<Date> obtenerFechasEntrega(Date fechaInicio, Date fechaFin, int cantidad) {
        List<Date> fechasDivididas = new ArrayList<>();

        LocalDate localFechaInicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localFechaFin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long diferenciaEnMilisegundos = fechaFin.getTime() - fechaInicio.getTime();
        int diasEnRango = (int) (diferenciaEnMilisegundos / (1000 * 60 * 60 * 24));
        int diasPorIntervalo = diasEnRango / cantidad;

        LocalDate fechaActual = localFechaInicio.minusDays(diasPorIntervalo);

        for (int i = 0; i < cantidad; i++) {
            fechaActual = fechaActual.plusDays(diasPorIntervalo);
            if (fechaActual.getDayOfWeek() == DayOfWeek.SATURDAY) {
                fechaActual = fechaActual.minusDays(1); // Aproximamos a viernes
            } else if (fechaActual.getDayOfWeek() == DayOfWeek.SUNDAY) {
                fechaActual = fechaActual.plusDays(1); // Aproximamos a lunes
            }
            fechasDivididas.add(Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        fechasDivididas.add(fechaFin);
        return fechasDivididas;
    }

    public static Date aumentarUnaSemana(Date fecha) {
        // Convertir a java.time.LocalDate
        LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Aumentar una semana
        LocalDate nuevaFecha = localDate.plusWeeks(1);

        // Convertir de nuevo a java.util.Date
        Date nuevaFechaUtilDate = Date.from(nuevaFecha.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return nuevaFechaUtilDate;
    }
}
