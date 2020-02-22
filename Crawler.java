import java.lang.Exception;
import java.util.*

public class Crawler {
	
	// Список посещённых сайтов, и ещё непосещённых
	LinkedList<URLDepthPair> notVisitedList;
	LinkedList<URLDepthPair> visitedList;
	
	// Конструктор
	public Crawler() {
		notVisitedList = new LinkedList<URLDepthPair>();
		visitedList = new LinkedList<URLDepthPair>();
	}
	
	// Точка входа
	public static void main (String[] args) {
		
		String url;
		int depth = 0;
		
		if (args.length == 0) {
			System.out.println("Wow, it seems like there isnt any args, so lets add some data");
			
			Scanner in = new Scanner(System.in);
			
			System.out.println("Enter URL:");
			url = in.next();
			
			// Очистка буфера
			if (in.hasNextLine()) {
				System.out.println("There is flood symbols in input buffer - need to clear: " + in.nextLine());
			}
			
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
		}
		else {
			url = args[0];
			
			try {
				depth = Integer.parseInt(args[1]);
			} catch (Exception e) {
				System.out.println("Error depth - args[1] parameter");
			}
		}
		
		System.out.println("\nURL = " + url);
		System.out.println("Depth = " + depth);
	}
	
	/*
	* Получение аргументов командной строки
	*/
	private URLDepthPair getURLDepthPair() {
		
	}
	
	/*
	* Получение пользовательского ввода
	*/
}