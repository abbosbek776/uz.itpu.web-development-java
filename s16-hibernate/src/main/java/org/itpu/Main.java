package org.itpu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.itpu.entity.Comment;
import org.itpu.entity.Post;
import org.itpu.service.SocialMediaService;
import org.itpu.service.SocialMediaServiceImpl;

import java.util.List;

/*
Add Watchers :
em.createNativeQuery("select * from posts", org.itpu.entity.Post.class).getResultList()
em.createNativeQuery("select * from comments", org.itpu.entity.Comment.class).getResultList()
 */
public class Main {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("socialMediaPersistenceUnit");
             EntityManager em = emf.createEntityManager()) {
            // Initialize EntityManagerFactory and EntityManager
            SocialMediaService service = new SocialMediaServiceImpl(em);
            populateDatabase(em, service);

            nPlus1Problem(em);

            jpaMidTermTaskSolution(em, service);

            // check lazy loading
            checkLazyLoading(em, service);


            System.out.println("EXIT");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void nPlus1Problem(EntityManager em) {
        // n+1
        List<Post> posts = em.createNativeQuery("SELECT * FROM Posts", Post.class).getResultList();
        Post postId1 = posts.get(0);
        List<Comment> comments1n2 = postId1.getComments();
        Post postId2 = posts.get(1);
        List<Comment> comments3n4 = postId2.getComments();
        em.clear();
        System.out.println("");
    }

    private static void jpaMidTermTaskSolution(EntityManager em, SocialMediaService service) {
        // JPA practical task solution
        service.removeComment(2);
        service.removePost(2);
        em.clear();
        System.out.println("");
    }

    private static void checkLazyLoading(EntityManager em, SocialMediaService service) {
        em.getTransaction().begin();

        Post post = em.find(Post.class, 1);
        Post postCopy = em.find(Post.class, 1);

        em.getTransaction().commit();

        Post postById = service.getPostById(1);
        Post postByIdCopy = service.getPostById(1);
        em.clear();
        Post postByIdNewCall = service.getPostById(1);

        em.clear();
        System.out.println("");
    }

    private static void populateDatabase(EntityManager em, SocialMediaService service) {
        Integer postId1 = service.createPost(post -> {
            post.setContent("Post content 1");
            post.setAuthor("Author 1");
        });
        Integer postId2 = service.createPost(post -> {
            post.setContent("Post content 2");
            post.setAuthor("Author 2");
        });

        service.addComment(postId1, comment -> {
            comment.setText("Comment 1");
            comment.setCommenter("Commenter 1");
        });
        service.addComment(postId1, comment -> {
            comment.setText("Comment 2");
            comment.setCommenter("Commenter 2");
        });
        service.addComment(postId2, comment -> {
            comment.setText("Comment 3");
            comment.setCommenter("Commenter 3");
        });
        service.addComment(postId2, comment -> {
            comment.setText("Comment 4");
            comment.setCommenter("Commenter 4");
        });
        em.clear();
        System.out.println("");
    }
}
