package com.aldeamo.bartenderaldeamo.service;

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

    /**
     *
     * @param iterations Número de iteraciones de entrada
     * @param idDataBase Id unico de entrada
     * @return Devuelve objeto de respuesta del servicio. En caso de error devuelve el codigo y mensaje del error
     */
    @Override
    public ResponseEntity getBartenderLogic(int iterations, int idDataBase) {
        b.clear(); answerArray.clear(); a.clear();
        try {
            if (idDataBase > 5 || idDataBase < 1) {
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Parámetros inválidos en la petición");
            } else {
                findDatabase(idDataBase);
                separateGlasses(iterations,idDataBase);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en el servidor : " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body("Array Respuesta --> " + answerArray);
    }

    /**
     * Name: separateGlasses
     * Description: Divide los valores del arreglo de  (Arreglo a) segùn las condiciones de separación.
     * @param iterations Número de iteraciones deseadas
     * @param idDataBase Id único de entrada. Se envia al método que escribe el log
     */
    private void separateGlasses(int iterations, int idDataBase) throws Exception {
        try {
            for (int i = 0; i < iterations; i++) {
                flipArray();
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
        }catch (Exception e){
            throw new Exception("Falla al separar arreglos");
        }

    }

    /**
     * Name: flipArray
     * Description: Revierte el arreglo a
     */
    public void flipArray() throws Exception{
        try {
            Collections.reverse(a);
        }catch (Exception e){
            throw new Exception("Falla al revertír el arreglo");
        }
    }

    /**
     * Name: findDatabase
     * Description: Consulta información en base de datos
     * @param idDataBase Id unico que trae la información del arreglo desde la base de datos
     */
    public void findDatabase(int idDataBase) throws Exception {
        try{
            //Arrays rep = dbRepository.findArraysById(idDataBase);
            //String strArray[] = rep.getInput_array().split(",");

            String strArray[] = {"2", "3", "4", "5", "6", "7"};

            for (String x : strArray) {
                a.add(Integer.parseInt(x));
            }
        }catch (Exception e){
            throw new Exception("Falla al consultar la DB");
        }
    }

    /**
     * Name: writeIterationLog
     * Description: Escribe logs en consola después de cada iteración en referencia a cada arreglo
     * @param iterations Si es la primera iteración, escribe encabezado
     * @param idDataBase Se escribe como el id consultado de la base de datos
     * @param numIteration Se imprime como el número de iteraciones a realizar
     */
    public void writeIterationLog(int iterations, int idDataBase, int numIteration) throws Exception {
        try{
            if(numIteration == 0) {
                System.out.println("Num de iteraciones: " + iterations);
                System.out.println("Id de base de datos: " + idDataBase);
            }

            System.out.print("\n======================");
            System.out.println("\nIteración número: " + (numIteration+1));

            System.out.print("\nArreglo a: ");
            printArray(a);

            System.out.print("\nArreglo b: ");
            printArray(b);

            System.out.print("\nArreglo respuesta: ");
            printArray(answerArray);
        }catch (Exception e){
            throw new Exception("Falla al escribír log");
        }

    }

    /**
     * Nmae printArray
     * Description: Inprime todos los valores de un arreglo
     * @param array Arreglo que se imprime en el log
     */
    public void printArray(ArrayList<Integer> array){
        for (Integer x: array) {
            System.out.print("|" + x );
        }
        System.out.print("|");
    }
}