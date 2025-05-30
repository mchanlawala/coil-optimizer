package com.dmtech.coiloptimizer.planning.simulator;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dmtech.coiloptimizer.bean.CollectionUtil;
import com.dmtech.coiloptimizer.bean.Group;
import com.dmtech.coiloptimizer.bean.GroupIterator;
import com.dmtech.coiloptimizer.bean.GroupingStrategy;
import com.dmtech.coiloptimizer.bean.Material;
import com.dmtech.coiloptimizer.bean.Order;
import com.dmtech.coiloptimizer.bean.ParameterGroup;
import com.dmtech.coiloptimizer.bean.PlanCoilDetail;
import com.dmtech.coiloptimizer.bean.PlanDetail;
import com.dmtech.coiloptimizer.bean.PlanOrderDetail;
import com.dmtech.coiloptimizer.bean.PlanOutputDetail;
import com.dmtech.coiloptimizer.bean.PlanParameterDetail;
import com.dmtech.coiloptimizer.grouping.strategy.simple.SimpleGroupingStrategy;
import com.dmtech.coiloptimizer.planning.OptimizationEngineImpl;
import com.dmtech.coiloptimizer.planning.OptimizationService;
import com.dmtech.coiloptimizer.planning.util.ExcelReader;
import com.dmtech.coiloptimizer.planning.util.ExcelWriter;

public class Main {

