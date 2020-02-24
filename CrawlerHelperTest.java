public class CrawlerHelperTest {
	
	public static void main (String[] args) {
		
		CrawlerHelper helper = new CrawlerHelper();
		
		// Тест обрезания формата
		String testURL = "http://users.cms.caltech.edu/~donnie/cs11/java/lab2/index.html";
		String getURL = helper.cutURLEndFormat(testURL);
		System.out.println("Before cut: [" + testURL + "]");
		System.out.println("After cut: [" + getURL + "]");
		System.out.println("");
		
		testURL = "http://users.cms.caltech.edu/~donnie/cs11/java/lab2/";
		getURL = helper.cutURLEndFormat(testURL);
		System.out.println("Before cut: [" + testURL + "]");
		System.out.println("After cut: [" + getURL + "]");
		System.out.println("");
		
		// Тест по выделение URL из тэга
		testURL = "<a href=\"../javastyle.html\">CS11 Java Style Guidelines</a>.  There is even a --> [../javastyle.html] --> 11, 28";
		getURL = helper.getURLFromHTMLTag(testURL);
		System.out.println("Before cut: [" + testURL + "]");
		System.out.println("After cut: [" + getURL + "]");
		System.out.println("");
		
		testURL = "   <a href=\"../\"";
		getURL = helper.getURLFromHTMLTag(testURL);
		System.out.println("Before cut: [" + testURL + "]");
		System.out.println("After cut: [" + getURL + "]");
		System.out.println("");
		
		// Тест соединение ссылки с возвратом
		testURL = "http://users.cms.caltech.edu/~donnie/cs11/java/lab2/index.html";
		getURL = CrawlerHelper.urlFromBackRef(testURL, "../javastyle.html");
		System.out.println("Before glue: [" + testURL + "] + [../javastyle.html]");
		System.out.println("After glue: [" + getURL + "]");
		System.out.println("");
		
		// Тест очистки мусора после указания формата
		testURL = "http://java.sun.com/javase/6/docs/api/java/lang/Boolean.html#parseBoolean(java.lang.String)";
		getURL = CrawlerHelper.cutTrashAfterFormat(testURL);
		System.out.println("Before cutting trash: [" + testURL + "]");
		System.out.println("After cutting trash: [" + getURL + "]");
		System.out.println("");
		
		// Тест определения принудительного перехода на https протокол
		testURL = "http://download.oracle.com/javase/tutorial/essential/concurrency/sync.html";
		System.out.println("What protocol inside? : [" + testURL + "]");
		System.out.println("True answer: [https], test answer: [" + CrawlerHelper.whatProtocolInside(testURL) + "]");
		System.out.println("");
		
	}
}