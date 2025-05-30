package com.dmtech.coiloptimizer.planning.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public LinkedHashMap<Integer, LinkedHashMap<String, Object>> getDateFromFile(String dataFilePath,
			String sheetName) {
		try {

			LinkedHashMap<Integer, LinkedHashMap<String, Object>> datas = new LinkedHashMap<Integer, LinkedHashMap<String, Object>>();
			FileInputStream file = new FileInputStream(new File(dataFilePath));
			// Get the workbook instance for XLS file
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			// Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);
			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			int cnt = 0;
			Map<Integer, String> header = new LinkedHashMap<Integer, String>();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
				if (cnt == 1) {
					Iterator<Cell> cellIterator = row.cellIterator();
					int cellCnt = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						String cellValue = cell.getStringCellValue();
						header.put(cellCnt, cellValue);
						cellCnt = cellCnt + 1;
					}
				}

				if (cnt >= 2) {
					Iterator<Cell> cellIterator = row.cellIterator();
					int cellCnt = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						String cellValue = null;
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_BOOLEAN:
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								cellValue = String.valueOf(cell.getDateCellValue());
							} else {
								cellValue = String.valueOf(cell.getNumericCellValue());
							}
							break;
						case Cell.CELL_TYPE_STRING:
							cellValue = cell.getStringCellValue();
							break;

						}

						if (cellValue != null) {
							cellValue = cellValue.trim();
						}

						data.put(header.get(cellCnt), cellValue);

						cellCnt = cellCnt + 1;
					}
					datas.put(cnt, data);
				}
				cnt = cnt + 1;
			}
			return datas;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
