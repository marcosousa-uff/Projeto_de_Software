package dao.controle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dao.impl.AssisteDAOImpl;
import dao.impl.AulaDAOImpl;
import dao.impl.ProfessorDAOImpl;
import dao.impl.UsuarioDAOImpl;
import net.sf.cglib.proxy.Enhancer;

// A anota��o @Configuration indica que esta classe possui um ou mais m�todos anotados com @Bean.
@Configuration
public class FabricaDeDao {
	
	// @Bean diz ao Spring:
	// Aqui est� uma inst�ncia da classe ProdutoDAOImpl, por favor, 
	// guarde esta inst�ncia e me devolva quando eu a pedir.

	// @Autowired em ProdutoAppServiceImpl diz:
	// Por favor, me retorne uma inst�ncia do tipo ProdutoDAO, isto �,  
	// aquela que foi criada mais cedo com a anota��o @Bean.
	@Bean
	public static UsuarioDAOImpl getUsuarioDao() throws Exception {
		return getDao(dao.impl.UsuarioDAOImpl.class);
	}
	@Bean
	public static AulaDAOImpl getAulaDao() throws Exception {
		return getDao(dao.impl.AulaDAOImpl.class);
	}
	@Bean
	public static AssisteDAOImpl getAulaAssistidaDao() throws Exception {
		return getDao(dao.impl.AssisteDAOImpl.class);
	}
	@Bean
	public static ProfessorDAOImpl getProfessorDao() throws Exception {
		return getDao(dao.impl.ProfessorDAOImpl.class);
	}
	@SuppressWarnings("unchecked")
	public static <T> T getDao(Class<T> classeDoDao) throws Exception {
		return (T) Enhancer.create(classeDoDao, new InterceptadorDeDAO());
	}
}