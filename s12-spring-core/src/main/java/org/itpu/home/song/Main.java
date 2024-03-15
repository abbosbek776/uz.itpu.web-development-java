package org.itpu.home.song;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SongConfig.class);) {
            Song song = context.getBean(Song.class);

            System.out.println(song);
        }
    }
}
