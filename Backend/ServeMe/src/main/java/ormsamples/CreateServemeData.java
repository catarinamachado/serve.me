/**
 * Licensee: Tiago Fontes(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class CreateServemeData {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = utilizador.ServemePersistentManager.instance().getSession().beginTransaction();
		try {
			utilizador.Cliente utilizadorCliente = utilizador.ClienteDAO.createCliente();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : avaliacoes, numServicosCancelados, numServicosRealizados, nif, classificacao, numTelemovel
			utilizador.ClienteDAO.save(utilizadorCliente);
			utilizador.Avaliacao utilizadorAvaliacao = utilizador.AvaliacaoDAO.createAvaliacao();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : classificacao
			utilizador.AvaliacaoDAO.save(utilizadorAvaliacao);
			categorias.Classe categoriasClasse = categorias.ClasseDAO.createClasse();
			// Initialize the properties of the persistent object here
			categorias.ClasseDAO.save(categoriasClasse);
			categorias.Categoria categoriasCategoria = categorias.CategoriaDAO.createCategoria();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : classe
			categorias.CategoriaDAO.save(categoriasCategoria);
			servico.Proposta servicoProposta = servico.PropostaDAO.createProposta();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : vencedora, precoProposto, prestador, pedido
			servico.PropostaDAO.save(servicoProposta);
			servico.Pedido servicoPedido = servico.PedidoDAO.createPedido();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : estado, duracao, precoHora, categoria, cliente
			servico.PedidoDAO.save(servicoPedido);
			servico.Servico servicoServico = servico.ServicoDAO.createServico();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : estado, proposta, pedido, prestador, cliente
			servico.ServicoDAO.save(servicoServico);
			utilizador.Prestador utilizadorPrestador = utilizador.PrestadorDAO.createPrestador();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : avaliacoes, numServicosCancelados, numServicosRealizados, nif, classificacao, numTelemovel
			utilizador.PrestadorDAO.save(utilizadorPrestador);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			CreateServemeData createServemeData = new CreateServemeData();
			try {
				createServemeData.createTestData();
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
