/**
 * 
 */
package com.iam_vip.process_example.action.process.one;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iam_vip.process_example.action.ActionBase;
import com.iam_vip.process_example.biz.process.IProcessOne;

/**
 * @author Colin
 * 		
 */
@Controller
@RequestMapping( value = { "/u/process/one" } )
public final class ProcessOneAction extends ActionBase {
	
	private static final String		CUR_ACT		= "/u/process/one/";
	private static final String		VIEW_BASE	= "process/one/";
												
	@Autowired private IProcessOne	processOne;
									
									
	/**
	 * 
	 */
	public ProcessOneAction() {}
	
	
	private static final String REQ_showProcessStart = "show/start/view";
	
	
	/**
	 * 现实流程一启动页面
	 */
	@RequestMapping( method = RequestMethod.GET, value = { REQ_showProcessStart } )
	public ModelAndView showProcessStart( HttpServletRequest request, HttpServletResponse response, ModelMap model ) {
		
		return new ModelAndView( VIEW_BASE + "p1-start", model );
	}
	
	
	private static final String REQ_startProcessOne = "2/start";
	
	
	/**
	 * 启动流程一
	 */
	@RequestMapping( method = RequestMethod.POST, value = { REQ_startProcessOne } )
	public ModelAndView startProcessOne( HttpServletRequest request, HttpServletResponse response, ModelMap model, String onlineUName ) {
		
		processOne.startProcess( onlineUName );
		
		return new ModelAndView( "redirect:" + CUR_ACT + REQ_showProcessStart, model );
	}
	
	
	private static final String REQ_redeployProcessOne = "do/redeploy";
	
	
	/**
	 * 重新部署流程一
	 */
	@RequestMapping( method = RequestMethod.GET, value = { REQ_redeployProcessOne } )
	public ModelAndView redeployProcessOne( HttpServletRequest request, HttpServletResponse response, ModelMap model ) {
		
		processOne.redeployProcess();
		
		return new ModelAndView( "redirect:" + CUR_ACT + REQ_showProcessStart, model );
	}
	
}
