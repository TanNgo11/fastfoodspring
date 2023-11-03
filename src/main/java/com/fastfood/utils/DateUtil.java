package com.fastfood.utils;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fastfood.dto.ItemDTO;
import com.fastfood.dto.OrderDTO;
import com.fastfood.dto.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DateUtil {
	 private List<String> monthsAndYears = new ArrayList<>();
	    private LocalDate startDate = LocalDate.of(2022, Month.FEBRUARY, 1);
	    private YearMonth currentYearMonth = YearMonth.now();
	    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");

	    public void populateMonthsAndYears() {
	        
	        monthsAndYears.clear();

	       
	        while (startDate.isBefore(currentYearMonth.atEndOfMonth())) {
	            monthsAndYears.add(currentYearMonth.format(formatter));
	            currentYearMonth = currentYearMonth.minusMonths(1);
	        }

	        monthsAndYears.add(currentYearMonth.format(formatter));
	    }
	    
	    public static int getMonthInt(String monthName) {
	       
	        monthName = monthName.toUpperCase();
	        
	     
	        Month month = Month.valueOf(monthName);
	        
	        
	        int monthValue = month.getValue();
	        
	        return monthValue;
	    }

}