	public static void main(String[] args) {
		System.out.println(System.getProperty("java.library.path"));
		GroupingStrategy strategy = new SimpleGroupingStrategy();
		System.out.println(System.getProperty("user.dir"));
		final String basePath = System.getProperty("user.dir") + "\\excel\\";
		System.out.println(basePath);
		ExcelReader excelReader = new ExcelReader();
		final LinkedHashMap<Integer, LinkedHashMap<String, Object>> planningDataFrmExcel = excelReader
				.getDateFromFile(basePath + "CoilCombiner_Input.xlsx", "Planning Parameter");

		LinkedHashMap<Integer, LinkedHashMap<String, Object>> materialDataFrmExcel = excelReader
				.getDateFromFile(basePath + "CoilCombiner_Input.xlsx", "Material");
		List<Material> materials = getMaterialDataFromExcel(materialDataFrmExcel);

		LinkedHashMap<Integer, LinkedHashMap<String, Object>> orderDataFrmExcel = excelReader
				.getDateFromFile(basePath + "CoilCombiner_Input.xlsx", "Order");
		List<Order> orders = getOrderDataFromExcel(orderDataFrmExcel);

		final LinkedHashMap<Integer, LinkedHashMap<String, Object>> cpocontrainDataFrmExcel = excelReader
				.getDateFromFile(basePath + "CoilCombiner_Input.xlsx", "CPO Constraint");

		GroupIterator iterator = strategy.group(orders, materials,
				(int) Double.parseDouble(planningDataFrmExcel.get(2).get("SCENARIO_TYPE").toString()),
				(int) Double.parseDouble(planningDataFrmExcel.get(2).get("MULTIPARTING").toString()));

		CollectionUtil.each(iterator, new CollectionUtil.IIterate<List<Group>>() {

			@Override
			public void process(List<Group> groups) {
				List<PlanDetail> planParameters = new LinkedList<PlanDetail>();
				int groupId = 1;
				List<PlanOutputDetail<Integer>> planOutputDetails = new LinkedList<PlanOutputDetail<Integer>>();
				for (Group group : groups) {
					List<PlanParameterDetail> parameterDetails = getPlanningParameter(groupId, group.getGroupParams(),
							cpocontrainDataFrmExcel);
					PlanDetail planDetail = new PlanDetail(planningDataFrmExcel.get(2).get("PLAN_ID").toString(),
							(int) Double.parseDouble(planningDataFrmExcel.get(2).get("SCENARIO_TYPE").toString()),
							Double.parseDouble(planningDataFrmExcel.get(2).get("LAST_PART_WEIGHT").toString()),
							Double.parseDouble(planningDataFrmExcel.get(2).get("MAX_ADDITIONAL_TRIM").toString()),
							(int) Double.parseDouble(planningDataFrmExcel.get(2).get("MULTIPARTING").toString()),
							Double.parseDouble(planningDataFrmExcel.get(2).get("MAX_SIDE_TRIM").toString()),
							(int) Double.parseDouble(planningDataFrmExcel.get(2).get("MAX_CUTTING").toString()));
					planDetail.setCoilDetail(getCoilDetails(groupId, group.getMaterials()));
					planDetail.setOrderDetail(getOrderDetail(groupId, group.getOrders()));
					planDetail.setParameterDetail(parameterDetails);
					planParameters.add(planDetail);
					OptimizationService service = new OptimizationEngineImpl();
					planOutputDetails.add(service.callEngine(planDetail));
					groupId++;
				}
				// setStockWeight(planOutputDetails);
				System.out.println("Result : " + planOutputDetails);
				ExcelWriter excelWriter = new ExcelWriter();
				excelWriter.writeOutputPlan(basePath + "\\CoilCombiner_Output.xlsx", planOutputDetails);
			}

			private void setStockWeight(List<PlanOutputDetail<Integer>> planOutputDetails) {
				/*
				 * Map<String, Double> stockWeights = new HashMap<String,
				 * Double>(); Map<String, Double> batchPartWeight = new
				 * HashMap<String, Double>(); for (int i = 0; i <
				 * planOutputDetails.size(); i++) { for (int j = 0; j <
				 * planOutputDetails.get(i).getOrderCoilMappingDetails().size();
				 * j++) { if
				 * (!batchPartWeight.containsKey(planOutputDetails.get(i).
				 * getOrderCoilMappingDetails().get(j) .getBatchNo() + "_" +
				 * planOutputDetails.get(i).getOrderCoilMappingDetails().get(j).
				 * getNoOfPatiton())) { batchPartWeight.put(
				 * planOutputDetails.get(i).getOrderCoilMappingDetails().get(j).
				 * getBatchNo() + "_" +
				 * planOutputDetails.get(i).getOrderCoilMappingDetails().get(j)
				 * .getNoOfPatiton(),
				 * planOutputDetails.get(i).getOrderCoilMappingDetails().get(j).
				 * getPartionWeight()); if (stockWeights.containsKey(
				 * planOutputDetails.get(i).getOrderCoilMappingDetails().get(j).
				 * getBatchNo())) { stockWeights.put(
				 * planOutputDetails.get(i).getOrderCoilMappingDetails().get(j).
				 * getBatchNo(),
				 * +planOutputDetails.get(i).getOrderCoilMappingDetails().get(j)
				 * .getPartionWeight()); } else { stockWeights.put(
				 * planOutputDetails.get(i).getOrderCoilMappingDetails().get(j).
				 * getBatchNo(),
				 * planOutputDetails.get(i).getOrderCoilMappingDetails().get(j)
				 * .getPartionWeight()); } }
				 * 
				 * } }
				 * 
				 * for (String batchNumber : stockWeights.keySet()) { for (int i
				 * = 0; i < planOutputDetails.size(); i++) { for (int j = 0; j <
				 * planOutputDetails.get(i).getOrderCoilMappingDetails().size();
				 * j++) { if
				 * (planOutputDetails.get(i).getOrderCoilMappingDetails().get(j)
				 * .getBatchNo() == batchNumber) {
				 * planOutputDetails.get(i).getOrderCoilMappingDetails().get(j)
				 * .setStockWeight(stockWeights.get(batchNumber) +
				 * Double.parseDouble(
				 * planningDataFrmExcel.get(2).get("LAST_PART_WEIGHT").toString(
				 * ))); } } } }
				 */
			}

		});

	}

