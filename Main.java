import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;


public class Main {

	public static void main(String[] str)
	{
		 createThread();
	}
	private static void createThread()
	    {
	        Runnable run = new Runnable() {
				
				@Override
				public void run()
				{
					while (true)
					{
						final    DateFormat dateformat = new SimpleDateFormat("dd/M/yyyy");
						final   DateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
						final    Date date = new Date();
						final  ODatabaseDocumentTx db = new ODatabaseDocumentTx("remote:localhost/storedb")
						.open("root", "1234");
						
						 try 
						 {
							 db.begin();
							  ODocument ft = new ODocument("time");
							  ft.field( "time",timeformat.format(date) );
							  ft.save();
							  
							  db.commit();
							System.out.println("Time :"+timeformat.format(date));
							 Thread.sleep(1000);
						 }
					     catch (InterruptedException ie)
						 {
					    	   
						 }
						  db.close();
					}
				}
			};
			new Thread(run).start();
	    }
}
