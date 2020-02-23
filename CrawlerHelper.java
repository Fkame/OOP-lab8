import java.lang.Exception;
import java.util.*;
import java.net.MalformedURLException;
import java.net.*;
import java.io.*;

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
	
	/*
	* Вывод информации по интересующему URL
	*/
	public static String[] getInfoAboutUrl(URL url, boolean needToOut) {
		
		String[] info = new String[10];
		info[0] = url.toString();
		info[1] = url.getHost();
		try {
			info[2] = url.getContent().toString();
		} catch (IOException e) {
			e.printStackTrace();
			info[2] = "";
		}
		info[3] = url.getProtocol();
		info[4] = url.getPath();
		info[5] = url.getUserInfo();
		info[6] = url.getFile();
		info[7] = url.getRef();
		try {
			info[8] = url.toURI().toString();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			info[8] = "";
		}
		info[9] = String.valueOf(url.getPort());
		
		if (needToOut) {
			System.out.println("\n---Info about this url---");
			System.out.println("Full url: " + info[0]);
			System.out.println("Host name of url: " + info[1]);
			System.out.println("Content of url: " + info[2]);
			System.out.println("Protocol of url: " + info[3]);
			System.out.println("Path of url: " + info[4]);
			System.out.println("UserInfo of url: " + info[5]);
			System.out.println("Files on url: " + info[6]);
			System.out.println("Ref of url: " + info[7]);
			System.out.println("URI of url: " + info[8]);
			System.out.println("Port of url: " + info[9]);
			System.out.println("---------------------------\n");
		}
		
		return info;
	}
	
	public static String[] getInfoAboutUrl(String urlStr, boolean needToOut) {
		URL url = null;
		try {
			url = new URL(urlStr);
		}
		catch (MalformedURLException e) {
			System.err.println("MalformedURLException: " + e.getMessage());
			return null;
		}
		
		String[] info = getInfoAboutUrl(url, needToOut);
		
		return info;
		
	}
	
	public String getURLFromHTMLTag(String line) {
		int indexStart = line.indexOf("\"");
		
		return null;
	}
}