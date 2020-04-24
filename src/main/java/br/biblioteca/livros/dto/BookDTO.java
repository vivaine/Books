package br.biblioteca.livros.dto;

import java.util.ArrayList;
import java.util.List;

public class BookDTO {

	private String title;

	private int numberPages;

	private String author;

	private List<EvaluationDTO> evaluations = new ArrayList<EvaluationDTO>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNumberPages() {
		return numberPages;
	}

	public void setNumberPages(int numberPages) {
		this.numberPages = numberPages;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<EvaluationDTO> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<EvaluationDTO> evaluations) {
		this.evaluations = evaluations;
	}

}
