package quest.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoRestController {


	@GetMapping("/{text}")
	public String demo(@PathVariable String text,@RequestParam String mot) 
	{
		return "/api/demo a recu "+text+" en pathVariable et "+mot+" en params";
	}
}
