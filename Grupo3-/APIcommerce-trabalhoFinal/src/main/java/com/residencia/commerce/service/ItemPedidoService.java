package com.residencia.commerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.commerce.dto.ItemPedidoDTO;
import com.residencia.commerce.entity.ItemPedido;
import com.residencia.commerce.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	public List<ItemPedidoDTO> findAllItemPedido(){
        List<ItemPedido> listaItemPedidoEntity = itemPedidoRepository.findAll();
        List<ItemPedidoDTO> listaItemPedidoDTO = new ArrayList<>();

        for(ItemPedido itemPedido : listaItemPedidoEntity) {
            listaItemPedidoDTO.add(converterEntityToDTO(itemPedido));
        }

        return listaItemPedidoDTO;
    }

    public ItemPedidoDTO findItemPedidoById(Integer id) {
        return itemPedidoRepository.findById(id).isPresent() ? converterEntityToDTO(itemPedidoRepository.findById(id).get()) : null;
    }

    public ItemPedidoDTO saveItemPedido(ItemPedidoDTO itemPedidoDTO) {
    	itemPedidoDTO.setValorBrutoItemPedido(itemPedidoDTO.getPrecoVendaItemPedido()*itemPedidoDTO.getQuantidadeItemProduto());
    	Double perce = itemPedidoDTO.getValorBrutoItemPedido()*(itemPedidoDTO.getPercentualDescontoItemPedido()/100);
    	itemPedidoDTO.setValorLiquidoItemPedido( itemPedidoDTO.getValorBrutoItemPedido() - perce);
    	ItemPedido itemPedido = itemPedidoRepository.save(ConverteDTOToEntidade(itemPedidoDTO));
        return converterEntityToDTO(itemPedido);
    }

    public ItemPedidoDTO updateItemPedido(ItemPedidoDTO itemPedidoDTO) {
    	ItemPedido itemPedido = itemPedidoRepository.save(ConverteDTOToEntidade(itemPedidoDTO));
        return converterEntityToDTO(itemPedido);
    }
    public void deleteItemPedidoById(Integer id) {
    	ItemPedido itemPedido = itemPedidoRepository.findById(id).get();
    	itemPedidoRepository.delete(itemPedido);

    }
	
	private ItemPedido ConverteDTOToEntidade(ItemPedidoDTO itemPedidoDTO) {
		ItemPedido itemPedido = new ItemPedido();

		itemPedido.setIdItemPedido(itemPedidoDTO.getIdItemPedido());
		itemPedido.setQuantidadeItemProduto(itemPedidoDTO.getQuantidadeItemProduto());
		itemPedido.setPrecoVendaItemPedido(itemPedidoDTO.getPrecoVendaItemPedido());
		itemPedido.setPercentualDescontoItemPedido(itemPedidoDTO.getPercentualDescontoItemPedido());
		itemPedido.setValorBrutoItemPedido(itemPedidoDTO.getValorBrutoItemPedido());
		itemPedido.setValorLiquidoItemPedido(itemPedidoDTO.getValorLiquidoItemPedido());
		//itemPedido.getPedido().setIdPedido(itemPedidoDTO.getPedidoDTO().getIdPedido());
		itemPedido.getProduto().setIdProduto(itemPedidoDTO.getProdutoDTO().getIdProduto());


		return itemPedido;
	}

	private ItemPedidoDTO converterEntityToDTO(ItemPedido itemPedido) {
		ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();

		itemPedidoDTO.setIdItemPedido(itemPedido.getIdItemPedido());
		itemPedidoDTO.setQuantidadeItemProduto(itemPedido.getQuantidadeItemProduto());
		itemPedidoDTO.setPrecoVendaItemPedido(itemPedido.getPrecoVendaItemPedido());
		itemPedidoDTO.setPercentualDescontoItemPedido(itemPedido.getPercentualDescontoItemPedido());
		itemPedidoDTO.setValorBrutoItemPedido(itemPedido.getValorBrutoItemPedido());
		itemPedidoDTO.setValorLiquidoItemPedido(itemPedido.getValorLiquidoItemPedido());
		//itemPedidoDTO.getPedidoDTO().setIdPedido(itemPedido.getPedido().getIdPedido());
		itemPedidoDTO.getProdutoDTO().setIdProduto(itemPedido.getProduto().getIdProduto());
		
		return itemPedidoDTO;

	}
}
