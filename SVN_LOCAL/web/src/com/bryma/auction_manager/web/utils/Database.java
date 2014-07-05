package com.bryma.auction_manager.web.utils;

/**
 * 
 * Database All database names used in the services
 * 
 * @author <a href="mailto:enter email address">Peter</a>
 * @version enter version, 7 Nov 2013
 * @since enter jdk version
 */
public interface Database {

	public static final String USERS_TABLENAME = "`users`";

	public static final String ROLE_TABLENAME = "`roles`";

	//public static final String PRODUCTTABLENAME = "`product`";
	
	public static final String PRODUCTTABLENAME1 = "`product1`";
	
	public static final String SUBSCRIPTIONTABLENAME = "`bidsubscriptions`";

	//public static final String AUCTIONTABLENAME = "`auction`";
	
	public static final String AUCTIONTABLENAME1 = "`auction1`";
	
	public static final String COMPONENTTABLENAME = "`components`";
	
	public static final String SAVEDBIDS = "`savedbids`";
	
	public static final String HITSLOG = "`hitslog`";
	
	public static String BULK_MESSAGESENDERTABLE = "`bulkmessagesender` ";

}
