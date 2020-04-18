package br.biblioteca.livros.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "BOOKS")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@NotEmpty
	@Column(name = "NAME", nullable = false)
	private String name;

	@Min(2)
	@Column(name = "NUMBERPAGES")
	private int numberPages;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "AUTHOR_ID")
	private Author author;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getNumberPages() {
		return numberPages;
	}

	public void setNumberPages(final int numberPages) {
		this.numberPages = numberPages;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(final Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder()//
				.append("Livro [")//
				.append("id=")//
				.append(id)//
				.append(",nome=\"")//
				.append(name).append("\"")//
				.append(",quantidadePaginas=")//
				.append(numberPages)//
				.append("]");
		return builder.toString();
	}
}