	private static List<Order> getOrderDataFromExcel(
			LinkedHashMap<Integer, LinkedHashMap<String, Object>> orderDataFrmExcel) {
		List<Order> orders = new LinkedList<Order>();
		for (Integer cnt : orderDataFrmExcel.keySet()) {
			try {
				LinkedHashMap<String, Object> data = orderDataFrmExcel.get(cnt);
				Order order = new Order();
				// order.setBucketId((int)
				// Double.parseDouble(data.get("").toString()));
				// order.setDate(new Date(data.get("ORDER_DATE").toString()));
				order.setFgMaxWt(Double.parseDouble(data.get("FG_MAX_WEIGHT").toString()));
				order.setFgMinWt(Double.parseDouble(data.get("FG_MIN_WEIGHT").toString()));
				order.setFgMaxLength(Double.parseDouble(data.get("FG_MAX_LENGTH").toString()));
				order.setFgMinLength(Double.parseDouble(data.get("FG_MIN_LENGTH").toString()));
				order.setGrade(data.get("GRADE").toString());
				// order.setOrderDeliveryDate(new
				// Date(data.get("DELIVERY_DATE").toString()));
				order.setOrderLength(Double.parseDouble(data.get("LENGTH").toString()));
				order.setOrderlengthMax(Double.parseDouble(data.get("MAX_LENGTH").toString()));
				order.setOrderlengthMin(Double.parseDouble(data.get("MIN_LENGTH").toString()));
				order.setOrderLineItem(data.get("ORDER_LINE_NUMBER").toString());
				order.setOrderNo(data.get("ORDER_NUMBER").toString());
				order.setOrderType(data.get("ORDER_TYPE").toString());
				order.setProductFamily(data.get("PRODUCT_FAMILY").toString());
				order.setThickness(Double.parseDouble(data.get("THICKNESS").toString()));
				order.setWeight(Double.parseDouble(data.get("WEIGHT").toString()));
				order.setWeightMax(Double.parseDouble(data.get("MAX_WEIGHT").toString()));
				order.setWeightMin(Double.parseDouble(data.get("MIN_WEIGHT").toString()));
				order.setWidth(Double.parseDouble(data.get("WIDTH").toString()));
				orders.add(order);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return orders;
	}

	private static List<Material> getMaterialDataFromExcel(
			LinkedHashMap<Integer, LinkedHashMap<String, Object>> materialDataFrmExcel) {
		List<Material> materials = new LinkedList<Material>();
		for (Integer cnt : materialDataFrmExcel.keySet()) {
			try {
				LinkedHashMap<String, Object> data = materialDataFrmExcel.get(cnt);
				Material material = new Material();
				material.setAge((int) Double.parseDouble(data.get("AGE").toString()));
				material.setBatchNo(data.get("BATCH_NUMBER").toString());
				material.setColiStatus(data.get("COIL_STATUS").toString());
				// material.setDate(new Date(data.get("DATE").toString()));
				material.setGrade(data.get("GRADE").toString());
				material.setLength(Double.parseDouble(data.get("LENGTH").toString()));
				material.setMotherBatchNo(data.get("MOTHER_BATCH_NUMBER").toString());
				material.setParentBatchNo(data.get("PARENT_BATCH_NUMBER").toString());
				material.setProductFamily(data.get("PRODUCT_FAMILY").toString());
				material.setStockType(data.get("STOCK_TYPE").toString());
				material.setThickness(Double.parseDouble(data.get("THICKNESS").toString()));
				material.setWeight(Double.parseDouble(data.get("WEIGHT").toString()));
				material.setWidth(Double.parseDouble(data.get("WIDTH").toString()));
				materials.add(material);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return materials;
	}

	private static List<PlanParameterDetail> getPlanningParameter(int groupId, ParameterGroup groupParams,
			LinkedHashMap<Integer, LinkedHashMap<String, Object>> cpocontrainDataFrmExcel) {
		List<PlanParameterDetail> parameterDetails = new LinkedList<PlanParameterDetail>();
		for (Integer cnt : cpocontrainDataFrmExcel.keySet()) {
			try {
				LinkedHashMap<String, Object> data = cpocontrainDataFrmExcel.get(cnt);
				if (groupParams.getGrade().equalsIgnoreCase(data.get("GRADE").toString())
						&& groupParams.getProductFamily().equalsIgnoreCase(data.get("PRODUCT_FAMILY").toString())
						&& groupParams.getThickness() == Double.parseDouble(data.get("THICKNESS").toString())) {
					PlanParameterDetail parameterDetail = new PlanParameterDetail();
					parameterDetail.setGrade(data.get("GRADE").toString());
					parameterDetail.setGroupId(groupId);
					// parameterDetail.setMaxBucketNumber();
					parameterDetail.setMaxCoilWeight(Double.parseDouble(data.get("MAX_WEIGHT").toString()));
					parameterDetail.setMaxDensity(Double.parseDouble(data.get("DENSITY").toString()));
					// parameterDetail.setMaxEndPiece();
					// parameterDetail.setMaxEndTrim();
					// parameterDetail.setMaxNoOfCut();
					parameterDetail.setMaxWidth(Double.parseDouble(data.get("MAX_WIDTH").toString()));
					parameterDetail.setMinCoilWeight(Double.parseDouble(data.get("MIN_WEIGHT").toString()));
					parameterDetail.setMinWidth(Double.parseDouble(data.get("MIN_WIDTH").toString()));
					// parameterDetail.setOptimumWidth();
					parameterDetail.setProductFamily(data.get("PRODUCT_FAMILY").toString());
					parameterDetail.setThickness(Double.parseDouble(data.get("THICKNESS").toString()));
					parameterDetail.setMinLength(Double.parseDouble(data.get("MIN_LENGTH").toString()));
					parameterDetail.setMaxLength(Double.parseDouble(data.get("MAX_LENGTH").toString()));
					parameterDetail.setMinFGLength(Double.parseDouble(data.get("MIN_FG_LENGTH").toString()));
					parameterDetail.setMaxFGLength(Double.parseDouble(data.get("MAX_FG_LENGTH").toString()));
					parameterDetails.add(parameterDetail);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return parameterDetails;
	}

	private static List<PlanParameterDetail> getPlanDetails(int groupId, ParameterGroup groupParams,
			List<PlanParameterDetail> parameterDetails) {
		List<PlanParameterDetail> details = new LinkedList<PlanParameterDetail>();
		for (PlanParameterDetail parameterDetail : parameterDetails) {
			if (parameterDetail.getGrade().equalsIgnoreCase(groupParams.getGrade())
					&& parameterDetail.getThickness() == groupParams.getThickness()
					&& parameterDetail.getProductFamily().equalsIgnoreCase(groupParams.getProductFamily())) {
				details.add(parameterDetail);
			}
		}
		return details;
	}

	private static List<PlanOrderDetail> getOrderDetail(int groupId, List<Order> orders) {
		List<PlanOrderDetail> orderDetails = new LinkedList<PlanOrderDetail>();
		for (Order order : orders) {
			PlanOrderDetail orderDetail = new PlanOrderDetail();
			orderDetail.setBucketId(order.getBucketId());
			orderDetail.setDeliveryDate(order.getOrderDeliveryDate());
			orderDetail.setGroupId(groupId);
			orderDetail.setMaxCoilWeight(order.getFgMaxWt());
			orderDetail.setMinCoilWeight(order.getFgMinWt());
			orderDetail.setMaxCoilLength(order.getFgMaxLength());
			orderDetail.setMinCoilLength(order.getFgMinLength());
			orderDetail.setOrderThickness(order.getThickness());
			orderDetail.setMaxThickness(order.getThickness());
			orderDetail.setMinThickness(order.getThickness());
			orderDetail.setOrderIndex(order.getOrderNo());
			orderDetail.setOrderLength(order.getOrderLength());
			orderDetail.setOrderLineNumber(order.getOrderLineItem());
			orderDetail.setOrderMaxQuantity(order.getWeightMax());
			orderDetail.setOrderMinQuantity(order.getWeightMin());
			orderDetail.setOrderQuantity(order.getWeight());
			orderDetail.setOrderType(order.getOrderType());
			orderDetail.setOrderWidth(order.getWidth());
			orderDetails.add(orderDetail);
		}
		return orderDetails;
	}

	private static List<PlanCoilDetail> getCoilDetails(int groupId, List<Material> materials) {
		List<PlanCoilDetail> coilDetails = new LinkedList<PlanCoilDetail>();
		for (Material material : materials) {
			PlanCoilDetail planCoilDetail = new PlanCoilDetail();
			planCoilDetail.setCoilIndex(material.getBatchNo());
			planCoilDetail.setAge(material.getAge());
			planCoilDetail.setCoilStatus(material.getColiStatus());
			planCoilDetail.setFinalStockQuantity(material.getWeight());
			planCoilDetail.setGroupdId(groupId);
			planCoilDetail.setLength(material.getLength());
			planCoilDetail.setStockType(material.getStockType());
			planCoilDetail.setUsableStockWidth(material.getWidth());
			coilDetails.add(planCoilDetail);
		}
		return coilDetails;
	}
}
