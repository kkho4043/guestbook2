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

		} else if ("add".equals(action)) {//추가
			System.out.println("add");
			
			String name = request.getParameter("name");
			String pwd = request.getParameter("pwd");
			String content = request.getParameter("content");

			GuestDao guestDao = new GuestDao();
			GuestVo guestVo = new GuestVo(name, pwd, content);
			guestDao.guestInsert(guestVo);

			response.sendRedirect("/guestbook2/gtr?action=list");

		} else if ("conpwd".equals(action)) {//비밀번호 확인창 앞에서 변경을 누르던 삭제를 누르던 이창으로 온다

			String where = request.getParameter("where");//de or up 이지만 처음 변경을 누룬지 삭제를 누른지 판단대주는 변수.
			int no = Integer.parseInt(request.getParameter("no"));

			request.setAttribute("where", where);
			request.setAttribute("no", no);

			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/confirmpassword.jsp");
			rd.forward(request, response);
		} else if ("delandup".equals(action)) {
			//비밀번호가 맞는지 확인해주고 틀리면 틀렸다는 창으로 맞으면 처음에 어떤 창을 선택했는지에 따라 각 행동을 한다.
			
			int no = Integer.parseInt(request.getParameter("no"));
			String where = request.getParameter("where");
			String pwd1 = request.getParameter("pwd");

			GuestDao guestDao = new GuestDao();
			List<GuestVo> guestVo = guestDao.getList(no);

			String pwd2 = guestVo.get(0).getPassword();

			if (pwd1.equals(pwd2)) { //비밀번호가 맞으면.
				if ("de".equals(where)) {//de일때 delete수행 
					guestDao.guestDelete(no);
					response.sendRedirect("/guestbook2/gtr?action=list");
				} else if ("up".equals(where)) {//up일때 update폼으로 이동. 
					request.setAttribute("guestVo", guestVo);
					RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/updateform.jsp");
					rd.forward(request, response);
				}

			} else { //비밀번호가 틀렸을때 번호와 삭제를 눌렀는지 변경을 눌렀는지 확인해주는 변수를 들고 비밀번호틀림 창으로 이동한다.
				request.setAttribute("no", no);
				request.setAttribute("where", where);
				RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/notsamepwd.jsp");
				rd.forward(request, response);
			}
		} else if ("update".equals(action)) {

			int no = Integer.parseInt(request.getParameter("no"));
			String name = request.getParameter("name");
			String content = request.getParameter("content");
			String date = request.getParameter("date");
			String pwd1 = request.getParameter("pwd");

			GuestDao guestDao = new GuestDao();
			
			GuestVo updateVo = new GuestVo(no, name, pwd1, content, date);
			guestDao.guestupdate(updateVo);

			response.sendRedirect("/guestbook2/gtr?action=list");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// doGet(request, response);
	}

}
