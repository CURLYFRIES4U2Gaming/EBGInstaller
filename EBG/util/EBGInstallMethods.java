package EBG.util;

public class EBGInstallMethods {
	/**Help Method for printing with different methods.*/
	public static void print(String text, int option){
		if(option == 1){
			System.out.println(text);
		}
		if(option == 2){
			System.out.print(text);
		}
		if(option == 3){
			System.err.println(text);
		}
		if(option == 4){
			System.err.print(text);
		}
	}
	
	/**An test method.*/
	public static int calc(int first, int second, int result){
		return result = (first + second);
	}
}
