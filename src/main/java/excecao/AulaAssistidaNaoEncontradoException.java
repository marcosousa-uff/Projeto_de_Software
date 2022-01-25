package excecao;

public class AulaAssistidaNaoEncontradoException extends Exception {

	private final static long serialVersionUID = 1;
	
	private int codigo;
	
	public AulaAssistidaNaoEncontradoException(String msg)
	{	super(msg);
	}

	public AulaAssistidaNaoEncontradoException(int codigo, String msg)
	{	super(msg);
		this.codigo = codigo;
	}
	
	public int getCodigoDeErro()
	{	return codigo;
	}

}
