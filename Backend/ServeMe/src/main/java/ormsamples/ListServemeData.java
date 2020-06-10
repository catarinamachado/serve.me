/**
 * Licensee: Tiago Fontes(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class ListServemeData {
	private static final int ROW_COUNT = 100;
	
	public void listTestData() throws PersistentException {
		System.out.println("Listing Cliente...");
		utilizador.Cliente[] utilizadorClientes = utilizador.ClienteDAO.listClienteByQuery(null, null);
		int length = Math.min(utilizadorClientes.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(utilizadorClientes[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Avaliacao...");
		utilizador.Avaliacao[] utilizadorAvaliacaos = utilizador.AvaliacaoDAO.listAvaliacaoByQuery(null, null);
		length = Math.min(utilizadorAvaliacaos.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(utilizadorAvaliacaos[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Classe...");
		categorias.Classe[] categoriasClasses = categorias.ClasseDAO.listClasseByQuery(null, null);
		length = Math.min(categoriasClasses.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(categoriasClasses[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Categoria...");
		categorias.Categoria[] categoriasCategorias = categorias.CategoriaDAO.listCategoriaByQuery(null, null);
		length = Math.min(categoriasCategorias.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(categoriasCategorias[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Proposta...");
		servico.Proposta[] servicoPropostas = servico.PropostaDAO.listPropostaByQuery(null, null);
		length = Math.min(servicoPropostas.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(servicoPropostas[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Pedido...");
		servico.Pedido[] servicoPedidos = servico.PedidoDAO.listPedidoByQuery(null, null);
		length = Math.min(servicoPedidos.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(servicoPedidos[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Servico...");
		servico.Servico[] servicoServicos = servico.ServicoDAO.listServicoByQuery(null, null);
		length = Math.min(servicoServicos.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(servicoServicos[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Prestador...");
		utilizador.Prestador[] utilizadorPrestadors = utilizador.PrestadorDAO.listPrestadorByQuery(null, null);
		length = Math.min(utilizadorPrestadors.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(utilizadorPrestadors[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
	}
	
	public void listByCriteria() throws PersistentException {
		System.out.println("Listing Cliente by Criteria...");
		utilizador.ClienteCriteria utilizadorClienteCriteria = new utilizador.ClienteCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//utilizadorClienteCriteria.ID.eq();
		utilizadorClienteCriteria.setMaxResults(ROW_COUNT);
		utilizador.Cliente[] utilizadorClientes = utilizadorClienteCriteria.listCliente();
		int length =utilizadorClientes== null ? 0 : Math.min(utilizadorClientes.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(utilizadorClientes[i]);
		}
		System.out.println(length + " Cliente record(s) retrieved."); 
		
		System.out.println("Listing Avaliacao by Criteria...");
		utilizador.AvaliacaoCriteria utilizadorAvaliacaoCriteria = new utilizador.AvaliacaoCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//utilizadorAvaliacaoCriteria.ID.eq();
		utilizadorAvaliacaoCriteria.setMaxResults(ROW_COUNT);
		utilizador.Avaliacao[] utilizadorAvaliacaos = utilizadorAvaliacaoCriteria.listAvaliacao();
		length =utilizadorAvaliacaos== null ? 0 : Math.min(utilizadorAvaliacaos.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(utilizadorAvaliacaos[i]);
		}
		System.out.println(length + " Avaliacao record(s) retrieved."); 
		
		System.out.println("Listing Classe by Criteria...");
		categorias.ClasseCriteria categoriasClasseCriteria = new categorias.ClasseCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//categoriasClasseCriteria.ID.eq();
		categoriasClasseCriteria.setMaxResults(ROW_COUNT);
		categorias.Classe[] categoriasClasses = categoriasClasseCriteria.listClasse();
		length =categoriasClasses== null ? 0 : Math.min(categoriasClasses.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(categoriasClasses[i]);
		}
		System.out.println(length + " Classe record(s) retrieved."); 
		
		System.out.println("Listing Categoria by Criteria...");
		categorias.CategoriaCriteria categoriasCategoriaCriteria = new categorias.CategoriaCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//categoriasCategoriaCriteria.ID.eq();
		categoriasCategoriaCriteria.setMaxResults(ROW_COUNT);
		categorias.Categoria[] categoriasCategorias = categoriasCategoriaCriteria.listCategoria();
		length =categoriasCategorias== null ? 0 : Math.min(categoriasCategorias.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(categoriasCategorias[i]);
		}
		System.out.println(length + " Categoria record(s) retrieved."); 
		
		System.out.println("Listing Proposta by Criteria...");
		servico.PropostaCriteria servicoPropostaCriteria = new servico.PropostaCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//servicoPropostaCriteria.ID.eq();
		servicoPropostaCriteria.setMaxResults(ROW_COUNT);
		servico.Proposta[] servicoPropostas = servicoPropostaCriteria.listProposta();
		length =servicoPropostas== null ? 0 : Math.min(servicoPropostas.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(servicoPropostas[i]);
		}
		System.out.println(length + " Proposta record(s) retrieved."); 
		
		System.out.println("Listing Pedido by Criteria...");
		servico.PedidoCriteria servicoPedidoCriteria = new servico.PedidoCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//servicoPedidoCriteria.ID.eq();
		servicoPedidoCriteria.setMaxResults(ROW_COUNT);
		servico.Pedido[] servicoPedidos = servicoPedidoCriteria.listPedido();
		length =servicoPedidos== null ? 0 : Math.min(servicoPedidos.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(servicoPedidos[i]);
		}
		System.out.println(length + " Pedido record(s) retrieved."); 
		
		System.out.println("Listing Servico by Criteria...");
		servico.ServicoCriteria servicoServicoCriteria = new servico.ServicoCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//servicoServicoCriteria.ID.eq();
		servicoServicoCriteria.setMaxResults(ROW_COUNT);
		servico.Servico[] servicoServicos = servicoServicoCriteria.listServico();
		length =servicoServicos== null ? 0 : Math.min(servicoServicos.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(servicoServicos[i]);
		}
		System.out.println(length + " Servico record(s) retrieved."); 
		
		System.out.println("Listing Prestador by Criteria...");
		utilizador.PrestadorCriteria utilizadorPrestadorCriteria = new utilizador.PrestadorCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//utilizadorPrestadorCriteria.ID.eq();
		utilizadorPrestadorCriteria.setMaxResults(ROW_COUNT);
		utilizador.Prestador[] utilizadorPrestadors = utilizadorPrestadorCriteria.listPrestador();
		length =utilizadorPrestadors== null ? 0 : Math.min(utilizadorPrestadors.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(utilizadorPrestadors[i]);
		}
		System.out.println(length + " Prestador record(s) retrieved."); 
		
	}
	
	public static void main(String[] args) {
		try {
			ListServemeData listServemeData = new ListServemeData();
			try {
				listServemeData.listTestData();
				//listServemeData.listByCriteria();
			}
			finally {
				utilizador.ServemePersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
