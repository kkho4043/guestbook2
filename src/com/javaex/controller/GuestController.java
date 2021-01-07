package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

@WebServlet("/gtr")
public class GuestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("list".equals(action)) {
			System.out.println("리스트");
			GuestDao guestDao = new GuestDao();
			List<GuestVo> guestList = guestDao.getguestList();

			// 데이터 전달
			request.setAttribute("glist", guestList);

			// jsp포어드
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/list.jsp");
			rd.forward(request, response);

		} else if ("add".equals(action)) {
			System.out.println("add");
			
			String name = request.getParameter("name");
			String pwd = request.getParameter("pwd");
			String content = request.getParameter("content");
			
			GuestDao guestDao = new GuestDao();
			GuestVo guestVo =new GuestVo(name,pwd,content);
			guestDao.guestInsert(guestVo);
			
			response.sendRedirect("/guestbook2/gtr?action=list");

		} else if ("deform".equals(action)) {
			int no = Integer.parseInt(request.getParameter("no"));
			
			request.setAttribute("no", no);
			
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/deleteform.jsp");
			rd.forward(request, response);
		} else if ("delete".equals(action)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			String pwd1 = request.getParameter("pwd");
			
			GuestDao guestDao = new GuestDao();
			List<GuestVo> guestVo = guestDao.getList(no);
			
			String pwd2 = guestVo.get(0).getPassword();
			
			if(pwd1.equals(pwd2)) {
				guestDao.guestDelete(no);
				response.sendRedirect("/guestbook2/gtr?action=list");
			}else {
				request.setAttribute("no", no);
				request.setAttribute("where", "de");
				RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/notsamepwd.jsp");
				rd.forward(request, response);
			}
		} else if ("upform".equals(action)) {
			int no = Integer.parseInt(request.getParameter("no"));
			
			GuestDao guestDao = new GuestDao();
			List<GuestVo> guestVo = guestDao.getList(no);
			
			request.setAttribute("guestVo", guestVo);
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/updateform.jsp");
			rd.forward(request, response);
		}	else if ("update".equals(action)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			String name = request.getParameter("name");
			String content = request.getParameter("content");
			String date = request.getParameter("date");
			String pwd1 = request.getParameter("pwd");
			
			GuestDao guestDao = new GuestDao();
			List<GuestVo> guestVo = guestDao.getList(no);
			String pwd2 = guestVo.get(0).getPassword();
			
			if(pwd1.equals(pwd2)) {
				
				GuestVo updateVo = new GuestVo(no,name,pwd1,content,date);
				guestDao.guestupdate(updateVo);
				
				response.sendRedirect("/guestbook2/gtr?action=list");
			}else {
				request.setAttribute("no", no);
				request.setAttribute("where", "up");
				RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/notsamepwd.jsp");
				rd.forward(request, response);
			}

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// doGet(request, response);
	}

}
