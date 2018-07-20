package shop.mapper;

import java.util.List;

import shop.model.Cellphone;

public interface IndexMapper {
	List<Cellphone> search(Cellphone cellphone);

	Cellphone findOne(Long id);

}
