package excel.excel;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Writer {
	
	public void write( ExcelRead er )
	{
		   
        try {
        	String globalSheetName = er.getGlobalSheetName();
    		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

    		// root elements
    		Document doc = docBuilder.newDocument();
    		
    		Element rootElement = doc.createElement( globalSheetName );
    		doc.appendChild(rootElement);
    		
    		//REQUEST
    		Element request = doc.createElement( "xs:complexType");
    		request.setAttribute( "name" , globalSheetName + "Request" );
    		
    		Element header = getHeader( doc );
    		request.appendChild(header);
    		Element inputHead = getInputHead( doc , globalSheetName ); 
    		request.appendChild(inputHead);
    		
    		//RESPONSE
    		Element response = doc.createElement( "xs:complexType");
    		response.setAttribute( "name" , globalSheetName + "Response" );
    		
    		Element status = getStatus( doc );
    		response.appendChild(status);
    		Element outputHead = getOutputHead( doc , globalSheetName ); 
    		response.appendChild(outputHead);
    		
    		
    		//INPUT
    		Element inputBlock = doc.createElement( "xs:complexType" );
    		inputBlock.setAttribute("name", globalSheetName + "Input");
    		

    		for(Input i : er.getInputs())
    		{
    			String name = i.getName();
    			if( !name.equalsIgnoreCase("none") )
    			{
		    		Element inputE = doc.createElement( "xs:element");
	    			inputE.setAttribute("name", name);
	    			inputE.setAttribute("type", i.getType());
	    			inputBlock.appendChild(inputE);
    			}
    		}
    		
    		//OUTPUT
    		Element outputBlock = doc.createElement( "xs:complexType" );
    		outputBlock.setAttribute("name", globalSheetName + "Output");
    		
    		for(Output i : er.getOutputs())
    		{
    			String name = i.getName();
    			if( !name.equalsIgnoreCase("none") )
    			{
    				Element outputE = doc.createElement( "xs:element");
    				outputE.setAttribute("name", name);
    				outputE.setAttribute("type", i.getType());
    				outputBlock.appendChild(outputE);
    			}
    		}
    		
    		
    		rootElement.appendChild(request);
    		rootElement.appendChild(response);
    		rootElement.appendChild(inputBlock);
    		rootElement.appendChild(outputBlock);

    		
    		// write the content into xml file
    		TransformerFactory transformerFactory = TransformerFactory.newInstance();
    		Transformer transformer = transformerFactory.newTransformer();
    		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    		transformer.setOutputProperty(
    				   "{http://xml.apache.org/xslt}indent-amount", "5");
    		DOMSource source = new DOMSource(doc);
    		String fileOutput = "result\\result.xsd";
    		StreamResult result = new StreamResult(new File( fileOutput ));

    		// Output to console for testing
    		// StreamResult result = new StreamResult(System.out);

    		transformer.transform(source, result);


    	  } catch (ParserConfigurationException pce) {
    		pce.printStackTrace();
    	  } catch (TransformerException tfe) {
    		tfe.printStackTrace();
    	  }
        System.out.println("Xsd written");
	
	}
	
	private static Element getInputHead(Document doc , String globalSheetName)
	{
		Element input = doc.createElement( "xs:element" );
		input.setAttribute("name", globalSheetName + "Input");
		input.setAttribute( "type" , "tns:" + globalSheetName + "Input");
		return input;
	}
	
	private static Element getOutputHead(Document doc , String globalSheetName)
	{
		Element input = doc.createElement( "xs:element" );
		input.setAttribute("name", globalSheetName + "Output");
		input.setAttribute( "type" , "tns:" + globalSheetName + "Output");
		return input;
	}
	
	private static Element getStatus(Document doc )
	{
		Element status = doc.createElement( "xs:element" );
		status.setAttribute("name", "responseStatus");
		status.setAttribute( "type" , "rstat:responseStatus");
		return status;
	}
	
	private static Element getHeader(Document doc)
	{
		Element header = doc.createElement( "xs:element");
		header.setAttribute("name", "header");
		header.setAttribute( "type" , "head:Header");
		return header;
	}

}
