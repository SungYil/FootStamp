package com.footstamp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;

/**
 * Servlet implementation class AlarmServlet
 */
@Controller
public class AlarmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id=request.getParameter("id");
		System.out.println("AlarmServlet-doGet : "+id);
		// �������θ� ��ȯ.
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		obj.put("result", "success");
		out.println(obj);
	}

}
