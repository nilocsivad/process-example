/**
 * 
 */
package com.iam_vip.process_example.rs.u;

/**
 * @author Colin
 *		
 */
public class AppConfigUtil {
	
	private static String	key;
									
									
	/**
	 * 
	 */
	public AppConfigUtil( String key ) {
		AppConfigUtil.key = key;
	}
	
	/**
	 * @return the key
	 */
	public static String getKey() {
		
		return key;
	}
	
}
