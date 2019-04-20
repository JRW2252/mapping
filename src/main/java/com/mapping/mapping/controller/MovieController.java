package com.mapping.mapping.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(path = "/movie", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
public class MovieController {

}
