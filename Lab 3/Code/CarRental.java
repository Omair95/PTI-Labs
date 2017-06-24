import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.jdom.Attribute;
import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;
import java.util.Scanner;
import java.io.IOException;
import java.util.Random;

public class CarRental {

    /**
     * Read and parse an xml document from the file at example.xml.
     * @return the JDOM document parsed from the file.
     */
    public static Document readDocument() {
        try {
            SAXBuilder builder = new SAXBuilder();
            Document anotherDocument = builder.build(new File("carrental.xml"));
            return anotherDocument;
        } catch(JDOMException e) {
            e.printStackTrace();
        } catch(NullPointerException e) {
            e.printStackTrace();
        } catch(java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method creates a JDOM document with elements that represent the
     * properties of a car.
     * @return a JDOM Document that represents the properties of a car.
     */
    public static Document createDocument() {
        // Create the root element
        Element carElement = new Element("carrental");
        //create the document
        Document myDocument = new Document(carElement);
        return myDocument;
    }

    /**
     * This method accesses a child element of the root element
     * @param myDocument a JDOM document
     */
    public static void accessChildElement(Document myDocument) {
        //some setup
        Element carElement = myDocument.getRootElement();
        //Access a child element
        Element yearElement = carElement.getChild("year");
        //show success or failure
        if(yearElement != null) {
            System.out.println("Here is the element we found: " +
                yearElement.getName() + ".  Its content: " +
                yearElement.getText() + "\n");
        } else {
            System.out.println("Something is wrong.  We did not find a year Element");
        }
    }

    /**
     * This method removes a child element from a document.
     * @param myDocument a JDOM document.
     */
    public static void removeChildElement(Document myDocument) {
        //some setup
        System.out.println("About to remove the year element.\nThe current document:");
        outputDocument(myDocument);
        Element carElement = myDocument.getRootElement();
        //remove a child Element
        boolean removed = carElement.removeChild("year");
        //show success or failure
        if(removed) {
            System.out.println("Here is the modified document without year:");
            outputDocument(myDocument);
        } else {
            System.out.println("Something happened.  We were unable to remove the year element.");
        }
    }

    /**
     * This method shows how to use XMLOutputter to output a JDOM document to
     * the stdout.
     * @param myDocument a JDOM document.
     */
    public static void outputDocument(Document myDocument) {
        try {
            // XMLOutputter outputter = new XMLOutputter("  ", true);
            XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
            outputter.output(myDocument, System.out);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method shows how to use XMLOutputter to output a JDOM document to
     * a file located at myFile.xml.
     * @param myDocument a JDOM document.
     */
    public static void outputDocumentToFile(Document myDocument) {
        //setup this like outputDocument
        try {
            // XMLOutputter outputter = new XMLOutputter("  ", true);
            XMLOutputter outputter = new XMLOutputter();
            //output to a file
            FileWriter writer = new FileWriter("carrental.xml");
            outputter.output(myDocument, writer);
            writer.close();
        } catch(java.io.IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method takes a JDOM document in memory, an XSLT file at example.xslt,
     * and outputs the results to stdout.
     * @param myDocument a JDOM document .
     */
    public static void executeXSLT(Document myDocument) {
  		try {
  			TransformerFactory tFactory = TransformerFactory.newInstance();
        // Make the input sources for the XML and XSLT documents
        org.jdom.output.DOMOutputter outputter = new org.jdom.output.DOMOutputter();
        org.w3c.dom.Document domDocument = outputter.output(myDocument);
        javax.xml.transform.Source xmlSource = new javax.xml.transform.dom.DOMSource(domDocument);
        StreamSource xsltSource = new StreamSource(new FileInputStream("carrental.xslt"));
  			//Make the output result for the finished document
        StreamResult xmlResult = new StreamResult(System.out);
  			//Get a XSLT transformer
  			Transformer transformer = tFactory.newTransformer(xsltSource);
  			//do the transform
  			transformer.transform(xmlSource, xmlResult);
          } catch(FileNotFoundException e) {
              e.printStackTrace();
          } catch(TransformerConfigurationException e) {
              e.printStackTrace();
  		} catch(TransformerException e) {
              e.printStackTrace();
      } catch(org.jdom.JDOMException e) {
              e.printStackTrace();
      }
	}

    public static void newRental()
    {
  		Scanner reader = new Scanner(System.in);
  		System.out.println("Enter car maker: ");
  		String carM = reader.nextLine();
  		System.out.println("Enter car model: ");
  		String carModel = reader.nextLine();
  		System.out.println("Enter start date: ");
  		String start = reader.nextLine();
  		System.out.println("Enter finish date: ");
  		String finish = reader.nextLine();
      try {
        Document document = readDocument();
        Element root = document.getRootElement();
        Random rand = new Random();
        int n = rand.nextInt(50) + 1;
        Element rental = new Element("rental");
        rental.setAttribute("id", Integer.toString(n));
        rental.addContent(new Element("make").setText(carM));
        rental.addContent(new Element("model").setText(carModel));
        rental.addContent(new Element("start").setText(start));
        rental.addContent(new Element("end").setText(finish));
        root.addContent(rental);
        document.setContent(root);
        XMLOutputter xmlOutput = new XMLOutputter();
  	    xmlOutput.setFormat(Format.getPrettyFormat());
  		  xmlOutput.output(document, new FileWriter("carrental.xml"));
      } catch (IOException io) {
		      io.printStackTrace();
        }
    }

    /**
     * Main method that allows the various methods to be used.
     * It takes a single command line parameter.  If none are
     * specified, or the parameter is not understood, it prints
     * its usage.
     */
    public static void main(String argv[]) {
        if(argv.length == 1) {
            String command = argv[0];
            if(command.equals("reset")) outputDocumentToFile(createDocument());
            else if (command.equals("new")) { newRental(); }
            else if (command.equals("list")) outputDocument(readDocument());
            else if (command.equals("xslt")) executeXSLT(readDocument());
            else {
                System.out.println(command + " is not a valid option.");
                printUsage();
            }
        } else {
            printUsage();
        }
    }

    /**
     * Convience method to print the usage options for the class.
     */
    public static void printUsage() {
        System.out.println("Usage: CarRental [option] \nwhere option is one of the following:");
        System.out.println("  reset -  create a new document and save it carrental.xml");
        System.out.println("  new - A new rental is added in the carrental.xml file");
        System.out.println("  list - Read the carrental.xml XML document into memory and print it to the console");
        System.out.println("  xslt - create a new document and transform it to HTML with the XSLT stylesheet in carrental.xslt");
    }
}
