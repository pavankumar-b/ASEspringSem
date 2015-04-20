

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.services.samples.youtube.cmdline.youtube_cmdline_search_sample.Search;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

/**
 * Servlet implementation class YoutubeLog
 */
public class YoutubeLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> result;   
	private List<SearchResult> searchResultList;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YoutubeLog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}
public void doGet(HttpServletRequest request, HttpServletResponse response){
	
}
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		System.out.println("inside service of youtube log servlet");
		String query=request.getParameter("searchquery");
		System.out.println(query);
		try{
			
		List<SearchResult> searchResults=SearchWeb(query);
		request.setAttribute("list", searchResults);
		
		String op="";
		Iterator<SearchResult> iteratorSearchResults=searchResultList.iterator();
		while (iteratorSearchResults.hasNext()) {

		      SearchResult singleVideo = iteratorSearchResults.next();
		      ResourceId rId = singleVideo.getId();

		      // Double checks the kind is video.
		      if (rId.getKind().equals("youtube#video")) {
		        Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().get("default");
		        op=op+rId.getVideoId()+"\n";
		        op=op+singleVideo.getSnippet().getTitle()+"\n";
		        op=op+thumbnail.getUrl()+"\n";
		        op=op+"\n-------------------------------------------------------------\n";
		        
		        System.out.println(" Video Id from youtube log" + rId.getVideoId());
		        System.out.println(" Title youtube log: " + singleVideo.getSnippet().getTitle());
		        System.out.println(" Thumbnail youtube log: " + thumbnail.getUrl());
		        System.out.println("\n-------------------------------------------------------------\n");
		        	        
		      }
		    }
		StringBuilder html=new StringBuilder(op);
		System.out.println("html>>>>>>"+html);
		HttpSession sess=request.getSession(true);
		sess.setAttribute("res", html);
		ServletContext sc=getServletContext();
		sc.setAttribute("res", html);
		
		request.getRequestDispatcher("youtubelog.jsp").forward(request,response);
		doGet(request,response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public List<SearchResult> SearchWeb(String query)throws IOException{
		/*String url="http://gdata.youtube.com/feeds/api/videos/?v=2&alt=jsonc&callback=?&paid-content=false&duration=long&orderby=viewCount&max-results=50&q=pk";
		URLConnection urlConnection =  new URL(url).openConnection();
		urlConnection.connect();
		 JsonReader reader = new JsonReader(new InputStreamReader(urlConnection.getInputStream()));
		 JsonParser parser = new JsonParser();
		 JsonElement rootElement = parser.parse(reader);
		 JsonArray arrayJson = rootElement.getAsJsonArray();
		 JsonObject jsonObject=new JsonObject();
		  int size=arrayJson.size();
          result=new ArrayList<>();
          for(int i=0;i<size;i++){
              System.out.println(arrayJson.get(i));
   
          }*/
		Search search = new Search();
		String [] args=new String[]{query};
		searchResultList=search.main(args);
		
		return searchResultList;
		//System.exit(0);
		
	}

}
