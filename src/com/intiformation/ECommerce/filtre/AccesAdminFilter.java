package com.intiformation.ECommerce.filtre;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccesAdminFilter implements Filter {

	FacesContext contextJSF = FacesContext.getCurrentInstance();

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();


        /* Filtrage sur le dossier acces_admin */
		if (session.getAttribute("user_login") == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Connexion nécessaire", "Connectez vous pour accéder aux fonctionnalités de l'application");
			contextJSF.addMessage(null, message );
			request.getRequestDispatcher( "/authentification.xhtml" ).forward( request, response );
		} else {
			if(session.getAttribute("user_statut")!="admin") {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Statut 'admin' nécessaire", "Connectez vous en tant qu'administrateur pour accéder à l'espace gestion de l'application");
				contextJSF.addMessage(null, message );
				request.getRequestDispatcher( "/authentification.xhtml" ).forward( request, response );
			}//end if
			chain.doFilter(request, response);
		}//end else
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}//end class