import java.lang.Exception;
import java.util.*;
import java.net.MalformedURLException;
import java.net.*;
import java.io.*;

public class Crawler {
	
	public static final int HTTP_PORT = 80;
	public static final String HOOK_REF = "<a href=\"";
	public static final String HOOK_REF2 = "<a href=\"http://";
	
	
	public static final String BAD_REQUEST_LINE = "HTTP/1.1 400 Bad Request";
	
	// ����� ��� ���஢���� ��� �����
	//public static final String testURL = "http://users.cms.caltech.edu/~donnie/cs11/java/";
	public static final String testURL = "http://users.cms.caltech.edu/~donnie/cs11/java/lectures/cs11-java-lec1.pdf";
	public static final int testDepth = 1;
	
	// ���᮪ ������� ᠩ⮢, � ��� ���������
	LinkedList<URLDepthPair> notVisitedList;
	LinkedList<URLDepthPair> visitedList;
	
	// ��㡨��
	int depth;
	
	
	// ���������
	public Crawler() {
		notVisitedList = new LinkedList<URLDepthPair>();
		visitedList = new LinkedList<URLDepthPair>();
	}
	
	
	// ��窠 �室�
	public static void main (String[] args) {
		
		Crawler crawler = new Crawler();
	
		//crawler.getFirstURLDepthPair(args);
		
		//crawler.startParse();
	
		crawler.testParse();
	}


	/*
	* ��室 �� �ᥬ ᠩ⠬ �� ��।����� ��㡨��
	*/
	public void startParse() {
		System.out.println("Stating parsing:\n");
		
		int nowDepth = 0;	
		
		while (!this.notVisitedList.isEmpty()) {
			URLDepthPair element = this.notVisitedList.getFirst();	
			nowDepth = element.getDepth();
			
			System.out.println("Now on " + element.toString() + " /" + this.depth);
			
			// �᫨ ��㡨�� ⥪�饣� ������� ࠢ�� ��������� ��ண�, � � �� 㦥 �� �㦭�
			// �᪠�� ��뫪�. 
			// ��� ᯨ᪠ ࠡ���� �� �ਭ樯� FIFO
			this.notVisitedList.removeFirst();
			if (nowDepth == depth) {
				this.visitedList.addLast(element);
				//this.notVisitedList.removeFirst();
				continue;
			}
			
			// �᫨ ����� �஢��� ��� �� �।�� - �஢���� ��� ���ᨭ�
			
			String host = element.getHostName();
			
			Socket socket = null;
			//OutputStreamReader stream = null;
			PrintWriter readStream = null;
			
			// ����⨥ ᮪��
			try {
				socket = new Socket(host, HTTP_PORT);
				System.out.println("Socket opened: " + host);
				System.out.println("");
			
			}
			catch (UnknownHostException e) {
				System.err.println("UnknownHostException: " + e.getMessage() + "\n");
				continue;
			}
			catch (IOException e) {
				e.printStackTrace();
				continue;
			}
			
			// ��⠭���� ⠩����
			try {
				socket.setSoTimeout(3000);
			}
			catch (SocketException exc) {
				System.err.println("SocketException: " + exc.getMessage());
				continue;
			}
			
		
			// �����⨥ ᮪��
			try {
				socket.close();
				System.out.println("Page had been closed\n");
			}
			catch (IOException e) {
				System.out.println("URL ERROR SOCKET CLOSING: " + host + "\n");
			}
			
		}
	}
	
