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
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(filterName = "AdminFilter", urlPatterns = {"/acces_admin/*" })
public class AccesAdminFilter implements Filter {

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
			String userStatut = (String) session.getAttribute("user_statut") ;
			
			String reqURI = req.getRequestURI();
			if ((session != null && session.getAttribute("user_login") != null)|| reqURI.contains("javax.faces.resource"))
			{
				if(("admin").equals(userStatut)){
					chain.doFilter(request, response);
				}else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Statut 'admin' nécessaire", "Connectez vous en tant qu'administrateur pour accéder à l'espace gestion de l'application");
					contextJSF.addMessage(null, message );
					resp.sendRedirect(req.getContextPath() + "/authentification.xhtml");
				}//end else
			}else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Connexion nécessaire", "Connectez vous pour accéder aux fonctionnalités de l'application"); 
				contextJSF.addMessage(null, message);
				resp.sendRedirect(req.getContextPath() + "/authentification.xhtml");
			}//end else
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}//end doFilter()

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}//end class