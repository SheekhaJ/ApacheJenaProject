package com.semanticweb.apachejenaproject;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.VCARD4;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        final String personURI = "http://somewhere/personURI";
        final String firstName = "John";
        final String lastName = "Smith";
        final String fullName = firstName + " " + lastName;
        
        Model model = ModelFactory.createDefaultModel();
        Resource user1 = model.createResource(personURI).addProperty(VCARD4.fn, fullName);
        Resource user2 = user1.addProperty(VCARD4.given_name, "Jane").addProperty(VCARD4.family_name, "Doe");
        
//        StmtIterator iter = model.listStatements();
//        
//        while(iter.hasNext()) {
//        	Statement st = iter.nextStatement();
//        	Resource subject = st.getSubject();
//        	Resource predicate = st.getPredicate();
//        	RDFNode object = st.getObject();
//        	
//        	System.out.println("Subject is '"+subject+"' Predicate is '"+predicate+"' Object is '"+object+"'");
//        	
//        	if(object instanceof Resource) {
//        		System.out.println("Object is of type Resource - "+object.toString());
//        	} else {
//        		System.out.println("Object is of type Literal - "+object.toString());
//        	}        	
//        }
        
        //Writing the RDF in XML format to console
        model.write(System.out);        
        
        System.out.println("--------Writing RDF in Triples format------------------");
        model.write(System.out, "N-TRIPLES");
        
    }
}
