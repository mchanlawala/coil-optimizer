/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dmtech.coiloptimizer.planning;

import java.util.LinkedList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dmtech.coiloptimizer.bean.OrderCoilMappingDetail;
import com.dmtech.coiloptimizer.bean.PlanCoilDetail;
import com.dmtech.coiloptimizer.bean.PlanDetail;
import com.dmtech.coiloptimizer.bean.PlanOrderDetail;
import com.dmtech.coiloptimizer.bean.PlanOutputDetail;
import com.dmtech.coiloptimizer.bean.PlanParameterDetail;
import com.dmtech.coiloptimizer.bean.SlitSequenceDetail;

import lpsolve.LpSolve;

/**
 * 
 * @author Administrator
 */
public class OptimizationEngineImpl implements OptimizationService<Integer> {

	@Override
	public PlanOutputDetail<Integer> callEngine(PlanDetail planDetail) {
		return coilCombinerPlanningLogic(planDetail);
	}

	private PlanOutputDetail<Integer> coilCombinerPlanningLogic(
			PlanDetail planDetail) {
		/**
		 * Project Planning without Coil
		 */
		List<SlitSequenceDetail<Integer>> slitSequenceDetails = new LinkedList<SlitSequenceDetail<Integer>>();
		List<OrderCoilMappingDetail<Integer>> orderCoilMappingDetails = new LinkedList<OrderCoilMappingDetail<Integer>>();
		PlanOutputDetail<Integer> planOutputDetail = new PlanOutputDetail(
				slitSequenceDetails, orderCoilMappingDetails);
		if (planDetail.getScnerioType() == 0) {
			List<PlanDetail> planDetails = getLengthWisePlan(planDetail);
			for (int i = 0; i < planDetails.size(); i++) {
				if (i > 0) {
					planDetails.get(i).setCoilDetail(
							planDetails.get(i - 1).getCoilDetail());
				}

				projectionPlanningWithoutMaterial(planDetails.get(i),
						planOutputDetail);
			}
		} else if (planDetail.getScnerioType() == 1) {
			projectionPlanningWithMaterial(planDetail, planOutputDetail);
		}

		return planOutputDetail;
	}

	private List<PlanDetail> getWidthWisePlan(PlanDetail planDetail) {
		Map<Double, PlanDetail> lengths = getWidths(planDetail,
				planDetail.getOrderDetail(), planDetail.getCoilDetail());
		List<PlanDetail> details = new LinkedList<PlanDetail>();
		for (PlanDetail detail : lengths.values()) {
			details.add(detail);
		}
		return details;
	}

	private List<PlanDetail> getLengthWisePlan(PlanDetail planDetail) {
		Map<Double, PlanDetail> lengths = getLengths(planDetail,
				planDetail.getOrderDetail());
		List<PlanDetail> details = new LinkedList<PlanDetail>();
		for (PlanDetail detail : lengths.values()) {
			details.add(detail);
		}
		return details;
	}

	private Map<Double, PlanDetail> getWidths(PlanDetail planDetail,
			List<PlanOrderDetail> orderDetail, List<PlanCoilDetail> coilDetails) {
		Map<Double, PlanDetail> map = new HashMap<Double, PlanDetail>();

		for (PlanOrderDetail planOrderDetail : orderDetail) {
			if (map.containsKey(planOrderDetail.getOrderWidth())) {
				map.get(planOrderDetail.getOrderWidth()).getOrderDetail()
						.add(planOrderDetail);
			} else {
				List<PlanOrderDetail> planOrderDetails = new LinkedList<PlanOrderDetail>();
				planOrderDetails.add(planOrderDetail);
				PlanDetail newPlanDetail = new PlanDetail(
						planDetail.getPlanId(), planDetail.getScnerioType(),
						planDetail.getLastPartWeight(),
						planDetail.getMaxAdditionalTrim(),
						planDetail.getMultiParting(),
						planDetail.getMaxSideTrim(), planDetail.getMaxcut());
				newPlanDetail.setOrderDetail(planOrderDetails);
				newPlanDetail.setParameterDetail(planDetail
						.getParameterDetail());
				map.put(planOrderDetail.getOrderWidth(), newPlanDetail);
			}
		}

		return map;
	}

