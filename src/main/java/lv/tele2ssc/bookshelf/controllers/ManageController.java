package lv.tele2ssc.bookshelf.controllers;


import java.util.List;
import lv.tele2ssc.bookshelf.model.Book;
import lv.tele2ssc.bookshelf.model.User;
import lv.tele2ssc.bookshelf.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class ManageController {
    private static final Logger logger 
            = LoggerFactory.getLogger(ManageController.class);
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(method = RequestMethod.GET, path = "/manage")
    public String page(Model model) {
        List<User> list = userService.findAll();
        model.addAttribute("users", list);
        return "manage";
    }
    
}
