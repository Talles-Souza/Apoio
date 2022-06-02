package com.residencia.commerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.commerce.dto.EnderecoDTO;
import com.residencia.commerce.dto.ProdutoDTO;
import com.residencia.commerce.entity.Endereco;
import com.residencia.commerce.repository.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	EnderecoRepository enderecoRepository;

	public List<EnderecoDTO> findAllEndereco(){
        List<Endereco> listaEnderecoEntity = enderecoRepository.findAll();
        List<EnderecoDTO> listaEnderecoDTO = new ArrayList<>();

        for(Endereco endereco : listaEnderecoEntity) {
            listaEnderecoDTO.add(converterEntityToDTO(endereco));
        }

        return listaEnderecoDTO;
    }

    public EnderecoDTO findEnderecoById(Integer id) {
        return enderecoRepository.findById(id).isPresent() ? converterEntityToDTO(enderecoRepository.findById(id).get()) : null;
    }

    public EnderecoDTO saveEndereco(EnderecoDTO enderecoDTO) {
    	Endereco endereco = enderecoRepository.save(ConverteDTOToEntidade(enderecoDTO));
        return converterEntityToDTO(endereco);
    }

    public EnderecoDTO updateEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoRepository.save(ConverteDTOToEntidade(enderecoDTO));
        return converterEntityToDTO(endereco);
    }


	public void deleteEnderecoById(Integer id) {
		Endereco endereco = enderecoRepository.findById(id).get();
		enderecoRepository.delete(endereco);
	}

	public Endereco ConverteDTOToEntidade(EnderecoDTO enderecoDTO) {
		Endereco endereco = new Endereco();

		endereco.setBairroEndereco(enderecoDTO.getBairroEndereco());
		endereco.setCepEndereco(enderecoDTO.getCepEndereco());
		endereco.setCidadeEndereco(enderecoDTO.getCidadeEndereco());
		endereco.setComplemetnoEndereco(enderecoDTO.getComplemetnoEndereco());
		endereco.setIdEndereco(enderecoDTO.getIdEndereco());
		endereco.setNumeroEndereco(enderecoDTO.getNumeroEndereco());
		endereco.setRuaEndereco(enderecoDTO.getRuaEndereco());
		endereco.setUfEndereco(enderecoDTO.getUfEndereco());

		return endereco;
	}
	
	public EnderecoDTO converterEntityToDTO(Endereco endereco) {
		EnderecoDTO enderecoDTO = new EnderecoDTO();

		enderecoDTO.setBairroEndereco(endereco.getBairroEndereco());
		enderecoDTO.setCepEndereco(endereco.getCepEndereco());
		enderecoDTO.setCidadeEndereco(endereco.getCidadeEndereco());
		enderecoDTO.setComplemetnoEndereco(endereco.getComplemetnoEndereco());
		enderecoDTO.setIdEndereco(endereco.getIdEndereco());
		enderecoDTO.setNumeroEndereco(endereco.getNumeroEndereco());
		enderecoDTO.setRuaEndereco(endereco.getRuaEndereco());
		enderecoDTO.setUfEndereco(endereco.getUfEndereco());

		return enderecoDTO;
	}
}
