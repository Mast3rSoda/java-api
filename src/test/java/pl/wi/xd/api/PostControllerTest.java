package pl.wi.xd.api;

import org.junit.jupiter.api.Test;
import pl.wi.xd.api.db.controller.PostController;
import pl.wi.xd.api.db.entity.Post;
import pl.wi.xd.api.db.repository.CategoryRepository;
import pl.wi.xd.api.db.services.CategoryServiceImpl;
import pl.wi.xd.api.db.services.EmailService;
import pl.wi.xd.api.db.services.PostServiceImpl;
import pl.wi.xd.api.db.services.UserServiceImpl;
import pl.wi.xd.api.db.services.interfaces.CategoryServiceInterface;
import pl.wi.xd.api.db.services.interfaces.IEmailService;
import pl.wi.xd.api.db.services.interfaces.PostServiceInterface;
import pl.wi.xd.api.db.services.interfaces.UserServiceInterface;

import static org.mockito.Mockito.mock;

public class PostControllerTest {

    @Test
    public void createPostShouldCallloadUserByUsernameAndSaveOnPostService() {
        PostServiceImpl postService = mock(PostServiceImpl.class);
        CategoryServiceImpl categoryService = mock(CategoryServiceImpl.class);
        EmailService emailService = mock(EmailService.class);
        UserServiceImpl userService = mock(UserServiceImpl.class);

        PostController controller = new PostController(postService, categoryService, emailService, userService);
    }
}
