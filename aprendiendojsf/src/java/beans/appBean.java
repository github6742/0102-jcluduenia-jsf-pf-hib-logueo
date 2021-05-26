package beans;

import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import util.MyUtil;

@ManagedBean
@ApplicationScoped
public class appBean implements Serializable{
     
    
    public static String baseurl()
    {
        return "http://localhost:9090/aprendiendojsf/";
    }

    public static String basepathlogin()
    {
        return "/aprendiendojsf/faces/";
    }
    
    public static String basepath()
    {
        return "/faces/views/";
    }
}
