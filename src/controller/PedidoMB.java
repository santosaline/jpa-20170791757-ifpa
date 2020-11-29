package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import dao.PedidoDAO;
import entidades.Pedido;
import model.PedidoVO;
import utilitarios.GeradorID;

@Named 
@SessionScoped
public class PedidoMB implements Serializable {
	private PedidoVO pedido = new PedidoVO();	
	private List<PedidoVO> pedidos;
	private Integer idCliente;
	 
	static PedidoDAO dao = new PedidoDAO();
	
	public PedidoMB() {}
 	
	public String salvar() {
        // se o "id" do objeto "pedidoVO" está NULL significa um "novo pedido"
		if (this.pedido.getId()==null) {
			cadastrarNovoPedido();
		} else
			atualizarPedido();
		return "";
	}
	
	public String novoPedido() {
		this.pedido = new PedidoVO();
		FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_INFO, 
	                		"Informe os dados do novo pedido", ""));	       
		return "";	
	}
	
    // método privado para incluir um novo pedido na base dados.
	private void cadastrarNovoPedido() {
		boolean incluiu = dao.incluir(pedido);
		if (incluiu) 
		   FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Pedido <"+pedido.getNomeProduto() + "> "
					+ " cadastrado com ID="+pedido.getId(), null));

		else
		   FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na Operação!", null));
		// limpa o "VO" para incluir um novo
		this.pedido = new PedidoVO();			
	}

	// método privado para alterar os dados do pedido na base dados.
	private void atualizarPedido() {		
		boolean ok = dao.atualiza(this.pedido);
		if (ok)
		   FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, 
						"Pedido <" + this.pedido.getNomeProduto()
						+ "> atualizado com sucesso!", null));
		else
		   FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, 
						"Erro na Operação!", null));
		// limpa o "VO" para incluir um novo
		this.pedido = new PedidoVO();					
	}
	
	public void delete(String id) {
		int idPK = Integer.parseInt(id);	
		Pedido ped = dao.findById(idPK);
		dao.delete(idPK);       
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, 
						"Pedido <" + ped.getNomeProduto()
						+ "> Excluído com sucesso!", null));
    }
	
	public String update(String id) {
		int idPK = Integer.parseInt(id);		 
	    Pedido ped = dao.findById(idPK);
	    this.pedido.setId(ped.getId());
	    this.pedido.setNomeProduto(ped.getNomeProduto());
	    this.pedido.setQuantidade(ped.getQuantidade());
	    this.pedido.setValorTotal(ped.getValorTotal());
	    return "";
	}
	
	public String updateID(String id) {
		int idPK = Integer.parseInt(id);		 
	    Pedido ped = dao.findById(idPK);
	    this.pedido.setIdCliente(ped.getIdCliente());
	    return "";
	}
	
	// getters e setters
	public PedidoVO getPedido() {
		return this.pedido;
	}
	public void setPedido(PedidoVO pedido) {
		this.pedido = pedido;
	}

	public List<PedidoVO> getPedidos() {
		List<Pedido> pedidosEnt = dao.getPedidos();
		this.pedidos = new ArrayList<PedidoVO>();
		for (Pedido pedido : pedidosEnt) {			
			
			PedidoVO vo = new PedidoVO();
			
			if(GeradorID.getId()==pedido.getIdCliente()) {
				vo.setId(pedido.getId());
				vo.setNomeProduto(pedido.getNomeProduto());
				vo.setQuantidade(pedido.getQuantidade());
				vo.setValorTotal(pedido.getValorTotal());
				vo.setIdCliente(pedido.getIdCliente());
				this.pedidos.add(vo);	
			}	
		}		
		return this.pedidos;
	}

	public void setClientes(List<PedidoVO> pedidos) {
		this.pedidos = pedidos;
	}

	
	public String idCliente(String idCli) {	 
		 idCliente = Integer.parseInt(idCli);
		 
		 GeradorID.setId(idCliente);
		 
		return "cadastro-pedido";
	
	
	}
	
	public String pagCliente() {	 

		return "cadastro-cliente";

	}
 
}
