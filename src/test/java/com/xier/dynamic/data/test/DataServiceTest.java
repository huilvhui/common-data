package com.xier.dynamic.data.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.xier.WebApplication;
import com.xier.dynamic.constant.EnumCompareType;
import com.xier.dynamic.constant.EnumStatisticsType;
import com.xier.dynamic.data.block.InsertPersistBlock;
import com.xier.dynamic.data.block.PersistBlock;
import com.xier.dynamic.data.block.PersistBlock.DataBaseType;
import com.xier.dynamic.data.block.PersistBlock.Filter;
import com.xier.dynamic.data.block.QueryPersistBlock;
import com.xier.dynamic.data.constructor.InsertPersistBodyConstruct;
import com.xier.dynamic.data.constructor.PersistBodyConstruct;
import com.xier.dynamic.data.constructor.QueryPersistBodyConstruct;
import com.xier.dynamic.data.constructor.QueryPersistBodyConstruct.LimitType;
import com.xier.dynamic.form.AbstrctLinkHandler;
import com.xier.dynamic.form.Cell;
import com.xier.dynamic.form.LinkageCell;
import com.xier.dynamic.form.LinkageOption;
import com.xier.dynamic.form.Option;


//@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext_test.xml" })
@SpringBootTest(classes = WebApplication.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataServiceTest {


	@Test
	public void query() {
		//一个持久化查询构造器 ：LimitType.LIMIT为支持分页 NOT_LIMIT为不分页查询全部
		PersistBodyConstruct queryPersistBodyConstruct = new QueryPersistBodyConstruct(LimitType.LIMIT);
		//查询字段
		List<String> columns = new ArrayList<String>();
		columns.add("dateTime");
		columns.add("deviceType");
		columns.add("moduleType");
		//支持pg与mysql  DataBaseType.POSTGRESQL和DataBaseType.MYSQL
		PersistBlock pb = new QueryPersistBlock("tableName", columns, DataBaseType.POSTGRESQL);
		// 检索条件
		List<Filter> filtes = new ArrayList<Filter>();
		//范围查询条件
		filtes.add(new Filter("dateTime", EnumCompareType.MORE.getName(), "20170101000000"));
		filtes.add(new Filter("dateTime", EnumCompareType.LESS.getName(), "20170202235959"));
		//等于查询条件
		filtes.add(new Filter("deviceCode", EnumCompareType.EQUALS.getName(), "123123123aaaaa"));
		pb.setFilters(filtes);
		//查询页条数
		pb.setPageSize(10);
		//查询当前页码
		pb.setCurrentPage(1);
		//支持排序
		pb.setOrderByClause("datetime desc"); //时间倒序
		//生成sql
		String querySql = pb.build(queryPersistBodyConstruct).getExcuteSql();
		
		System.out.println("==========================querySql : " + querySql);
	}
	
	
	@Test
	public void GroupQuery() {
		//一个持久化查询构造器 ：LimitType.LIMIT为支持分页 NOT_LIMIT为不分页查询全部
		PersistBodyConstruct queryPersistBodyConstruct = new QueryPersistBodyConstruct(LimitType.NOT_LIMIT);
		//统计字段值 格式：字段名称：统计类型类型{deviceType:EnumStatisticsType.MAX ,deviceCode:EnumStatisticsType.SUM}
		Map<String, EnumStatisticsType> countColumns =  new HashMap<String, EnumStatisticsType>(){
            private static final long serialVersionUID = 1L;
            {
            	//统计字段colomn1的最大值
            	put("colomn1",EnumStatisticsType.MAX);
            	//统计字段colomn2的汇总值
            	put("colomn2",EnumStatisticsType.SUM);
            }
		};
		//group字段
		List<String> groupBy = Arrays.asList("deviceType","modelType");
		//是否统计count  在group查询模式下支持
		boolean countNum =  true;
		// group数据检索要素块
		PersistBlock pb = new QueryPersistBlock("tableName", countColumns, groupBy,countNum,DataBaseType.POSTGRESQL);
		// 检索条件
		List<Filter> filtes = new ArrayList<Filter>();
		//范围查询条件
		filtes.add(new Filter("dateTime", EnumCompareType.MORE.getName(), "20170101000000"));
		filtes.add(new Filter("dateTime", EnumCompareType.LESS.getName(), "20170202235959"));
		pb.setFilters(filtes);
		pb.build(queryPersistBodyConstruct).getExcuteSql();
		String groupQuerySql = pb.build(queryPersistBodyConstruct).getExcuteSql();
		System.out.println("==========================groupQuerySql : " + groupQuerySql);
	}
	
	@Test
	public void groupInsert() {
		//一个持久化插入构造器 ：支持控制插入字段的最大字符长度，此处为100
		PersistBodyConstruct insertPersistBodyConstruct = new InsertPersistBodyConstruct(100);
		//支持设置字段插入默认值
		Map<String,String> defaultValue = new HashMap<String, String>(){
            private static final long serialVersionUID = 1L;
            {
            	//colomn1字段插入的默认值
            	put("colomn1","1");
            	//colomn2字段的插入默认值
            	put("colomn2","2");
            }
		};
		//插入字段名称
		List<String> columns = Arrays.asList("colomn1","colomn2");
		//插入值，支持批插入
		List<Map<String,String>> insertValues = Arrays.asList(new HashMap<String, String>(){
            private static final long serialVersionUID = 1L;
            {
            	//colomn1字段插入值
            	put("colomn1","11");
            	//colomn2字段插入值
            	put("colomn2","22");
            }
		},
		new HashMap<String, String>(){
            private static final long serialVersionUID = 1L;
            {
            	//colomn1字段插入值
            	put("colomn1","33");
            	//colomn2字段插入值
            	put("colomn2","44");
            }
		});
		//数据插入要素块
		PersistBlock pbInsert = new InsertPersistBlock("tableName", defaultValue, columns, insertValues,
		        DataBaseType.POSTGRESQL);
		
		String insertSql = pbInsert.build(insertPersistBodyConstruct).getExcuteSql();
		System.out.println("==========================insertSql : " + insertSql);
		
	}
	
	@Test
	public void dynForm(){

		Device d1 = new Device();
		d1.setCode("1231");
		d1.setName("device1");
		d1.setProType(1);
		Device d2 = new Device();
		d2.setCode("1241");
		d2.setName("device2");
		d2.setProType(1);
		Procol p1= new Procol();
		p1.setCode(1);
		p1.setName("Procol1");
		Procol p2= new Procol();
		p2.setCode(2);
		p2.setName("Procol2");
		/**
		 * 页面动态表单，支持在页面选择下拉后动态展示元素
		 */
		//创建一个动态页面单元格 包含element在页面的key，展示label，类型type，与动态展示元素，该元素一定是个select类型的元素
		Cell cell = new LinkageCell("设备选择下拉-key", "设备选择下拉-label", "", "默认值",new AbstrctLinkHandler<Device>(Arrays.asList(d1,d2)) {
			@Override
			public Option setOptionsLinkCell(Device select) {
				//组装select下的每个option
				LinkageOption selectOption = new LinkageOption(select.getCode(), select.getName());
				//option对应的元素集合cells
				List<Cell> cells = new ArrayList<>();
				if("1231".equals(select.getCode())){
					cells.add(new Cell(String.valueOf(p1.getCode()),p1.getName(),"type","vtype"));	
				}else{
					cells.add(new Cell(String.valueOf(p2.getCode()),p2.getName(),"type","vtype"));
					//假如还有二级联动：
					cells.add(new LinkageCell("key", "label", "","value",new AbstrctLinkHandler<String>(
						        Arrays.asList("sonObject")) {
							@Override
							public Option setOptionsLinkCell(String select2) {
								LinkageOption selectOption2 = new LinkageOption(select2,select2);
								selectOption2.setCell(Arrays.asList(new Cell("key","label","type","vtype")));
								return selectOption2;
							}
						}));
				}
				selectOption.setCell(cells);
				return selectOption;
			}
		});
		
		System.out.println("==========================LinkageCell : " + JSON.toJSONString(cell));
	}
	
	
}
