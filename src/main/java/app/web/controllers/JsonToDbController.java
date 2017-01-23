package app.web.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.domain.Title;
import app.services.TitleService;
import app.util.DateUtil;
/**
*
* @author Divya Ravi
* 
* Controller to add title data from given json to db
*/

@RestController
@RequestMapping(value = "/json")
public class JsonToDbController {
	 @Autowired
	    TitleService titleService;
	    
	    private Logger log = Logger.getLogger(UserController.class);

		@ResponseStatus(HttpStatus.CREATED)
		  @RequestMapping(method = RequestMethod.POST)
	    public void saveTitle(@RequestHeader String urlString)  throws Exception {


		
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            String jsonFile;
            br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            jsonFile = br.readLine();
            while (jsonFile != null) {
                sb.append(jsonFile);
                jsonFile = br.readLine();
            }
            log.info("Json String");
            log.info(sb.toString());
            Title title = null;
            JSONArray item = new JSONArray(sb.toString());
            
            for(int i=0;i<item.length();i++)
            {
                title = new Title();
                
                JSONObject jsonObject = item.getJSONObject(i); 
                if(!jsonObject.isNull( "title" ))
                {
                title.setTitle(jsonObject.getString("title"));
                }
                if(!jsonObject.isNull( "year" ))
                {
                title.setYear(jsonObject.getInt("year"));
                }
                if(!jsonObject.isNull( "runtime" ))
                {
                title.setRuntime(jsonObject.getString("runtime"));
                }
                if(!jsonObject.isNull( "imdbId" ))
                {
                title.setImdbID(jsonObject.getString("imdbId"));
                }
                if(!jsonObject.isNull( "rated" ))
                {
                title.setRated(jsonObject.getString("rated"));
                }
                if(!jsonObject.isNull( "released" ))
                {
                title.setReleased(DateUtil.get(jsonObject.getString("released")));
                }
                if(!jsonObject.isNull( "genre" ))
                {
                title.setGenre(jsonObject.getString("genre"));
                }
                if(!jsonObject.isNull( "director" ))
                {                
                title.setDirector(jsonObject.getString("director"));
                }
                if(!jsonObject.isNull( "writer" ))
                {  
                title.setWriter(jsonObject.getString("writer"));
                }
                if(!jsonObject.isNull( "actors" ))
                { 
                title.setActors(jsonObject.getString("actors"));
                }
                if(!jsonObject.isNull( "plot" ))
                {
                title.setPlot(jsonObject.getString("plot"));
                }
                if(!jsonObject.isNull( "language" ))
                {
                title.setLanguage(jsonObject.getString("language"));
                }
                if(!jsonObject.isNull( "country" ))
                {
                title.setCountry(jsonObject.getString("country"));
                }
                if(!jsonObject.isNull( "awards" ))
                {
                title.setAwards(jsonObject.getString("awards"));
                }
                if(!jsonObject.isNull( "poster" ))
                {
                title.setPoster(jsonObject.getString("poster"));
                }
                if(!jsonObject.isNull( "metaScore" ))
                {
                title.setMetascore(jsonObject.getInt("metaScore"));
                }
                if(!jsonObject.isNull( "imdbRating" ))
                {
                title.setImdbRating((float)(jsonObject.getDouble("imdbRating")));
                }
                if(!jsonObject.isNull( "imdbVotes" ))
                {
                title.setImdbVotes((long)jsonObject.getLong("imdbVotes"));
                }
                if(!jsonObject.isNull( "type" ))
                {
                title.setTitle(jsonObject.getString("type"));
                }
                title.setRating(0);
             titleService.saveTitle(title);
            }
          

	} catch (MalformedURLException e) {
       e.printStackTrace();
 
   } catch (IOException e) {
       e.printStackTrace();
 
   }
	}
}
