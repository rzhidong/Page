package com.serlvet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.IUsersDao;
import com.dao.impl.UsersDaoImpl;
import com.sun.jndi.toolkit.url.Uri;
import com.util.PageBean;
import com.vo.Users;

public class QueryServlet extends HttpServlet {

	private IUsersDao usersDao = new UsersDaoImpl();

	/**
	 * Constructor of the object.
	 */
	public QueryServlet() {
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
		
		try {
			String condition = request.getParameter("condition").trim();
			
			if (!"".equals(condition)) {
				condition = "";
			}
			
			// 1、获取当前页参数,(第一次访问当前页为null)
			String currPage = request.getParameter("currentPage").trim();
			
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
			usersDao.getByCondition(pageBean, condition);

			// 4、保存pageBean 对象到request域中
			request.setAttribute("pageBean", pageBean);

			request.setAttribute("condition", condition);

			//uri = "/WEB-INF/query.jsp";
			request.getRequestDispatcher("/WEB-INF/query.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}

		//request.getRequestDispatcher(uri).forward(request, response);

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
