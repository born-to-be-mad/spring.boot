package by.dma.securityjwt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/person")
public class PersonController {

  @Autowired
  private PersonService personService;

  @RequestMapping(value = {"/add", "/edit"}, method = {RequestMethod.POST})
  public String addPerson(@ModelAttribute("person") Person person) {
    personService.save(person);
    return "redirect:view?id=" + person.getId();
  }

  @ModelAttribute("person")
  public Person getPerson(@RequestParam(value = "id", required = false) Integer id) {
    Person person = null;
    if (id != null) {
      person = personService.findOne(id);
    }
    return person == null ? new Person() : person;
  }

  @RequestMapping(value = "/view", method = RequestMethod.GET)
  public String viewPersonForm() {
    return "person/view";
  }

  @RequestMapping(value = "/edit", method = {RequestMethod.GET})
  public String showEditForm() {
    return "/person/edit";
  }


}

@Service
class PersonService {

  Collection<Person> list() {
    return new ArrayList<>();
  }

  Person findOne(Integer id) {
    return new Person(id);
  }


  Person save(Person person) {
    return person;
  }
}

class Person {
  Integer id;

  public Person() {
  }

  public Person(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
