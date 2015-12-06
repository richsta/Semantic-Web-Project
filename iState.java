package org.eclipse.wtp.iState;
//package org.semweb.assign6;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.iri.impl.Main;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;



/**
 * Servlet implementation class iState
 */
@WebServlet("/iState")
public class iState extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public iState() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	static String[] sparqlTest(String s)
	{
		FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
		Model model = FileManager.get().loadModel("/crime.rdf");
		String queryString = 
				"PREFIX rdf: <http://w3.org/1999/02/22-rdf-syntax-ns#> " +
		"PREFIX crime: <http://www.semanticweb.org/dell/ontologies/2015/10/untitled-ontology-13#>" +
		"PREFIX st: <file:///>" +
		"SELECT * WHERE {" +
		"st:" +s+ " crime:violentNumber ?vnumber ;" +
	"crime:violentRate ?vrate ;" +
	"crime:propertyCrimeNumber ?pnumber ;" +
	"crime:propertyCrimeRate ?prate ;" +
	"crime:picture ?pic." +
		"}";
		
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try
		{
			ResultSet results = qexec.execSelect();
			
		while (results.hasNext()) {
			QuerySolution soln = results.nextSolution();
			String type0 = soln.get("pic").toString();
			System.out.println("Picture of "+s);
			System.out.println(type0);
			
			String type1 = soln.get("vnumber").toString();
			System.out.println("Violence Number for "+s);
			System.out.println(type1);
			
			String type2 = soln.get("vrate").toString();
			System.out.println("Violence Rate for "+s);
			System.out.println(type2);
			
			String type3 = soln.get("pnumber").toString();
			System.out.println("Property Crime Number for "+s);
			System.out.println(type3);
			
			String type4 = soln.get("prate").toString();
			System.out.println("Property Rate for "+s);
			System.out.println(type4);
			
			String[] crime = {type1, type2, type3, type4, type0};
			return crime;
			
		}
	}
		finally {
			qexec.close();
		}
		return null;
}
	
	
	
	
	
	
	
	
	
	static List<String> uniTest(String s)
	{
		FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
		Model model = FileManager.get().loadModel("/university.rdf");
		String queryString2 = 
				"PREFIX rdf: <http://w3.org/1999/02/22-rdf-syntax-ns#> " +
						"PREFIX uni: <http://www.semanticweb.org/dell/ontologies/2015/10/untitled-ontology-13#>" +
						"PREFIX st: <file:///>" +
						"SELECT * WHERE {?univ uni:state '"+ s +"';"
								+ "uni:city ?city;"
								+ "uni:address ?address;"
						+ "uni:url ?url}";
		System.out.println("\nInstitutes in "+s);
              
		
		Query query = QueryFactory.create(queryString2);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try
		{
			ResultSet results = qexec.execSelect();
			List<String> myList = new ArrayList<String>();
		while (results.hasNext()) {
			QuerySolution soln = results.nextSolution();
			
			String type7 = soln.get("city").toString();
			System.out.println(type7);
			
			String type6 = soln.get("address").toString();
			System.out.println(type6);
			
			String typeorig = soln.get("univ").toString();
			String type5 = typeorig.replace("file:///", "");
			
			System.out.println(typeorig.replace("file:///", ""));
			
			String type8 = soln.get("url").toString();
			System.out.println(type8);
			
			myList.add(type5);
			myList.add(type6);
			myList.add(type7);
			myList.add(type8);
		}
		return myList;
	}
		finally {
			
			qexec.close();
		}
}
	
	
	static List<String> twitterTest(String s)
	{
		FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
		Model model = FileManager.get().loadModel("/twitter.rdf");
		String queryString3 = 
				"PREFIX rdf: <http://w3.org/1999/02/22-rdf-syntax-ns#> " +
		"PREFIX twitter: <http://www.semanticweb.org/dell/ontologies/2015/10/untitled-ontology-13>" +
		"PREFIX st: <file:///>" +		
"select * where {st:" +s+ " a ?type; twitter:mentionedin ?tweet. ?tweet twitter:text ?status. ?tweet twitter:retweeted ?ret. ?tweet twitter:favorited ?fav.}";
		
		System.out.println("\nTweets in "+s);
		
		
		Query query = QueryFactory.create(queryString3);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try
		{
			ResultSet results = qexec.execSelect();
			List<String> myList = new ArrayList<String>();
		while (results.hasNext()) {
			QuerySolution soln = results.nextSolution();
			String type9 = soln.get("status").toString();		
			System.out.println(type9);
			
			String type10 = soln.get("ret").toString();		
			System.out.println(type10);
			
			String type11 = soln.get("fav").toString();		
			System.out.println(type11);
			
			myList.add(type9);
			myList.add(type10);
			myList.add(type11);
		
		
		}
		return myList;
	}
		finally {
			qexec.close();
		}
}
	
	
	static List<String> yelpTest(String s)
	{
		FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
		Model model = FileManager.get().loadModel("/yelp.rdf");
		String queryString2 = 
				"PREFIX rdf: <http://w3.org/1999/02/22-rdf-syntax-ns#> " +
		"PREFIX yelp: <http://www.semanticweb.org/dell/ontologies/2015/10/untitled-ontology-13#>" +
		"PREFIX st: <file:///>" +
		"select * where {st:" +s+ " a ?type; yelp:happening ?event. ?event yelp:name ?name. ?event yelp:rating ?rate. ?event yelp:categories ?cat.}";
		System.out.println("\nEvents in "+s);

		Query query = QueryFactory.create(queryString2);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try
		{
			ResultSet results = qexec.execSelect();
			List<String> myList = new ArrayList<String>();
		while (results.hasNext()) {
			QuerySolution soln = results.nextSolution();
			String type12 = soln.get("name").toString();
			System.out.println(type12);
			
			String type13 = soln.get("rate").toString();
			System.out.println(type13);
			
			String type14 = soln.get("cat").toString();
			System.out.println(type14);
			
			myList.add(type12);
			myList.add(type13);
			myList.add(type14);
			
		
		}
		return myList;
	}
		finally {
			qexec.close();
		}
}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		
		String firststate = request.getParameter("first");

		String secondstate = request.getParameter("second");
		
		String[] first = sparqlTest(firststate);
		String[] second = sparqlTest(secondstate);
		
		List<String> firstuni = uniTest(firststate);
		List<String> seconduni = uniTest(secondstate);
		
		List<String> firsttwi = twitterTest(firststate);
		List<String> secondtwi = twitterTest(secondstate);
		
		List<String> firstyelp = yelpTest(firststate);
		List<String> secondyelp = yelpTest(secondstate);		
		
		out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'><html xmlns='http://www.w3.org/1999/xhtml'><head>");
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"+
				"<title>iState</title><link rel='stylesheet' type='text/css' href='view.css' media='all'>"+
				"<link href='style.css' rel='stylesheet' type='text/css' />"+
