package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.ClienteComum;
import br.edu.ifsul.modelo.ClienteEmpresa;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author diego
 */
@FacesConverter(value = "converterCliente")
public class ConverterCliente implements Converter, Serializable {

    @PersistenceContext(unitName = "TrabalhoEtapa2-WebPU")
    private EntityManager em;

    // converte da tela para objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null) {
            return null;
        }
        Object obj = em.find(ClienteComum.class, Integer.parseInt(string));
        if(obj==null){
            obj = em.find(ClienteEmpresa.class, Integer.parseInt(string));
        }
        return obj;
    }

    // Converter do objeto para a tela. Se o Objeto Recebido for uma Instancia de ClienteComum ele cria um objeto ClienteComum
    // caso contrário se for ClienteEmpresa ele cria um objeto ClienteEmpresa para que a lista do SelectOneMenu possa mostrar
    // o nome independente se for Cliente ou Empresa.
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof ClienteComum) {
            ClienteComum obj = (ClienteComum) o;
            return obj.getId() + "";
        } else {
            ClienteEmpresa obj = (ClienteEmpresa) o;
            return obj.getId() + "";
        }
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
