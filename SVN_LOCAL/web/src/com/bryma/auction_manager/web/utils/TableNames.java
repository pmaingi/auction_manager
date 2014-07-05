package com.bryma.auction_manager.web.utils;

/**
 * 
 * TableNames
 * 
 * @author <a href="mailto:pmaingi@gmail.com">Peter Maingi</a>
 * @version enter version, 15 Oct 2013
 * @since  enter jdk version
 */
public interface TableNames {
	
	public static String DATABASE = "`auction`";
	
	public static String AUCTIONTABLE = DATABASE+".`auction`";
	public static String AUCTIONTABLE1 = DATABASE+".`auction1`";
	public static String BIDSTABLE = DATABASE + ".`savedbids`";
	public static String BIDSREGISTERTABLE = DATABASE + ".`bidregister`";
	public static String BIDSSUBSCRIPTION = DATABASE + ".`bidsubscriptions`";
	public static String PRODUCTTABLE = DATABASE + ".`product`";
	public static String PRODUCTTABLE1 = DATABASE + ".`product1`";
	public static String SESSIONTABLE = DATABASE + ".`session`";
	public static String SUBSCRIBERSTABLE = DATABASE + ".`subscribers`";
	public static String WINNERSTABLE = DATABASE + ".`winners`";
	public static String LOSERSSTABLE = DATABASE + ".`losers`";
	public static String MESSAGESTABLE = DATABASE + ".`messages`";
	public static String MESSAGESENDERTABLE = DATABASE + ".`messagesender`";
	public static String BULK_MESSAGESLOGTABLE = DATABASE + ".`bulkmessageslog`";
	public static String HITSLOGTABLE = DATABASE + ".`hitslog`";
	public static String BULK_MESSAGESENDERTABLE = DATABASE + ".`bulkmessagesender`";

}