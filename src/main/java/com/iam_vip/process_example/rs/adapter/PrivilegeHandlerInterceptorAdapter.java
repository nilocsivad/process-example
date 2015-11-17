/**
 * 
 */
package com.iam_vip.process_example.rs.adapter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iam_vip.process_example.rs.c.C;

/**
 * @author Colin
 */
public class PrivilegeHandlerInterceptorAdapter extends HandlerInterceptorAdapter implements C {
	
	private boolean	GET_URL		= false;	// ** 是否已经得到 根目录 URI 的值
	private String	REDIRECT	= "";		// ** 根目录 URI
								
								
	/**
	 * 
	 */
	public PrivilegeHandlerInterceptorAdapter() {}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
		
		System.out.println( "RequestURI " + request.getRequestURI() );
		System.out.println( "RequestURL " + request.getRequestURL() );
		
		// ** 匹配到需要拦截的 URL
		
		if ( handler instanceof HandlerMethod ) {
			// <backend-control-area>
			
			HandlerMethod method = ( HandlerMethod ) handler;
			String className = method.getBean().getClass().getName(); // ** Controller 的类名
			String methodName = method.getMethod().getName(); // ** Controller 里执行的方法名称
			
			// System.out.println( "Execute " + className + "." + methodName );
			
			if ( !GET_URL ) {
				REDIRECT = request.getContextPath();
				GET_URL = true;
			}
			
			// ** ignore all index method
			if ( methodName.equalsIgnoreCase( "index" ) ) { return true; }
			
			if ( IgnoreMethods.isEnable() ) { // ignore configuration URL action method
				boolean ignore = false;
				String all = className + "." + methodName;
				for ( String line : IgnoreMethods.getMethods() ) {
					if ( all.equals( line ) ) {
						ignore = true;
						break;
					}
				}
				if ( ignore ) { return true; }
			}
			
			if ( className.startsWith( PACKAGE_INTERCEPTOR ) ) {
				// <backend-control-area>
//				return this.controlURL( request, response, ONLINE_MANAGER );
				// </backend-control-area>
			}
		}
		
		return super.preHandle( request, response, handler );
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	boolean controlURL( HttpServletRequest request, HttpServletResponse response, String sessionKey ) throws IOException {
		
		HttpSession httpSession = request.getSession( false );
		// ** 未登录则不通过
		if ( httpSession == null ) {
			System.out.println( "111111111111111111111111111111111111  httpSession == null" );
			response.sendRedirect( REDIRECT );
			return false;
		}
		
		// ** 判断是否登录
		Object onlineObj = httpSession.getAttribute( sessionKey );
		if ( onlineObj == null ) {
			System.out.println( "222222222222222222222222222222222222 onlineObj == null" );
			response.sendRedirect( REDIRECT );
			return false;
		}
		
		// ** 最后一次访问时间大于 30 分钟
		long lastAccessed = httpSession.getLastAccessedTime();
		if ( System.currentTimeMillis() - lastAccessed > 30 * 60 * 1000 ) {
			System.out.println( "333333333333333333333333333333333333 lastAccessed > 30 * 60 * 1000" );
			response.sendRedirect( REDIRECT );
			return false;
		}
		
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception {
		
		super.postHandle( request, response, handler, modelAndView );
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex ) throws Exception {
		
		super.afterCompletion( request, response, handler, ex );
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterConcurrentHandlingStarted(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public void afterConcurrentHandlingStarted( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
		
		super.afterConcurrentHandlingStarted( request, response, handler );
	}
	
}
