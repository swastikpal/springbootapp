package com.gcdc.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontEndController {

	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
}
