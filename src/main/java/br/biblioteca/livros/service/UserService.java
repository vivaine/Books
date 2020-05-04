package br.biblioteca.livros.service;

import java.util.List;

import br.biblioteca.livros.models.User;

public interface UserService {

	void save(User user);

	User findByUsername(String username);

	List<User> findAll();

}
