package org.yzh.web.service;

import org.yzh.web.model.vo.Location;
import org.yzh.web.model.entity.LocationDO;
import org.yzh.web.model.vo.LocationQuery;

import java.util.List;

public interface LocationService {

    List<LocationDO> find(LocationQuery query);

    void batchInsert(List<Location> list);
}