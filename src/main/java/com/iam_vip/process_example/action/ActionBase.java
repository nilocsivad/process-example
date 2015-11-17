/**
 * 
 */
package com.iam_vip.process_example.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.iam_vip.process_example.rs.c.C;

/**
 * @author Colin
 *
 */
public class ActionBase implements C {

	private static final String REDIRECT_PERMISSION_DENIED = "/bprivilege/show/permission/denied/access";

	/**
	 * 
	 */
	public ActionBase() {
	}

	private String URL;

	protected String base_url( HttpServletRequest request ) {
		return this.base_url( request, "" );
	}

	protected String base_url( HttpServletRequest request, String action ) {
		return this.base_url( request, action, "" );
	}

	protected String base_url( HttpServletRequest request, String action, String method ) {
		if ( URL == null ) {
			int port = request.getServerPort();
			URL = request.getScheme() + "://" + request.getServerName() + ( port == 80 ? "" : ":" + port );
			String path = request.getContextPath();
			URL = URL + ( URL.endsWith( "/" ) ? "" : "/" ) + ( path.startsWith( "/" ) ? path.substring( 1 ) : path );
		}
		return URL + ( URL.endsWith( "/" ) ? "" : "/" ) + action + ( method == null || method.equals( "" ) ? "" : "/" + method );
	}

	/**
	 * @return
	 */
	public ModelAndView show_permission_denied( String privilegeKey ) {
		return new ModelAndView( "redirect:" + REDIRECT_PERMISSION_DENIED + "?privilegeKey=" + privilegeKey );
	}

}
