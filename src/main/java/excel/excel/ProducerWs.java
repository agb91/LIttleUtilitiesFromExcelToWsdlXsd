package excel.excel;

public class ProducerWs {
	
	public void mapInputs( int page )
	{
		Reader r = new Reader();
		ExcelRead er = r.read( page );
		
		System.out.println( "INPUTS" );
		for( Input i : er.getInputs() )
		{
			String serviceName = er.getGlobalSheetName();
			
			String inputName = i.getName();
			inputName = inputName.substring(0, 1).toUpperCase() + inputName.substring(1);//first char uppercase...
			String toPrint = "input.set" + inputName + "( " + serviceName.toLowerCase() 
					+ ".get" + inputName + "() )";
			
			System.out.println( toPrint );
		}
		
		System.out.println( "OUTPUTS" );
		for( Input i : er.getInputs() )
		{
			
			String outputName = i.getName();
			outputName = outputName.substring(0, 1).toUpperCase() + outputName.substring(1);
			String toPrint = "output.set" + outputName + "( servOut.get" + outputName + "() )";
			
			System.out.println( toPrint );
		}
	}
	

}
