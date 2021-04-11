package ru.uoles.proj.service;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUploadAlbumRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUploadPhotoRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUserFeedRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramCarouselMediaItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import ru.uoles.proj.model.PostInfo;

import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;
import static ru.uoles.proj.utils.FileUtils.urlToFile;

public class InstagramServiceImpl implements InstagramService {

    private Instagram4j instagram4j;
    private final Format formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void connect(String login, String password) throws IOException {
        instagram4j = Instagram4j.builder().username(login).password(password).build();
        instagram4j.setup();
        instagram4j.login();
    }

    @Override
    public List<PostInfo> getPosts(String login) throws IOException {
        List<PostInfo> posts = new ArrayList<>();
        InstagramSearchUsernameResult usernameResult = instagram4j.sendRequest(new InstagramSearchUsernameRequest(login));

        InstagramFeedResult postList = instagram4j.sendRequest(new InstagramUserFeedRequest(usernameResult.getUser().getPk()));
        for (int i = 0; i < postList.getItems().size(); i++) {
            InstagramFeedItem post = postList.getItems().get(i);
            List<File> listPhoto = new ArrayList<>();

            System.out.println("Post #" + i + " got");

            if (post.image_versions2 == null) {
                for (InstagramCarouselMediaItem pict : post.carousel_media) {
                    listPhoto.add(
                            urlToFile(
                                    pict.image_versions2.candidates.get(0).url,
                                    Integer.toString(i)
                            )
                    );
                }
            } else {
                listPhoto.add(
                        urlToFile(
                                post.image_versions2.getCandidates().get(0).url,
                                Integer.toString(i)
                        )
                );
            }

            String caption = "";
            if (post.caption != null && post.caption.getText() != null) {
                caption = post.caption.getText();
            }

            String postDate = formatter.format(new Date((long) post.taken_at * 1000));
            posts.add(
                    new PostInfo(
                            caption,
                            listPhoto,
                            postDate
                    )
            );
        }

        return posts;
    }

    @Override
    public void addPosts(List<PostInfo> posts, String captionPrefix, int postDelay) throws IOException, InterruptedException {
        if (posts != null && !posts.isEmpty()) {
            for (int i = posts.size() - 1; i >= 0; i--) {
                String text = captionPrefix + " " + posts.get(i).getFromDate() + "\n" + posts.get(i).getCaption();

                if (posts.get(i).getListPhoto().size() > 1) {
                    // если в публикации больше 1го фото
                    instagram4j.sendRequest(
                            new InstagramUploadAlbumRequest(
                                    posts.get(i).getListPhoto(),
                                    text
                            )
                    );
                } else {
                    // если в публикации 1 фото
                    instagram4j.sendRequest(
                            new InstagramUploadPhotoRequest(
                                    posts.get(i).getListPhoto().get(0),
                                    text
                            )
                    );
                }

                System.out.println(text);
                System.out.println("---------------------------------");

                sleep(postDelay);
            }
        }
    }
}
