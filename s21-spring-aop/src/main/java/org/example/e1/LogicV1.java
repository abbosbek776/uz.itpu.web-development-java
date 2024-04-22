package org.example.e1;

import org.springframework.stereotype.Service;

@Service
public class LogicV1 {

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
