package lv.tele2ssc.bookshelf.controllers;

import java.util.ArrayList;
import java.util.List;
import lv.tele2ssc.bookshelf.model.Book;
import lv.tele2ssc.bookshelf.model.Reservation;
import lv.tele2ssc.bookshelf.model.User;
import lv.tele2ssc.bookshelf.services.ReservationService;
import lv.tele2ssc.bookshelf.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
        @Autowired
    private ReservationService reservationService;

    @RequestMapping(method = RequestMethod.GET, path = "/manage")
    public String page(Model model) {
        User user = userService.findByUserId();
        
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        
        List<Reservation> reservations = reservationService.findAllByUser(user);
        
        List<Book> available = new ArrayList<>();
        List<Book> owned = new ArrayList<>();
        
        for (Reservation r : reservations) {
            Book b = r.getBook();
            
            switch (r.getStatus()) {
                case AVAILABLE:
                    available.add(b);
                    break;
                case TAKEN:
                    owned.add(b);
                    break;
            }
        }

        model.addAttribute("availableBooks", available);
        model.addAttribute("ownedBooks", owned);
        
        return "manage";
    }

}
