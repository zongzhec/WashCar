package foo.zongzhe.wash_car;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import foo.zongzhe.utils.LogUtil;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class WashCar {
	public static final String SAMPLE_XLSX_FILE_PATH = "H:/2017weather.xlsx";

	public static void main(String[] args) throws IOException, InvalidFormatException {

		// WashCar obj = new WashCar();
		LogUtil log = new LogUtil();
		WashEntity[] washes = new WashEntity[365];

		// initialize
		for (int i = 0; i < washes.length; i++) {
			washes[i] = new WashEntity();
			washes[i].setDate("");
			washes[i].setWeather("");
			washes[i].setWash("不洗");
		}

		Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

		// Iterator<Sheet> sheetIterator = workbook.sheetIterator();
		log.info("正在读取 2017 年天气...");
		System.out.println();

		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(0);

		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		// 1. You can obtain a rowIterator and columnIterator and iterate over them
		// System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
		Iterator<Row> rowIterator = sheet.rowIterator();
		int rowNum = 0;
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			// Now let's iterate over the columns of the current row
			Iterator<Cell> cellIterator = row.cellIterator();
			washes[rowNum].setDate(sheet.getRow(rowNum).getCell(0).toString());
			String currentWeather = sheet.getRow(rowNum).getCell(1).toString();
			if (currentWeather.contains("阵雨")) {
				if (0 == rowNum % 2) {
					currentWeather = "下雨";
				} else {
					currentWeather = "不下雨";
				}
			} else if (currentWeather.contains("雨")) {
				currentWeather = "下雨";
			} else {
				currentWeather = "不下雨";
			}
			washes[rowNum].setWeather(currentWeather);
			rowNum++;

		}
		washes[0].setWash("洗车");
		log.info("正在决定2017年洗车计划...\n");
		int i = 7;
		while (i < washes.length) {
//			System.out.println(i);
			if (washes[i - 1].getWeather().equals("不下雨") && washes[i - 2].getWeather().equals("不下雨")
					&& washes[i - 3].getWeather().equals("不下雨")) {
				washes[i].setWash("洗车");
				i += 7;
			} else {
				i++;
			}
		}
		
		log.info("正在查看哪次洗车后2天内下雨...\n");

		for (int j = 0; j < washes.length-2; j++) {
			if (washes[j].getWash().equals("洗车") && (washes[j+1].getWeather().equals("下雨") || washes[j+2].getWeather().equals("下雨") || washes[j+3].getWeather().equals("下雨"))) {
				System.out.println(
						String.format("%s %s, 两天内下雨了……mmp", washes[j].getDate(), washes[j].getWash()));	
			}
			

		}

		// Closing the workbook
		workbook.close();
	}

}