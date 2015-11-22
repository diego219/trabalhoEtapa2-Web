package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.dao.ClienteComumDAO;
import br.edu.ifsul.modelo.ClienteComum;
import br.edu.ifsul.modelo.Referencia;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author diego
 */
@ManagedBean(name = "controleClienteComum")
@ViewScoped
public class ControleClienteComum implements Serializable {

    @EJB
    private ClienteComumDAO dao;
    private ClienteComum objeto;
    @EJB
    private CidadeDAO daoCidade;
    private Referencia referencia;
    private Boolean novaReferencia;

    public ControleClienteComum() {
    }
       
    public String listar() {
        return "/privado/clientecomum/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new ClienteComum();        
    }

    public void salvar() {
        try {
            if (objeto.getId()+"" == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso");            
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + e.getMessage());            
        }
    }

    public void editar(Integer id) {
        try {
            objeto = dao.getObjectById(id);            
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "+e.getMessage());            
        }
    }
    
    public void excluir(Integer id){
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto:"+Util.getMensagemErro(e));
        }
    }

    public void novaReferencia(){
        referencia = new Referencia();
        novaReferencia = true;
    }
    
    public void alterarReferencia(int index){
        referencia = objeto.getReferencias().get(index);
        novaReferencia = false;
    }
    
    public void salvarReferencia(){
        if (novaReferencia){
            objeto.adicionarReferencia(referencia);
        } 
        Util.mensagemInformacao("Operação realizada com sucesso");
    }
    
    public void removerReferencia(int index){
        objeto.removerReferencia(index);
        Util.mensagemInformacao("Operação realizada com sucesso");
    }
    
    
    public ClienteComumDAO getDao() {
        return dao;
    }

    public void setDao(ClienteComumDAO dao) {
        this.dao = dao;
    }

    public ClienteComum getObjeto() {
        return objeto;
    }

    public void setObjeto(ClienteComum objeto) {
        this.objeto = objeto;
    }

    public CidadeDAO getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(CidadeDAO daoCidade) {
        this.daoCidade = daoCidade;
    }

    public Referencia getReferencia() {
        return referencia;
    }

    public void setReferencia(Referencia telefone) {
        this.referencia = telefone;
    }

    public Boolean getNovaReferencia() {
        return novaReferencia;
    }

    public void setNovaReferencia(Boolean novaReferencia) {
        this.novaReferencia = novaReferencia;
    }
}
