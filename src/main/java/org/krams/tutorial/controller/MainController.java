package org.krams.tutorial.controller;

import org.apache.log4j.Logger;
import org.krams.tutorial.domain.Person;
import org.krams.tutorial.domain.Registration;
import org.krams.tutorial.service.PeopleService;
import org.krams.tutorial.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;


/**
 * Handles and retrieves person request
 * Управляет и возвращает запрос
 */
@Controller
@RequestMapping("/main")
public class MainController {

    protected static Logger logger = Logger.getLogger("controller");

    @Resource(name ="personService")
    private PersonService personService;

    /**
     * Handles and retrieves all persons and show it in a JSP page
     * Получает всех персон и показывает их на jsp
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String getPersons(Model model) {

        logger.debug("Received request to show all persons");

        // Retrieve all persons by delegating the call to PersonService
        // Получает всех персон вызовом PersonService
        List<Person> persons = personService.getAll();
        // Person p=personService.get(1);
        // persons.add(p);
        // Attach persons to the Model
        // Прикрепляет персон к модели
        model.addAttribute("persons", persons);

        // This will resolve to /WEB-INF/jsp/personspage.jsp
        // Перенаправляет на /WEB-INF/jsp/personspage.jsp
        return "personspage";
    }
    //добавить страницу логина
    @RequestMapping(value = "/people_value", method = RequestMethod.GET)
    public String getPeople(Model model) {
        return "login_page";
    }

    //при входе на стр пользователя

    @Resource(name ="peopleService")
    private PeopleService peopleService;
    @RequestMapping(value = "/people_value/people", method = RequestMethod.GET)
    public String login(Model model) {
        List<Registration> people_value = peopleService.getAll();
        model.addAttribute("people_value", people_value);
        return "people_page";
    }

    @RequestMapping(value = "/people_value/reg", method = RequestMethod.GET)
    public String getReg(Model model) {
        model.addAttribute("peopleAttribute", new Registration());
        return "reg_page";
    }

    @RequestMapping(value = "/people_value/reg", method = RequestMethod.POST)
    public String reg(@ModelAttribute("peopleAttribute") Registration registration, Model model) {
        peopleService.reg(registration);
        model.addAttribute("people",registration);
        return "one_person_page";
    }



//    @RequestMapping(value = "/people_value/delete", method = RequestMethod.GET)
//    public String deleteFriend(@RequestParam(value="id", required=true) Integer id, Model model) {
//        peopleService.deleteFriend(id);
//        model.addAttribute("id", id);
//        return "one_person_page";
//    }
//
//    /**
//     * Retrieves the edit page
//     *
//     * @return the name of the JSP page
//     */
//    @RequestMapping(value = "/people_value/edit", method = RequestMethod.GET)
//    public String getEdit(@RequestParam(value="id", required=true) Integer id,
//                          Model model) {
//        model.addAttribute("peopleAttribute", peopleService.get(id));
//        return "one_person_page";
//    }
//
//    @RequestMapping(value = "/people_value/edit", method = RequestMethod.POST)
//    public String saveEdit(@ModelAttribute("peopleAttribute") Registration registration,
//                           @RequestParam(value="id", required=true) Integer id,
//                           Model model) {
//
//        registration.setId(id);
//        peopleService.edit(registration);
//        model.addAttribute("id", id);
//        return "one_persn_page";
//    }

    /**
     * Retrieves the add page
     * Возвращает страницу Добавления
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/persons/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
        logger.debug("Received request to show add page");

        // Create new Person and add to model
        // This is the formBackingOBject
        // Создает новую персону и добавляет ее в модель
        // Это возвращающая форма
        model.addAttribute("personAttribute", new Person());

        // This will resolve to /WEB-INF/jsp/addpage.jsp
        // перенаправление на страницу Добавления
        return "addpage";
    }

    /**
     * Adds a new person by delegating the processing to PersonService.
     * Displays a confirmation JSP page
     * Добавляет новую персону через PersonService
     * Показывает подтверждающую JSP
     * @return  the name of the JSP page
     */
    @RequestMapping(value = "/persons/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("personAttribute") Person person) {
        logger.debug("Received request to add new person");

        // The "personAttribute" model has been passed to the controller from the JSP
        // We use the name "personAttribute" because the JSP uses that name
        // Call PersonService to do the actual adding
        // модель "personAttribute" передана контроллеру из JSP
        // мы используем имя "personAttribute" потому что JSP  страница использует его
        // Вызов PersonService для совершения добавления
        personService.add(person);

        // This will resolve to /WEB-INF/jsp/addedpage.jsp
        return "addedpage";
    }

    /**
     * Deletes an existing person by delegating the processing to PersonService.
     * Displays a confirmation JSP page
     *
     * @return  the name of the JSP page
     */
    @RequestMapping(value = "/persons/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value="id", required=true) Integer id,
                         Model model) {

        logger.debug("Received request to delete existing person");

        // Call PersonService to do the actual deleting
        personService.delete(id);

        // Add id reference to Model
        model.addAttribute("id", id);

        // This will resolve to /WEB-INF/jsp/deletedpage.jsp
        return "deletedpage";
    }

    /**
     * Retrieves the edit page
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/persons/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam(value="id", required=true) Integer id,
                          Model model) {
        logger.debug("Received request to show edit page");

        // Retrieve existing Person and add to model
        // This is the formBackingOBject
        model.addAttribute("personAttribute", personService.get(id));

        // This will resolve to /WEB-INF/jsp/editpage.jsp
        return "editpage";
    }

    /**
     * Edits an existing person by delegating the processing to PersonService.
     * Displays a confirmation JSP page
     *
     * @return  the name of the JSP page
     */
    @RequestMapping(value = "/persons/edit", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute("personAttribute") Person person,
                           @RequestParam(value="id", required=true) Integer id,
                           Model model) {
        logger.debug("Received request to update person");

        // The "personAttribute" model has been passed to the controller from the JSP
        // We use the name "personAttribute" because the JSP uses that name

        // We manually assign the id because we disabled it in the JSP page
        // When a field is disabled it will not be included in the ModelAttribute
        person.setId(id);

        // Delegate to PersonService for editing
        personService.edit(person);

        // Add id reference to Model
        model.addAttribute("id", id);

        // This will resolve to /WEB-INF/jsp/editedpage.jsp
        return "editedpage";
    }

}