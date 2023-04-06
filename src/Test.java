import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Test {
	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("https://www.google.com/search?q=청량리&tbm=isch").get();
		Elements e1 = doc.getElementsByClass("rg_i Q4LuWd");
//		System.out.println(e1.size());
		Element e = e1.get(0);
//		System.out.println(e.attributes());
		Attributes atts = e.attributes();
		for(Attribute att : atts) {
			System.out.println(att);
		}
//		System.out.println(s.toString());
//		for (int i = 0; i < e1.size(); i++) {
//			System.out.println(e1.get(i));
//			System.out.println();
//		}
		
	}
}

