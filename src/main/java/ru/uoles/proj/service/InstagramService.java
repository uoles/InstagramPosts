package ru.uoles.proj.service;

import ru.uoles.proj.model.PostInfo;

import java.io.IOException;
import java.util.List;

public interface InstagramService {

    void connect(String login, String password) throws IOException;

    List<PostInfo> getPosts(String login) throws IOException;

    void addPosts(List<PostInfo> posts, String captionPrefix, int postDelay) throws IOException, InterruptedException;
}
