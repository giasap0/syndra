package com.sapo.drakkar.web.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by giampaolo.saporito on 15/12/2016.
 */
@WebServlet(name = "DrakkarConsoleServlet", urlPatterns = { "/console/*" }, loadOnStartup = 1)
public class DrakkarConsole extends HttpServlet
{
	private static final Logger logger = LogManager.getLogger( DrakkarConsole.class );

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		this.execute(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		this.execute(req, resp);
	}

	private void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		PrintWriter writer = resp.getWriter();
		writer.append("<div>ciao ciao sono la console</div>");

		//req.getRequestDispatcher("console.jsp").forward( req, resp );
	}

}
