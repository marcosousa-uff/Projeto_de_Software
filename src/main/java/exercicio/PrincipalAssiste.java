package exercicio;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

import corejava.Console;
import excecao.AulaAssistidaNaoEncontradoException;
import modelo.Assiste;
import modelo.SingletonPerfis;
import servico.AssisteAppService;

public class PrincipalAssiste {
	public static void main (String[] args) 
	{	
		int idUsuario;
		int idAula;
		Date data;
		Time hora;
		
		Assiste umaAula;
		
		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		AssisteAppService assisteAppService = (AssisteAppService)fabrica.getBean("assisteAppService");; 
		
		String[] perfis = {"user", "admin"};
		
		SingletonPerfis singletonPerfis = SingletonPerfis.getSingletonPerfis();
		singletonPerfis.setPerfis(perfis);

		boolean continua = true;
		while (continua)
		{
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Assistir uma aula");
			System.out.println("2. Alterar historico de aulas assistidas");
			System.out.println("3. Remover uma aula assistida do historico");
			System.out.println("4. Listar todos as aulas assistidas ");
			System.out.println("5. Sair");
						
			int opcao = Console.readInt('\n' + 
							"Digite um número entre 1 e 5:");
					
			switch (opcao)
			{	
				case 1:
				{
					idAula = Integer.parseInt(Console.readLine('\n' + 
						"Informe o id da aula: "));
					idUsuario = Integer.parseInt(Console.readLine("Informe o numero do usuario: "));
					
					data = Date.valueOf(LocalDateTime.now().toLocalDate());
					
					hora = Time.valueOf(LocalDateTime.now().toLocalTime());
						
					umaAula = new Assiste(idUsuario, idAula, data, hora);
					
					try {
						assisteAppService.inclui(umaAula);
						
					}catch(PersistenceException e)
					{ 
						System.out.println("O id do usuario ou aula inserido não corresponde a nenhum dado existente no banco de dados");
					}
						

					break;
				}

				case 2:
				{	int resposta = Console.readInt('\n' + 
						"Digite o id do historico de aulas que você deseja alterar: ");
										
					try
					{	umaAula = assisteAppService.recuperaUmaAulaAssistida(resposta);
					}
					catch(AulaAssistidaNaoEncontradoException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"Id do usuario = " + umaAula.getIdUsuario() + 
						"    Id da aula = " + umaAula.getIdAula() +
						"    data = " + umaAula.getData()+
						"    hora = " + umaAula.getHora() +
						"	 versão ="+ umaAula.getVersao()); 
												
					System.out.println('\n' + "O que você deseja alterar?");
					System.out.println('\n' + "1. Numero do usuario");
					System.out.println("2. numero da aula");
					System.out.println("3. data");
					System.out.println("4. hora");
					

					int opcaoAlteracao = Console.readInt('\n' + 
											"Digite um número de 1 a 4:");
					
					switch (opcaoAlteracao)
					{	case 1:
							int novoNumero = Integer.parseInt(Console.
										readLine("Digite o novo numero: "));
							
							umaAula.setIdUsuario(novoNumero);

							try
							{	assisteAppService.altera(umaAula);

							}
							catch(OptimisticLockException e)
							{ 
								System.out.println("objeto ja foi alterado por outro usuario");
							}
							catch(AulaAssistidaNaoEncontradoException e)
							{	System.out.println('\n' + e.getMessage());
							}
							catch(DataIntegrityViolationException e)
							{ 
								System.out.println("O id do usuario inserido não corresponde a nenhum usuario no banco de dados");
							}
								
							break;
					
						case 2:
							int novoIdAula = Integer.parseInt(Console.readLine("Digite o novo numero: "));
		
							umaAula.setIdUsuario(novoIdAula);
		
							try {
								assisteAppService.altera(umaAula);
		
							} catch (OptimisticLockException e) {
								System.out.println("objeto ja foi alterado por outro usuario");
							} catch (AulaAssistidaNaoEncontradoException e) {
								System.out.println('\n' + e.getMessage());
							}catch(DataIntegrityViolationException e)
							{ 
								System.out.println("O id da aula inserida não corresponde a nenhuma aula no banco de dados");
							}
								
							break;
						case 3:
							String stringData = Console.
							readLine("Digite a haro no padrão aaaa-mm-dd: ");
							Date novaData = Date.valueOf(stringData );
							
							umaAula.setData(novaData);

							try
							{	assisteAppService.altera(umaAula);

						
							}
							catch(OptimisticLockException e)
							{ 
								System.out.println("objeto ja foi alterado por outro usuario");
							}
							catch(AulaAssistidaNaoEncontradoException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;
						case 4:
							String stringHora = Console.
							readLine("Digite a nova hora no padrão hh:mm:ss ");
							Time novaHora = Time.valueOf(stringHora);
							
							umaAula.setHora(novaHora);

							try
							{	assisteAppService.altera(umaAula);
						
							}
							catch(OptimisticLockException e)
							{ 
								System.out.println("objeto ja foi alterado por outro usuario");
							}
							catch(AulaAssistidaNaoEncontradoException e)
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
						"Digite o número do historico de aulas assistidas que você deseja remover: ");
									
					try
					{	umaAula = assisteAppService.
										recuperaUmaAulaAssistida(resposta);
					}
					catch(AulaAssistidaNaoEncontradoException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"id do usuario = " + umaAula.getIdUsuario() + 
						"    id da aula = " + umaAula.getIdAula()+
						"    data = " + umaAula.getData()+
						"    hora = " + umaAula.getHora());
														
					String resp = Console.readLine('\n' + 
						"Confirma a remoção da aula?");

					if(resp.equals("s"))
					{	try
						{	assisteAppService.exclui(umaAula);
						}
						catch(AulaAssistidaNaoEncontradoException e)
						{	System.out.println('\n' + e.getMessage());
						}
					}
					else
					{	System.out.println('\n' + 
							"registro não removido.");
					}
					
					break;
				}
				case 4:
				{	
					List<Assiste> aulasAssistidas = assisteAppService.recuperaAulasAssistidas();

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
				{	continua = false;
					break;
				}

				default:
					System.out.println('\n' + "Opção inválida!");
			}
		}		
	}


}
