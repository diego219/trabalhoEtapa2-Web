package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.dao.ClienteEmpresaDAO;
import br.edu.ifsul.modelo.ClienteEmpresa;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author diego
 */
@ManagedBean(name = "controleClienteEmpresa")
@ViewScoped
public class ControleClienteEmpresa implements Serializable {

    @EJB
    private ClienteEmpresaDAO dao;
    private ClienteEmpresa objeto;
    @EJB
    private CidadeDAO daoCidade;

    public ControleClienteEmpresa() {
    }
       
    public String listar() {
        return "/privado/clienteempresa/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new ClienteEmpresa();
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

    public ClienteEmpresaDAO getDao() {
        return dao;
    }

    public void setDao(ClienteEmpresaDAO dao) {
        this.dao = dao;
    }

    public ClienteEmpresa getObjeto() {
        return objeto;
    }

    public void setObjeto(ClienteEmpresa objeto) {
        this.objeto = objeto;
    }

    public CidadeDAO getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(CidadeDAO daoCidade) {
        this.daoCidade = daoCidade;
    }
}