	/*
	* ����� ��⠫ ��� �������, � ��� ����� �����஢���
	*/
	private void testParse() {
		
		//  ����ன�� ��砫쭮�� ����
		URLDepthPair pair;
		depth = testDepth;
		try {
			pair = new URLDepthPair(testURL, 0);
			notVisitedList.add(pair);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return;
		}
		catch (MalformedURLException e) {
			System.out.println(e.getMessage());
			return;
		}
		
		System.out.println("Start pair created!");
		
		// ��ନ஢���� ��ꥪ� URL �� ��ப�, ᮤ�ঠ饩 URL
		URL url = null;
		try {
			url = new URL(pair.getURL());
		}
		catch (MalformedURLException e) {
			System.err.println("MalformedURLException: " + e.getMessage());
			return;
		}
		
		System.out.println("URL formed");
		
		// ����⨥ ������祭��
		try {
			Socket socket = new Socket(url.getHost(), HTTP_PORT);	
			System.out.println("Connection to [ " + url + " ] created!");	

			CrawlerHelper.getInfoAboutUrl(url, true);
			
			// ��� ��ࠢ�� ����ᮢ �� �ࢥ�
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			// ��ࠢ�� ����� �� ����祭�� html-��࠭���
			out.println("GET " + url.getPath() + " HTTP/1.1");
			out.println("Host: " + url.getHost());
			out.println("Connection: close");
			out.println("");
			
			// ����祭�� �⢥�
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			// �஢�ઠ �� bad request
			String line = in.readLine();
			if (line.startsWith(BAD_REQUEST_LINE)) {
				System.out.println("ERROR: BAD REQUEST!");
				System.out.println(line + "\n");
			} else {
				System.out.println("REQUEST IS GOOD!\n");
			}
			
			// �⥭�� �᭮����� 䠩��
			System.out.println("---Start of file---");	
			//System.out.println(line);
			int strCount = 1;
			while(line != null) {
				try {
					
					/* 
					* �뢮� ⮫쪮 ��ப � ��뫪��� �� http ��࠭���
					* ��� �� �����࠭��� ������� ���, ����騥 http ��⮪��
					*/ 
					line = in.readLine();
					
					if (line.indexOf(HOOK_REF2) != -1) 
						System.out.println(strCount + " |  " + line);	
					else if (line.indexOf(HOOK_REF) != -1) 
					{	
						int indexStart = line.indexOf(HOOK_REF) + HOOK_REF.length();
						int indexEnd = line.indexOf("\"", indexStart);
						String subRef = line.substring(indexStart, indexEnd);
						
						// ����祭��� ��뫪�, ᪮॥ �ᥣ� �����⠫��, �㦭� ��ꥤ����� � �।��騬 ��⥬,
						// �८�ࠧ����� ����祭��� ��ப� � url, � �஢���� ��⮪��
						// ��� �� �㦭�, �� ���� �����⠫�� ���-⠪�, ����� ��⮪�� ������ ᮢ������
						String fullSubRef = url + subRef;
						URL newUrl = URLDepthPair.getUrlObjectFromUrlString(fullSubRef);
						String newUrlProtocol = newUrl.getProtocol();
						
						System.out.println(strCount + " |  " + line + " --> [" + subRef + "] --> " + indexStart + ", " + indexEnd);
						System.out.println("Full ref = " + newUrl.toString() + ", protocol = " + newUrlProtocol + "\n");
					}
					strCount += 1;
				}
				catch (Exception e) {
					break;
				}
			}
			if (strCount == 1) System.out.println("No http refs in this page!");
			System.out.println("---End of file---\n");	
		}
		catch (UnknownHostException e) {
            System.err.println("Don't know about host " + pair.getHostName());
			return;
		}
		catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + pair.getHostName());
            return;
        } 
	}
	
	/*
	* ����祭�� ᯨ᪠ ��ᬮ�७��� ��࠭��
	*/
	public LinkedList<URLDepthPair> getSites() {
		return this.visitedList;
	}
	

	/*
	* �஢�ઠ ��������� ��ப�, ����� ���짮��⥫� � ���������� ��ࢮ�� ��ꥪ� URLDepthPair
	* � ᯨ᮪ ����ᬮ�७���
	* �᫨ ��� ����� �� ��������� ��ப� ��।����� ���� null
	*/
	public void getFirstURLDepthPair(String[] args) {
		
		CrawlerHelper help = new CrawlerHelper();
		
		// �⥭�� ��㬥�⮢ �� ��������� ��ப�
		URLDepthPair urlDepth = help.getURLDepthPairFromArgs(args);
		if (urlDepth == null) {
			System.out.println("Args are empty or have exception. Now you need to enter URL and depth manually!\n");
			
			// ����祭�� ����� �� ���짮��⥫��
			urlDepth = help.getURLDepthPairFromInput();
		}
		
		// ����祭�� � ������ ��㡨��
		this.depth = urlDepth.getDepth();
		urlDepth.setDepth(0);
		
		// ����ᥭ�� � ᯨ᮪
		notVisitedList.add(urlDepth);
		
		// �뢮� ��ࢮ�� ��ꥪ� URLDepthPair
		System.out.println("First site: " + urlDepth.toString() + "\n");
		
		
	}
}