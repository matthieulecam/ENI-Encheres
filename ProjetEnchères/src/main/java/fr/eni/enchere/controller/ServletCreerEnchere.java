package fr.eni.enchere.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletCreerEnchere
 */
@MultipartConfig 
@WebServlet("/creer_enchere")
public class ServletCreerEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur connectedUser = (Utilisateur) request.getSession().getAttribute("userConnected");
		request.setAttribute("user", connectedUser);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/creerEnchere.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	
		 	
			ArticleManager a = ArticleManager.getInstance();
		 	Utilisateur user = (Utilisateur) request.getSession().getAttribute("userConnected");
		 	int numeroVendeur = user.getNoUtilisateur();
			String nom = request.getParameter("nom_article");
	        String description = request.getParameter("description");
	        int categorie = Integer.parseInt(request.getParameter("no_categorie"));
	        int prixInitial = Integer.parseInt(request.getParameter("prix_initial"));
	        LocalDateTime dateDébut = LocalDateTime.parse(request.getParameter("date_debut_encheres"));
	        LocalDateTime dateFin = LocalDateTime.parse(request.getParameter("date_fin_encheres"));
	       // String modalitesRetrait = request.getParameter("modalitesRetrait");

	        
	        Article art = new Article (nom,description, dateDébut, dateFin, prixInitial, categorie, numeroVendeur);
	        Integer key = null;
	        try {
				key = a.ajouter(art);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        
	        
	        
	    
	        
	        Article article = null;
			try {
				article = a.selectArticleById(key);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        
	        request.setAttribute("article", article);

	        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/confirmationArticle.jsp");

	        dispatcher.forward(request, response);
	        
	        
	}

}
