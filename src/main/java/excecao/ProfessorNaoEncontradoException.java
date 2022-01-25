package excecao;

public class ProfessorNaoEncontradoException extends Exception {

	private final static long serialVersionUID = 1;
	
	private int codigo;
	
	public ProfessorNaoEncontradoException(String msg)
	{	super(msg);
	}

	public ProfessorNaoEncontradoException(int codigo, String msg)
	{	super(msg);
		this.codigo = codigo;
	}
	
	public int getCodigoDeErro()
	{	return codigo;
	}

}
