insert ignore into area(id, limit_in_out, limit_speed, limit_time, name, agency_id, mark_type, geom_type, geom_text) values
  (1, 1, 0, 1, '圆形1', 0, 1, 1, '121.740204,30.886695,100'),
  (2, 2, 10, 1, '矩形4', 0, 1, 2, '121.746942,30.881797,121.750547,30.879329'),
  (3, 1, 10, 1, '多边形2', 0, 1, 3, '121.739603,30.882754,121.740548,30.882644,121.740140,30.880784,121.742629,30.880692,121.742307,30.879789,121.739110,30.880047'),
  (4, 2, 5, 1, '路线3', 0, 1, 4, '121.740140,30.882183,121.739582,30.880158,121.735376,30.880416,30')
;

insert ignore into area_vehicle(vehicle_id, area_id) values
  (1, 1),
  (1, 2),
  (1, 3),
  (1, 4)
;