package ru.uoles.proj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.uoles.proj.model.PostInfo;
import ru.uoles.proj.model.Property;
import ru.uoles.proj.service.InstagramService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            Property property = (Property) context.getBean("property");
            InstagramService fromInstagram = (InstagramService) context.getBean("fromInstagram");
            InstagramService toInstagram = (InstagramService) context.getBean("toInstagram");

            System.out.println("Loginning to Instagramm as '" + property.getFromUsername() + "'...");
            fromInstagram.connect(property.getFromUsername(), property.getFromPassword());
            System.out.println("Get posts...");
            List<PostInfo> posts = fromInstagram.getPosts(property.getFromUsername());

            System.out.println("Loginning to Instagramm as '" + property.getToUsername() + "'...");
            toInstagram.connect(property.getToUsername(), property.getToPassword());
            System.out.println("Add posts...");
            toInstagram.addPosts(posts, "Repost from", 60000);

            System.out.println("All posts added!!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
