package com.bank.cc.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home redirection to swagger api documentation
 */
@Controller
public class HomeController {
  private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

  @RequestMapping(value = "/")
  public String index() {
    LOGGER.info("swagger-ui.html");
    return "redirect:swagger-ui.html";
  }
}