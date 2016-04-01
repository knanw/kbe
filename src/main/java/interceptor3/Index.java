package interceptor3;

import java.security.Principal;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.interceptor.Interceptors;

/**
 *
 * @author happyFace
 */
@Named("index")
@ViewScoped
@Interceptors({ LoggedInterceptor.class})
public class Index {
    private String name = "Get A Lawyer";

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    public String logout(){
        Principal userPrincipal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        System.out.println("Principal: "+userPrincipal);
        return "login?faces-redirect=true";
    }
    
}
