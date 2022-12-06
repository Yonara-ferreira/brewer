package com.ProjetoNarah.brewer.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.ProjetoNarah.brewer.storage.FotoStorage;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class FotoStorageLocal implements FotoStorage {
	
	private static final Logger logger = LoggerFactory.getLogger(FotoStorageLocal.class);
	
	
	@Override
	public byte[] recuperarFotoTemporaria(String nome) {
		try {
			return Files.readAllBytes(this.localTemporario.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro na leitura da foto temporaria", e);
		
		}
	}
	
	private Path local;
	private Path localTemporario;
	
	
	
	
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
			Files.move(this.localTemporario.resolve(foto), this.local.resolve(foto));
		} catch (IOException e) {
			throw new RuntimeException("erro ao move a foto para o diretorio final", e);
		}
		
		try {
			Thumbnails.of(this.local.resolve(foto).toString()).size(40, 68).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			throw new RuntimeException("erro apos gerar thumbanails", e);
		}
	}
	
	
	@Override
	public byte[] recuperar(String nome) {
		try {
			return Files.readAllBytes(this.local.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro na leitura da foto do diretorio final", e);
		
		}
	}
	
	private void criarPastas() {
		try {
			Files.createDirectories(this.local);
			this.localTemporario = getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemporario);
			
			if(logger.isDebugEnabled()) {
				logger.debug("Pastas com finalidade de salvar as fotos");
				logger.debug("Pastas defaoult:  " + this.local.toAbsolutePath());
				logger.debug("pasta temporaria: " + this.localTemporario.toAbsolutePath());
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro na criação da pasta para salvar fotos", e);
		}
	}


	
	@Override
	public String salvarTemporariamente(MultipartFile[] files) {
		String novoNome = null;
		if (files != null && files.length >0 ) {
			MultipartFile arquivo = files[0];
			novoNome = renomearArquivo(arquivo.getOriginalFilename());
			try {
				arquivo.transferTo(new File(this.localTemporario.toAbsolutePath().toString() + getDefault().getSeparator() + novoNome));
			} catch ( IOException e) {
				throw new RuntimeException("erro ao salvar a foto na pasta temporaria", e);
			}
		}
		return novoNome;
	}
	
	private String renomearArquivo(String nomeOriginal) {
		String novoNome = UUID.randomUUID().toString() +  "__" + nomeOriginal;
		
		if(logger.isDebugEnabled()) {
			logger.debug(String.format("nome original: %s, novo nome: %s",nomeOriginal, novoNome));
		}
		return novoNome;
	}



	




}
