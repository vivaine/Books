package br.biblioteca.livros.converter;

import java.util.List;
import java.util.stream.Collectors;

import br.biblioteca.livros.dto.EvaluationDTO;
import br.biblioteca.livros.models.Book;
import br.biblioteca.livros.models.Evaluation;

public class EvaluationConverter {

	public static Evaluation toModel(EvaluationDTO evaluationDTO) {

		Evaluation evaluation = new Evaluation();
		evaluation.setComment(evaluationDTO.getComment());
		evaluation.setGrade(evaluationDTO.getGrade());
		return evaluation;
	}

	public static Evaluation toModel(EvaluationDTO evaluationDTO, Book book) {

		Evaluation evaluation = new Evaluation();
		evaluation.setComment(evaluationDTO.getComment());
		evaluation.setGrade(evaluationDTO.getGrade());
		evaluation.setBook(book);
		return evaluation;
	}

	public static EvaluationDTO toDTO(Evaluation evaluation) {
		EvaluationDTO dto = new EvaluationDTO();
		dto.setComment(evaluation.getComment());
		dto.setGrade(evaluation.getGrade());
		return dto;
	}

	public static List<EvaluationDTO> toDTO(List<Evaluation> evaluations) {

		return evaluations.stream().map(l -> toDTO(l)).collect(Collectors.toList());
	}
}
