package exercicio;

import java.util.List;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

import corejava.Console;
import excecao.AulaNaoEncontradoException;
import modelo.Assiste;
import modelo.Aula;
import modelo.SingletonPerfis;
import servico.AulaAppService;

public class PrincipalAula {
	public static void main (String[] args) 
	{	
		int numero;
		int idProfessor;
		String curso;
		String topico;
		String categoria;
		String link;
		
		Aula umaAula;
		
		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		AulaAppService aulaAppService = (AulaAppService)fabrica.getBean("aulaAppService");; 
		
		String[] perfis = {"user"};
		
		SingletonPerfis singletonPerfis = SingletonPerfis.getSingletonPerfis();
		singletonPerfis.setPerfis(perfis);

		boolean continua = true;
		while (continua)
		{
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar uma aula");
			System.out.println("2. Alterar uma aula");
			System.out.println("3. Remover uma aula - lembrando que usuario não tem permissão pra isso");
			System.out.println("4. Listar aulas assistidas");
			System.out.println("5. Listar todos as aulas");
			System.out.println("6. Sair");
						
			int opcao = Console.readInt('\n' + 
							"Digite um número entre 1 e 6:");
					
			switch (opcao)
			{	
				case 1:
				{
					curso = Console.readLine('\n' + 
						"Informe o nome do curso: ");
					categoria = Console.readLine(
						"Informe a categoria do curso: ");
					topico = Console.readLine(
						"Informe o topico da aula ");
					link = Console.readLine("Informe o link da aula: ");
					numero = Integer.parseInt(Console.readLine("Informe o numero da aula: "));
					
					idProfessor = Integer.parseInt(Console.readLine("Informe o id do professor: "));
						
					umaAula = new Aula(numero, idProfessor, curso, topico, categoria, link);
					
					try {
						aulaAppService.inclui(umaAula);
					}
					catch(PersistenceException e)
					{ 
						System.out.println("O id inserido não corresponde a nenhum professor no banco de dados");
					}	

					break;
				}

				case 2:
				{	int resposta = Console.readInt('\n' + 
						"Digite o número do usuario que você deseja alterar: ");
										
					try
					{	umaAula = aulaAppService.recuperaUmaAula(resposta);
					}
					catch(AulaNaoEncontradoException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"Aula = " + umaAula.getNumero() + 
						"    professor = " + umaAula.getIdProfessor() +
						"    Curso = " + umaAula.getCurso() +
						"    topico = " + umaAula.getTopico() +
						"    categoria = " + umaAula.getCategoria() +
						"    link = " + umaAula.getLink() +
						"	 versão ="+ umaAula.getVersao()); 
												
					System.out.println('\n' + "O que você deseja alterar?");
					System.out.println('\n' + "1. Numero");
					System.out.println("2. curso");
					System.out.println("3. topico");
					System.out.println("4. categoria");
					System.out.println("5. link");
					System.out.println("6. id do professor");
					

					int opcaoAlteracao = Console.readInt('\n' + 
											"Digite um número de 1 a 6:");
					
					switch (opcaoAlteracao)
					{	case 1:
							int novoNumero = Integer.parseInt(Console.
										readLine("Digite o novo numero: "));
							
							umaAula.setNumero(novoNumero);

							try
							{	aulaAppService.altera(umaAula);

							}
							catch(OptimisticLockException e)
							{ 
								System.out.println("objeto ja foi alterado por outro usuario");
							}
							catch(AulaNaoEncontradoException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;
					
						case 2:
							String novoCurso = Console.
									readLine("Digite o novo curso: ");
							
							umaAula.setCurso(novoCurso);

							try
							{	aulaAppService.altera(umaAula);
					
							}
							catch(OptimisticLockException e)
							{ 
								System.out.println("objeto ja foi alterado por outro usuario");
							}
							catch(AulaNaoEncontradoException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;
						case 3:
							String novaTopico = Console.
									readLine("Digite o novo topico: ");
							
							umaAula.setTopico(novaTopico);

							try
							{	aulaAppService.altera(umaAula);

					
							}
							catch(OptimisticLockException e)
							{ 
								System.out.println("objeto ja foi alterado por outro usuario");
							}
							catch(AulaNaoEncontradoException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;
						case 4:
							String novaCategoria = Console.
									readLine("Digite a nova categoria: ");
							
							umaAula.setCategoria(novaCategoria);

							try
							{	aulaAppService.altera(umaAula);

						
							}
							catch(OptimisticLockException e)
							{ 
								System.out.println("objeto ja foi alterado por outro usuario");
							}
							catch(AulaNaoEncontradoException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;
						case 5:
							String novoLink = Console.
									readLine("Digite o novo topico: ");
							
							umaAula.setLink(novoLink);

							try
							{	aulaAppService.altera(umaAula);
						
							}
							catch(OptimisticLockException e)
							{ 
								System.out.println("objeto ja foi alterado por outro usuario");
							}
							catch(AulaNaoEncontradoException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;
						case 6:
							int novoId = Integer.parseInt(Console.
										readLine("Digite o novo id do professor: "));
							
							umaAula.setIdProfessor(novoId);

							try
							{	aulaAppService.altera(umaAula);

							}
							catch(OptimisticLockException e)
							{ 
								System.out.println("objeto ja foi alterado por outro usuario");
							}
							catch(AulaNaoEncontradoException e)
							{	System.out.println('\n' + e.getMessage());
							}
							catch(DataIntegrityViolationException e)
							{ 
								System.out.println("O id inserido não corresponde a nenhum professor no banco de dados");
							}
								
							break;

						default:
							System.out.println('\n' + "Opção inválida!");
					}

					break;
				}

				case 3:
				{	int resposta = Console.readInt('\n' + 
						"Digite o número da aula que você deseja remover: ");
									
					try
					{	umaAula = aulaAppService.
										recuperaUmaAula(resposta);
					}
					catch(AulaNaoEncontradoException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"Aula= " + umaAula.getNumero() + 
						"    Nome = " + umaAula.getCurso());
														
					String resp = Console.readLine('\n' + 
						"Confirma a remoção da aula?");

					if(resp.equals("s"))
					{	try
						{	aulaAppService.exclui(umaAula);

						}
						catch(AulaNaoEncontradoException e)
						{	System.out.println('\n' + e.getMessage());
						}
					}
					else
					{	System.out.println('\n' + 
							"usuario não removido.");
					}
					
					break;
				}
				case 4:
				{	int resposta = Console.readInt('\n' + 
						"Digite o número da aula que você deseja listar: ");
									
					try
					{	umaAula = aulaAppService.
							recuperaUmaAulaAssistida(resposta);
					}
					catch(AulaNaoEncontradoException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
					System.out.println('\n' + 
							"Aula = " + umaAula.getNumero() + 
							"    professor = " + umaAula.getIdProfessor() +
							"    Curso = " + umaAula.getCurso() +
							"    topico = " + umaAula.getTopico() +
							"    categoria = " + umaAula.getCategoria() +
							"    link = " + umaAula.getLink() +
							"	 versão ="+ umaAula.getVersao()); 
					
					List<Assiste> aulasAssistidas = umaAula.getAssistiu();

					for (Assiste aulaAssistida : aulasAssistidas)
					{	
						System.out.println('\n' + 
								"Id = " + aulaAssistida.getId() + 
								"	id do usuario = " + aulaAssistida.getIdUsuario() + 
								"    id da aula = " + aulaAssistida.getIdAula() +
								"    data = " + aulaAssistida.getData() +
								"    hora = " + aulaAssistida.getHora() +
								"	 versão ="+ aulaAssistida.getVersao());
					}
												
					
					
					break;
				}
				case 5:
				{	
					List<Aula> aulas = aulaAppService.recuperaAulas();

					for (Aula aula : aulas)
					{	
						System.out.println('\n' + 
								"Id = " + aula.getId() + 
								"	Aula = " + aula.getNumero() + 
								"	Id do professor = " + aula.getIdProfessor() +
								"    Curso = " + aula.getCurso() +
								"    topico = " + aula.getTopico() +
								"    categoria = " + aula.getCategoria() +
								"    link = " + aula.getLink() +
								"	 versão ="+ aula.getVersao());
					}
					
					break;
				}

				case 6:
				{	continua = false;
					break;
				}

				default:
					System.out.println('\n' + "Opção inválida!");
			}
		}		
	}

}
