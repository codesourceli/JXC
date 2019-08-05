package com.ns.dao;

import java.util.List;

import com.ns.entity.DamageList;
import com.ns.entity.DamageListGoods;
import com.ns.entity.Goods;

public interface IGoodsDamageDao {

	int SaveDamageBill(DamageList damageList);

	int SaveDamageListGoods(DamageListGoods damageListGoods);

	void UpdateInventoryQuantity(Goods goods);

	List<DamageList> QueryDamage(DamageList damageList);

	List<DamageListGoods> SelectDamageListGoods(DamageList damageList);

}
