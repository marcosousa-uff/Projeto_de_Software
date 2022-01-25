package aspecto;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import anotacao.Perfil;
import excecao.NivelDeAcessoException;
import modelo.SingletonPerfis;

@Aspect
public class AspectoLoginDeUsuarios {
	 SingletonPerfis singletonPerfis = SingletonPerfis.getSingletonPerfis();
	 String[] perfis = singletonPerfis.getPerfis();
	 
	 @Pointcut("execution(* servico..*.*(..))")
	 public void verificaUsuarioLogadoAround() {
		 
	 }
	 
	 @Around("verificaUsuarioLogadoAround()")
	public Object verificaUsuarioLogadoAround(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		boolean possuiAnota��o = signature.getMethod().isAnnotationPresent(Perfil.class);
		try {
			if (possuiAnota��o) {
				boolean nomeEncontrado = false;
				String[] nomes = signature.getMethod().getAnnotation(Perfil.class).nomes();
				for (String nome : nomes) {
					for (String perfil : perfis) {
						if (nome.equals(perfil))
							nomeEncontrado = true;
					}
				}
				if (nomeEncontrado) {
					System.out.println('\n' + "Opera��o efetuada com sucesso!!");
					return joinPoint.proceed();
				} else {
					throw new NivelDeAcessoException(
							"o usuario logado n�o tem o n�vel de acesso necess�rio para executar essa opera��o");
				}
			} else {
				return joinPoint.proceed();
			}
		} catch (NivelDeAcessoException e) {
			System.out.println(e);
		}
		return null;

	}
	 
	 
			 
	

}
