package br.lumis.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.lumis.model.Candidato;
import br.lumis.model.enumerator.EnumCategoria;
import br.lumis.service.CandidatoService;

@Controller
public class CandidatoController implements WebMvcConfigurer {

	@Autowired
	CandidatoService candidatoService;

	@GetMapping
	public String index(Model model) {

		model.addAttribute("candidato", new Candidato());
		return "index";
	}

	@PostMapping("/gravar")
	public String criarUsuario(@ModelAttribute @Validated Candidato newEntity, BindingResult result, Model model)
			throws Exception {

		if (result.hasErrors()) {
			return "index";
		} else {

			if (!candidatoService.verificarSeExiste(newEntity)) {
				candidatoService.criarUsuario(newEntity);
				model.addAttribute("candidatos", candidatoService.buscarTodos());
				return "listaCandidatos";
			}

			return "index";
		}

	}

	@GetMapping("/editarCandidato/{id}")
	public String editarCandidato(@PathVariable("id") long id, Model model) throws IllegalArgumentException, Exception {

		Candidato candidato = candidatoService.buscarCandidato(id)
				.orElseThrow(() -> new IllegalArgumentException("Candidato Inválido :" + id));
		model.addAttribute("candidato", candidato);
		return "editarCandidato";

	}

	@PostMapping("/atualizarCandidato/{id}")
	public String atualizarCandidato(@PathVariable("id") long id, @Validated Candidato candidato, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			candidato.setId(id);
			return "editarCandidato";
		}
		candidatoService.criarUsuario(candidato);
		model.addAttribute("candidatos", candidatoService.buscarTodos());
		return "listaCandidatos";
	}

	@GetMapping("/listarCandidatos")
	public String listarTodos(Model model) {
		model.addAttribute("candidatos", candidatoService.buscarTodos());
		return "listaCandidatos";
	}

	@GetMapping("/deletarCandidato/{id}")
	public String deletarCandidato(@PathVariable("id") long id, Model model)
			throws IllegalArgumentException, Exception {
		Candidato candidato = candidatoService.buscarCandidato(id)
				.orElseThrow(() -> new IllegalArgumentException("Id Invalido :" + id));

		candidatoService.deletarCandidato(candidato);
		model.addAttribute("candidatos", candidatoService.buscarTodos());

		return "listaCandidatos";

	}

	@GetMapping("/detalharCandidato/{id}")
	public String detalharCandidato(@PathVariable("id") long id, Model model) throws Exception {
		Candidato candidato = candidatoService.buscarCandidato(id)
				.orElseThrow(() -> new IllegalArgumentException("Id Invalido :" + id));

		model.addAttribute("candidato", candidato);
		return "detalhes";

	}

}