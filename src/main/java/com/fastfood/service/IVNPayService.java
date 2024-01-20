package com.fastfood.service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

public interface IVNPayService {
	String createOrder(int total, String urlReturn) throws UnsupportedEncodingException;
	int orderReturn(HttpServletRequest request);
}
