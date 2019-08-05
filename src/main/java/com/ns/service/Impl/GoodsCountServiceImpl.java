package com.ns.service.Impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ns.Util.DateUtils;
import com.ns.dao.IGoodsCountDao;
import com.ns.entity.CustomerReturnList;
import com.ns.entity.CustomerReturnListGoods;
import com.ns.entity.Goods;
import com.ns.entity.PurchaseList;
import com.ns.entity.PurchaseListGoods;
import com.ns.entity.ReturnList;
import com.ns.entity.ReturnListGoods;
import com.ns.entity.SaleCount;
import com.ns.entity.SaleList;
import com.ns.entity.SaleListGoods;
import com.ns.service.IGoodsCountService;

@Service
@Transactional
public class GoodsCountServiceImpl implements IGoodsCountService {

	@Autowired
	private IGoodsCountDao goodsCountDao;

	/**
	 * 采购统计（进货）
	 */
	@Override
	public List<PurchaseListGoods> PurchaselistGoodsCount(PurchaseListGoods purchaseListGoods, String begin,String end) {
		List<PurchaseListGoods> pcount= goodsCountDao.PurchaselistGoodsCount(purchaseListGoods);
		List<PurchaseList> purchaseList=goodsCountDao.PurchaselistCount(begin,end);
		
		List<PurchaseListGoods> returnGoods=new ArrayList<PurchaseListGoods>();
		for (int i = 0; i < pcount.size(); i++) {
			PurchaseListGoods pg=pcount.get(i);
			for (int j = 0;j < purchaseList.size(); j++) {
				PurchaseList pl=purchaseList.get(j);
				if(pg.getPurchaseList().getId()==pl.getId()){
					pg.setPurchaseList(pl);
					returnGoods.add(pg);
				}
			}
		}
		return returnGoods;
	}
	/**
	 * 采购统计（退货）
	 */
	@Override
	public List<ReturnListGoods> PurchaselistGoodsCount(ReturnListGoods returnListGoods, String begin, String end) {
		List<ReturnListGoods> rcount = goodsCountDao.ReturnListGoodsCount(returnListGoods);
		List<ReturnList> returnList=goodsCountDao.ReturnListCount(begin,end);
		
		List<ReturnListGoods> returnGoods=new ArrayList<ReturnListGoods>();
		for (int i = 0; i < rcount.size(); i++) {
			ReturnListGoods rg=rcount.get(i);
			for (int j = 0;j < returnList.size(); j++) {
				ReturnList rl=returnList.get(j);
				if(rg.getReturnList().getId()==rl.getId()){
					rg.setReturnList(rl);
					returnGoods.add(rg);
				}
			}
		}
		return returnGoods;
	}

	
	/**
	 * 销售统计（进货）
	 */
	@Override
	public List<SaleListGoods> SaleListGoodsCount(SaleListGoods saleListGoods, String begin, String end) {
		List<SaleListGoods> scount= goodsCountDao.SaleListGoodsCount(saleListGoods);
		List<SaleList> saleList=goodsCountDao.SaleListCount(begin,end);
		
		List<SaleListGoods> returnGoods=new ArrayList<SaleListGoods>();
		for (int i = 0; i < scount.size(); i++) {
			SaleListGoods sg=scount.get(i);
			for (int j = 0;j < saleList.size(); j++) {
				SaleList sl=saleList.get(j);
				if(sg.getSaleList().getId()==sl.getId()){
					sg.setSaleList(sl);
					returnGoods.add(sg);
				}
			}
		}
		return returnGoods;
	}
	/**
	 * 销售统计（退货）
	 */
	@Override
	public List<CustomerReturnListGoods> CustomerReturnListGoodsCount(CustomerReturnListGoods customerReturnListGoods,String begin, String end) {
		List<CustomerReturnListGoods> ccount= goodsCountDao.CustomerReturnListGoodsCount(customerReturnListGoods);
		List<CustomerReturnList> customerreturnList=goodsCountDao.CustomerReturnListCount(begin,end);
		
		List<CustomerReturnListGoods> returnGoods=new ArrayList<CustomerReturnListGoods>();
		for (int i = 0; i < ccount.size(); i++) {
			CustomerReturnListGoods cg=ccount.get(i);
			for (int j = 0;j < customerreturnList.size(); j++) {
				CustomerReturnList cl=customerreturnList.get(j);
				if(cg.getCustomerReturnList().getId()==cl.getId()){
					cg.setCustomerReturnList(cl);
					returnGoods.add(cg);
				}
			}
		}
		return returnGoods;
	}
	
	
	/**
	 * 统计分析
	 * @throws ParseException 
	 */
	@Override
	public List<SaleCount> QuerySaleDayCount(String begin, String end,String type) throws ParseException {
		List<SaleList> salelist = goodsCountDao.SaleListCount(begin,end);
		List<SaleCount> saleCountList=new ArrayList<SaleCount>();
		float amountCost=0; float amountSale=0;float mountProfit=0;
			for (int i = 0; i < salelist.size(); i++) {
				for (int j = 0; j < salelist.get(i).getSaleListGoodsList().size(); j++) {
					Integer goodsId = salelist.get(i).getSaleListGoodsList().get(j).getGoodsId();
					Goods goods = goodsCountDao.QueryGoodsById(goodsId);
					SaleCount saleCount=new SaleCount();
					amountCost+=goods.getPurchasingPrice()*salelist.get(i).getSaleListGoodsList().get(j).getNum(); //231   800 =1031
					amountSale+=salelist.get(i).getSaleListGoodsList().get(j).getTotal();							//280.5  1299 = 1579.5
					if(j+1==salelist.get(i).getSaleListGoodsList().size()){
						saleCount.setAmountCost(amountCost);
						saleCount.setAmountProfit(amountSale-amountCost);
						saleCount.setAmountSale(amountSale);
						String date=null;
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						date = formatter.format(salelist.get(i).getSaleDate());
						saleCount.setDate(date);
						saleCountList.add(saleCount);
						amountCost=0;mountProfit=0;amountSale=0;
					}
				}
			}
			
			if(type.equals("day")){
				return saleCountList;
			}
			
			List<SaleCount> saleCountListValidata=new ArrayList<SaleCount>();
			//序列化集合对象  实现深拷贝
			for (int i = 0; i < saleCountList.size(); i++) {
				saleCountListValidata.add((SaleCount) SerializationUtils.clone(saleCountList.get(i)));
			}
			List<SaleCount> saleCountListAsMonth=new ArrayList<SaleCount>();
			if(type.equals("month")){
				for (int i = 0; i < saleCountList.size(); i++) {
					SaleCount scD=saleCountList.get(i);
					for (int j =0; j < saleCountListValidata.size(); j++) {
						if(saleCountListValidata.get(j).getDate()==saleCountList.get(i).getDate()){continue;}
						SaleCount scM=saleCountListValidata.get(j);
						if(scD.getDate()!=""&&scM.getDate()!=""&&DateUtils.formatyyyyMM(scD.getDate()).equals(DateUtils.formatyyyyMM(scM.getDate()))){
							amountCost+=scM.getAmountCost();
							amountSale+=scM.getAmountSale();
							mountProfit+=scM.getAmountProfit();
							saleCountListValidata.get(j).setDate("");
						}
						if(j+1==saleCountListValidata.size()&&amountCost>0){
							SaleCount saleCount=new SaleCount();
							saleCount.setAmountCost(amountCost);
							saleCount.setAmountProfit(mountProfit);
							saleCount.setAmountSale(amountSale);
							saleCount.setDate(DateUtils.formatyyyyMM(scD.getDate()));
							saleCountList.get(i).setDate("");
							saleCountListAsMonth.add(saleCount);
							amountCost=0;mountProfit=0;amountSale=0;
						}else if(j+1==saleCountListValidata.size()&&amountCost==0.0&&amountSale==0.0&&mountProfit==0.0&&saleCountListValidata.get(j).getDate()!=""&&saleCountList.get(i).getDate()!=""){
							SaleCount saleCount=new SaleCount();
							saleCount.setAmountCost(scD.getAmountCost());
							saleCount.setAmountProfit(scD.getAmountProfit());
							saleCount.setAmountSale(scD.getAmountSale());
							saleCount.setDate(DateUtils.formatyyyyMM(scD.getDate()));
							saleCountList.get(i).setDate("");
							saleCountListAsMonth.add(saleCount);
						}
						
					}
				}
			}
			
			//按月统计分析
			//List<SaleCount> AsMonthList =AsMonthCount(salelist);
			System.out.println(saleCountListAsMonth);
		return saleCountListAsMonth;
	}
	
	
}
