package exercicio;

import java.util.List;

import javax.persistence.OptimisticLockException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.UsuarioNaoEncontradoException;
import modelo.Assiste;
import modelo.SingletonPerfis;
import modelo.Usuario;
import servico.UsuarioAppService;

public class PrincipalUsuario
{	public static void main (String[] args) 
	{	
		String nome;
		String senha;
		String login;
		Usuario umUsuario;
		

		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		UsuarioAppService usuarioAppService = (UsuarioAppService)fabrica.getBean ("usuarioAppService"); 
		
		String[] perfis = {"user", "admin"};
		
		SingletonPerfis singletonPerfis = SingletonPerfis.getSingletonPerfis();
		singletonPerfis.setPerfis(perfis);

		boolean continua = true;
		while (continua)
		{
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um usuario");
			System.out.println("2. Alterar um usuario");
			System.out.println("3. Remover um usuario");
			System.out.println("4. Listar um usuario e historico de aulas assistidas");
			System.out.println("5. Listar todos os usuario");
			System.out.println("6. Sair");
						
			int opcao = Console.readInt('\n' + 
							"Digite um número entre 1 e 6:");
					
			switch (opcao)
			{	
				case 1:
				{
					nome = Console.readLine('\n' + 
						"Informe o nome do usuario: ");
					login = Console.readLine(
						"Informe o login: ");
					senha = Console.readLine(
						"Informe a senha: ");
						
					umUsuario = new Usuario(nome, login, senha);
					
					usuarioAppService.inclui(umUsuario);
						

					break;
				}

				case 2:
				{	int resposta = Console.readInt('\n' + 
						"Digite o número do usuario que você deseja alterar: ");
										
					try
					{	umUsuario = usuarioAppService.recuperaUmUsuario(resposta);
					}
					catch(UsuarioNaoEncontradoException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"Número = " + umUsuario.getId() + 
						"    Nome = " + umUsuario.getNome() +
						"    Login = " + umUsuario.getLogin() +
						"    senha = " + umUsuario.getSenha() +
						"	 versão ="+ umUsuario.getVersao()); 
												
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
							
							umUsuario.setNome(novoNome);

							try
							{	usuarioAppService.altera(umUsuario);

							}
							catch(OptimisticLockException e)
							{ 
								System.out.println("objeto ja foi alterado por outro usuario");
							}
							catch(UsuarioNaoEncontradoException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;
					
						case 2:
							String novoLogin = Console.
									readLine("Digite o novo login: ");
							
							umUsuario.setLogin(novoLogin);

							try
							{	usuarioAppService.altera(umUsuario);
						
							}
							catch(OptimisticLockException e)
							{ 
								System.out.println("objeto ja foi alterado por outro usuario");
							}
							catch(UsuarioNaoEncontradoException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;
						case 3:
							String novaSenha = Console.
									readLine("Digite a nova senha: ");
							
							umUsuario.setLogin(novaSenha);

							try
							{	usuarioAppService.altera(umUsuario);
					
							}
							catch(OptimisticLockException e)
							{ 
								System.out.println("objeto ja foi alterado por outro usuario");
							}
							catch(UsuarioNaoEncontradoException e)
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
						"Digite o número do usuario que você deseja remover: ");
									
					try
					{	umUsuario = usuarioAppService.
										recuperaUmUsuario(resposta);
					}
					catch(UsuarioNaoEncontradoException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"Número = " + umUsuario.getId() + 
						"    Nome = " + umUsuario.getNome());
														
					String resp = Console.readLine('\n' + 
						"Confirma a remoção do usuario?");

					if(resp.equals("s"))
					{	try
						{	usuarioAppService.exclui (umUsuario);

						}
						catch(UsuarioNaoEncontradoException e)
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
						"Digite o número do usuario que você deseja listar: ");
									
					try
					{	umUsuario = usuarioAppService.
							recuperaUmUsuarioEAulasAssitidas(resposta);
					}
					catch(UsuarioNaoEncontradoException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
					System.out.println('\n' + 
							"Número = " + umUsuario.getId() + 
							"    Nome = " + umUsuario.getNome() +
							"    Login = " + umUsuario.getLogin() +
							"    senha = " + umUsuario.getSenha() +
							"	 versão ="+ umUsuario.getVersao()); 
					
					
					List<Assiste> aulasAssistidas = umUsuario.getAssistiu();

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
					List<Usuario> usuarios = usuarioAppService.recuperaUsuarios();

					for (Usuario usuario : usuarios)
					{	
						System.out.println('\n' + 
							"Id = " + usuario.getId() +
							"  Nome = " + usuario.getNome() +
							"  Login = " + usuario.getLogin() +
							"  senha = " + usuario.getSenha()+
							"  versão = "+ usuario.getVersao());
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
