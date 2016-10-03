package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AdminManager;

@WebServlet("/AddNewsByAdminServlet")
public class AddNewsByAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("loggedAs") == null || request.getSession().isNew()) {
			response.sendRedirect("login.jsp");
		}
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		String category = request.getParameter("category");
		String picturesURL = request.getParameter("picturesURL");
		boolean validation = false;
		if (title != null && text != null && category != null && picturesURL != null) {
			if (title != "" && text != "" && category != "" && picturesURL != "") {
				validation = true;
			}
		}
		if (validation) {
			AdminManager.getInstance().addNews(title, text, category, picturesURL);
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		} else {
			RequestDispatcher view = request.getRequestDispatcher("addNews.jsp");
			view.forward(request, response);

		}
	}

}
