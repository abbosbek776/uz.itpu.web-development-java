package org.example.e4;

import org.springframework.stereotype.Service;

@Service
public class LogicV4 {

    public int textLength(String text) {
        System.out.println("Logic#textLength()");
        return text.length();
    }

    public int textLength(String text, int i) {
        System.out.println("Logic#textLength()");
        return text.length();
    }

    public void perform() {
        System.out.println("Logic#perform()");
    }
}
