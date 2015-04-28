package br.com.caelum.notasfiscais.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public Object intercept(InvocationContext ctx) throws Exception {
		System.out.println("Abrindo a transação");
		manager.getTransaction().begin();
		
		// Passando para o JSF tratar a requisição e pegando o 
		// retorno da lógica
		Object resultado = ctx.proceed();
		
		manager.getTransaction().commit();
		System.out.println("Comitando a transação");
		
		return resultado;
	}
	
}
