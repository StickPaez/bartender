package com.aldeamo.bartenderaldeamo.service;

import com.aldeamo.bartenderaldeamo.modelData.Arrays;
import com.aldeamo.bartenderaldeamo.repository.DbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class BartenderServiceImpl implements BartenderService{
    int primeNumbers[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83};
    private ArrayList<Integer> a = new ArrayList<Integer>();
    private ArrayList<Integer> a1 = new ArrayList<Integer>();
    private ArrayList<Integer> b = new ArrayList<Integer>();
    private ArrayList<Integer> answerArray = new ArrayList<Integer>();

    @Autowired
    DbRepository dbRepository;

    @Override
    public ResponseEntity getBartenderLogic(int iterations, int idDataBase) {
        b.clear(); answerArray.clear(); a.clear();
        try {
            if (idDataBase > 5 || idDataBase < 1) {
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Parámetros inválidos en la petición");
            } else {
                findDatabase(idDataBase);
                flipArray();
                separateGlasses(iterations,idDataBase);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en el servidor : " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body("Array Respuesta exitoso --> " + answerArray);
    }

    /**
     * Name: separateGlasses
     * @param iterations
     * @param idDataBase
     */
    private void separateGlasses(int iterations, int idDataBase) {
        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < a.size(); j++) {
                if (a.get(j) % primeNumbers[i] == 0)
                    b.add(a.get(j));
                else
                    a1.add(a.get(j));
            }

            answerArray.addAll(b);
            a = new ArrayList<>(a1);
            writeIterationLog(iterations, idDataBase, i);
            a1.clear();
            b.clear();
        }
        answerArray.addAll(a);
    }

    /**
     * Name: flipArray
     */
    public void flipArray(){
        Collections.reverse(a);
    }

    /**
     * Name: findDatabase
     * @param idDataBase
     */
    public void findDatabase(int idDataBase){
        Arrays rep = dbRepository.findArraysById(idDataBase);
        String strArray[] = rep.getInput_array().split(",");

        //String strArray[] = {"2", "3", "4", "5", "6", "7"};

        for (String x : strArray) {
            a.add(Integer.parseInt(x));
        }
    }

    /**
     * Name: writeIterationLog
     * @param iterations
     * @param idDataBase
     * @param numIteration
     */
    public void writeIterationLog(int iterations, int idDataBase, int numIteration){
        System.out.println("Num de iteraciones: " + iterations);
        System.out.println("Id de base de datos: " + idDataBase);
        System.out.println("Iteración número: " + (numIteration+1));

        System.out.print("\nArreglo a: ");
        printArray(a);

        System.out.print("\nArreglo b: ");
        printArray(b);

        System.out.print("\nArreglo respuesta: ");
        printArray(answerArray);
    }

    /**
     * Nmae printArray
     * @param array
     */
    public void printArray(ArrayList<Integer> array){
        for (Integer x: array) {
            System.out.print("|" + x );
        }
    }
}
