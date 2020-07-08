package com.intiformation.ECommerce.filtre;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class AccesClientFilter implements Filter {
	
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


        /* Filtrage sur le dossier acces_client */
		if (session.getAttribute("user_login") == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Connexion nécessaire", "Connectez vous pour accéder aux fonctionnalités de l'application");
			contextJSF.addMessage(null, message );
			request.getRequestDispatcher( "/authentification.xhtml" ).forward( request, response );
		} else {
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}//end class
