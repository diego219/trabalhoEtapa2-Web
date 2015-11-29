package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ClienteComumDAO;
import br.edu.ifsul.dao.ClienteEmpresaDAO;
import br.edu.ifsul.dao.OrdemServicoDAO;
import br.edu.ifsul.dao.ProdutoServicoDAO;
import br.edu.ifsul.modelo.OrdemServico;
import br.edu.ifsul.modelo.ItemOrdemServico;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author diego
 */
@ManagedBean(name = "controleOrdemServico")
@ViewScoped
public class ControleOrdemServico implements Serializable {

    @EJB
    private OrdemServicoDAO dao;
    private OrdemServico objeto;
    @EJB
    private ClienteComumDAO daoClienteComum;
    @EJB
    private ClienteEmpresaDAO daoClienteEmpresa;
    @EJB
    private ProdutoServicoDAO daoProdutoServico;
    private ItemOrdemServico itemOrdemServico;
    private Boolean novoItem;

    public ControleOrdemServico() {
    }
       
    public String listar() {
        return "/privado/ordemservico/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new OrdemServico();        
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

    public void novoItemOrdemServico(){
        itemOrdemServico = new ItemOrdemServico();
        novoItem = true;
    }
    
    public void alterarItemOrdemServico(int index){
        itemOrdemServico = objeto.getItens_ordem_servico().get(index);
        novoItem = false;
    }
    
    public void salvarItemOrdemServico(){
        if (novoItem){
            objeto.adicionarItemOrdemServico(itemOrdemServico);
        } 
        Util.mensagemInformacao("Operação realizada com sucesso");
    }
    
    public void removerItemOrdemServico(int index){
        objeto.removerItemOrdemServico(index);
        Util.mensagemInformacao("Operação realizada com sucesso");
    }
    
    
    public OrdemServicoDAO getDao() {
        return dao;
    }

    public void setDao(OrdemServicoDAO dao) {
        this.dao = dao;
    }

    public OrdemServico getObjeto() {
        return objeto;
    }

    public void setObjeto(OrdemServico objeto) {
        this.objeto = objeto;
    }

    public ClienteComumDAO getDaoClienteComum() {
        return daoClienteComum;
    }

    public void setDaoClienteComum(ClienteComumDAO daoClienteComum) {
        this.daoClienteComum = daoClienteComum;
    }
    
    public ClienteEmpresaDAO getDaoClienteEmpresa() {
        return daoClienteEmpresa;
    }

    public void setDaoClienteEmpresa(ClienteEmpresaDAO daoClienteEmpresa) {
        this.daoClienteEmpresa = daoClienteEmpresa;
    }
    
    public ProdutoServicoDAO getDaoProdutoServico() {
        return daoProdutoServico;
    }

    public void setDaoProdutoServico(ProdutoServicoDAO daoProdutoServico) {
        this.daoProdutoServico = daoProdutoServico;
    }

    public ItemOrdemServico getItemOrdemServico() {
        return itemOrdemServico;
    }

    public void setItemOrdemServico(ItemOrdemServico itemOrdemServico) {
        this.itemOrdemServico = itemOrdemServico;
    }

    public Boolean getNovoItem() {
        return novoItem;
    }

    public void setNovoItem(Boolean novoItem) {
        this.novoItem = novoItem;
    }
}
