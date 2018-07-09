import blog.model.Post;
import blog.services.PostService;
import blog.services.PostServiceImp;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPostService {

    @Test
    public void testCreatePost(){
        Post post = new Post();
        post.setTitle("Test Post");
        post.setBody("Test body");
        PostService postService = new PostServiceImp();
        postService.create(post);
        Post fetchedPost = postService.findByTitle(post.getTitle());
        Assert.assertEquals(fetchedPost.getTitle(), post.getTitle());
        Assert.assertEquals(fetchedPost.getBody(), post.getBody());
    }
}
