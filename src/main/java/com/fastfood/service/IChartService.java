package com.fastfood.service;

import java.util.List;
import java.util.Map;

public interface IChartService {
	List<List<Map<Object, Object>>> getCanvasjsChartData();
	List<List<Map<Object, Object>>> getCanvasjsChartTop10ProductsBySalesData();
	List<List<Map<Object, Object>>> getCanvasjsChartgetTotalPaymentsByMonthForYear(int year);
}
