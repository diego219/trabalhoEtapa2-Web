
package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.ProdutoServico;
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
@FacesConverter(value = "converterProdutoServico")
public class ConverterProdutoServico implements Converter, Serializable{
    
    @PersistenceContext(unitName = "TrabalhoEtapa2-WebPU")
    private EntityManager em;

    // converte da tela para objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null){
            return null;
        }
        return em.find(ProdutoServico.class, Integer.parseInt(string));
    }

    // converter do objeto para a tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        ProdutoServico obj = (ProdutoServico) o;
        return obj.getId()+"";
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
