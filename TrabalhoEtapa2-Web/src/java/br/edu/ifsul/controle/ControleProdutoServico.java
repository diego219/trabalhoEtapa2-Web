package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ProdutoServicoDAO;
import br.edu.ifsul.modelo.ProdutoServico;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author diego
 */
@ManagedBean(name = "controleProdutoServico")
@ViewScoped
public class ControleProdutoServico implements Serializable {

    @EJB
    private ProdutoServicoDAO dao;
    private ProdutoServico objeto;

    public ControleProdutoServico() {
    }
       
    public String listar() {
        return "/privado/produtoservico/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new ProdutoServico();        
    }

    public void salvar() {
        try {
            if (objeto.getData_cadastro() == null) {
                objeto.setData_cadastro(Calendar.getInstance());
            }
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
    
    public ProdutoServicoDAO getDao() {
        return dao;
    }

    public void setDao(ProdutoServicoDAO dao) {
        this.dao = dao;
    }

    public ProdutoServico getObjeto() {
        return objeto;
    }

    public void setObjeto(ProdutoServico objeto) {
        this.objeto = objeto;
    }
}
