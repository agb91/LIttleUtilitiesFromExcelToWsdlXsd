package excel.excel;

public class App 
{
    public static void main( String[] args )
    {
    	Reader r = new Reader();
    	ExcelRead er = r.read(3);
    	
    	Writer w = new Writer();
    	w.write( er );
    		
    	WriteGroup wg = new WriteGroup();
    	wg.writeGroup( er.getGroup() );
    	
    	ProducerWs pw = new ProducerWs();
    	pw.mapInputs( 3 );
    	
    	
    }
    
    
  
  
}
