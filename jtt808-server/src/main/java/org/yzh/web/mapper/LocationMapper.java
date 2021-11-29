package org.yzh.web.mapper;

import org.springframework.stereotype.Repository;
import org.yzh.web.model.entity.LocationDO;
import org.yzh.web.model.vo.LocationQuery;

import java.util.List;

@Repository
public interface LocationMapper {

    List<LocationDO> find(LocationQuery query);

}