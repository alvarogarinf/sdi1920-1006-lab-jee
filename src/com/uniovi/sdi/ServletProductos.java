package com.uniovi.sdi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

/**
 * Servlet implementation class ServletProductos
 */
@WebServlet("/productos")
public class ServletProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Producto> productosTienda = new ProductosService().getProductos();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProductos() {
        super();
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		HashMap<String,Integer> productos =
		 (HashMap<String,Integer>) request.getSession().getAttribute("productos");
		// No hay carrito, creamos uno y lo insertamos en sesión
		if (productos == null) {
		productos = new HashMap<String,Integer>();
		 request.getSession().setAttribute("productos", productos);
		}
		String producto = request.getParameter("producto");
		request.setAttribute("productos", productos);
		response.getWriter().append("Served at: ").append(request.getContextPath());
		getServletContext().getRequestDispatcher("/vista-productos.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
