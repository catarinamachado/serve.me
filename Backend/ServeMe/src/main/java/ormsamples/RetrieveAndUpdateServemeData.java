/**
 * Licensee: Tiago Fontes(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class RetrieveAndUpdateServemeData {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = utilizador.ServemePersistentManager.instance().getSession().beginTransaction();
		try {
			utilizador.Cliente utilizadorCliente = utilizador.ClienteDAO.loadClienteByQuery(null, null);
			// Update the properties of the persistent object
			utilizador.ClienteDAO.save(utilizadorCliente);
			utilizador.Avaliacao utilizadorAvaliacao = utilizador.AvaliacaoDAO.loadAvaliacaoByQuery(null, null);
			// Update the properties of the persistent object
			utilizador.AvaliacaoDAO.save(utilizadorAvaliacao);
			categorias.Classe categoriasClasse = categorias.ClasseDAO.loadClasseByQuery(null, null);
			// Update the properties of the persistent object
			categorias.ClasseDAO.save(categoriasClasse);
			categorias.Categoria categoriasCategoria = categorias.CategoriaDAO.loadCategoriaByQuery(null, null);
			// Update the properties of the persistent object
			categorias.CategoriaDAO.save(categoriasCategoria);
			servico.Proposta servicoProposta = servico.PropostaDAO.loadPropostaByQuery(null, null);
			// Update the properties of the persistent object
			servico.PropostaDAO.save(servicoProposta);
			servico.Pedido servicoPedido = servico.PedidoDAO.loadPedidoByQuery(null, null);
			// Update the properties of the persistent object
			servico.PedidoDAO.save(servicoPedido);
			servico.Servico servicoServico = servico.ServicoDAO.loadServicoByQuery(null, null);
			// Update the properties of the persistent object
			servico.ServicoDAO.save(servicoServico);
			utilizador.Prestador utilizadorPrestador = utilizador.PrestadorDAO.loadPrestadorByQuery(null, null);
			// Update the properties of the persistent object
			utilizador.PrestadorDAO.save(utilizadorPrestador);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public void retrieveByCriteria() throws PersistentException {
		System.out.println("Retrieving Cliente by ClienteCriteria");
		utilizador.ClienteCriteria utilizadorClienteCriteria = new utilizador.ClienteCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//utilizadorClienteCriteria.ID.eq();
		System.out.println(utilizadorClienteCriteria.uniqueCliente());
		
		System.out.println("Retrieving Avaliacao by AvaliacaoCriteria");
		utilizador.AvaliacaoCriteria utilizadorAvaliacaoCriteria = new utilizador.AvaliacaoCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//utilizadorAvaliacaoCriteria.ID.eq();
		System.out.println(utilizadorAvaliacaoCriteria.uniqueAvaliacao());
		
		System.out.println("Retrieving Classe by ClasseCriteria");
		categorias.ClasseCriteria categoriasClasseCriteria = new categorias.ClasseCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//categoriasClasseCriteria.ID.eq();
		System.out.println(categoriasClasseCriteria.uniqueClasse());
		
		System.out.println("Retrieving Categoria by CategoriaCriteria");
		categorias.CategoriaCriteria categoriasCategoriaCriteria = new categorias.CategoriaCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//categoriasCategoriaCriteria.ID.eq();
		System.out.println(categoriasCategoriaCriteria.uniqueCategoria());
		
		System.out.println("Retrieving Proposta by PropostaCriteria");
		servico.PropostaCriteria servicoPropostaCriteria = new servico.PropostaCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//servicoPropostaCriteria.ID.eq();
		System.out.println(servicoPropostaCriteria.uniqueProposta());
		
		System.out.println("Retrieving Pedido by PedidoCriteria");
		servico.PedidoCriteria servicoPedidoCriteria = new servico.PedidoCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//servicoPedidoCriteria.ID.eq();
		System.out.println(servicoPedidoCriteria.uniquePedido());
		
		System.out.println("Retrieving Servico by ServicoCriteria");
		servico.ServicoCriteria servicoServicoCriteria = new servico.ServicoCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//servicoServicoCriteria.ID.eq();
		System.out.println(servicoServicoCriteria.uniqueServico());
		
		System.out.println("Retrieving Prestador by PrestadorCriteria");
		utilizador.PrestadorCriteria utilizadorPrestadorCriteria = new utilizador.PrestadorCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//utilizadorPrestadorCriteria.ID.eq();
		System.out.println(utilizadorPrestadorCriteria.uniquePrestador());
		
	}
	
	
	public static void main(String[] args) {
		try {
			RetrieveAndUpdateServemeData retrieveAndUpdateServemeData = new RetrieveAndUpdateServemeData();
			try {
				retrieveAndUpdateServemeData.retrieveAndUpdateTestData();
				//retrieveAndUpdateServemeData.retrieveByCriteria();
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
