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
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(filterName = "ClientFilter", urlPatterns = {"/acces_client/*" })
public class AccesClientFilter implements Filter {
	
	FacesContext contextJSF = FacesContext.getCurrentInstance();

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		try {
			
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession session = req.getSession(false);
			
			String reqURI = req.getRequestURI();
			if ((session != null && session.getAttribute("user_login") != null)|| reqURI.contains("javax.faces.resource"))
			{
				chain.doFilter(request, response);
			}else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Connexion nécessaire", "Connectez vous pour accéder aux fonctionnalités de l'application"); 
				contextJSF.addMessage(null, message);
				resp.sendRedirect(req.getContextPath() + "/authentification.xhtml");
			}//end else
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		


//        /* Filtrage sur le dossier acces_client */
//		if (session.getAttribute("user_login") == null) {
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Connexion nécessaire", "Connectez vous pour accéder aux fonctionnalités de l'application");
//			contextJSF.addMessage(null, message );
//			request.getRequestDispatcher( "/authentification.xhtml" ).forward( request, response );
//		} else {
//			chain.doFilter(request, response);
//		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}//end class
