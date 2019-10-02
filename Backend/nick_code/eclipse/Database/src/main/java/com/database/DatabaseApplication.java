package com.database;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import com.database.bulletin.*;
import com.database.lists.*;

@SpringBootApplication
public class DatabaseApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}
		
	  @RestController
	  class GreetingController implements ErrorController {
			
		  @Autowired
		  private RoomListService roomListService;
		  
		  @Autowired
		  private BulletinService bulletinService;
		  
		  @Autowired
		  private ErrorAttributes errorAttributes;
		 
		  @RequestMapping("/hello/{name}")
		  String hello(@PathVariable String name) {
		      return "Hello, " + name + "!";
		  }
		  
		  @GetMapping("/list")
		  public String getRoomList() {
			  List<RoomList> roomLists = roomListService.getLists();
			  String ret = "{\"RoomLists\":[";
			  for (RoomList temp : roomLists) {
				ret += temp.toString() + ",";
			  	}
		  	  ret = ret.substring(0, ret.length()-1) + "]}";
		      return ret;
		  }
		  
		  @PostMapping(path = "/list", consumes = "application/json", produces = "application/json")
		  public String addRoomList(@RequestBody String item) {
			  String Description = item.substring(16, item.indexOf('\"', 16));
			  String Title = item.substring(Description.length()+27, item.indexOf('\"', Description.length()+27));
			  roomListService.addList(new RoomList(Title, Description));
			  return "{\"done\":\"ok\"}";
		  }
		  
		  @GetMapping("/bulletin")
		  public String getBulletin() {
			  List<Pin> pins = bulletinService.getPins();
			  String ret = "{\"BulletinBoard\":[";
			  for (Pin temp : pins) {
				ret += temp.toString() + ",";
			  	}
		  	  ret = ret.substring(0, ret.length()-1) + "]}";
		      return ret;
		  }
		  
		  @PostMapping(path = "/bulletin", consumes = "application/json", produces = "application/json")
		  public String addToBulletin(@RequestBody String item) {
			  String User = item.substring(9, item.indexOf('\"', 9));
			  String Contents = item.substring(User.length()+23, item.indexOf('\"', User.length()+23));
			  bulletinService.addPin(new Pin(User, Contents));
			  return "{\"done\":\"ok\"}";
		  }
		  
		  @Override
		  public String getErrorPath() {
		      return "/error";
		  }
		
		  @RequestMapping("/error")
		  public String error(HttpServletRequest servletRequest, Model model) {
		      Map<String, Object> attrs = errorAttributes.getErrorAttributes((WebRequest) new ServletRequestAttributes(servletRequest), false);
		      model.addAttribute("attrs", attrs);
		      return "error: "+attrs.toString();
		  }
	  }
}
