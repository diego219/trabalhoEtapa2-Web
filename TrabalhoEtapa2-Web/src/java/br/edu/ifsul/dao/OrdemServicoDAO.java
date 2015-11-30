
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.OrdemServico;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author diego
 */
@Stateless
public class OrdemServicoDAO implements Serializable {
    
    @PersistenceContext(unitName = "TrabalhoEtapa2-WebPU")
    private EntityManager em;
    private List<OrdemServico> listarTodos;

    public OrdemServicoDAO() {
    }
    
    public void persist(OrdemServico objeto) throws Exception {
        em.persist(objeto);        
    }
    
    public void merge(OrdemServico objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remove(OrdemServico objeto) throws Exception{
        
        em.remove(em.getReference(OrdemServico.class, objeto.getId()));
        //objeto = em.merge(objeto);
        //em.remove(objeto);
        
        
    }
    
    public OrdemServico getObjectById(Integer id) throws Exception {
        OrdemServico obj = em.find(OrdemServico.class, id);
        return obj;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<OrdemServico> getListarTodos() {
        return em.createQuery("from OrdemServico order by id").getResultList();
    }

    public void setListarTodos(List<OrdemServico> listarTodos) {
        this.listarTodos = listarTodos;
    }

}
