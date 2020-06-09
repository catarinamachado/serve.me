/**
 * Licensee: Tiago Fontes(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class DeleteServemeData {
	public void deleteTestData() throws PersistentException {
		PersistentTransaction t = utilizador.ServemePersistentManager.instance().getSession().beginTransaction();
		try {
			utilizador.Cliente utilizadorCliente = utilizador.ClienteDAO.loadClienteByQuery(null, null);
			// Delete the persistent object
			utilizador.ClienteDAO.delete(utilizadorCliente);
			utilizador.Avaliacao utilizadorAvaliacao = utilizador.AvaliacaoDAO.loadAvaliacaoByQuery(null, null);
			// Delete the persistent object
			utilizador.AvaliacaoDAO.delete(utilizadorAvaliacao);
			categorias.Classe categoriasClasse = categorias.ClasseDAO.loadClasseByQuery(null, null);
			// Delete the persistent object
			categorias.ClasseDAO.delete(categoriasClasse);
			categorias.Categoria categoriasCategoria = categorias.CategoriaDAO.loadCategoriaByQuery(null, null);
			// Delete the persistent object
			categorias.CategoriaDAO.delete(categoriasCategoria);
			servico.Proposta servicoProposta = servico.PropostaDAO.loadPropostaByQuery(null, null);
			// Delete the persistent object
			servico.PropostaDAO.delete(servicoProposta);
			servico.Pedido servicoPedido = servico.PedidoDAO.loadPedidoByQuery(null, null);
			// Delete the persistent object
			servico.PedidoDAO.delete(servicoPedido);
			servico.Servico servicoServico = servico.ServicoDAO.loadServicoByQuery(null, null);
			// Delete the persistent object
			servico.ServicoDAO.delete(servicoServico);
			utilizador.Prestador utilizadorPrestador = utilizador.PrestadorDAO.loadPrestadorByQuery(null, null);
			// Delete the persistent object
			utilizador.PrestadorDAO.delete(utilizadorPrestador);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			DeleteServemeData deleteServemeData = new DeleteServemeData();
			try {
				deleteServemeData.deleteTestData();
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
