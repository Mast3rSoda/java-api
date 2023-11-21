package pl.wi.xd.api;

import org.junit.jupiter.api.Test;
import pl.wi.xd.api.db.entity.Category;
import pl.wi.xd.api.db.entity.Post;
import pl.wi.xd.api.db.entity.User;
import pl.wi.xd.api.db.enums.Status;
import pl.wi.xd.api.db.repository.PostRepository;
import pl.wi.xd.api.db.services.PostServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class PostServiceTest {

    @Test
    public void findAllShouldCallFindAllFromRepository() {
        PostRepository repository = mock(PostRepository.class);

        when(repository.findAll()).thenReturn(null);

        PostServiceImpl service = new PostServiceImpl(repository);
        service.findAll();

        verify(repository, times(1)).findAll();
    }

    @Test
    public void findByIdShouldCallFindById() {
        Category category = new Category("TEST");
        User user = new User("1", "2", "1@2.p");
        Post post = new Post("1", "2", category, user);
        PostRepository repository = mock(PostRepository.class);

        when(repository.findById(post.getId())).thenReturn(Optional.of(post));

        PostServiceImpl service = new PostServiceImpl(repository);
        service.findById(post.getId());

        verify(repository, times(1)).findById(post.getId());
    }

    @Test
    public void deleteShouldCallFindByIdAndDeleteFromRepo() {
        Category category = new Category("TEST");
        User user = new User("1", "2", "1@2.p");
        Post post = new Post("1", "2", category, user);
        PostRepository repository = mock(PostRepository.class);

        when(repository.findById(post.getId())).thenReturn(Optional.of(post));

        PostServiceImpl service = new PostServiceImpl(repository);
        service.delete(post.getId());

        verify(repository, times(1)).findById(post.getId());
        verify(repository, times(1)).delete(post);
    }

    @Test
    public void findByStatusShouldCallFindByStatusFromRepo() {
        Category category = new Category("TEST");
        User user = new User("1", "2", "1@2.p");
        Post post = new Post("1", "2", category, user);
        List<Post> posts = Arrays.asList(post);

        PostRepository repository = mock(PostRepository.class);

        when(repository.findByStatusEquals(Status.STATUS_PENDING)).thenReturn(Optional.of(posts));

        PostServiceImpl service = new PostServiceImpl(repository);
        List<Post> result = service.findByStatus(Status.STATUS_PENDING);

        verify(repository, times(1)).findByStatusEquals(Status.STATUS_PENDING);
        assert result == posts;
    }
}
