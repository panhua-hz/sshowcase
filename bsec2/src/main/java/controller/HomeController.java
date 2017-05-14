package controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@RequestMapping(method = GET)
    public String home() {
        logger.info("--->into home......");
        return "home";
    }
	
	@RequestMapping(value="/hello")
    public String hello() {
        logger.info("--->into hello......");
        return "hello";
    }
	
	@RequestMapping(value="/login")
    public String login() {
        logger.info("--->into login......");
        return "login";
    }
}
