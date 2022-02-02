package com.aldeamo.bartenderaldeamo.service;

import org.springframework.http.ResponseEntity;

public interface BartenderService {
    ResponseEntity getBartenderLogic(int iterations, int idDataBase);
}
