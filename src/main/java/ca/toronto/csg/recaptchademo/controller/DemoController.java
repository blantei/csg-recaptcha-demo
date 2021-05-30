package ca.toronto.csg.recaptchademo.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
   
    @GetMapping("/")
    public String index(Model model) {
        logger.info("Entered index()");
        return "index";
    }
    
}