"<script type='text/javascript' src='view.js'></script>"
+ "<script src='src/jquery.collapse.js'></script>");
		  out.println("<link rel='stylesheet' href='//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css'>		  <script src='//code.jquery.com/jquery-1.10.2.js'></script>		  <script src='//code.jquery.com/ui/1.11.4/jquery-ui.js'></script> <link rel='stylesheet' href='/resources/demos/style.css'>		  <script>	  $(function() {		    $( '#accordion' ).accordion({		      heightStyle: 'content'		    });  });	  </script>");
		out.println("</head>");
		out.println("<body><div class='stars'></div>"+
    "<div class='twinkling'></div><div class='clouds'></div>");
		
		String str = first[1].replaceAll(",", "");
		float a = (Float.parseFloat(str)); 	

		System.out.println(a);
		String str1 = second[1].replaceAll(",", "");
		float b = (Float.parseFloat(str1));

		System.out.println(b);
		String state;
		if (a>b){
			state = secondstate+" is safer!";
			
		}
		else if(a == b)
		{
			state = "Both states have similar crime rates!";
		}else {state = firststate +" is safer!";
				
		};
		
		System.out.println(state);
		
		Integer u = firstuni.size();
		Integer v = seconduni.size();
		String edu;
		if (u>v){
			edu = firststate+" has better educational prospects!";
			
		}
		else if(u == v)
		{
			edu = "Both states have similar educational prospects.";
		}else {edu = secondstate +" has better educational prospects!";
				
		};

		float e = Float.parseFloat(firstyelp.get(1));
		float f = Float.parseFloat(firstyelp.get(4));
		float g = Float.parseFloat(firstyelp.get(7));
		float h = Float.parseFloat(firstyelp.get(10));
		float k = Float.parseFloat(firstyelp.get(13));
		
		float j = (e+f+g+h+k)/5;
		
		float p = Float.parseFloat(secondyelp.get(1));
		float q = Float.parseFloat(secondyelp.get(4));
		float r = Float.parseFloat(secondyelp.get(7));
		float s = Float.parseFloat(secondyelp.get(10));
		float t = Float.parseFloat(secondyelp.get(13));
		
		float m = (p+q+r+s+t)/5;
		
		String yelp;
		if (j>m){
			yelp = firststate+" has better social life!";
			
		}
		else if(j == m)
		{
			yelp = "Both states have similar social life.";
		}else {yelp = secondstate +" has better social life!";
				
		};
		
		out.println("<div id='page_container'>"
				+ "<center><img src='http://i.imgur.com/XF1IajZ.gif' alt='iState' style='width:264px;height:95px;'></img></center></br>" 	
				+ "<div id='pop'><div align = 'center'><button style='height:50px;width:200px;color:green;font-size:20px' onClick='compare()'>Compare the States</button>"
				+ "<script>function compare(){"
				+ "document.getElementById('comp').innerHTML = '<b>"+state+"</br>"+edu+"</br>"+yelp+"</b>';}</script>"
				+ "<center><h2 id = 'comp'></h2></center></div>"
				+ "</div>");
		
		out.println("<div id='page_container'>");
		out.println("<div class='left-panel'><h1><b>"+firststate+ ":</br> <img src = '"+first[4]+"' alt='iState' style='width:404px;height:242px;'></b></h1></br>");
				
		out.println("<h1>"+firststate+" Crime Statistics</h1>");
		out.println("<div class='pad'>");
		out.println("<b>Violence number: "+first[0]+"</b></br>"+
				"<b>Violence Rate: "+first[1]+"</b></br>"+
		"<b>Property Crime Number: "+first[2]+"</b></br>"+
		"<b>Property Crime Rate: "+first[3]+"</b></br>");
		
		 out.println("</div>");
		
