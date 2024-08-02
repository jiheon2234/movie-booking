package info.jiheon.moviebooking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BasicController {

	@RequestMapping("")
	public String root(){
		return """
			<h1 style="display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; font-family: Arial, sans-serif; font-size: 100px;">HELLO WORLD❗️</h1>
			"""
			;
	}
}
