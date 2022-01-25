package excecao;

public class NivelDeAcessoException extends Exception{
	private final static long serialVersionUID = 1;
	
	private int codigo;
	
	public NivelDeAcessoException(String msg)
	{	super(msg);
	}

	public NivelDeAcessoException(int codigo, String msg)
	{	super(msg);
		this.codigo = codigo;
	}
	
	public int getCodigoDeErro()
	{	return codigo;
	}

}
