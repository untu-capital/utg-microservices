package com.example.creditms.utils;

import java.util.Random;

/**
 * @author Chirinda Nyasha Dell 23/11/2021
 */

public class RandomNumUtils {
    public static String generateCode(int number){
        Random random = new Random();
        String value = String.valueOf(random.nextInt(9));
        for(int i = 0; i < number -1; i++){
            value = value + String.valueOf(random.nextInt(9));
        }
        return value;
    }
}
