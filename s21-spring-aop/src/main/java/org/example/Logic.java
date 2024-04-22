package org.example;

import org.springframework.stereotype.Service;

@Service
public class Logic {

    @Log
    public int count(int num) {
        System.out.println("Logic#count()");
        return num++;
    }

    @Log
    public int textLength(String text) {
        System.out.println("Logic#textLength()");
        return text.length();
    }

}
