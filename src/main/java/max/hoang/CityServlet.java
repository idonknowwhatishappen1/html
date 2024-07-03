package max.hoang;

import java.io.*;
import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "CityServlet", urlPatterns = "/city") //annotation to declare the servlet

public class CityServlet extends HttpServlet {
    private City city;

    @Override
    public void init() throws ServletException {
        // Initialize ( khoi tao) the city with some sample data
        Set<Inhabitant> inhabitants = new HashSet<>();
        inhabitants.add(new Inhabitant("Hoang", "08/06/2002", "Married"));
        inhabitants.add(new Inhabitant("Phi", "02/04/2003", "Single"));
        inhabitants.add(new Inhabitant("Thu", "24/06/2003", "Married"));
        city = new City(inhabitants); // Create a new City object with the sample data
    }

    // The doGet method is called when a GET request is sent to the servlet (yeu cau http get)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
            String action = request.getParameter("action");

        if (action != null) {
            if (action.equals("searchLocal")) {
                String name = request.getParameter("name"); // Get the name parameter from the request
                Inhabitant local = city.searchLocal(name);     // Call searchLocal method of City object
                response.setContentType("text/html");       // Set the response content type to HTML


                try (PrintWriter out = response.getWriter()) {  // Get the PrintWriter object to write the response
                    out.println("<html><head><title>Search Result</title></head><body>");
                    out.println("<h1>Search Result</h1>");
                    if (local != null) {    // If inhabitant with given name is found
                        out.println("<p>Inhabitant found: " + local.getName() + "</p>");
                        out.println("<p>Date of Birth: " + local.getDob() + "</p>");
                        out.println("<p>Marital Status: " + local.getMarried() + "</p>");
                    } else {
                        out.println("<p>Inhabitant not found</p>");
                    }

                    //add a link to go back to the home page
                    out.println("<a href=\"index.jsp\">Back to Home</a>");
                    out.println("<div><a href=\"addInhabitant.html\">Add Inhabitants</a></div>");
                    out.println("<div><a href=\"searchInhabitants.html\">Search Inhabitants</a></div>");
                    out.println("<div><a href=\"getAllInhabitants.html\">Get All Inhabitants</a></div>");
                    out.println("<div><a href=\"setMaritalStatus.html\">Get/Set Marital Status</a></div>");
                    out.println("</body></html>");
                }

                // If action is getAllInhabitants, get all inhabitants and display them
            } else if (action.equals("getAllInhabitants")) {
                Set<Inhabitant> inhabitants = city.getInhabitants();
                response.setContentType("text/html");
                // Get the PrintWriter object to write the response
                try (PrintWriter out = response.getWriter()) {
                    out.println("<html><head><title>All Inhabitants</title></head><body>");
                    out.println("<h1>All Inhabitants</h1>");
                    for (Inhabitant local : inhabitants) {
                        out.println("<p>" + local.getName() + "</p>");
                    }

                    //add a link to go back to the home page
                    out.println("<a href=\"index.jsp\">Back to Home</a>");
                    out.println("<div><a href=\"addInhabitant.html\">Add Inhabitants</a></div>");
                    out.println("<div><a href=\"searchInhabitants.html\">Search Inhabitants</a></div>");
                    out.println("<div><a href=\"getAllInhabitants.html\">Get All Inhabitants</a></div>");
                    out.println("<div><a href=\"setMaritalStatus.html\">Get/Set Marital Status</a></div>");
                    out.println("</body></html>");
                }
            } else {
                response.setContentType("text/html");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<html><head><title>General Result</title></head><body>");
                    out.println("<h1>General Result</h1>");
                    out.println("<p>Invalid action</p>");
                    out.println("<a href=\"index.jsp\">Back to Home</a>");
                    out.println("<div><a href=\"addInhabitant.html\">Add Inhabitants</a></div>");
                    out.println("<div><a href=\"searchInhabitants.html\">Search Inhabitants</a></div>");
                    out.println("<div><a href=\"getAllInhabitants.html\">Get All Inhabitants</a></div>");
                    out.println("<div><a href=\"setMaritalStatus.html\">Get/Set Marital Status</a></div>");
                    out.println("</body></html>");
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");

        if (action != null) {
            if (action.equals("addInhabitant")) {
                String name = request.getParameter("name");
                String dob = request.getParameter("dob");
                String married = request.getParameter("married");
                city.addInhabitant(name, dob, married);
                response.setContentType("text/html");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<html><head><title>Add Inhabitant</title></head><body>");
                    out.println("<h1>Add Inhabitant</h1>");
                    out.println("<p>Inhabitant added Successfully</p>");

                    //add a link to go back to the home page
                    out.println("<a href=\"index.jsp\">Back to Home</a>");
                    out.println("<div><a href=\"addInhabitant.html\">Add Inhabitants</a></div>");
                    out.println("<div><a href=\"searchInhabitants.html\">Search Inhabitants</a></div>");
                    out.println("<div><a href=\"getAllInhabitants.html\">Get All Inhabitants</a></div>");
                    out.println("<div><a href=\"setMaritalStatus.html\">Get/Set Marital Status</a></div>");
                    out.println("</body></html>");
                }
            } else if (action.equals("setMaritalStatus")) {
                String name = request.getParameter("name");
                String married = request.getParameter("married");
                String result = city.setMaritalStatus(name, married); // Call setMaritalStatus method of City object
                response.setContentType("text/html");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<html><head><title>Set Marital Status</title></head><body>");
                    out.println("<h1>Set Marital Status</h1>");
                    out.println("<p>" + result + "</p>");

                    //add a link to go back to the home page
                    out.println("<a href=\"index.jsp\">Back to Home</a>");
                    out.println("<div><a href=\"addInhabitant.html\">Add Inhabitants</a></div>");
                    out.println("<div><a href=\"searchInhabitants.html\">Search Inhabitants</a></div>");
                    out.println("<div><a href=\"getAllInhabitants.html\">Get All Inhabitants</a></div>");
                    out.println("<div><a href=\"setMaritalStatus.html\">Get/Set Marital Status</a></div>");
                    out.println("</body></html>");
                }
            } else {
                response.setContentType("text/html");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<html><head><title>General Result</title></head><body>");
                    out.println("<h1>General Result</h1>");
                    out.println("<p>Invalid action</p>");

                    //add a link to go back to the home page
                    out.println("<a href=\"index.jsp\">Back to Home</a>");
                    out.println("<div><a href=\"addInhabitant.html\">Add Inhabitants</a></div>");
                    out.println("<div><a href=\"searchInhabitants.html\">Search Inhabitants</a></div>");
                    out.println("<div><a href=\"getAllInhabitants.html\">Get All Inhabitants</a></div>");
                    out.println("<div><a href=\"setMaritalStatus.html\">Get/Set Marital Status</a></div>");
                    out.println("</body></html>");
                }

            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
