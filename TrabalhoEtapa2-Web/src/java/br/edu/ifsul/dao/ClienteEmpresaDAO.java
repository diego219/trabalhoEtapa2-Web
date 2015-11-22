
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.ClienteEmpresa;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author diego
 */
@Stateless
public class ClienteEmpresaDAO implements Serializable {
    
    @PersistenceContext(unitName = "TrabalhoEtapa2-WebPU")
    private EntityManager em;
    private List<ClienteEmpresa> listarTodos;

    public ClienteEmpresaDAO() {
    }
    
    public void persist(ClienteEmpresa objeto) throws Exception {
        em.persist(objeto);        
    }
    
    public void merge(ClienteEmpresa objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remove(ClienteEmpresa objeto) throws Exception{
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public ClienteEmpresa getObjectById(Integer id) throws Exception {
        ClienteEmpresa obj = em.find(ClienteEmpresa.class, id);
        return obj;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<ClienteEmpresa> getListarTodos() {
        return em.createQuery("from ClienteEmpresa order by nome").getResultList();
    }

    public void setListarTodos(List<ClienteEmpresa> listarTodos) {
        this.listarTodos = listarTodos;
    }

}
