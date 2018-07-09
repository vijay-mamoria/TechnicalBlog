package blog.common;

import blog.model.Post;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PostsManager {

    private FileOperations<Post> fileOperations;

    public PostsManager() {
        fileOperations = FileOperations.getInstance();
    }

    public static void main(String[] args) {
        Post post = new Post();
        post.setTitle("First Post");
        post.setBody("This is inserted using File Operations");
        new PostsManager().writeToFile(post);
    }

    public List<Post> readAllPosts() {

        return (ArrayList<Post>) fileOperations.readAllFiles(Constants.POST_DIR_NAME);
    }

    public List<Post> getThreePosts() {

        return (ArrayList<Post>) fileOperations.readRecentFiles(3, Constants.POST_DIR_NAME);
    }

    public static int numberOfPosts() {

        File file = new File(Constants.POST_DIR_NAME);
        File[] files = file.listFiles();
        return files.length;
    }

    public boolean deletePost(final String postTitle) {

        return (boolean) fileOperations.deleteFile(Constants.POST_FILE_PREFIX, postTitle);
    }

    public Post writeToFile(final Post post){
        fileOperations.writeToFile(Constants.POST_FILE_PREFIX, post, post.getTitle());
        return null;
    }

    public Post getPost(final String prefix) {
        return (Post) fileOperations.readFile(Constants.POST_FILE_PREFIX, prefix);
    }
}
