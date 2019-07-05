package com.semanticweb.apachejenaproject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD4;

/**
 * Hello world!
 *
 */
public class App 
{
    static final String fileName = "rdfOutput.rdf";
    static final String triplesFileName = "triplesRDFOutput.rdf";
    
    static final String personURI = "http://somewhere/personURI";
    static final String firstName = "John";
    static final String lastName = "Smith";
    static final String fullName = firstName + " " + lastName;
    
    public static void main( String[] args ) throws IOException
    {   
        Model model = ModelFactory.createDefaultModel();
        Resource user1 = model.createResource(personURI).addProperty(VCARD4.fn, fullName);
        Resource user2 = user1.addProperty(VCARD4.given_name, "Jane").addProperty(VCARD4.family_name, "Doe");
        
        readStatements(model);
        
        writeRDF(model);
        
        readRDF();
    }
    
    private static void readStatements(Model model) {
      StmtIterator iter = model.listStatements();
      
      while(iter.hasNext()) {
      	Statement st = iter.nextStatement();
      	Resource subject = st.getSubject();
      	Resource predicate = st.getPredicate();
      	RDFNode object = st.getObject();
      	
      	System.out.println("Subject is '"+subject+"' Predicate is '"+predicate+"' Object is '"+object+"'");
      	
      	if(object instanceof Resource) {
      		System.out.println("Object is of type Resource - "+object.toString());
      	} else {
      		System.out.println("Object is of type Literal - "+object.toString());
      	}        	
      }
    }
    
    private static void writeRDF(Model model) throws IOException {
    	File rdfOutputFile = new File(fileName);
        
        //Writing the RDF in XML format to console
        model.write(new FileWriter(rdfOutputFile));        
        
        System.out.println("--------Writing RDF in Triples format------------------");
        model.write(new FileWriter(new File(triplesFileName)), "N-TRIPLES");
    }
    
    private static void readRDF() {
    	//Read model from the file with name fileName
    	Model model = ModelFactory.createDefaultModel();
    	InputStream in = FileManager.get().open(fileName);
    	
    	if(in == null) {
    		throw new IllegalArgumentException("File with name "+fileName+" is not found.");
    	}
    	model.read(in, personURI);
    	model.write(System.out);
    }
    
}
