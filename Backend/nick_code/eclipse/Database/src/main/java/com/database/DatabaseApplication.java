package com.database;

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

@SpringBootApplication
public class DatabaseApplication {
	String myString = "{\"List\":[{\"contents\":\"do the dishes\",\"dateCreate\":\"feb 5\"},{\"contents\":\"Take out the garbage\",\"dateCreate\":\"feb 6\"},{\"contents\":\"Get groceries\",\"dateCreate\":\"feb 7\"},{\"contents\":\"Do a mewtwo raid\",\"dateCreate\":\"feb 9\"}]}";

	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}

	@RestController
	  class GreetingController implements ErrorController {
	      
	      @RequestMapping("/hello/{name}")
	      String hello(@PathVariable String name) {
	          return "Hello, " + name + "!";
	      }
	      
	      @GetMapping("/list")
	      String list() {
	    	  return myString;
	      }
	      
	      
	      @PostMapping(path = "/listadd", consumes = "application/json", produces = "application/json")
	    	  public void addMember(@RequestBody String item) {
	          myString = myString.substring(0, myString.length()-2) + "," + item + "]}";
	          }
	      
	      @Autowired
	      private ErrorAttributes errorAttributes;

	      @Autowired
	      private BulletinRepository bulletinRepository;
	      
	      @GetMapping("/testendpoint")
	      String testendpoint() {
	    	  return //Return something with a bulletin object;
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
