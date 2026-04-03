package fr.formation.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.dao.IDAOHello;
import fr.formation.model.Hello;

@RestController
@RequestMapping("/api/hello")
public class HelloRestController {
    @Autowired
    private IDAOHello daoHello;

    @GetMapping
    public String hello() {
        return "Hello world!";
    }

    @GetMapping("/all")
    public List<Hello> helloAll() {
        List<Hello> result = this.daoHello.findAll();

        System.out.println(result);

        return result;
    }
}
