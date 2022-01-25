package excecao;

public class AulaNaoEncontradoException extends Exception {

	private final static long serialVersionUID = 1;
	
	private int codigo;
	
	public AulaNaoEncontradoException(String msg)
	{	super(msg);
	}

	public AulaNaoEncontradoException(int codigo, String msg)
	{	super(msg);
		this.codigo = codigo;
	}
	
	public int getCodigoDeErro()
	{	return codigo;
	}

}
