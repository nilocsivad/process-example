/**
 * 
 */
package com.iam_vip.process_example.action.process;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iam_vip.process_example.action.ActionBase;

/**
 * @author Colin
 */
@Controller
@RequestMapping( value = { "/u/home" } )
public class HomeAction extends ActionBase {
	
	private static final String					VIEW_BASE	= "process/";
															
	@Autowired private RepositoryService		repositoryService;
												
												
	/**
	 * 
	 */
	public HomeAction() {}
	
	@RequestMapping( method = RequestMethod.GET, value = { "", "index", "home" } )
	public ModelAndView index( HttpServletRequest request, HttpServletResponse response, ModelMap model ) {
		
		// String fmt = "{DeploymentId:'%s', Description:'%s', Id:'%s', Key:'%s', Name:'%s', ResourceName:'%s', TenantId:'%s', Version:%d}";
		
		List< ProcessDefinition > processes = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().desc().list();
		// for ( ProcessDefinition p : processes ) {
		// String val = String.format( fmt, p.getDeploymentId(), p.getDescription(), p.getId(), p.getKey(), p.getName(), p.getResourceName(), p.getTenantId(), p.getVersion() );
		// System.out.println( val );
		// }
		model.put( "processes", processes );
		
		return new ModelAndView( VIEW_BASE + "process-index", model );
	}
	
	
	private static final String REQ_processImage = "show/process/image/2/{deploymentId}";
	
	
	/**
	 * 查看流程图片
	 */
	@RequestMapping( method = RequestMethod.GET, value = { REQ_processImage } )
	public void processImage( HttpServletRequest request, HttpServletResponse response, ModelMap model, @PathVariable String deploymentId, String diagramResourceName ) throws IOException {
		
		InputStream is = repositoryService.getResourceAsStream( deploymentId, diagramResourceName );
		// is = ProcessDiagramGenerator.generateDiagram( bpmnModel, "png", activeIds );
		
		OutputStream os = response.getOutputStream();
		response.reset();
		response.setHeader( "Content-type: ", "image" );
		response.setHeader( "Content-Disposition", "image/png" );
		response.setContentType( "application/octet-stream; charset=utf-8" );
		
		byte[] buffer = new byte[ 1024 * 10 ];
		int c = 0;
		while ( ( c = is.read( buffer ) ) > 0 ) {
			os.write( buffer, 0, c );
		}
		buffer = null;
		os.flush();
		os.close();
	}
	
}
