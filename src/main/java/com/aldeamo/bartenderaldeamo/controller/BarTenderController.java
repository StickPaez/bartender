package com.aldeamo.bartenderaldeamo.controller;
import com.aldeamo.bartenderaldeamo.service.BartenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v0")
public class BarTenderController {

    @Autowired
    BartenderService bartenderService;
    /**
     * Name: getgetBartende
     * @param iterations = Número de itraciones de entrada
     * @param idDataBase = id de entrada a consultar en DB
     * @return retorna
     */
    @GetMapping(value="/getBartender")
    public ResponseEntity<Object> getgetBartender(@RequestParam int iterations, @RequestParam int idDataBase){;
       return bartenderService.getBartenderLogic(iterations, idDataBase);
    }
}