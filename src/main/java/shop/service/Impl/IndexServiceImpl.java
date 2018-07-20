package shop.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mapper.IndexMapper;
import shop.model.Cellphone;
import shop.service.IndexService;
@Service
public class IndexServiceImpl implements IndexService{
	
	private IndexMapper indexMapper;

	@Autowired
	public IndexServiceImpl(IndexMapper indexMapper) {
		this.indexMapper = indexMapper;
	}



	public Cellphone findOne(Long id) {
		// TODO Auto-generated method stub
		return indexMapper.findOne(id);
	}

	@Override
	public List<Cellphone> search(Cellphone cellphone) {
		// TODO Auto-generated method stub
		return indexMapper.search(cellphone);
	}

}
