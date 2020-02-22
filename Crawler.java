import java.lang.Exception;
import java.util.*;
import java.net.MalformedURLException;

public class Crawler {
	
	// Список посещённых сайтов, и ещё непосещённых
	LinkedList<URLDepthPair> notVisitedList;
	LinkedList<URLDepthPair> visitedList;
	
	// Глубина
	int depth;
	
	// Конструктор
	public Crawler() {
		notVisitedList = new LinkedList<URLDepthPair>();
		visitedList = new LinkedList<URLDepthPair>();
	}
	
	// Точка входа
	public static void main (String[] args) {
		
		Crawler crawler = new Crawler();
		
		crawler.getFirstURLDepthPair(args);
	
	}

	/*
	* Проход по всем сайтам на определённую глубину
	*/

	/*
	* Проверка командной строки, ввода пользователя и добавление первого объекта URLDepthPair
	* В список непросмотренных
	* Если нет ввода из командной строки передавать просто null
	*/
	public void getFirstURLDepthPair(String[] args) {
		
		CrawlerHelper help = new CrawlerHelper();
		
		// Чтение аргументов из командной строки
		URLDepthPair urlDepth = help.getURLDepthPairFromArgs(args);
		if (urlDepth == null) {
			System.out.println("Args are empty or have exception. Now you need to enter URL and depth manually!\n");
			
			// Получение ввода от пользователей
			urlDepth = help.getURLDepthPairFromInput();
		}
		
		// Получение и замена глубины
		this.depth = urlDepth.getDepth();
		urlDepth.setDepth(0);
		
		// Вывод первого объекта URLDepthPair
		System.out.println("First site: " + urlDepth.toString() + "\n");
		
		
	}
}