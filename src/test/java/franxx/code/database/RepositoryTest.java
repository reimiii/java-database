package franxx.code.database;

import franxx.code.database.entity.Comment;
import franxx.code.database.repository.CommentRepository;
import franxx.code.database.repository.CommentRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RepositoryTest {
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = new CommentRepositoryImpl();
    }

    @Test
    void testInsert() {
        Comment comment = new Comment("da@mail.co", "HAAIII");
        commentRepository.insert(comment);

        Assertions.assertNotNull(comment.getId());
        System.out.println(comment.getId());
    }

    @Test
    void testFindById() {
        Comment comment = commentRepository.findById(4307);
        Assertions.assertNotNull(comment);
        System.out.println(comment.getId());
        System.out.println(comment.getEmail());
        System.out.println(comment.getComment());

        Comment byIdNotFound = commentRepository.findById(100000);
        Assertions.assertNull(byIdNotFound);
    }

    @Test
    void testFindAll() {
        List<Comment> commentList = commentRepository.findAll();
        commentList.forEach(comment -> System.out.println(
                "id: " + comment.getId() + "\nemail: " + comment.getEmail() + "\ncomment: " + comment.getComment()

        ));
        System.out.println(commentList.size());
    }

    @Test
    void testFindByEmail() {
        List<Comment> allByEmail = commentRepository.findAllByEmail("da@mail.co");

        Assertions.assertNotNull(allByEmail);
    }
}
