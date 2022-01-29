package com.aldeamo.bartenderaldeamo.controller;

import com.aldeamo.bartenderaldeamo.modelData.Arrays;
import com.aldeamo.bartenderaldeamo.repository.DbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;

@RestController
@RequestMapping(value = "/v0")
public class BarTenderController {

    int primeNumer[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
    int a[] = {};
    int b[] = {};

    @Autowired
    DbRepository dbRepository;

    @GetMapping(value="/getBartender")
    public void getgetBartender(@RequestParam int iterations, @RequestParam int idDataBase){
        if (idDataBase > 5 || idDataBase < 1) {
            System.out.println("No válido");
            return;
        } else {
            Arrays rep = dbRepository.findArraysById(idDataBase);
            a = java.util.Arrays.stream(rep.getInput_array().split(",")).mapToInt(Integer::parseInt).toArray();

            for (int i = 0; i < iterations; i++) {
                Collections.reverse(java.util.Arrays.asList(a));
                if (a[i] % primeNumer[i] == 0) {
                    b[i] = a[i];
                } else {
                    b[i] = a[i];
                }
            }
        }
        System.out.println(iterations);
        System.out.println(idDataBase);
    }
}

