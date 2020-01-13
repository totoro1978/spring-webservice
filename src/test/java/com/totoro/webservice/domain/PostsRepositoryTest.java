package com.totoro.webservice.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.totoro.webservice.domains.posts.Posts;
import com.totoro.webservice.domains.posts.PostsRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

	@Autowired
	PostsRepository postsRepository;

	@AfterEach
	public void cleanup() {
		/* Clean up repository to make sure not to effect other tests*/
		postsRepository.deleteAll();
	}

	@Test
	public void retrievePosts() {
		//given
		postsRepository.save(Posts.builder()
				.title("Test Title")
				.content("Test Content")
				.author("test@gmail.com")
				.build());
	
		//when
		List<Posts> postsList = postsRepository.findAll();
	
		//then
		Posts posts = postsList.get(0);
		assertThat(posts.getTitle(), is("Test Title"));
		assertThat(posts.getContent(), is("Test Content"));
	}

	@Test
	public void BaseTimeEntity_Register() {
		//given
		LocalDateTime now = LocalDateTime.now();
		postsRepository.save(Posts.builder()
				.title("Test Title")
				.content("Test Content")
				.author("test@gmail.com")
				.build());
		//when
		List<Posts> postsList = postsRepository.findAll();

		//then
		Posts posts = postsList.get(0);
		assertTrue(posts.getCreatedDate().isAfter(now));
		assertTrue(posts.getModifiedDate().isAfter(now));
	}
}
