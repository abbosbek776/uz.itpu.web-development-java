package org.example.e3;

import org.springframework.stereotype.Service;

@Service
public class LogicV3 {

    public int textLength(String text) {
        System.out.println("Logic#textLength()");
        return text.length();
    }

    public void perform() {
        System.out.println("Logic#perform()");
    }
}
