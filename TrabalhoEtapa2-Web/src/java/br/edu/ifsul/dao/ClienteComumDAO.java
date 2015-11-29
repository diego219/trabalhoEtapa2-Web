
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.ClienteComum;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author diego
 */
@Stateless
public class ClienteComumDAO implements Serializable {
    
    @PersistenceContext(unitName = "TrabalhoEtapa2-WebPU")
    private EntityManager em;
    private List<ClienteComum> listarTodos;

    public ClienteComumDAO() {
    }
    
    public void persist(ClienteComum objeto) throws Exception {
        em.persist(objeto);        
    }
    
    public void merge(ClienteComum objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remove(ClienteComum objeto) throws Exception{
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public ClienteComum getObjectById(Integer id) throws Exception {
        ClienteComum obj = em.find(ClienteComum.class, id);
        // Executar o size das listas para inicializa-las
        obj.getReferencias().size();
        return obj;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<ClienteComum> getListarTodos() {
        return em.createQuery("from ClienteComum order by nome").getResultList();
    }
    
    public List<DataTable> getListarTodosClientes() {
        return em.createQuery("from Cliente order by nome").getResultList();
    }

    public void setListarTodos(List<ClienteComum> listarTodos) {
        this.listarTodos = listarTodos;
    }

}
