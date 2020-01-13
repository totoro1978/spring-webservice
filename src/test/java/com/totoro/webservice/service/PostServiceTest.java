package com.totoro.webservice.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.totoro.webservice.domains.posts.Posts;
import com.totoro.webservice.domains.posts.PostsRepository;
import com.totoro.webservice.dto.posts.PostsSaveRequestDto;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostServiceTest {

	@Autowired
	private PostsService postsService;

	@Autowired
	private PostsRepository postsRepository;

	@AfterEach
	public void cleanup () {
		postsRepository.deleteAll();
	}

	@Test
	public void testSavingDto_postsTable() {
		//given
		PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
				.author("testor@gmail.com")
				.content("Test")
				.title("Test Title")
				.build();
	
		//when
		postsService.save(dto);
	
		//then
		Posts posts = postsRepository.findAll().get(0);
		assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
		assertThat(posts.getContent()).isEqualTo(dto.getContent());
		assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
	}
	
}
