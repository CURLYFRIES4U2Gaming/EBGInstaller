package EBG.util;

/**Throws if wanted an custom exception.*/
public class EBGInstallException extends RuntimeException{

	private static final long serialVersionUID = 2282159355396734645L;

	public EBGInstallException(String error){
		super(error);
	}
}
