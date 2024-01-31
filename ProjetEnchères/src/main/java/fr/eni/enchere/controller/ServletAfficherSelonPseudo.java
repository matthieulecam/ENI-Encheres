package fr.eni.enchere.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletAfficherProfil
 */
@WebServlet("/ServletAfficherSelonPseudo")
public class ServletAfficherSelonPseudo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur connectedUser = (Utilisateur) session.getAttribute("ConnectedUser");
				
		String pseudo = request.getParameter("pseudo");	
		Utilisateur userAffiche = null;
		

			userAffiche = UtilisateurManager.selectUserByPseudo(pseudo);
	
		
		
		if (userAffiche != null && connectedUser != null) {
			
			request.setAttribute("UserAffiche", userAffiche);
			request.getRequestDispatcher("/WEB-INF/jsp/mon_profil.jsp").forward(request, response);	
					
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
