package com.fastfood.service.impl;

import java.math.BigDecimal;

//CanvasjsChartData.java

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fastfood.dto.CategoryRevenueDTO;
import com.fastfood.dto.MonthlyTotalDTO;
import com.fastfood.dto.ProductSalesDTO;
import com.fastfood.service.ICategoryService;
import com.fastfood.service.IChartService;
import com.fastfood.service.IOrderService;
import com.fastfood.service.IProductService;

@Component
public class ChartService implements IChartService {

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IOrderService orderService;

	@Autowired
	private IProductService productService;

	public List<List<Map<Object, Object>>> getCanvasjsChartData() {
		Map<Object, Object> map = null;
		List<List<Map<Object, Object>>> list = new ArrayList<List<Map<Object, Object>>>();
		List<Map<Object, Object>> dataPoints1 = new ArrayList<Map<Object, Object>>();

		BigDecimal sum = orderService.findTotalSumOfAllOrders();
		double sumAsDouble = sum.doubleValue();

		List<CategoryRevenueDTO> listCategoryBaseOnRevenue = categoryService.getRevenueByCategory();

		for (CategoryRevenueDTO categoryRevenueDTO : listCategoryBaseOnRevenue) {

			double percentage = (double) (categoryRevenueDTO.getRevenue().doubleValue() / sumAsDouble) * 100;

			percentage = (double) Math.round(percentage * 10) / 10;
			map = new HashMap<Object, Object>();
			map.put("label", categoryRevenueDTO.getCategoryName());
			map.put("y", percentage);
			dataPoints1.add(map);
		}
		list.add(dataPoints1);

		return list;
	}

	@Override
	public List<List<Map<Object, Object>>> getCanvasjsChartTop10ProductsBySalesData() {
		Map<Object, Object> map = null;
		List<List<Map<Object, Object>>> list = new ArrayList<List<Map<Object, Object>>>();
		List<Map<Object, Object>> dataPoints1 = new ArrayList<Map<Object, Object>>();

		List<ProductSalesDTO> listProduct = productService.getTop10ProductsBySales();

		for (ProductSalesDTO productSalesDTO : listProduct) {
			map = new HashMap<Object, Object>();
			map.put("label", productSalesDTO.getProductName());
			map.put("y", productSalesDTO.getTotalQuantity());
			dataPoints1.add(map);

		}
		list.add(dataPoints1);

		return list;
	}

	@Override
	public List<List<Map<Object, Object>>> getCanvasjsChartgetTotalPaymentsByMonthForYear(int year) {
		Map<Object, Object> map = null;
		List<List<Map<Object, Object>>> list = new ArrayList<List<Map<Object, Object>>>();
		List<Map<Object, Object>> dataPoints1 = new ArrayList<Map<Object, Object>>();
		List<MonthlyTotalDTO> listSalesByMonth = orderService.getTotalPaymentsByMonthForYear(year);

		for (MonthlyTotalDTO monthlyTotalDTO : listSalesByMonth) {
			map = new HashMap<Object, Object>();
			long xValue = changeMonthAndYearToTimeStamp(monthlyTotalDTO, year);
			System.out.println(xValue);
			map.put("x", xValue);
			map.put("y", monthlyTotalDTO.getTotal());
			dataPoints1.add(map);
		}

		list.add(dataPoints1);
		return list;
	}

	private long changeMonthAndYearToTimeStamp(MonthlyTotalDTO monthlyTotalDTO, int year) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, monthlyTotalDTO.getMonth() - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		long timestamp = calendar.getTimeInMillis();

		return timestamp;
	}

}