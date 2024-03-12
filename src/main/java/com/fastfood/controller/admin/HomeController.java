package com.fastfood.controller.admin;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fastfood.service.IAccountService;
import com.fastfood.service.ICategoryService;
import com.fastfood.service.IOrderService;
import com.fastfood.service.impl.ChartService;

@Controller(value = "homeControllerOfAdmin")
public class HomeController {
	@Autowired
	private ChartService chart;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IOrderService orderService;
	

	@Autowired
	private IAccountService accountService;

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView productManagementPage(ModelMap modelMap) {
		ModelAndView mav = new ModelAndView("/admin/home");
		List<List<Map<Object, Object>>> canvasjsDataList = chart.getCanvasjsChartData();

		modelMap.addAttribute("dataPointsList", canvasjsDataList);
		modelMap.addAttribute("dataPointsListOfTop10ProductSales", chart.getCanvasjsChartTop10ProductsBySalesData());
		BigDecimal totalSales = orderService.findTotalSumOfAllOrders();
		BigDecimal currentTotalSales = orderService.findTotalSumForCurrentDate();

		
		
		modelMap.addAttribute("totalAccount", accountService.getTotalAccount());
		modelMap.addAttribute("pageName", "/admin/home");
		modelMap.addAttribute("totalSales", formatVietNamCurrentcy(totalSales));
		modelMap.addAttribute("currentTotalSales", formatVietNamCurrentcy(currentTotalSales));

		return mav;
	}

	private String formatVietNamCurrentcy(BigDecimal totalSales) {
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

		String formattedTotalSales = currencyFormat.format(totalSales == null ? 0 : totalSales.doubleValue());

		return formattedTotalSales;
	}

}
