package demo.htmlparser.parser;


import demo.htmlparser.entity.ResultEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import demo.mysql.*;
import demo.htmlparser.main.*;

/**
 * Html parser.
 * @author DingDuan
 *
 * 
 */
public class HtmlParser {

	/**
	 * The entry of parse.
	 * @param rootDirectoryPath
	 * 
	 * 
	 */
	public static void parser(String rootDirectoryPath) {
		List<ResultEntity> resultEntityList = getInfoFromHtml(rootDirectoryPath);
		if (resultEntityList != null) {
			for (ResultEntity resultEntity : resultEntityList)
			System.err.println(resultEntity.toString());
			DatabaseUtil.writeResultEntityListToDatabase(resultEntityList);
		}
	}

	/**
	 * Get all resultEntitys from html.
	 * @param rootDirectoryPath
	 * @return
	 * 
	 * 
	 */
	private static List<ResultEntity> getInfoFromHtml(String rootDirectoryPath)  {
		File[] groupDirectoryArray = FileUtil.traverseRootDirectory(rootDirectoryPath);
		if (groupDirectoryArray == null) {
			return null;
		}
		for (File groupDirectory : groupDirectoryArray) {
			if (groupDirectory.isHidden()) {
				continue ;
			}
			File[] taskDirectoryArray = FileUtil.traverseGroupDirectory(groupDirectory);
			if (taskDirectoryArray == null) {
				return null;
			}
			List<ResultEntity> resultEntityList = new ArrayList<ResultEntity>(taskDirectoryArray.length);
			for (File taskDirectory : taskDirectoryArray) {
				if (taskDirectory.getAbsolutePath().contains(".DS_Store")) {
					continue;
				}
				String taskDirectoryPath = taskDirectory.getAbsolutePath();
					//int dourpId = FileUtil.getGroupIdFromPath(taskDirectoryPath);
					String lastDirctoryName = FileUtil.getLastDirByPath(taskDirectoryPath);
					String[] array = lastDirctoryName.split("_");
					ResultEntity resultEntity = new ResultEntity();
					//resultEntity.setGroup_id(dourpId);
					resultEntity.setSubject(array[0]);
					resultEntity.setTime_budget(Integer.parseInt(array[2]));
					resultEntity.setTool(array[1]);
					setResultEntityCoveragePercentage(taskDirectoryPath, resultEntity);
					resultEntityList.add(resultEntity);
				
			}
			return resultEntityList;
		}
		return null;
	}
	
	/**
	 * Set resultEntity's BC, MC and total value.
	 * @param resultEntity
	 * @param jacocoHtmlFilePath
	 * @param pitestHtmlFilePath
	 * 
	 *
	 */
	private static void setResultEntityCoveragePercentage(String taskDirectoryPath, ResultEntity resultEntity) {
		String jacocoHtmlFilePath = taskDirectoryPath + File.separator + "JaCoCo" + File.separator + "index.html";
		String pitestHtmlFilePath = taskDirectoryPath + File.separator + "PITest" + File.separator + "index.html";
		double branchCoveragePercentage = HtmlParser.parseJacocoHtml(jacocoHtmlFilePath);
		resultEntity.setBC(branchCoveragePercentage);
		double mutationCoveragePercentage = HtmlParser.parsePitestHtml(pitestHtmlFilePath);
		resultEntity.setMC(mutationCoveragePercentage);
		double totalScore = branchCoveragePercentage * 0.5 + mutationCoveragePercentage * 0.5;
		resultEntity.setTotal(totalScore);
	}
	
	/**
	 * Parse jacoco html.
	 * @param htmlFilePath
	 * @return
	 * 
	 * 
	 */
	private static double parseJacocoHtml(String htmlFilePath) {
		String htmlContent = FileUtil.readHtmlFile(htmlFilePath);
		double coveragePercentage = extractBranchCoveragePercentageFromJacocoHtmlContent(htmlContent);
		return coveragePercentage;
	}

	/**
	 * Parse pitest html.
	 * @param htmlFilePath
	 * @return
	 * 
	 * 
	 */
	private static double parsePitestHtml(String htmlFilePath) {
		String htmlContent = FileUtil.readHtmlFile(htmlFilePath);
		double coveragePercentage = extractMutationCoveragePercentageFromPitestHtmlContent(htmlContent);
		return coveragePercentage;
	}
	
	/**
	 * Extract branch coverage percentage from jacoco html content.
	 * @param htmlContent
	 * @return
	 * 
	 * 
	 */
	private static double extractBranchCoveragePercentageFromJacocoHtmlContent(String htmlContent) {
		String regex = "(<tr><td>Total)(.*?)(</td></tr>)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(htmlContent);
		String branchCoveragePercentageTr = "";
		while (matcher.find()) {
			branchCoveragePercentageTr = matcher.group();
		}
		String regex1 = "(<td class=\"ctr2\">)(.*?)(%)(.*?)(</td>)";
		pattern = Pattern.compile(regex1);
	    matcher = pattern.matcher(branchCoveragePercentageTr);
	    String branchCoveragePercentageTd = "";
		int i = 0;
		while (matcher.find()) {
			i++;
			if (i == 2) {
				branchCoveragePercentageTd = matcher.group();
			}
		}
		String td = branchCoveragePercentageTd.replaceAll("class=\"ctr2\"", "");
		String regex2 = "[0-9]";
		pattern = Pattern.compile(regex2);
	    matcher = pattern.matcher(td);
	    StringBuffer buffer = new StringBuffer();
		while (matcher.find()) {
			buffer.append(matcher.group());
		}
		String mutationCoveragePercentageString = buffer.toString().trim();
		double mutationCoveragePercentage = Double.parseDouble(mutationCoveragePercentageString);
		return mutationCoveragePercentage;
	}
	
	/**
	 * Extract mutation coverage percentage from pitest html content.
	 * @param htmlContent
	 * @return
	 * 
	 * 
	 */
	private static double extractMutationCoveragePercentageFromPitestHtmlContent (String htmlContent) {
		String regex = "(<td>)(.*?)(class=\"coveragePercentage\")(.*?)(</td>)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(htmlContent);
		int i = 0;
		String mutationCoveragePercentageTd = "";
		while (matcher.find()) {
			i++;
			if (i == 2) {
				mutationCoveragePercentageTd = matcher.group();
			}
		}
		String regex1 = "(<div class=\"coveragePercentage\">)(.*?)(</div>)";
		pattern = Pattern.compile(regex1);
	    matcher = pattern.matcher(mutationCoveragePercentageTd);
	    String mutationCoveragePercentageDiv = "";
		while (matcher.find()) {
			mutationCoveragePercentageDiv = matcher.group();
		}
		String regex2 = "[0-9]";
		pattern = Pattern.compile(regex2);
	    matcher = pattern.matcher(mutationCoveragePercentageDiv);
	    StringBuffer buffer = new StringBuffer();
		while (matcher.find()) {
			buffer.append(matcher.group());
		}
		String mutationCoveragePercentageString = buffer.toString().trim();
		double mutationCoveragePercentage = Double.parseDouble(mutationCoveragePercentageString);
		return mutationCoveragePercentage;
	}
}
