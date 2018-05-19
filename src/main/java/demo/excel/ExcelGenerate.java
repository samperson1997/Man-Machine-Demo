package demo.excel;

import demo.htmlparser.entity.ResultEntity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import demo.mysql.*;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelGenerate {

	public static void main(String[] args){
		ExcelGenerate eg = new ExcelGenerate();
		eg.excelGenerate();
		System.out.println("report generated!");
	}
	
	public void excelGenerate(){
		DatabaseUtil du = new DatabaseUtil();
		ArrayList<ResultEntity> list = du.getResultEntityFromDatabase();
		//create HSSFWorkbook object
		HSSFWorkbook wb = new HSSFWorkbook();
		//create HSSFSheet object
		HSSFSheet sheet = wb.createSheet("sheet0");
		//create HSSFRow object
		HSSFRow row0 = sheet.createRow(0);
		//set row0 value
		row0.createCell(0).setCellValue("ID");
		row0.createCell(1).setCellValue("subject");
		row0.createCell(2).setCellValue("tool");
		row0.createCell(3).setCellValue("time_budget");
		row0.createCell(4).setCellValue("coverage_score");
		row0.createCell(5).setCellValue("mutation_score");
		row0.createCell(6).setCellValue("total_score");
		row0.createCell(7).setCellValue("time_start");
		row0.createCell(8).setCellValue("time_end");

		//set ResultEntity value
		int count=1;
		for(ResultEntity re:list){
			HSSFRow row = sheet.createRow(count);
			row.createCell(0).setCellValue(re.getGroup_id());
			row.createCell(1).setCellValue(re.getSubject());
			row.createCell(2).setCellValue(re.getTool());
			row.createCell(3).setCellValue(re.getTime_budget());
			row.createCell(4).setCellValue(re.getBC());
			row.createCell(5).setCellValue(re.getMC());
			row.createCell(6).setCellValue(re.getTotal());
			row.createCell(7).setCellValue(re.getTime_start());
			row.createCell(8).setCellValue(re.getTime_end());
			count++;
		}
		
		//output Excel 
		try {
			String outputPath = "C:\\Users\\dlydd\\Desktop\\Senior\\ise\\human-machine\\report.xls";
			FileOutputStream output=new FileOutputStream(outputPath);
			wb.write(output);
			output.flush();
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
