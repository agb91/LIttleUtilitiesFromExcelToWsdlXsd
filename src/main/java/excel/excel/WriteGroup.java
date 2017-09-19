package excel.excel;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteGroup {
	
	public void writeGroup( String groupName )
	{
		//System.out.println( "GROUP: " + groupName );
		try {
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
		
			// root elements
			Document doc = docBuilder.newDocument();
			Element schema = doc.createElement( "xs:schema" );
			schema.setAttribute( "xmlns:tns", "http://unicredit.eu/PLCredits/V1/" + groupName + "/xsd" );
			schema.setAttribute( "xmlns:xs", "http://www.w3.org/2001/XMLSchema" );
			schema.setAttribute( "xmlns:pn", "http://unicredit.eu/PLCredits/V1/ProposalNumber/xsd" );
			schema.setAttribute( "xmlns:head", "http://unicredit.eu/PLCredits/header/xsd" );
			schema.setAttribute( "xmlns:rstat", "http://unicredit.eu/PLCredits/responseStatus/xsd" );		
			schema.setAttribute( "xmlns:ci", "http://unicredit.eu/PLCredits/V1/CustomerIdentifier/xsd" );
			schema.setAttribute( "xmlns:crecod3", "http://unicredit.eu/xmlns/CreditCode3/V1");		
			schema.setAttribute( "xmlns:cretecfor", "http://unicredit.eu/xmlns/CreditTechnicalForm/V1");		
			schema.setAttribute( "xmlns:posamo", "http://unicredit.eu/xmlns/PositiveAmount/V1");		
			schema.setAttribute( "xmlns:isodat", "http://unicredit.eu/xmlns/ISODate/V1");		
			schema.setAttribute( "xmlns:proarrtyp", "http://unicredit.eu/xmlns/ProductArrangementType/V1");	
			schema.setAttribute( "elementFormDefault", "qualified");
			schema.setAttribute( "attributeFormDefault", "unqualified");
			schema.setAttribute( "targetNamespace", "http://unicredit.eu/PLCredits/V1/CollateralAssetAdministrationResourceItem/xsd");		
			schema.setAttribute( "version", "1.0");

			Element xsImport = doc.createElement( "xs:import" );
			xsImport.setAttribute("namespace", "http://unicredit.eu/PLCredits/V1/ProposalNumber/xsd");
			xsImport.setAttribute("schemaLocation", "ProposalNumberSchema.xsd");
			schema.appendChild(xsImport);
			
			xsImport = doc.createElement( "xs:import" );
			xsImport.setAttribute("namespace", "http://unicredit.eu/PLCredits/header/xsd");
			xsImport.setAttribute("schemaLocation", "header-v0.2.xsd");
			schema.appendChild(xsImport);
			
			xsImport = doc.createElement( "xs:import" );
			xsImport.setAttribute("namespace", "http://unicredit.eu/PLCredits/responseStatus/xsd");
			xsImport.setAttribute("schemaLocation", "responseStatus.xsd");
			schema.appendChild(xsImport);

			xsImport = doc.createElement( "xs:import" );
			xsImport.setAttribute("namespace", "http://unicredit.eu/PLCredits/V1/CustomerIdentifier/xsd");
			xsImport.setAttribute("schemaLocation", "CustomerIdentifierSchema.xsd");
			schema.appendChild(xsImport);
			
			xsImport = doc.createElement( "xs:import" );
			xsImport.setAttribute("namespace", "http://unicredit.eu/xmlns/CreditCode3/V1");
			xsImport.setAttribute("schemaLocation", "cmm_CreditCode3.xsd");
			schema.appendChild(xsImport);

			xsImport = doc.createElement( "xs:import" );
			xsImport.setAttribute("namespace", "http://unicredit.eu/xmlns/CreditTechnicalForm/V1");
			xsImport.setAttribute("schemaLocation", "cmm_CreditTechnicalForm.xsd");
			schema.appendChild(xsImport);
			
			xsImport = doc.createElement( "xs:import" );
			xsImport.setAttribute("namespace", "http://unicredit.eu/xmlns/PositiveAmount/V1");
			xsImport.setAttribute("schemaLocation", "cmm_PositiveAmount.xsd");
			schema.appendChild(xsImport);

			xsImport = doc.createElement( "xs:import" );
			xsImport.setAttribute("namespace", "http://unicredit.eu/xmlns/ISODate/V1");
			xsImport.setAttribute("schemaLocation", "cmm_ISODate.xsd");
			schema.appendChild(xsImport);
			
			xsImport = doc.createElement( "xs:import" );
			xsImport.setAttribute("namespace", "http://unicredit.eu/xmlns/ProductArrangementType/V1");
			xsImport.setAttribute("schemaLocation", "cmm_ProductArrangementType.xsd");
			schema.appendChild(xsImport);

			xsImport = doc.createElement( "xs:import" );
			xsImport.setAttribute("namespace", "http://unicredit.eu/xmlns/CreditProposalIdentifier8/V1");
			xsImport.setAttribute("schemaLocation", "cmm_CreditProposalIdentifier8.xsd");
			schema.appendChild(xsImport);
			
					
			doc.appendChild(schema);
			
			
			// write the content into xml file
    		TransformerFactory transformerFactory = TransformerFactory.newInstance();
    		Transformer transformer = transformerFactory.newTransformer();
    		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    		transformer.setOutputProperty(
    				   "{http://xml.apache.org/xslt}indent-amount", "5");
    		DOMSource source = new DOMSource(doc);
    		String fileOutput = "result\\resultGroup.xsd";
    		StreamResult result = new StreamResult(new File( fileOutput ));

    		// Output to console for testing
    		// StreamResult result = new StreamResult(System.out);

    		transformer.transform(source, result);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
