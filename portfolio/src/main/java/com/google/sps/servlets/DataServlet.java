// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays; 

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/comments")
public class DataServlet extends HttpServlet {
    private List<String> comments;
  @Override
  public void init() {
    comments = new ArrayList<>();
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      String comment = request.getParameter("text-input");
      String username = request.getParameter("username");

    
      Entity commentEntity = new Entity("comments");
      commentEntity.setProperty("username", username);
      commentEntity.setProperty("comment", comment);

       DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
       datastore.put(commentEntity);
      
      response.setContentType("text/html;");
    
      response.sendRedirect("/index.html");
  }


  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {    
    response.setContentType("text/html");


     Query query = new Query("comments");
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);


    //  List<String> comments = new ArrayList<>();
    for (Entity entity : results.asIterable()) {
      long id = entity.getKey().getId();
      String username = (String) entity.getProperty("username");
      String comment = (String) entity.getProperty("comment");
        String both = username + " says: " + comment;

      comments.add(both);
    }
   
    response.getWriter().println("<h1>Comments Posted to Cheyenne's Portfolio</h1>");
    for (String comment: comments) {
        response.getWriter().println("<p>" + comment + "</p>");
    }
    comments.clear();
  }
}
