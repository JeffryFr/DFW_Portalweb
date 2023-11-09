package sv.edu.cms.www.managedbeans;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;
import sv.edu.cms.www.entities.MaestroEntity;
import sv.edu.cms.www.entities.RolEntity;
import sv.edu.cms.www.models.MaestroModel;
import sv.edu.cms.www.entities.AlumnoEntity;
import sv.edu.cms.www.models.AlumnoModel;
import sv.edu.cms.www.entities.RolEntity;
import sv.edu.cms.www.utils.JsfUtil;
import java.util.List;

@ManagedBean
@RequestScoped
public class loginBean {
    String codigo;
    String contra;
    MaestroModel modeloMaestro = new MaestroModel();
    AlumnoModel modeloAlumno = new AlumnoModel();

    //verificar sesión
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ExternalContext externalContext = facesContext.getExternalContext();
    HttpSession session = (HttpSession) externalContext.getSession(false);

    public loginBean(){
        codigo = "";
        contra = "";
    }

    public String getCodigo(){
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getContra(){
        return contra;
    }
    public void setContra(String contra) {
        this.contra = contra;
    }

    public String login(){
        if(modeloMaestro.verificarMaestro(codigo)){
            MaestroEntity maestro = modeloMaestro.iniciarMaestro(codigo, contra);


            if(maestro != null){
                RolEntity rol = (RolEntity) maestro.getRolByIdRol();
                int idRol = rol.getIdRol();
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                switch(idRol){
                    case 1:
                        session.setAttribute("rol", "Director");
                        session.setAttribute("usuario", maestro);
                        return "login.xhtml";
                    case 2:
                        session.setAttribute("rol", "Maestro");
                        session.setAttribute("usuario", maestro);
                        return "login.xhtml";
                }
            }else{
                JsfUtil.setErrorMessage(null, "Su contraseña es incorrecta");
                return null;
            }
        }
        if(modeloAlumno.verificarAlumno(codigo)){
            AlumnoEntity alumno = modeloAlumno.iniciarAlumno(codigo, contra);
            if(alumno != null){
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.setAttribute("rol", "Alumno");
                session.setAttribute("usuario", alumno);
                return "login.xhtml";
            }else{
                JsfUtil.setErrorMessage(null, "Su contraseña es incorrecta");
                return null;
            }
        }
        if(!(modeloMaestro.verificarMaestro(codigo) && modeloAlumno.verificarAlumno(codigo))){
            JsfUtil.setErrorMessage(null, "Verifique sus credenciales");
            return null;
        }
        return null;
    }

    public String logout(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.removeAttribute("rol"); // Eliminar el atributo "rol"
            session.removeAttribute("usuario"); // Eliminar el atributo "usuario"
            session.invalidate(); // Invalidar la sesión
        }
        return "login.xhtml";
    }
}