out.println("<h1>"+firststate+" Universities</h1>");

out.println("<div class='pad'>");
for(int i=0; i<21; i+=4){
out.println("<b>Univeristy Name: "+firstuni.get(i)+"</b></br>"+
		"<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbspAddress: "+firstuni.get(i+1)+"</b></br>"+
"<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbspCity: "+firstuni.get(i+2)+"</b></br>"+
"<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbspWebsite URL: <a href='http://"+firstuni.get(i+3)+"'>"+firstuni.get(i+3)+"</a></b></br>");
}
out.println("</br><b>Number of Universities: "+firstuni.size()+"</b>");
out.println("</div>");

out.println("<h1>"+firststate+" Tweets</h1>");

out.println("<div class='pad'>");
for(int i=0; i<15; i+=3){
out.println("<b>Tweet: "+firsttwi.get(i)+"</b></br>"+
		"<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbspRetweets: "+firsttwi.get(i+1)+"</b></br>"+
"<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbspFavorites: "+firsttwi.get(i+2)+"</b></br>");
}
out.println("</div>");

out.println("<h1>"+firststate+" Events</h1>");

out.println("<div class='pad'>");
for(int i=0; i<15; i+=3){
out.println("<b> Event: "+firstyelp.get(i)+"</b></br>"+
		"<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbspRating: "+firstyelp.get(i+1)+"</b></br>"+
"<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbspCategories: "+firstyelp.get(i+2)+"</b></br>");
}
out.println("</div>");
out.println("</div>");




out.println("<div class='right-panel'><h1><b>"+secondstate+ ":</br> <img src = '"+second[4]+"' alt='iState' style='width:404px;height:242px;'></b></h1></br>");



		out.println("<h1>"+secondstate+" Crime Statistics</h1>");
	

out.println("<div class='pad'>");
		out.println("<b>Violence number: "+second[0]+"</b></br>"+
				"<b>Violence Rate: "+second[1]+"</b></br>"+
		"<b>Property Crime Number: "+second[2]+"</b></br>"+
		"<b>Property Crime Rate: "+second[3]+"</b></br>");

out.println("</div>");
		
		out.println("<h1>"+secondstate+" Universities</h1>");


out.println("<div class='pad'>");
for(int i=0; i<21; i+=4){
		out.println("<b>Univeristy Name: "+seconduni.get(i)+"</b></br>"+
				"<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbspAddress: "+seconduni.get(i+1)+"</b></br>"+
		"<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbspCity: "+seconduni.get(i+2)+"</b></br>"+
		"<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbspWebsite URL: <a href='http://"+seconduni.get(i+3)+"'>"+seconduni.get(i+3)+"</a></b></br>");
}

out.println("</br><b>Number of Universities: "+seconduni.size()+"</b>");
out.println("</div>");

out.println("<h1>"+secondstate+" Tweets</h1>");


out.println("<div class='pad'>");
for(int i=0; i<15; i+=3){
out.println("<b>Tweet: "+secondtwi.get(i)+"</b></br>"+
		"<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbspRetweets: "+secondtwi.get(i+1)+"</b></br>"+
"<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbspFavorites: "+secondtwi.get(i+2)+"</b></br>");
}
out.println("</div>");


out.println("<h1>"+secondstate+" Events</h1>");


out.println("<div class='pad'>");
for(int i=0; i<15; i+=3){
out.println("<b>Event: "+secondyelp.get(i)+"</b></br>"+
		"<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbspRating: "+secondyelp.get(i+1)+"</b></br>"+
"<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbspCategories: "+secondyelp.get(i+2)+"</b></br>");

}

out.println("</div>");

				out.println("</div>");
			
				
				out.println("</body></html>");
		
		
		
		
	}

}
