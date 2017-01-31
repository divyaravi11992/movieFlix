package app.web.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.domain.Title;
import app.domain.User;
import app.enums.TitleType;
import app.enums.UserType;
import app.services.RatingService;
import app.services.TitleService;
import app.services.UserService;
import app.util.DateUtil;
/**
*
* @author Divya Ravi
* 
* Controller class to handle HTTP requests for title
*/
@RestController
@RequestMapping(value = "/title")
public class TitleController {


	@Autowired
    UserService userService;

    @Autowired
    TitleService titleService;
  
    @Autowired
    RatingService ratingService;
    private Logger log = Logger.getLogger(UserController.class);

	@ResponseStatus(HttpStatus.CREATED)
	   @RequestMapping(method = RequestMethod.POST)
    public Title saveTitle(@RequestBody Map<String, String> reqPar,@RequestHeader String userId)  throws ParseException, IOException {

		log.debug("Title Create");
    	Long userID=Long.parseLong(userId);
        userService.validateUser(userID);
        User user=userService.getUserById(userID);
        Title title=new Title();
        if(user.getUserType().equals(UserType.ADMIN))
        {
        title.setTitle(reqPar.get("title"));
        title.setYear(Integer.parseInt(reqPar.get("year")));
        title.setImdbID(reqPar.get("imdbid"));
        title.setRated(reqPar.get("rated"));
        title.setRuntime(reqPar.get("runtime"));
        title.setReleased(DateUtil.get(reqPar.get("released")));
        title.setGenre(reqPar.get("genre"));
        title.setDirector(reqPar.get("director"));
        title.setWriter(reqPar.get("writer"));
        title.setActors(reqPar.get("actor"));
        title.setPlot(reqPar.get("plot"));
        title.setLanguage(reqPar.get("language"));
        title.setCountry(reqPar.get("country"));
        title.setAwards(reqPar.get("awards"));
        title.setPoster(reqPar.get("poster"));
        title.setMetascore(Integer.parseInt(reqPar.get("metascore")));
        title.setImdbRating(Float.parseFloat(reqPar.get("imdbrating")));
        title.setImdbVotes(Long.parseLong(reqPar.get("imdbvote")));
        title.setTitle(reqPar.get("title"));
        if(reqPar.get("type").equals(TitleType.MOVIE.toString()))
            title.setType(TitleType.MOVIE);
            	if(reqPar.get("type").equals(TitleType.SERIES.toString()))
            title.setType(TitleType.SERIES);
        title.setRating(0);
        
        titleService.saveTitle(title);
    	log.debug("titleCreate end");
        }
      return title;
    }
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping( method = RequestMethod.PUT)
	public Title updateTitle(@RequestBody Map<String, String> reqPar,@RequestHeader String userId)  throws ParseException, IOException {


 	Long userID=Long.parseLong(userId);
     userService.validateUser(userID);
     User user=userService.getUserById(userID);
     Title title=titleService.getTitleById(Long.parseLong(reqPar.get("titleid")));
 	log.debug("title update start"+title);
     if(user.getUserType().equals(UserType.ADMIN))
     {
     title.setTitle(reqPar.get("title"));
     title.setYear(Integer.parseInt(reqPar.get("year")));
     title.setImdbID(reqPar.get("imdbid"));
     title.setRated(reqPar.get("rated"));
     title.setReleased(DateUtil.get(reqPar.get("released")));
     title.setGenre(reqPar.get("genre"));
     title.setDirector(reqPar.get("director"));
     title.setWriter(reqPar.get("writer"));
     title.setActors(reqPar.get("actor"));
     title.setPlot(reqPar.get("plot"));
     title.setLanguage(reqPar.get("language"));
     title.setCountry(reqPar.get("country"));
     title.setAwards(reqPar.get("awards"));
     title.setPoster(reqPar.get("poster"));
     title.setMetascore(Integer.parseInt(reqPar.get("metascore")));
     title.setImdbRating(Float.parseFloat(reqPar.get("imdbrating")));
     title.setImdbVotes(Long.parseLong(reqPar.get("imdbvote")));
     title.setTitle(reqPar.get("title"));
     if(reqPar.get("type").equals(TitleType.MOVIE.toString()))
         title.setType(TitleType.MOVIE);
         	if(reqPar.get("type").equals(TitleType.SERIES.toString()))
         title.setType(TitleType.SERIES);
     titleService.saveTitle(title);
     log.debug("title update end"+title);
     }
   return title;
 }
	@ResponseStatus(HttpStatus.OK)
	  @RequestMapping(method = RequestMethod.DELETE)
 public void deleteTitle(@RequestBody Map<String, String> reqPar,@RequestHeader String userId)  throws ParseException, IOException {


 	Long userID=Long.parseLong(userId);
     userService.validateUser(userID);
     User user=userService.getUserById(userID);
     log.debug("title delete start");
     if(user.getUserType().equals(UserType.ADMIN))
     {
    titleService.deleteTitle(Long.parseLong(reqPar.get("title")));
    
     }
     log.debug("title delete end");
 }

	
@RequestMapping( method = RequestMethod.GET)
public PagedListHolder<Title> listAllTitle(@RequestHeader String userId)  {
PagedListHolder<Title> p = new PagedListHolder<Title>();
p.setPage(5);

	Long userID=Long.parseLong(userId);
   userService.validateUser(userID);
   User user=userService.getUserById(userID);
   log.debug("title list start");
   List<Title> titleList = new ArrayList<Title>();
   if(user.getUserType().equals(UserType.USER))
   {
	   titleList=   titleService.listAllTitle();
	   titleList.sort(Comparator.comparing(a -> a.getYear()));
	 
  
   }
   p.setSource(titleList);
return p;
}
@RequestMapping( value = "/movies",method = RequestMethod.GET)
public PagedListHolder<Title> listAllTopMovies(@RequestHeader String userId)  {
PagedListHolder<Title> p = new PagedListHolder<Title>();
p.setPage(5);

	Long userID=Long.parseLong(userId);
   userService.validateUser(userID);
   User user=userService.getUserById(userID);
   log.debug("title list start");
   List<Title> titleList = new ArrayList<Title>();
   if(user.getUserType().equals(UserType.USER))
   {
	   titleList=   titleService.listAllMovies(TitleType.MOVIE);
	   titleList.sort(Comparator.comparing(a -> a.getYear()));
	 
  
   }
   p.setSource(titleList);
return p;
}
@RequestMapping(value = "/series", method = RequestMethod.GET)
public PagedListHolder<Title> listAllTitleTopSeries(@RequestHeader String userId)  {
PagedListHolder<Title> p = new PagedListHolder<Title>();
p.setPage(5);

	Long userID=Long.parseLong(userId);
   userService.validateUser(userID);
   User user=userService.getUserById(userID);
   log.debug("title list start");
   List<Title> titleList = new ArrayList<Title>();
   if(user.getUserType().equals(UserType.USER))
   {
	   titleList=   titleService.listAllSeries(TitleType.SERIES);
	   titleList.sort(Comparator.comparing(a -> a.getYear()));
	 
  
   }
   p.setSource(titleList);
return p;
}
@RequestMapping(value = "/imdbrating", method = RequestMethod.GET)
public PagedListHolder<Title> listAllTitleImdbRating(@RequestHeader String userId)  {
PagedListHolder<Title> p = new PagedListHolder<Title>();
p.setPage(5);

	Long userID=Long.parseLong(userId);
   userService.validateUser(userID);
   User user=userService.getUserById(userID);
   log.debug("title list start");
   List<Title> titleList = new ArrayList<Title>();
   if(user.getUserType().equals(UserType.USER))
   {
	   titleList=   titleService.listAllTitle();
	   titleList.sort(Comparator.comparing(a -> a.getImdbRating()));
	 
  
   }
   p.setSource(titleList);
return p;
}

@RequestMapping(value = "/imdbVotes", method = RequestMethod.GET)
public PagedListHolder<Title> listAllTitleImdbVotes(@RequestHeader String userId)  {
PagedListHolder<Title> p = new PagedListHolder<Title>();
p.setPage(5);

	Long userID=Long.parseLong(userId);
   userService.validateUser(userID);
   User user=userService.getUserById(userID);
   log.debug("title list start");
   List<Title> titleList = new ArrayList<Title>();
   if(user.getUserType().equals(UserType.USER))
   {
	   titleList=   titleService.listAllTitle();
	   titleList.sort(Comparator.comparing(a -> a.getImdbVotes()));
	 
  
   }
   p.setSource(titleList);
return p;
}

@RequestMapping(value = "/filters", method = RequestMethod.GET)
public PagedListHolder<Title> listAllTitleFilters(@RequestParam String type,@RequestParam String year,@RequestParam String genre, @RequestHeader String userId)  {
PagedListHolder<Title> p = new PagedListHolder<Title>();
p.setPage(5);

	Long userID=Long.parseLong(userId);
   userService.validateUser(userID);
   User user=userService.getUserById(userID);
   log.debug("title list start");
   List<Title> titleList = new ArrayList<Title>();
   TitleType ttype=setTitleType(type);
   if(user.getUserType().equals(UserType.USER))
   {
	   titleList=   titleService.findTitleWithFilters(genre,ttype,Integer.parseInt(year));
	   titleList.sort(Comparator.comparing(a -> a.getImdbVotes()));
	 
  
   }
   p.setSource(titleList);
return p;
}

@RequestMapping(value = "/search", method = RequestMethod.GET)
public PagedListHolder<Title> listAllTitleSearch(@RequestParam String text,@RequestHeader String userId)  {
PagedListHolder<Title> p = new PagedListHolder<Title>();
p.setPage(5);

	Long userID=Long.parseLong(userId);
   userService.validateUser(userID);
   User user=userService.getUserById(userID);
   log.debug("title list start");
   List<Title> titleList = new ArrayList<Title>();
 
   if(user.getUserType().equals(UserType.USER))
   {
	   titleList=   titleService.findTitleWithSearch(text);
	   titleList.sort(Comparator.comparing(a -> a.getImdbVotes()));
	 
  
   }
   p.setSource(titleList);
return p;
}

@RequestMapping(value = "/{titleid}", method = RequestMethod.GET)
public Title getTitleDetails(@PathVariable String titleid,@RequestHeader String userId)  {

	Long userID=Long.parseLong(userId);
   userService.validateUser(userID);
   User user=userService.getUserById(userID);
   log.debug("title list start");
   Title title=new Title();
   if(user.getUserType().equals(UserType.USER))
   {
	   title=   titleService.getTitleById(Long.parseLong(titleid));
	 
  
   }

   return title;
}



@RequestMapping(value = "/year", method = RequestMethod.GET)
public List<Integer> getTitleYearList()  {
	List<Integer> yearlist= new ArrayList<>();
	
  
	yearlist=   titleService.findDistinctYear();
	
   return yearlist;
}
@RequestMapping(value = "/genre", method = RequestMethod.GET)
public List<String> getTitleGenreList()  {
	List<String> genrelist= new ArrayList<>();
	
  
	genrelist=   titleService.findDistinctGenre();
	
   return genrelist;
}
private TitleType setTitleType( String str) {
	TitleType t = null;
	switch (str) {
	case "movie":
		t= TitleType.MOVIE;
		break;
	case "series":
		t= TitleType.SERIES;
		break;
	
	}
	return t;
}
}