	private Map<Double, PlanDetail> getLengths(PlanDetail planDetail,
			List<PlanOrderDetail> orderDetail) {
		Map<Double, PlanDetail> map = new HashMap<Double, PlanDetail>();

		for (PlanOrderDetail planOrderDetail : orderDetail) {
			if (map.containsKey(planOrderDetail.getMinCoilLength())) {
				map.get(planOrderDetail.getMinCoilLength()).getOrderDetail()
						.add(planOrderDetail);
			} else {
				List<PlanOrderDetail> planOrderDetails = new LinkedList<PlanOrderDetail>();
				planOrderDetails.add(planOrderDetail);
				PlanDetail newPlanDetail = new PlanDetail(
						planDetail.getPlanId(), planDetail.getScnerioType(),
						planDetail.getLastPartWeight(),
						planDetail.getMaxAdditionalTrim(),
						planDetail.getMultiParting(),
						planDetail.getMaxSideTrim(), planDetail.getMaxcut());
				newPlanDetail.setOrderDetail(planOrderDetails);
				newPlanDetail.setParameterDetail(planDetail
						.getParameterDetail());
				map.put(planOrderDetail.getMinCoilLength(), newPlanDetail);
			}
		}
		return map;
	}

	private PlanOutputDetail<Integer> projectionPlanningWithMaterial(
			PlanDetail planDetail, PlanOutputDetail<Integer> planOutputDetail) {
		try {

			for (PlanCoilDetail coilDetail : planDetail.getCoilDetail()) {
				List<PlanOrderDetail> orderDetails = planDetail
						.getOrderDetail();
				List<PlanParameterDetail> parameterDetails = planDetail
						.getParameterDetail();
				List<SlitSequenceDetail<Integer>> slitSequenceDetails = planOutputDetail
						.getSlitSequenceDetails();
				List<OrderCoilMappingDetail<Integer>> orderCoilMappingDetails = planOutputDetail
						.getOrderCoilMappingDetails();
				boolean planningFlag = true;
				// PlanCoilDetail coilDetail = null;
				while (planningFlag && coilDetail != null
						&& coilDetail.getLength() > 0) {
					double[] optimizedWidths = null;

					String orders = orderWidthString(orderDetails);
					String selections = selectionCountString(orderDetails);
					LpSolve solver = LpSolve.makeLp(0, orderDetails.size());
					if (coilDetail != null) {

						solver.strAddConstraint(
								orders,
								LpSolve.LE,
								coilDetail.getUsableStockWidth()
										- planDetail.getMaxSideTrim());

						solver.strAddConstraint(orders, LpSolve.GE, 0);

						solver.strAddConstraint(selections, LpSolve.LE,
								planDetail.getMaxcut());

						for (int i = 1; i <= orderDetails.size(); i++) {
							solver.setLowbo(i, 0);
							solver.setUpbo(i, planDetail.getMaxcut());
							solver.setInt(i, true);
						}
						solver.strSetObjFn(orders);

						solver.setMaxim();
						solver.solve();

						System.out.println("Value of objective function: "
								+ solver.getObjective());
						optimizedWidths = solver.getPtrVariables();

						double planWidth = solver.getObjective();
						if (planWidth <= 0) {
							planningFlag = false;
						}

						if (coilDetail != null && planningFlag) {
							System.out.println("Orders :" + orders);
							System.out.println("Coils :" + coilDetail);
							planningFlag = createOrderCoilAllocation(
									optimizedWidths, orderDetails, planDetail,
									coilDetail, slitSequenceDetails,
									orderCoilMappingDetails);
							if (coilDetail.getLength() > 0) {
								planningFlag = true;
							}
						}
						solver.deleteLp();
					}

				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @param planDetail
	 * @return
	 */
	private PlanOutputDetail<Integer> projectionPlanningWithoutMaterial(
			PlanDetail planDetail, PlanOutputDetail<Integer> planOutputDetail) {
		try {

			while (!planDetail.getOrderDetail().isEmpty()) {
				List<PlanOrderDetail> orderDetails = planDetail
						.getOrderDetail();
				List<PlanCoilDetail> coilDetails = planDetail.getCoilDetail();
				List<PlanParameterDetail> parameterDetails = planDetail
						.getParameterDetail();
				List<SlitSequenceDetail<Integer>> slitSequenceDetails = planOutputDetail
						.getSlitSequenceDetails();
				List<OrderCoilMappingDetail<Integer>> orderCoilMappingDetails = planOutputDetail
						.getOrderCoilMappingDetails();
				boolean planningFlag = true;
				PlanCoilDetail coilDetail = null;
				while (planningFlag /*&& coilDetail.getLength() > 0*/) {
					double[] optimizedWidths = null;

					if (coilDetail != null) {
						coilDetails.add(coilDetail);
					}

					coilDetail = coilDetails != null ? checkBestCoil(
							coilDetails, orderDetails, planDetail) : null;
					String orders = orderWidthString(orderDetails);
					String selections = selectionCountString(orderDetails);
					LpSolve solver = LpSolve.makeLp(0, orderDetails.size());
					if (coilDetail == null) {
						solver.strAddConstraint(orders, LpSolve.LE,
								parameterDetails.get(0).getMaxWidth()
										- planDetail.getMaxSideTrim());
					} else {
						solver.strAddConstraint(orders, LpSolve.LE,
								coilDetail.getUsableStockWidth());
					}

					solver.strAddConstraint(
							orders,
							LpSolve.GE,
							parameterDetails.get(0).getMinWidth()
									+ planDetail.getMaxSideTrim());

					solver.strAddConstraint(selections, LpSolve.LE,
							planDetail.getMaxcut());

					for (int i = 1; i <= orderDetails.size(); i++) {
						solver.setLowbo(i, 0);
						solver.setUpbo(i, planDetail.getMaxcut());
						solver.setInt(i, true);
					}
					solver.strSetObjFn(orders);

					solver.setMaxim();
					solver.solve();

					System.out.println("Value of objective function: "
							+ solver.getObjective());
					optimizedWidths = solver.getPtrVariables();

					if (coilDetail == null) {
						coilDetail = preparedCoil(optimizedWidths, planDetail);
					}

					if (coilDetail != null) {
						System.out.println("Orders :" + orders);
						System.out.println("Coils :" + coilDetails);
						planningFlag = createOrderCoilAllocation(
								optimizedWidths, orderDetails, planDetail,
								coilDetail, slitSequenceDetails,
								orderCoilMappingDetails);
						break;
					}
					solver.deleteLp();

				}
				// planOutputDetail.getOrderCoilMappingDetails().addAll(orderCoilMappingDetails);
				// planOutputDetail.getSlitSequenceDetails().addAll(slitSequenceDetails);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	private PlanCoilDetail checkBestCoil(List<PlanCoilDetail> coilDetails,
			List<PlanOrderDetail> orderDetails, PlanDetail planDetail) {
		PlanCoilDetail coilDetail = null;
		List<Double> dummyCPO = new LinkedList<Double>();
		for (PlanCoilDetail planCoilDetail : coilDetails) {
			try {
				if (planCoilDetail.getLength() > 0) {
					String orders = orderWidthString(orderDetails);
					String selections = selectionCountString(orderDetails);
					LpSolve solver = LpSolve.makeLp(0, orderDetails.size());

					solver.strAddConstraint(orders, LpSolve.LE,
							planCoilDetail.getUsableStockWidth());

					solver.strAddConstraint(orders, LpSolve.GE, planDetail
							.getParameterDetail().get(0).getMinWidth()
							+ planDetail.getMaxSideTrim());

					solver.strAddConstraint(selections, LpSolve.LE,
							planDetail.getMaxcut());

					for (int i = 1; i <= orderDetails.size(); i++) {
						solver.setLowbo(i, 0);
						solver.setUpbo(i, planDetail.getMaxcut());
						solver.setInt(i, true);
					}
					solver.strSetObjFn(orders);

					solver.setMaxim();
					solver.solve();

					System.out.println("Value of objective function: "
							+ solver.getObjective());
					double planWidth = solver.getObjective();
					if (planWidth >= 0) {
						dummyCPO.add(planCoilDetail.getFinalStockQuantity()
								- planWidth);
					} else {
						dummyCPO.add(0.0);
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			double minWidth = 0;
			int selectedCoil = 0;
			boolean selectedCoilFlag = false;
			for (int i = 0; i < dummyCPO.size(); i++) {
				if (i == 0) {
					minWidth = dummyCPO.get(0);
					selectedCoil = 0;
					selectedCoilFlag = true;
				} else {
					if (minWidth > dummyCPO.get(i)) {
						minWidth = dummyCPO.get(i);
						selectedCoil = i;
						selectedCoilFlag = true;
					}
				}
			}
			if (selectedCoilFlag) {
				return coilDetails.get(selectedCoil);
			}
		}
		return null;
	}

	private boolean createOrderCoilAllocation(double[] optimizedWidths,
			List<PlanOrderDetail> orderDetails, PlanDetail planDetail,
			PlanCoilDetail coilDetail,
			List<SlitSequenceDetail<Integer>> slitSequenceDetails,
			List<OrderCoilMappingDetail<Integer>> orderCoilMappingDetails) {

		int cuttingPatterNumber = checkCuttingPatterNumber(
				orderCoilMappingDetails, coilDetail);

		int noOfpatition = checkNoOfPatition(orderCoilMappingDetails,
				coilDetail);
		double totalpartingWeight = checkPartingWeight(orderCoilMappingDetails,
				coilDetail);

		List<Boolean> lastSelection = new LinkedList<Boolean>();
		boolean breakFlag = true;
		double partingWeight = 0;
		double totalPartingLength = checkPartingLenght(orderCoilMappingDetails,
				coilDetail);
		double partLength = 0;
		while (coilDetail.getLength() > 0 && breakFlag) {
			boolean selectionFlag = false;
			boolean incrPartIndex = false;
			double orderAllocatedWidth = 0;

			// partingWeight = 0;
			boolean firstTimeLength = false;
			for (int i = 0; i < optimizedWidths.length; i++) {
				if (optimizedWidths[i] > 0) {
					if (orderDetails.get(i).getOrderLength() >= orderDetails
							.get(i).getMaxCoilLength()) {
						if (!firstTimeLength) {
							firstTimeLength = true;
							totalPartingLength = totalPartingLength
									+ orderDetails.get(i).getMinCoilLength();
							partLength = orderDetails.get(i).getMinCoilLength();
						}
						selectionFlag = true;
						orderAllocatedWidth = orderAllocatedWidth
								+ (orderDetails.get(i).getOrderWidth() * optimizedWidths[i]);
						OrderCoilMappingDetail orderCoilMappingDetail = new OrderCoilMappingDetail();
						orderCoilMappingDetail
								.setActualOrderWeight(orderDetails.get(i)
										.getOrderQuantity());
						double endCutLength = (planDetail.getLastPartWeight())
								/ (((orderDetails.get(i).getOrderThickness() * 100) / 1000000)
										* (coilDetail.getUsableStockWidth() / 1000) * planDetail
										.getParameterDetail().get(0)
										.getMaxDensity());
						double orderFGLength = (orderDetails.get(i)
								.getMinCoilWeight())
								/ (((orderDetails.get(i).getOrderThickness() * 100) / 1000000)
										* (orderDetails.get(i).getOrderWidth() / 1000) * planDetail
										.getParameterDetail().get(0)
										.getMaxDensity());
						orderCoilMappingDetail
								.setAllocatedOrderQty((((optimizedWidths[i] * orderDetails
										.get(i).getOrderWidth()) / 1000)
										* ((orderDetails.get(i)
												.getOrderThickness() * 100) / 1000000) * planDetail
										.getParameterDetail().get(0)
										.getMaxDensity())
										* (orderFGLength));
						orderCoilMappingDetail.setBatchNo(coilDetail
								.getCoilIndex());
						orderCoilMappingDetail.setCoilIndex(coilDetail
								.getCoilIndex());
						orderCoilMappingDetail
								.setCuttingPatternNumber(cuttingPatterNumber);
						orderCoilMappingDetail.setGroupId(planDetail
								.getParameterDetail().get(0).getGroupId());
						orderCoilMappingDetail.setLengthWise(0);
						orderCoilMappingDetail
								.setNoOfCuts((int) optimizedWidths[i]);
						orderCoilMappingDetail
								.setNoOfOrderWidth((int) optimizedWidths[i]);
						orderCoilMappingDetail.setNoOfPatiton(noOfpatition);
						orderCoilMappingDetail.setOrderIndex(orderDetails
								.get(i).getOrderIndex());
						orderCoilMappingDetail.setOrderLineItem(orderDetails
								.get(i).getOrderLineNumber());
						orderCoilMappingDetail.setOrderNo(orderDetails.get(i)
								.getOrderIndex());
						orderCoilMappingDetail.setOrderWidth(orderDetails
								.get(i).getOrderWidth());

						partingWeight = ((coilDetail.getUsableStockWidth() / 1000)
								* ((coilDetail.getThickness() * 100) / 1000000) * planDetail
								.getParameterDetail().get(0).getMaxDensity())
								* totalPartingLength;
						orderCoilMappingDetail.setPartionWeight(partingWeight);
						orderCoilMappingDetail
								.setPartionLength(totalPartingLength);
						orderCoilMappingDetail.setProcessId(0);

						orderCoilMappingDetail
								.setStockWeight(((coilDetail
										.getUsableStockWidth() / 1000)
										* ((coilDetail.getThickness() * 100) / 1000000) * planDetail
										.getParameterDetail().get(0)
										.getMaxDensity())
										* planDetail.getParameterDetail()
												.get(0).getMaxLength());

						orderCoilMappingDetail.setStockWidth(coilDetail
								.getUsableStockWidth());
						if (noOfpatition == 0) {
							orderCoilMappingDetail.setStockLength(coilDetail
									.getLength());
						} else {
							orderCoilMappingDetail.setStockLength(coilDetail
									.getLength()
									- partLength
									+ totalPartingLength);
						}

						orderDetails.get(i).setOrderLength(
								orderDetails.get(i).getOrderLength()
										- (partLength * optimizedWidths[i]));
						orderDetails.get(i).setOrderQuantity(
								orderDetails.get(i).getOrderQuantity()
										- orderCoilMappingDetail
												.getAllocatedOrderQty());
						orderCoilMappingDetails.add(orderCoilMappingDetail);
						for (int cnt = 0; cnt < optimizedWidths[i]; cnt++) {
							SlitSequenceDetail sequenceDetail = new SlitSequenceDetail();
							sequenceDetail
									.setBatchNo(coilDetail.getCoilIndex());
							sequenceDetail.setCoilIndex(coilDetail
									.getCoilIndex());
							sequenceDetail.setCutType(0);
							sequenceDetail.setOrderIndex(orderDetails.get(i)
									.getOrderIndex());
							sequenceDetail.setOrderLineItem(orderDetails.get(i)
									.getOrderLineNumber());
							sequenceDetail.setOrderNo(orderDetails.get(i)
									.getOrderIndex());
							// sequenceDetail.setOrderScheduleNumber(orderScheduleNumber);
							sequenceDetail.setPartIndex(noOfpatition);
							sequenceDetail.setWidth(orderDetails.get(i)
									.getOrderWidth());
							slitSequenceDetails.add(sequenceDetail);
						}

						if (((orderDetails.get(i).getOrderWidth() * optimizedWidths[i]) + (planDetail
								.getMaxSideTrim() * 2)) == coilDetail
								.getUsableStockWidth()) {
							// Free Width Part Entery
							SlitSequenceDetail sequenceDetail = new SlitSequenceDetail();
							sequenceDetail
									.setBatchNo(coilDetail.getCoilIndex());
							sequenceDetail.setCoilIndex(coilDetail
									.getCoilIndex());
							sequenceDetail.setCutType(0);
							sequenceDetail.setOrderIndex(-1);
							sequenceDetail.setOrderLineItem("-1");
							sequenceDetail.setOrderNo("-1");
							// sequenceDetail.setOrderScheduleNumber(orderScheduleNumber);
							sequenceDetail.setPartIndex(noOfpatition);
							sequenceDetail.setWidth((planDetail
									.getMaxSideTrim() * 2));
							slitSequenceDetails.add(sequenceDetail);
							incrPartIndex = true;
							noOfpatition++;
						} else if (optimizedWidths[i] == 2) {
							// Free Width Part Entery
							SlitSequenceDetail sequenceDetail = new SlitSequenceDetail();
							sequenceDetail
									.setBatchNo(coilDetail.getCoilIndex());
							sequenceDetail.setCoilIndex(coilDetail
									.getCoilIndex());
							sequenceDetail.setCutType(0);
							sequenceDetail.setOrderIndex(-1);
							sequenceDetail.setOrderLineItem("-1");
							sequenceDetail.setOrderNo("-1");
							// sequenceDetail.setOrderScheduleNumber(orderScheduleNumber);
							sequenceDetail.setPartIndex(noOfpatition);
							sequenceDetail
									.setWidth(coilDetail.getUsableStockWidth()
											- ((orderDetails.get(i)
													.getOrderWidth() * optimizedWidths[i])));
							slitSequenceDetails.add(sequenceDetail);
							incrPartIndex = true;
							noOfpatition++;
						}
					} else {
						break;
					}
				}
			}

			coilDetail.setLength(coilDetail.getLength() - partLength);

			coilDetail
					.setFinalStockQuantity(coilDetail.getFinalStockQuantity()
							- (((coilDetail.getUsableStockWidth() / 1000)
									* ((coilDetail.getThickness() * 100) / 1000000) * planDetail
									.getParameterDetail().get(0)
									.getMaxDensity()) * partLength));

			if (!incrPartIndex) {
				if (checkTrimOrFreeWIP(
						(coilDetail.getUsableStockWidth() - orderAllocatedWidth),
						orderDetails)) {
					SlitSequenceDetail sequenceDetail = new SlitSequenceDetail();
					sequenceDetail.setBatchNo(coilDetail.getCoilIndex());
					sequenceDetail.setCoilIndex(coilDetail.getCoilIndex());
					sequenceDetail.setCutType(0);
					sequenceDetail.setOrderIndex(-1);
					sequenceDetail.setOrderLineItem("-1");
					sequenceDetail.setOrderNo("-1");
					// sequenceDetail.setOrderScheduleNumber(orderScheduleNumber);
					sequenceDetail.setPartIndex(noOfpatition);
					sequenceDetail.setWidth(coilDetail.getUsableStockWidth()
							- orderAllocatedWidth);
					slitSequenceDetails.add(sequenceDetail);
					noOfpatition++;
				} else {
					if (planDetail.getCoilDetail() != null) {
						planDetail.getCoilDetail().add(coilDetail);
					} else {
						List<PlanCoilDetail> coilDetails = new LinkedList<PlanCoilDetail>();
						coilDetails.add(coilDetail);
						planDetail.setCoilDetail(coilDetails);
					}
				}

			}
			lastSelection.add(selectionFlag);
			boolean breakLoop = false;
			if (lastSelection.size() > 5) {
				int cnt = 0;
				for (int i = lastSelection.size() - 1; i >= 0; i--) {
					if (lastSelection.get(i) == false) {
						cnt++;
						if (cnt == 5) {
							breakLoop = true;
						}
					}
				}
			}

			if (breakLoop) {
				break;
			}

			for (int orderCnt = 0; orderCnt < optimizedWidths.length; orderCnt++) {
				if (optimizedWidths[orderCnt] > 0) {
					if (orderDetails.get(orderCnt).getOrderLength() < orderDetails
							.get(orderCnt).getMinCoilLength()) {
						breakFlag = false;
					}
				}
			}
		}

		if (!orderCoilMappingDetails.isEmpty()) {
			double coilWeight = 0;

			coilWeight = totalpartingWeight + planDetail.getLastPartWeight();

			List<PlanOrderDetail> orderDetailsNew = new LinkedList<PlanOrderDetail>();
			for (int i = 0; i < optimizedWidths.length; i++) {
				if (optimizedWidths[i] > 0) {
					if (orderDetails.get(i).getOrderLength() > orderDetails
							.get(i).getMinCoilLength()) {
						orderDetailsNew.add(orderDetails.get(i));
					}
				} else {
					orderDetailsNew.add(orderDetails.get(i));
				}
			}
			orderDetails.clear();
			orderDetails.addAll(orderDetailsNew);

			List<PlanCoilDetail> coilDetails = new LinkedList<PlanCoilDetail>();
			if (planDetail.getCoilDetail() != null
					&& !planDetail.getCoilDetail().isEmpty()) {
				for (PlanCoilDetail planCoilDetail : planDetail.getCoilDetail()) {
					if (orderDetails.isEmpty()) {
						coilDetails.add(planCoilDetail);
					} else {
						for (PlanOrderDetail orderDetail : orderDetails) {
							if (planCoilDetail.getLength() > orderDetail
									.getMinCoilLength()) {
								coilDetails.add(planCoilDetail);
							}

							if (coilDetail != null
									&& coilDetail.getCoilIndex() == planCoilDetail
											.getCoilIndex()
									&& planCoilDetail.getLength() < orderDetail
											.getMinCoilLength()) {
								coilDetail = null;
							}
						}
					}
				}
			} else {
				if (coilDetail.getLength() > checkWithOrder(orderDetails)) {
					if (planDetail.getCoilDetail() != null) {
						coilDetails.addAll(planDetail.getCoilDetail());
						coilDetails.add(coilDetail);
					} else {
						coilDetails.add(coilDetail);
						planDetail.setCoilDetail(coilDetails);
					}
				}
			}
			planDetail.setCoilDetail(coilDetails);

			if (coilDetail!=null && coilDetail.getLength() > checkWithOrder(orderDetails)) {
				return false;
			}
		}
		return true;
	}

	private double checkWithOrder(List<PlanOrderDetail> orderDetails) {
		double minOrderCoilLength = 0;
		int cnt = 0;

		for (PlanOrderDetail orderDetail : orderDetails) {
			if (cnt == 0) {
				minOrderCoilLength = orderDetail.getMaxCoilLength();
			} else {
				if (minOrderCoilLength < orderDetail.getMaxCoilLength()) {
					minOrderCoilLength = orderDetail.getMaxCoilLength();
				}
			}
			cnt++;
		}
		return minOrderCoilLength;
	}

	private double checkPartingLenght(
			List<OrderCoilMappingDetail<Integer>> orderCoilMappingDetails,
			PlanCoilDetail coilDetail) {
		double lastOrderPartLength = 0;
		for (OrderCoilMappingDetail orderCoilMappingDetail : orderCoilMappingDetails) {
			if (orderCoilMappingDetail.getBatchNo() == coilDetail
					.getCoilIndex()) {
				lastOrderPartLength = orderCoilMappingDetail.getPartionLength();
			}
		}
		return lastOrderPartLength;
	}

	private boolean checkTrimOrFreeWIP(double freeWidth,
			List<PlanOrderDetail> orderDetails) {
		boolean freeTrim = true;
		for (PlanOrderDetail orderDetail : orderDetails) {
			if (orderDetail.getOrderWidth() <= freeWidth) {
				return false;
			}
		}
		return freeTrim;
	}

	private double checkPartingWeight(
			List<OrderCoilMappingDetail<Integer>> orderCoilMappingDetails,
			PlanCoilDetail coilDetail) {
		double partingWeight = 0;
		boolean isExist = false;
		for (OrderCoilMappingDetail<Integer> orderCoilMappingDetail : orderCoilMappingDetails) {
			if (orderCoilMappingDetail.getBatchNo().equalsIgnoreCase(
					coilDetail.getCoilIndex())) {
				partingWeight = orderCoilMappingDetail.getPartionWeight();
				isExist = true;
			}

		}
		if (isExist) {
			return partingWeight;
		}
		return 0;
	}

	private int checkNoOfPatition(
			List<OrderCoilMappingDetail<Integer>> orderCoilMappingDetails,
			PlanCoilDetail coilDetail) {
		int noOfpatition = 0;
		boolean isExist = false;
		for (OrderCoilMappingDetail<Integer> orderCoilMappingDetail : orderCoilMappingDetails) {
			if (orderCoilMappingDetail.getBatchNo().equalsIgnoreCase(
					coilDetail.getCoilIndex())) {
				noOfpatition = orderCoilMappingDetail.getNoOfPatiton();
				isExist = true;
			}

		}
		if (isExist) {
			return noOfpatition + 1;
		}
		return 0;
	}

	private int checkCuttingPatterNumber(
			List<OrderCoilMappingDetail<Integer>> orderCoilMappingDetails,
			PlanCoilDetail coilDetail) {
		int cuttingPatterNumber = 0;
		boolean isExist = false;
		for (OrderCoilMappingDetail<Integer> orderCoilMappingDetail : orderCoilMappingDetails) {
			if (orderCoilMappingDetail.getBatchNo().equalsIgnoreCase(
					coilDetail.getCoilIndex())) {
				cuttingPatterNumber = orderCoilMappingDetail
						.getCuttingPatternNumber();
				isExist = true;
			}

		}
		if (isExist) {
			return cuttingPatterNumber + 1;
		}
		return 0;
	}

	private PlanCoilDetail preparedCoil(double[] optimizedWidths,
			PlanDetail planDetail) {
		List<PlanOrderDetail> orderDetails = planDetail.getOrderDetail();
		PlanCoilDetail coilDetail = new PlanCoilDetail();
		double width = 0.0;
		double minWeight = 0.0;
		double thickness = 0.0;
		double density = 0.0;
		double weight = 0.0;
		PlanParameterDetail parameterDetail = planDetail.getParameterDetail()
				.get(0);
		List<PlanOrderDetail> selectedOrders = new LinkedList<PlanOrderDetail>();
		for (int cnt = 0; cnt < optimizedWidths.length; cnt++) {
			if (optimizedWidths[cnt] > 0) {
				width = width
						+ (orderDetails.get(cnt).getOrderWidth() * optimizedWidths[cnt]);

				thickness = orderDetails.get(cnt).getOrderThickness();
				density = parameterDetail.getMaxDensity();
				selectedOrders.add(orderDetails.get(cnt));
				int roundPices = (int) (orderDetails.get(cnt)
						.getOrderQuantity() / orderDetails.get(cnt)
						.getMinCoilWeight());
				if (minWeight == 0.0) {
					minWeight = roundPices
							* orderDetails.get(cnt).getMinCoilWeight();
				} else if (minWeight < orderDetails.get(cnt).getOrderQuantity()) {
					minWeight = roundPices
							* orderDetails.get(cnt).getMinCoilWeight();
				}
			}
		}

		if (width >= 0 && minWeight >= 0) {
			width = width + (planDetail.getMaxSideTrim() * 2);
			weight = ((width / 1000) * density
					* planDetail.getParameterDetail().get(0).getMaxLength() * ((thickness * 100) / 1000000));
			weight = weight
					- planDetail.getParameterDetail().get(0).getMaxEndTrim();
			coilDetail.setCoilIndex(String.valueOf(new Date().getTime()));
			coilDetail.setAge(1);
			coilDetail.setCoilStatus("COIL");
			coilDetail.setFinalStockQuantity(weight);
			coilDetail.setGroupdId(parameterDetail.getGroupId());
			coilDetail.setLength(planDetail.getParameterDetail().get(0)
					.getMaxLength());
			coilDetail.setStockType("CPO");
			coilDetail.setUsableStockWidth(width);
			coilDetail.setThickness(thickness);
		}

		return coilDetail;
	}

	private String selectionCountString(List<PlanOrderDetail> orderDetails) {
		String orderStr = "";
		int cnt = 0;
		for (PlanOrderDetail orderDetail : orderDetails) {

			if (cnt == 0) {
				orderStr = String.valueOf("1");
			} else {
				orderStr = orderStr + " " + "1";
			}
			cnt++;
		}
		return orderStr.length() > 0 ? orderStr : null;

	}

	private String orderWidthString(List<PlanOrderDetail> orderDetails) {
		String orderStr = "";
		int cnt = 0;
		for (PlanOrderDetail orderDetail : orderDetails) {

			if (cnt == 0) {
				orderStr = String.valueOf(orderDetail.getOrderWidth());
			} else {
				orderStr = orderStr + " " + orderDetail.getOrderWidth();
			}
			cnt++;
		}
		return orderStr.length() > 0 ? orderStr : null;
	}

}
