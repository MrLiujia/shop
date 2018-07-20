package shop.service;

import java.util.List;

import shop.model.Cellphone;

public interface IndexService {

	List<Cellphone> search(Cellphone cellphone);

	Cellphone findOne(Long id);

}
