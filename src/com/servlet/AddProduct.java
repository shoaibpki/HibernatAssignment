package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ecommerce.EProduct;
import com.util.HibernateUtil;

/**
 * Servlet implementation class AddProduct
 */
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			EProduct product = new EProduct();
			
			product.setName(request.getParameter("pname"));
			product.setPrice(BigDecimal.valueOf(Double.valueOf((request.getParameter("price")))));
			product.setDateAdded(new Date());
			session.save(product);
			tx.commit();
			PrintWriter out = response.getWriter();
			out.print("<h3>Data has been Saved!</h3>");
			sf.close();
			session.close();
		}catch (Exception ex) {
			throw ex;
		}
	}


}
