package com.victorio.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.victorio.workshopmongo.domain.Post;
import com.victorio.workshopmongo.domain.User;
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

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Bora viajar", "Partiu para praia, abraços!", joao);
		Post post2 = new Post(null, sdf.parse("21/03/2018"), "Bora pedalar", "Meta é pedalar 50km hoje!!", victorio);

		userRepository.saveAll(Arrays.asList(joao, vitor, victorio));
		postRepository.saveAll(Arrays.asList(post1, post2));
	}
}
