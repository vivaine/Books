package br.biblioteca.livros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.biblioteca.livros.models.Evaluation;
import br.biblioteca.livros.repository.EvaluationRepository;

@Service
public class EvaluationsService {

	@Autowired
	EvaluationRepository evaluationRepository;

	public Long saveEvaluation(Evaluation evaluation) {
		return evaluationRepository.save(evaluation).getId();
	}

	public Evaluation findEvaluation(Long id) {
		return evaluationRepository.findById(id).orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));
	}

	public void deleteEvaluation(Long id) {
		evaluationRepository.deleteById(id);
	}
}
