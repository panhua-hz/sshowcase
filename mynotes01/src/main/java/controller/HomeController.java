package controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import domain.EntNote;
import domain.EntTag;
import form.NoteForm;
import form.NoteShow;
import services.NoteService;

@Controller
@RequestMapping("/")
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	NoteService noteService;
	
	@RequestMapping(method = GET)
    public String home(Model model, HttpServletRequest request) {
        logger.info("--->into home......");
        String loginUser = request.getUserPrincipal().getName();
        
		logger.info("Welcome "+loginUser);
        List<EntTag> entTagList = noteService.findAllTags();
        model.addAttribute(entTagList);
        NoteForm noteForm = new NoteForm();
        noteForm.setUsername(loginUser);
        model.addAttribute(noteForm);
        
        List<NoteShow> noteShowList = noteService.noteByUsername(loginUser);
        model.addAttribute(noteShowList);
        return "home";
        //return "example";
    }
	
	@RequestMapping(method = POST)
    //public String submitNote(@Valid NoteForm noteForm, Errors errors, RedirectAttributes model) {
	public String submitNote(@Valid NoteForm noteForm, Errors errors, HttpServletRequest request) {	
		logger.info("--->into submitNote......");
		String loginUser2 = (String) request.getSession().getAttribute("loginUser"); 
		logger.info("Welcome2 "+loginUser2);
		logger.info(noteForm.toString());	
		noteService.save(noteForm);
		return "redirect:/";
	}
}
