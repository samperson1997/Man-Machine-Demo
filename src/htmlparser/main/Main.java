package htmlparser.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import htmlparser.parser.HtmlParser;

/**
 * Main Class.
 * The entry of procedure.
 * @author DingDuan
 *
 * 
 */
public class Main {
	
	
//	public static void testParse() {
//		String pitestHtmlFilePath = "/Users/sunweisong/Downloads/index-pitest.html";
//		String jacocoHtmlFilePath = "/Users/sunweisong/Downloads/index-jacoco.html";
//		double branchCoveragePercentage = HtmlParser.parseJacocoHtml(jacocoHtmlFilePath);
//		System.out.println(branchCoveragePercentage);
//		double mutationCoveragePercentage = HtmlParser.parsePitestHtml(pitestHtmlFilePath);
//		System.out.println(mutationCoveragePercentage);
//	}
	
	public static void main(String[] args) {
		String rootDirectoryPath = "C:\\Users\\dlydd\\Desktop\\Senior\\ise\\human-machine\\human-machine-data";
		HtmlParser.parser(rootDirectoryPath);
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//   	 	String date=df.format(new Date());
//   	 	System.out.println(date);
	}

}
