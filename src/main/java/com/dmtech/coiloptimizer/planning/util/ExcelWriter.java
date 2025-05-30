package com.dmtech.coiloptimizer.planning.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dmtech.coiloptimizer.bean.OrderCoilMappingDetail;
import com.dmtech.coiloptimizer.bean.PlanOutputDetail;
import com.dmtech.coiloptimizer.bean.SlitSequenceDetail;

public class ExcelWriter {

	public void writeOutputPlan(String path, List<PlanOutputDetail<Integer>> outputResults) {

		// Using XSSF for xlsx format, for xls use HSSF
		Workbook workbook = new XSSFWorkbook();
		int rowIndex = 0;
		Sheet orderCoilMapping = workbook.createSheet("OrderCoilMapping");

		Row row = orderCoilMapping.createRow(rowIndex++);
		String[] headers = getOrderCoilMappingHearders();
		int cellIndex = 0;
		for (String str : headers) {
			row.createCell(cellIndex++).setCellValue(str);
		}

		for (PlanOutputDetail<Integer> outputDetail : outputResults) {
			List<OrderCoilMappingDetail<Integer>> orderCoilMappingDetails = outputDetail.getOrderCoilMappingDetails();
			for (OrderCoilMappingDetail orderCoilMappingDetail : orderCoilMappingDetails) {
				cellIndex = 0;
				row = orderCoilMapping.createRow(rowIndex++);
				row.createCell(cellIndex++).setCellValue(orderCoilMappingDetail.getGroupId());
				row.createCell(cellIndex++).setCellValue(orderCoilMappingDetail.getBatchNo());
				row.createCell(cellIndex++).setCellValue(orderCoilMappingDetail.getOrderNo());
				row.createCell(cellIndex++).setCellValue(orderCoilMappingDetail.getOrderLineItem());
				row.createCell(cellIndex++).setCellValue(orderCoilMappingDetail.getAllocatedOrderQty());
				row.createCell(cellIndex++).setCellValue(orderCoilMappingDetail.getCuttingPatternNumber());
				row.createCell(cellIndex++).setCellValue(orderCoilMappingDetail.getNoOfCuts());
				row.createCell(cellIndex++).setCellValue(orderCoilMappingDetail.getStockWeight());
				row.createCell(cellIndex++).setCellValue(orderCoilMappingDetail.getStockWidth());
				row.createCell(cellIndex++).setCellValue(orderCoilMappingDetail.getStockLength());
				row.createCell(cellIndex++).setCellValue(orderCoilMappingDetail.getOrderWidth());
				row.createCell(cellIndex++).setCellValue(orderCoilMappingDetail.getNoOfOrderWidth());
				row.createCell(cellIndex++).setCellValue(orderCoilMappingDetail.getProcessId());
				row.createCell(cellIndex++).setCellValue(orderCoilMappingDetail.getLengthWise());
				row.createCell(cellIndex++).setCellValue(orderCoilMappingDetail.getNoOfPatiton());
				row.createCell(cellIndex++).setCellValue(orderCoilMappingDetail.getPartionWeight());
				row.createCell(cellIndex++).setCellValue(orderCoilMappingDetail.getPartionLength());
			}
		}
		
		rowIndex = 0;
		Sheet slitSequence = workbook.createSheet("SlitSequence");
		row = slitSequence.createRow(rowIndex++);
		headers = getSlitSequenceHearders();
		cellIndex = 0;
		for (String str : headers) {
			row.createCell(cellIndex++).setCellValue(str);
		}

		for (PlanOutputDetail<Integer> outputDetail : outputResults) {
			List<SlitSequenceDetail<Integer>> slitSequenceDetails = outputDetail.getSlitSequenceDetails();
			for (SlitSequenceDetail<Integer> slitSequenceDetail : slitSequenceDetails) {
				cellIndex = 0;
				row = slitSequence.createRow(rowIndex++);
				row.createCell(cellIndex++).setCellValue(slitSequenceDetail.getBatchNo());
				row.createCell(cellIndex++).setCellValue(slitSequenceDetail.getPartIndex());
				row.createCell(cellIndex++).setCellValue(slitSequenceDetail.getWidth());
				row.createCell(cellIndex++).setCellValue(slitSequenceDetail.getOrderNo());
				row.createCell(cellIndex++).setCellValue(slitSequenceDetail.getOrderLineItem());
				row.createCell(cellIndex++).setCellValue(slitSequenceDetail.getCutType());
			}
		}
		// write this workbook in excel file.

		try {
			FileOutputStream fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();
			System.out.println(path + " is successfully written");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String[] getOrderCoilMappingHearders() {
		return new String[] { "GROUP_ID", "BATCH_NUMBER", "ORDER_NUMBER", "ORDER_LINE_NUMBER", "ALLOCATED_ORDER_QTY",
				"CUTTING_PATTERN_NUMBER", "NUMBER_CUTS", "STOCK_WEIGHT", "STOCK_WIDTH", "STOCK_LENGTH", "ORDER_WIDTH",
				"NUMBER_ORDER_WIDTH", "PROCESS_ID", "LENGTH_WISE", "NO_OF_PARTITION", "PARTING_WEIGHT", "PARTING_LENGTH" };
	}

	private String[] getSlitSequenceHearders() {
		return new String[] { "COIL_NUMBER", "PARTING_INDEX", "WIDTH", "ORDER_NUMBER", "ORDER_LINE_NUMBER",
				"CUT_TYPE" };
	}
}
