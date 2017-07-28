package com.serlvet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.IUsersDao;
import com.dao.impl.UsersDaoImpl;
import com.service.IUsersService;
import com.service.impl.UsersServiceImpl;
import com.util.PageBean;
import com.vo.Users;

public class IndexServlet extends HttpServlet {

	private IUsersDao usersDao = new UsersDaoImpl();
	
	private IUsersService usersService = new UsersServiceImpl();

	private String uri;

	/**
	 * Constructor of the object.
	 */
	public IndexServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");

		try {
			// 1、获取当前页参数,(第一次访问当前页为null)
			String currPage = request.getParameter("currentPage");

			// 判断
			if (currPage == null || "".equals(currPage.trim())) {
				// 第一次访问，设置当前页为1
				currPage = "1";
			}

			int currentPage = Integer.parseInt(currPage);
			
			// 2、创建PageBean对象，设置当前页参数，传入service方法参数
			PageBean<Users> pageBean = new PageBean<Users>();
			pageBean.setCurrentPage(currentPage);

			// 3、调用service
			// pageBean 已经被dao填充了数据
			//usersDao.getAll(pageBean);
			usersService.getAll(pageBean);

			// 4、保存pageBean 对象到request域中
			request.setAttribute("pageBean", pageBean);

			// 5、跳转
			uri = "/WEB-INF/list.jsp";

		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		
		request.getRequestDispatcher(uri).forward(request,
				response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
