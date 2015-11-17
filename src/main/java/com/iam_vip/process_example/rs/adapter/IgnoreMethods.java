/**
 * 
 */
package com.iam_vip.process_example.rs.adapter;

import java.util.List;

/**
 * @author Colin
 * 		
 */
public class IgnoreMethods {
	
	private static boolean			enable	= true;
	private static List< String >	methods;
									
									
	/**
	 * 
	 */
	public IgnoreMethods( boolean enable, List< String > methods ) {
		IgnoreMethods.enable = enable;
		IgnoreMethods.methods = methods;
	}
	
	/**
	 * @return the enable
	 */
	public static final boolean isEnable() {
		
		return enable;
	}
	
	/**
	 * @return the methods
	 */
	public static List< String > getMethods() {
		
		return methods;
	}
	
}
