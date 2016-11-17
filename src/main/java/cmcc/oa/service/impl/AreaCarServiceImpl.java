package cmcc.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cmcc.oa.dao.AreaCarMapper;
import cmcc.oa.entity.AreaCar;
import cmcc.oa.service.AreaCarService;

@Service
public class AreaCarServiceImpl implements AreaCarService {
	
	@Autowired
	private AreaCarMapper areaCarMapper;
	
	@Override
	public List<AreaCar> selectAllArea() {
		
		List<AreaCar> areaCars = areaCarMapper.selectAllArea();
		
		return areaCars;
	}

	@Override
	public AreaCar selectCarInfoByPrimaryKey(AreaCar record) {
		
		AreaCar car = areaCarMapper.selectCarInfoByPrimaryKey(record);
		
		return car;
	}

	@Override
	public void insertArea(AreaCar record) {
		AreaCar areaCar = new AreaCar();
		areaCar.setOwnerArea(record.getOwnerArea());
		areaCar.setInnerArea(record.getInnerArea());
		areaCar.setLeaderArea(record.getLeaderArea());
		areaCar.setOuterArea(record.getOuterArea());
		areaCar.setTotalCar(record.getTotalCar());
		areaCar.setFlag(1);
		areaCarMapper.insertArea(areaCar);
	}

	@Override
	public void updateArea(AreaCar record) {
		areaCarMapper.updateArea(record);
	}

	@Override
	public void deleteByPrimaryKey(Integer id) {
		areaCarMapper.deleteByPrimaryKey(id);
	}

}
