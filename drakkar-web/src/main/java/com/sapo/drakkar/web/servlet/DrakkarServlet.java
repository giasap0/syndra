package com.sapo.drakkar.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by giampaolo.saporito on 15/12/2016.
 */
//@WebServlet(name = "DrakkarServletProxy", urlPatterns = { "/services/*" } )
public class DrakkarServlet extends HttpServlet
{
	@Override
	public void init() throws ServletException {
		super.init();
	}

	// =====================================================================================================
	// protected
	// =====================================================================================================

	@Override
	protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
		this.execute(req, resp);
	}

	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
		this.execute(req, resp);
	}

	// =====================================================================================================
	// private
	// =====================================================================================================

	private void execute( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException
	{
	}
	

//	@Override
//	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
//	{
//		this.execute(req, resp);
//	}

//	@Override
//	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
//	{
//		super.doDelete(req, resp);
//	}
}
