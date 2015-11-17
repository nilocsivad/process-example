/**
 * 
 */
package com.iam_vip.process_example.biz.process.implement;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iam_vip.process_example.biz.process.IProcessOne;

/**
 * @author Colin
 */
@Service
public class ProcessOneServiceImplement implements IProcessOne {
	
	private static final String				PROCESS_KEY	= "Process_One";
														
	@Autowired private TaskService			taskService;
	@Autowired private RuntimeService		runtimeService;
	@Autowired private IdentityService		identityService;
	@Autowired private RepositoryService	repositoryService;
											
											
	/**
	 * 
	 */
	public ProcessOneServiceImplement() {}
	
	/*
	 * (non-Javadoc)
	 * @see com.iam_vip.process_example.biz.process.IProcessOne#startProcess(java.lang.String)
	 */
	public void startProcess( String onlineUName ) {
		
		identityService.setAuthenticatedUserId( onlineUName );
		ProcessInstance instance = runtimeService.startProcessInstanceByKey( PROCESS_KEY, "onlineUName-" + onlineUName );
		Task task = taskService.createTaskQuery().processInstanceId( instance.getId() ).orderByTaskId().desc().singleResult();
		taskService.claim( task.getId(), onlineUName );
		taskService.complete( task.getId() );
	}
	
	/* (non-Javadoc)
	 * @see com.iam_vip.process_example.biz.process.IProcessOne#redeployProcess()
	 */
	public void redeployProcess() {
		
		repositoryService.createDeployment().addClasspathResource( "com/dfs/process_example/action/process/one/process-one.bpmn" ).deploy();
	}
	
}
