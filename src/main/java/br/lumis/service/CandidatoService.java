package br.lumis.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.lumis.model.Candidato;
import br.lumis.repository.ICandidatoRepository;

@Service
public class CandidatoService {

	@Autowired
	private ICandidatoRepository candidatoRepository;
	private final Gson gson = new Gson();
	private final String caminho = "C:\\Users\\alexa\\eclipse-workspace\\TesteLumis2\\src\\main\\resources\\dados.json";

	
	public CandidatoService(ICandidatoRepository icandidatoRepository) {
		this.candidatoRepository = icandidatoRepository;
	}
	
	public void criarUsuario(Candidato entity) {
		
	   try {
		   
		   candidatoRepository.save(entity);
		   String json = gson.toJson(entity);
		   FileWriter file = new FileWriter(caminho,true);
		   file.write(json);
		   file.write("\n");
		   file.flush();
		   file.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Optional<Candidato> buscarCandidato(long  id) throws Exception{
			return this.candidatoRepository.findById(id);
	}
	public void deletarCandidato(Candidato entity) throws Exception {
			this.candidatoRepository.delete(entity);
	}
	public List<Candidato>buscarTodos(){
		return this.candidatoRepository.findAll();
	}
	
	public boolean verificarSeExiste(Candidato entity) throws Exception {
		
		try {
			
			FileInputStream fstream = new FileInputStream("../dados.json");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			
			List<Candidato> candidatos = new ArrayList<Candidato>();
			
			String linha;
			
			while((linha = br.readLine()) != null) {
				candidatos.add(gson.fromJson(linha, Candidato.class));
			}
			
			br.close();
			
			for (Candidato x : candidatos) {
					x = entity;
				if(candidatos.contains(x)) {
					 return true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void apagarDoArquivo(Candidato entity) {
		
	try {
			
			FileInputStream fstream = new FileInputStream("../dados.json");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			
			List<Candidato> candidatos = new ArrayList<Candidato>();
			
			String linha;
			
			while((linha = br.readLine()) != null) {
				candidatos.add(gson.fromJson(linha, Candidato.class));
			}
			
			br.close();
			
			for (Candidato obj : candidatos) {
				obj = entity;
				if(candidatos.contains(obj)) {
					
					candidatos.remove(obj);
				}
			}
			
			  String json = gson.toJson(candidatos);
			  
			   FileWriter file = new FileWriter(caminho,true);
			   file.write(json);
			   file.write("\n");
			   file.flush();
			   file.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
