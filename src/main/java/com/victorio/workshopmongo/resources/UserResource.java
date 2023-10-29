package com.victorio.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.victorio.workshopmongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {
		User joao = new User("1", "João", "joao.victorio@teste.com");
		User vitor = new User("2", "Vitor", "vitor.victorio@teste.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(joao, vitor));
		return ResponseEntity.ok().body(list);
	}
}
