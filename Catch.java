import java.io.*;
import java.util.*;
import java.net.*; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Catch {
	private final String WordHead = "http://dict.youdao.com/search?q=";
	private final String VoiceHead = "http://dict.youdao.com/dictvoice?audio=";
	private final String audioPath = "./audio/";
	private final String bookPath = "./book/";
	//private final String StopPoint = "<script type=\"text/javascript\" charset=\"utf-8\">";
	
	private static final String ECODING = "UTF-8";
	
	Catch(){
		
	}
	
	public void loadBook(Database D, String lname, String bookname) throws IOException{
		bookname = "CET4";
		FileInputStream book = new FileInputStream(new String(bookPath + bookname + ".txt"));
		BufferedReader breader = new BufferedReader(new InputStreamReader(book,"UNICODE" ));//
		String tempString;
		while ((tempString = breader.readLine()) != null) {
			Pattern p = Pattern.compile("/");		
			String[] word = p.split(tempString, 2);
			//System.out.println(word[0]);
			if(word[0].length() < 2) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				continue;
			}
			Word tmp = trWord(word[0]);
			D.addNewEntry(lname, tmp);
			/*System.out.println(tmp.pron);
			System.out.println(tmp.mean);
			System.out.println(tmp.expre);
			System.out.println(tmp.synonym);*/
        }
        breader.close();
		
	}
	
	public Word trWord(String name){
		String wordURL = WordHead + name;
		String content = getContentFromUrl(wordURL);
		Pattern pSplit = Pattern.compile("<!--(.*?)[^-]*?-->");		
		String[] contentPart = pSplit.split(content);
		//Word tmp = new Word();
		String pron = getPron(contentPart[5]);
		String mean = getMean(contentPart[5]);
		String expre = "", synonym = "";
		int i = 16, n = contentPart.length;
		if (n>16){
			do{
				expre = getExpre(contentPart[i]);
				i++;
			}while(expre.length() <= 5 && i< n);
			i = 16;
			do{
				synonym = getSyno(contentPart[i]);
				i++;
			}while(synonym.length() <= 5 && i< n);
		}
		//System.out.println("trWord: before pron="+pron);
		//int tmpx=pron.lastIndexOf("/");
		//pron=pron.substring(1, tmpx);
		pron=pron.replace("\'","\'\'");
		expre=expre.replace("\'","\'\'");
		//System.out.println("trWord: pron="+pron);
		Download(VoiceHead + name);
		Word tmp = new Word(name, pron, mean, expre, synonym, new Date(), -1);
		return tmp;
	}
	
	String getPron(String content){
		String patternString = "<span class=\"phonetic\">(.*?)</span>";
		Pattern pattern = Pattern.compile(patternString);//, Pattern.LITERAL);
		Matcher matcher = pattern.matcher( content );
		StringBuffer buffer = new StringBuffer();
		if (matcher.find()) {
			buffer.append(matcher.group(1));
			//buffer.append("\n");
		}
		return buffer.toString();
	}
	
	String getMean(String content){
		String patternString = "<li>(.*?)</li>";
		Pattern pattern = Pattern.compile(patternString);//, Pattern.LITERAL);
		Matcher matcher = pattern.matcher( content );
		StringBuffer buffer = new StringBuffer();
		if (matcher.find()) {
			buffer.append(matcher.group(1));
			//buffer.append("\n");
		}
		return buffer.toString();
	}
	
	String getExpre(String content){
		String patternString = "<div class=\"examples\">(.*?)[^<]*?<p>(.*?)</p>(.*?)[^<]*?<p>(.*?)</p>";
		Pattern pattern = Pattern.compile(patternString , Pattern.MULTILINE);//, Pattern.LITERAL);
		Matcher matcher = pattern.matcher( content );
		StringBuffer buffer = new StringBuffer();
		if (matcher.find()) {
			buffer.append(matcher.group(2));
			//buffer.append(" ");
			//buffer.append(matcher.group(4));
			//buffer.append("\n");
		}
		return buffer.toString();
	}
	
	String getSyno(String content){
		//String patternString = "<p class=\"gray\">((.*?)[^<]*?)<a((.*?)[^>]*?)>((.*?)[^<]*?)</a>";
		//String patternString = "<div id=\"synonyms\"((.*?)[^<]*?)<ul>(.*?)<li>((.*?)[^<]*?)</li>(.*?)"
				//+ "<p class=\"wordGroup\">(.*?)<span class=\"contentTitle\">(.*?)<a((.*?)[^>]*?)>((.*?)[^<]*?)</a>";
		String patternString = "<div id=\"synonyms\"[\\s\\S]*?</div>";
		Pattern pattern = Pattern.compile(patternString, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher( content );
		StringBuffer buffer = new StringBuffer();
		if(matcher.find()) {
			buffer.append(matcher.group());
		}	
		patternString = "<a[^>]*?>([^<]*?)</a>";
		pattern = Pattern.compile(patternString, Pattern.MULTILINE);
		matcher = pattern.matcher( buffer.toString() );
		buffer = new StringBuffer();
		if(matcher.find()) {
			buffer.append(matcher.group(1));
			buffer.append("    ");
		}
		return buffer.toString();
	}
	
	
	public String getContentFromUrl( String strUrl )
	{
		try { 
			URL url = new URL(strUrl);  
			InputStream stream = url.openStream();
			String content = readAll( stream, ECODING ); //甯歌鐨勭紪鐮佸寘鎷� GB2312, UTF-8
			return content;

		}catch ( MalformedURLException e) { 
			System.out.println("URL鏍煎紡鏈夐敊" ); 
		}catch (IOException ioe) {
			System.out.println("IO寮傚父" ); 
		}
		return "";
	}
	
	public String readAll( InputStream stream, String charcode ) throws IOException{
		BufferedReader reader = new BufferedReader(
			new InputStreamReader(stream, charcode)); 
		StringBuilder sb = new StringBuilder();
		String line; 
		while ((line = reader.readLine()) != null) { 
			//GetHref(line);
			sb.append(line+"\n"); 
		} 
		return sb.toString();
	}
	
	private void Download(String url) {
		try {
				String voiceName = audioPath + url.substring(url.lastIndexOf("=") + 1, url.length()) + ".mp3";
				URL uri = new URL(url);
				InputStream in = uri.openStream();
				FileOutputStream fo = new FileOutputStream(new File(voiceName));
				byte[] buf = new byte[1024];
				int length = 0;
				//System.out.println("寮�濮嬩笅杞�:" + url);
				while ((length = in.read(buf, 0, buf.length)) != -1) {
					fo.write(buf, 0, length);
				}
				in.close();
				fo.close();
				//System.out.println(voiceName + "涓嬭浇瀹屾垚");
		} catch (Exception e) {
			System.out.println("涓嬭浇澶辫触");
		}
	}
	
}
