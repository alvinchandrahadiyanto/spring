package id.batch7.demoSpring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
  // request param
  @GetMapping("/hello")
  // /hello?name=femil
  public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    return String.format("Hello, %s!", name);
  }

  @GetMapping("/hello2/{nama}")
  // /hello2/fazztrack
  public String hello2(@PathVariable("nama") String name) {
    return String.format("Hello dari path variable, %s!", name);
  }

  @GetMapping("/hello3")
  public String hello3(@RequestBody String name) {
    return String.format("Hello dari request body, %s!", name);
  }

}
