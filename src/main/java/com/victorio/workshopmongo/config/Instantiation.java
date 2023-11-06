package com.victorio.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.victorio.workshopmongo.domain.Post;
import com.victorio.workshopmongo.domain.User;
import com.victorio.workshopmongo.dto.AuthorDTO;
import com.victorio.workshopmongo.dto.CommentDTO;
import com.victorio.workshopmongo.repository.PostRepository;
import com.victorio.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... arg0) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();

		User joao = new User(null, "João", "joao@gmail.com");
		User vitor = new User(null, "Vitor", "vitor@gmail.com");
		User victorio = new User(null, "Victorio", "victorio@gmail.com");
		userRepository.saveAll(Arrays.asList(joao, vitor, victorio));

		Post post1 = new Post(null, sdf.parse("21/03/2023"), "Bora viajar", "Partiu para praia, abraços!",
				new AuthorDTO(joao));
		Post post2 = new Post(null, sdf.parse("10/04/2023"), "Bora pedalar", "Meta é pedalar 50km hoje!!",
				new AuthorDTO(victorio));

		CommentDTO c1 = new CommentDTO("Boa viajem!", sdf.parse("21/03/2023"), new AuthorDTO(vitor));
		CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("10/04/2023"), new AuthorDTO(victorio));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("10/04/2023"), new AuthorDTO(vitor));

		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));

		postRepository.saveAll(Arrays.asList(post1, post2));

		joao.getPosts().addAll(Arrays.asList(post1));
		userRepository.save(joao);

		victorio.getPosts().addAll(Arrays.asList(post2));
		userRepository.save(victorio);
	}
}
