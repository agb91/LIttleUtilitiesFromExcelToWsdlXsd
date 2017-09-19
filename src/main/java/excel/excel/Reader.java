package excel.excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Reader {
	
	public ExcelRead read( int page )
	{
		ExcelRead er = new ExcelRead();
		String file = "test.xlsx";
    	String globalSheetName = "";
    	int rowInputBegin = -1;
    	int rowInputEnd = -1;
    	int rowOutputBegin = -1;
    	int rowOutputEnd = -1;
    	List<Input> inputs = new ArrayList<Input>();
    	List<Output> outputs = new ArrayList<Output>();
    	try {
    		OPCPackage pkg = OPCPackage.open(new File( file ));
    		XSSFWorkbook wb = new XSSFWorkbook(pkg);
    		XSSFSheet sheet = wb.getSheetAt(page);
    	    XSSFRow row;
    	    XSSFCell cell;

    	    int rows; // No of rows
    	    rows = sheet.getPhysicalNumberOfRows();
    	    rowOutputEnd = rows;
    	    int cols = getNumCols( sheet );
    	    
    	    
    	    for(int r = 0; r < rows; r++) {
    	        row = sheet.getRow(r);
    	        if(row != null) {
    	            for(int c = 0; c < cols; c++) {
    	                cell = row.getCell((short)c);
    	                if( (c == 0) && (r == 0) && (!(cell.getStringCellValue() == null))
    	                	&& (!cell.getStringCellValue().equalsIgnoreCase("")) ) {
    	                	//System.out.println("cell: " + cell.getStringCellValue());
    	                	globalSheetName = cell.getStringCellValue();
    	                	er.setGlobalSheetName(globalSheetName);
    	                }
    	                if( (c == 0) && (!(cell.getStringCellValue() == null))
    	                	&& (!cell.getStringCellValue().equalsIgnoreCase("")) 
    	                	&& (cell.getStringCellValue().equalsIgnoreCase("input")) ) {
    	                	//System.out.println("cell: " + cell.getStringCellValue());
    	                	rowInputBegin = r + 2;
    	                }
    	                if( (c == 0) && (!(cell.getStringCellValue() == null))
    	                	&& (!cell.getStringCellValue().equalsIgnoreCase("")) 
    	                	&& (cell.getStringCellValue().equalsIgnoreCase("output")) ) {
    	                	//System.out.println("cell: " + cell.getStringCellValue());
    	                	rowOutputBegin = r + 2;
    	                	rowInputEnd = r - 3;
    	                }
    	                if( (c == 0) && (!(cell.getStringCellValue() == null)) 
    	                	&& (r > rowInputBegin) && ( rowInputBegin!=-1 )
    	                	&& ( rowInputEnd == -1  )) {
    	                	
    	                	Input toAdd = new Input();
    	                	toAdd.setName( cell.getStringCellValue() );
    	                	String rowType = row.getCell((short)(c+2)).getStringCellValue();
    	                	String rowTypeAlt = row.getCell((short)(c+10)).getStringCellValue();
    	                	
    	                	toAdd.setType( manageType( rowType , rowTypeAlt ) );
    	                	
    	                	try{
    	                		toAdd.setLimit( row.getCell((short)(c+11) ).getNumericCellValue() );
    	                	}catch(Exception e)
    	                	{
    	                		toAdd.setLimit( row.getCell((short)(c+11) ).getStringCellValue() );
    	    	            }
    	                	inputs.add(toAdd);
    	                }
    	                if( (c == 0) && (!(cell.getStringCellValue() == null)) 
        	                	&& (r > rowOutputBegin) && ( rowOutputBegin!=-1 )) {
        	                	
        	                	Output toAdd = new Output();
        	                	toAdd.setName( cell.getStringCellValue() );
        	                	String rowType = row.getCell((short)(c+2)).getStringCellValue();
        	                	String rowTypeAlt = row.getCell((short)(c+10)).getStringCellValue();
        	                	
        	                	toAdd.setType( manageType( rowType , rowTypeAlt ) );
        	                	
        	                	outputs.add(toAdd);
        	                }
    	                if( (c == 0) && (!(cell.getStringCellValue() == null)) ) {
        	                	if( cell.getStringCellValue().equalsIgnoreCase("Service name") )
        	                	{
        	                		String group = row.getCell((short)(c+1)).getStringCellValue();
        	                		er.setGroup(group);
        	                	}
        	                }
    	                
    	                
    	            }
    	        }
    	    }
    	    System.out.println( "global name: " + globalSheetName );
    	    System.out.println( "inputs begin at row: " + rowInputBegin + " and ends at row: "
    	    		+ rowInputEnd);
    	    System.out.println( "outputs begin at row: " + rowOutputBegin + " and ends at row: "
    	    		+ rowOutputEnd);
    	    System.out.println("inputs: " + inputs.size());
    	    System.out.println("outputs: " + outputs.size());
    	    
    	    System.out.println("test input: " + inputs.get(6).toString());
    	    
    	    System.out.println("test outut: " + outputs.get(6).toString());
    	    
    	    er.setInputs(inputs);
    	    er.setOutputs(outputs);
    	    return er;
    	} catch (InvalidFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	System.out.println( "Excel read" );
		return er;
	}
    	

    private static String manageType( String s , String alt )
    {
    	//FIRST
    	if(s.equalsIgnoreCase("char"))
    	{
    		return "xs:string";
    	}
    	if(s.equalsIgnoreCase("CreditCode3"))
    	{
    		return "crecod3:CreditCode3";
    	}
    	if(s.equalsIgnoreCase("CreditTechnicalForm"))
    	{
    		return "cretecfor:CreditTechnicalForm";
    	}
    	if(s.equalsIgnoreCase("PositiveAmount"))
    	{
    		return "posamo:PositiveAmount";
    	}
    	if(s.equalsIgnoreCase("ISODate"))
    	{
    		return "isodat:ISODate";
    	}
    	if(s.equalsIgnoreCase("ProductArrangementType"))
    	{
    		return "proarrtyp:ProductArrangementType";
    	}
    	if(s.equalsIgnoreCase("int") || s.equalsIgnoreCase("integer"))
    	{
    		return "xs:int";
    	}
    	if(s.equalsIgnoreCase("decimal"))
    	{
    		return "xs:long";
    	}
    	if(s.equalsIgnoreCase("long"))
    	{
    		return "xs:long";
    	}
    	
    	//ALT
    	if(alt.equalsIgnoreCase("long"))
    	{
    		return "xs:long";
    	}
    	if(alt.equalsIgnoreCase("decimal"))
    	{
    		return "xs:long";
    	}
    	if(alt.equalsIgnoreCase("char"))
    	{
    		return "xs:string";
    	}
    	if(alt.equalsIgnoreCase("string"))
    	{
    		return "xs:string";
    	}
    	
    	return "xs:string";
    }
    
    
    private static int getNumCols( XSSFSheet sheet )
    {
    	int colsL = 0;
	    int tmp = 0;
	    int rows = sheet.getPhysicalNumberOfRows();
	 
	    // This trick ensures that we get the data properly even if it doesn't start from first few rows
	    for(int i = 0; i < 10 || i < rows; i++) {
	    	XSSFRow row = sheet.getRow(i);
	        if(row != null) {
	            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
	            //System.out.println( "cells: " + tmp );
	            if(tmp > colsL) colsL = tmp;
	        }
	    }
	    return colsL;

    }

}
