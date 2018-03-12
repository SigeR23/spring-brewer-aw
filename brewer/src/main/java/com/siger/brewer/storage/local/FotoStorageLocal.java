package com.siger.brewer.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.siger.brewer.storage.FotoStorage;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;



public class FotoStorageLocal implements FotoStorage{

	private static final Logger LOGGER = LoggerFactory.getLogger(FotoStorageLocal.class);
	
	private Path local;
	private Path localtemporario;
	
	public FotoStorageLocal() {
		this(getDefault().getPath(System.getenv("HOME"), ".brewerfotos"));
	}
	
	public FotoStorageLocal(Path path) {
		this.local = path;
		criarPastas();
	}
	
	@Override
	public void salvar(String foto) {
		try {
			Files.move(this.localtemporario.resolve(foto), this.local.resolve(foto));
		} catch (IOException e) {
			throw new RuntimeException("Erro movendo a foto para destino final", e);
		}
		
		try {
			Thumbnails.of(this.local.resolve(foto).toString()).size(50, 68).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro gerando thumbnail", e);
		};
	}
	
	@Override
	public String salvarTemporariamente(MultipartFile[] files) {
		String novoNome = null;
		if (files != null && files.length > 0) {
			MultipartFile file = files[0];
			novoNome = renomearArquivo(file.getOriginalFilename());
			try {
				file.transferTo(new File(this.localtemporario.toAbsolutePath().toString() + getDefault().getSeparator() + novoNome));
			} catch (IOException e) {
				throw new RuntimeException("Erro salvando a foto na pasta temp", e);
			}
		}
		
		return novoNome;
	}
	
	@Override
	public byte[] recuperarFoto(String nome) {
		try {
			return Files.readAllBytes(this.local.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao recuperar a foto", e);
		}
	}
	
	@Override
	public byte[] recuperarFotoTemporaria(String nome) {
		try {
			return Files.readAllBytes(this.localtemporario.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao recuperar a foto", e);
		}
	}
	
	private void criarPastas() {
		try {
			Files.createDirectories(this.local);
			this.localtemporario = getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localtemporario);
			
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("Pasta criadas para salvar fotos");
				LOGGER.debug("Pasta default: " + this.local.toAbsolutePath());
				LOGGER.debug("Pasta temporaria: " + this.localtemporario.toAbsolutePath());
			}
			
		} catch (IOException e) {
			throw new RuntimeException("Erro criando pasta para salvar foto", e);
		}
		 
	}
	
	private String renomearArquivo(String nomeOriginal) {
		String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;
				
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug(String.format("Nome Original: %s, Novo Nome: %s", nomeOriginal, novoNome));
		}
		
		return novoNome;
	}
}