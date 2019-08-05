package com.ns.service;

import java.util.List;

import com.ns.entity.DamageList;
import com.ns.entity.DamageListGoods;

public interface IGoodsDamageService {

	int GoodsDamageSave(DamageList damageList, List<DamageListGoods> damageListGoods);

	List<DamageList> QueryDamage(DamageList damageList);

	List<DamageListGoods> QueryDamageListGoods(DamageList damageList);

}
