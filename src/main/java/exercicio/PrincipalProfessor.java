package exercicio;

import java.util.List;

import javax.persistence.OptimisticLockException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.ProfessorNaoEncontradoException;
import modelo.Aula;
import modelo.Professor;
import modelo.SingletonPerfis;
import servico.ProfessorAppService;

public class PrincipalProfessor {
	public static void main (String[] args) 
	{	
		String nome;
		String senha;
		String login;
		Professor umProfessor;
		
	
		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");
	
		ProfessorAppService professorAppService = (ProfessorAppService)fabrica.getBean ("professorAppService"); 
		
		String[] perfis = {"user", "admin"};
		
		SingletonPerfis singletonPerfis = SingletonPerfis.getSingletonPerfis();
		singletonPerfis.setPerfis(perfis);
	
		boolean continua = true;
		while (continua)
		{
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um professor");
			System.out.println("2. Alterar um professor");
			System.out.println("3. Remover um professor");
			System.out.println("4. Listar um professor e suas aulas");
			System.out.println("5. Listar todos os professor");
			System.out.println("6. Sair");
						
			int opcao = Console.readInt('\n' + 
							"Digite um número entre 1 e 6:");
					
			switch (opcao)
			{	
				case 1:
				{
					nome = Console.readLine('\n' + 
						"Informe o nome do professor: ");
					login = Console.readLine(
						"Informe o login: ");
					senha = Console.readLine(
						"Informe a senha: ");
						
					umProfessor = new Professor(nome, login, senha);
					
					professorAppService.inclui(umProfessor);
					
	
					break;
				}
	
				case 2:
				{	int resposta = Console.readInt('\n' + 
						"Digite o número do professor que você deseja alterar: ");
										
					try
					{	umProfessor = professorAppService.recuperaUmProfessor(resposta);
					}
					catch(ProfessorNaoEncontradoException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"Número = " + umProfessor.getId() + 
						"    Nome = " + umProfessor.getNome() +
						"    Login = " + umProfessor.getLogin() +
						"    senha = " + umProfessor.getSenha() +
						"	 versão ="+ umProfessor.getVersao()); 
												
					System.out.println('\n' + "O que você deseja alterar?");
					System.out.println('\n' + "1. Nome");
					System.out.println("2. Login");
					System.out.println("3. Senha");
					
	
					int opcaoAlteracao = Console.readInt('\n' + 
											"Digite um número de 1 a 3:");
					
					switch (opcaoAlteracao)
					{	case 1:
							String novoNome = Console.
										readLine("Digite o novo nome: ");
							
							umProfessor.setNome(novoNome);
	
							try
							{	professorAppService.altera(umProfessor);
	
							}
							catch(OptimisticLockException e)
							{ 
								System.out.println("objeto ja foi alterado por outro usuario");
							}
							catch(ProfessorNaoEncontradoException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;
					
						case 2:
							String novoLogin = Console.
									readLine("Digite o novo login: ");
							
							umProfessor.setLogin(novoLogin);
	
							try
							{	professorAppService.altera(umProfessor);
						
							}
							catch(OptimisticLockException e)
							{ 
								System.out.println("objeto ja foi alterado por outro usuario");
							}
							catch(ProfessorNaoEncontradoException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;
						case 3:
							String novaSenha = Console.
									readLine("Digite a nova senha: ");
							
							umProfessor.setSenha(novaSenha);
	
							try
							{	professorAppService.altera(umProfessor);
						
							}
							catch(OptimisticLockException e)
							{ 
								System.out.println("objeto ja foi alterado por outro usuario");
							}
							catch(ProfessorNaoEncontradoException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;
	
						default:
							System.out.println('\n' + "Opção inválida!");
					}
	
					break;
				}
	
				case 3:
				{	int resposta = Console.readInt('\n' + 
						"Digite o número do professor que você deseja remover: ");
									
					try
					{	umProfessor = professorAppService.
										recuperaUmProfessor(resposta);
					}
					catch(ProfessorNaoEncontradoException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"Número = " + umProfessor.getId() + 
						"    Nome = " + umProfessor.getNome());
														
					String resp = Console.readLine('\n' + 
						"Confirma a remoção do professor?");
	
					if(resp.equals("s"))
					{	try
						{	professorAppService.exclui (umProfessor);
						}
						catch(ProfessorNaoEncontradoException e)
						{	System.out.println('\n' + e.getMessage());
						}
					}
					else
					{	System.out.println('\n' + 
							"professor não removido.");
					}
					
					break;
				}
				
				case 4:
				{	
					int numero = Console.readInt('\n' + 
							"Digite o número do professor que você deseja listar: ");
					
					try {
						umProfessor = professorAppService.recuperaUmProfessorEAulas(numero);
					} catch (ProfessorNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
						break;
					}
					System.out.println('\n' + 
							"Id = " + umProfessor.getId() +
							"  Nome = " + umProfessor.getNome() +
							"  Login = " + umProfessor.getLogin() +
							"  senha = " + umProfessor.getSenha()+
							"  versão = "+ umProfessor.getVersao());
					
					List<Aula> aulas = umProfessor.getAulas();
	
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
	
				case 5:
				{	
					List<Professor> professores = professorAppService.recuperaProfessores();
	
					for (Professor professor : professores)
					{	
						System.out.println('\n' + 
							"Id = " + professor.getId() +
							"  Nome = " + professor.getNome() +
							"  Login = " + professor.getLogin() +
							"  senha = " + professor.getSenha()+
							"  versão = "+ professor.getVersao());
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
