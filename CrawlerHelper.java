import java.lang.Exception;
import java.util.*;
import java.net.MalformedURLException;

public class CrawlerHelper {
	
	/*
	* Общий алгоритм для проверки аргументов
	*/
	public URLDepthPair getURLDepthPairFromArgs(String[] args) {
		if (args.length > 2) System.out.println("Warning more than 2 parameters from command line!\n");
		if (args.length < 2) {
			System.out.println("Warning less than 2 parameters from command line!\n");
			return null;
		}
		
		// Проверка второго параметра - глубины
		int depth;
		try {
			depth = Integer.parseInt(args[1]);
		} catch (Exception e) {
			System.out.println("Error depth parameter!");
			return null;
		}
		
		URLDepthPair urlDepth;
		
		// Вызов конструктора класса конструктором класса (вызовается, если не понравится какой-либо параметр)
		try {
			urlDepth = new URLDepthPair(args[0], depth);
		} 
		catch (MalformedURLException ex) {
			System.out.println(ex.getMessage() + "\n");
			return null;
		}  
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage() + "\n");
			return null;
		}
		
		return urlDepth;
	}
	
	
	/*
	* Получение пользовательского ввода
	*/
	public URLDepthPair getURLDepthPairFromInput() {
		
		// Временные переменные для хранения
		String url;
		int depth;
		
		// Массив со значениями для передачи на проверку
		String[] args;
		
		// Объект искомого класса
		URLDepthPair urlDepth = null;
		
		// Сканер ввода пользователя
		Scanner in = new Scanner(System.in);
		
		/*
		* Считывание, преобразование и проверка ввода пользователя
		*/ 	
		while (urlDepth == null) {
			
			// Считывание
			System.out.println("Enter URL and depth of parsing (in a line with a space between):");
			String input = in.nextLine();
		
			// Преобразование
			args = input.split(" ", 2);
			
			// Проверка
			urlDepth = this.getURLDepthPairFromArgs(args);
			if (urlDepth == null) System.out.println("Try again!\n");
		}
		
		return urlDepth;
		
		/*
		System.out.println("Enter URL:");
		url = in.next();
			
		// Очистка буфера
		if (in.hasNextLine()) {
			System.out.println("There is flood symbols in input buffer - need to clear: " + in.nextLine());
		}
			
		// Ввод глубины
		System.out.println("Enter depth:");
		while (true) {
				
			// Если буфер пустой - сканер запросит ввод, а потом уже проверит его
			if (in.hasNextInt()) {
				depth = in.nextInt();
				break;
			}
			else {
				System.out.println("\nError depth - try again:");
					
				// Очистка буффера ввода
				in.nextLine();
			}
		}
		*/
		
	}
	
}