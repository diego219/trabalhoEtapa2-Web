
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.ProdutoServico;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author diego
 */
@Stateless
public class ProdutoServicoDAO implements Serializable {
    
    @PersistenceContext(unitName = "TrabalhoEtapa2-WebPU")
    private EntityManager em;
    private List<ProdutoServico> listarTodos;

    public ProdutoServicoDAO() {
    }
    
    public void persist(ProdutoServico objeto) throws Exception {
        em.persist(objeto);        
    }
    
    public void merge(ProdutoServico objeto) throws Exception {
        em.merge(objeto);
    }
    
    public void remove(ProdutoServico objeto) throws Exception{
        objeto = em.merge(objeto);
        em.remove(objeto);
    }
    
    public ProdutoServico getObjectById(Integer id) throws Exception {
        ProdutoServico obj = em.find(ProdutoServico.class, id);
        return obj;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<ProdutoServico> getListarTodos() {
        return em.createQuery("from ProdutoServico order by descricao").getResultList();
    }

    public void setListarTodos(List<ProdutoServico> listarTodos) {
        this.listarTodos = listarTodos;
    }

}
