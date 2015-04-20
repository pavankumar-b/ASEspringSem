<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "javax.servlet.RequestDispatcher" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type='text/javascript' src='//code.jquery.com/jquery-1.9.1.js'></script>
<script type="text/javascript" src="list.js"></script>
<script type='text/javascript'>
$(window).load(function(){
jQuery(document).ready(function ($) {
 $('#logout').click(function(){
	 <%session.invalidate();
	%>
	window.location.href="login.jsp";
 })
 
$( "#sortBy" ).change(function() {
  api();
})
 	//var searchList= new List();
  $('#btnSubmit').click(function () {
 	api();
   
  });
  function api(){
	  // the search term
	    var q = $('#searchquery').val().trim();
	    
	    var e = document.getElementById("sortBy");
	    var sortByString = e.options[e.selectedIndex].value;
	    //alert(strUser);
	    // container to display search results
	    var $results = $('#results');
	 
	    // YouTube Data API base URL (JSON response)
	    var url = "http://gdata.youtube.com/feeds/api/videos/?v=2&alt=jsonc&callback=?"
	 
	    // set paid-content as false to hide movie rentals
	    url = url + '&paid-content=false';
	 
	    // set duration as long to filter partial uploads
	    url = url + '&duration=long';
	 
	    // order search results by view count
	    url = url + '&orderby=' +sortByString;
	 
	    // we can request a maximum of 50 search results in a batch
	    url = url + '&max-results=50';
	 
	    $.getJSON(url + "&q=" + q, function (json) {
	 
	      var count = 0;
	 
	      if (json.data.items) {
	    	  
	        var items = json.data.items;
	        //listup(items);
	        var html = "";
	 
	        items.forEach(function (item) {
	 			
	 			
	          // Check the duration of the video,
	          // full-length movies are generally longer than 1 hour
	          var duration = Math.round((item.duration) / (60 * 60));
	 
	          // Filter out videos that aren't in the Film or Movies category
	          if ((duration > 1) && (item.category != "Movies" || item.category != "Film")) {
	 			
	            // Include the YouTube Watch URL youtu.be
	            html += '<p><a href="http://youtu.be/' + item.id + '">';
	 			//String item=html;
	 			
	            // Add the default video thumbnail (default quality)
	            html += '<img src="http://i.ytimg.com/vi/' + item.id + '/default.jpg">';
	 
	            // Add the video title and the duration
	            html += '<h2>' + item.title + ' ' + item.duration + '</h2></a></p>';
	            count++;
	            //searchList.add(item.duration,item);
	          }
	        });
	      }
	 
	      // Did YouTube return any search results?
	      if (count === 0) {
	        $results.html("No videos found");
	      } else {
	        // Display the YouTube search results
	        $results.html(html);
	      }
	    });
  }
});
});

</script> 
<title>DashBoard</title>
</head>

<body>
<table style="width:100%">

<tr>
<td><input id="searchquery" name="searchquery"/>&nbsp;&nbsp;<input id = "btnSubmit" type="submit" value="Search"/></td>

<td>
sort by <select id="sortBy" >
  <option value="viewCount">viewCount</option>
  <option value="rating">rating</option>
  <option value="published">published</option>
  <option value="relevance">relevance</option>
   
</select></td>
<td><form method="link" action="logout.jsp">
<input id="logout" type="submit" value="Logout" />
</form></td>
</tr>
</table>
<div id="results"></div>
<p></p>
<p></p>

</body>

</html>