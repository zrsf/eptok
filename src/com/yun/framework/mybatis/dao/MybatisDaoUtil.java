package com.yun.framework.mybatis.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import com.yun.framework.mybatis.util.Page;
import com.yun.framework.mybatis.util.SpringContextUtil;

import hikefa.core.web.page.Pager;
import hikefa.core.web.page.Paginater;

/**
 * dao层封装
 * @author hy
 *
 * @param <T>
 */
public class MybatisDaoUtil<T> {

	private static final Log logger = LogFactory.getLog(MybatisDaoUtil.class);
	private SqlSession sqlSession;
	
	protected Class<T> entityClass;
	
	public MybatisDaoUtil(Class<T> entityClass){
		this.entityClass = entityClass;
	}
	
	public Class<T> getEntityClass() {
		return entityClass;
	}
	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	public SqlSession getSqlSession() {
		if(sqlSession == null){
			sqlSession = (SqlSession)SpringContextUtil.getBean("sqlSession");
		}
		return sqlSession;
	}
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	/**
	 * @param statement (Unique identifier matching the statement to execute)
	 * @param parameter (A parameter object to pass to the statement)
	 * @return (int The number of rows affected by the insert.)
	 */
	public int addEntity(String statement, Object parameter){
		logger.info(statement+"------" + parameter);
		
		return this.getSqlSession().insert(statement, parameter);
	}
	
	/**
	 * @param statement (Unique identifier matching the statement to execute)
	 * @param parameter (A parameter object to pass to the statement)
	 * @return (int The number of rows affected by the insert.)
	 */
	public int updateEntity(String statement, Object parameter) {
		logger.info(statement+"------" + parameter);
		
		return this.getSqlSession().update(statement, parameter);
	}
	
	/**
	 * @param statement (Unique identifier matching the statement to execute)
	 * @param entity (A parameter object to pass to the statement)
	 * @return (int The number of rows affected by the insert.)
	 */
	public int deleteEntity(String statement, Object parameter) {
		logger.info(statement+"------" + parameter);
		
		return this.getSqlSession().delete(statement, parameter);
	}
	
	/**
	 * @param statement (Unique identifier matching the statement to execute)
	 * @param parameter (A parameter object to pass to the statement)
	 * @return (int The number of rows affected by the insert.)
	 */
	public int deleteEntityById(String statement, Object parameter) {
		logger.info(statement+"------" + parameter);
		
		return this.getSqlSession().delete(statement, (Serializable)parameter);
	}
	
	
	/**
	 * @param statement (statement Unique identifier matching the statement to use.)
	 * @param parameter A parameter object to pass to the statement.
	 * @return	(Mapped object) <T> the returned object type
	 */
	@SuppressWarnings("unchecked")
	public T getEntity(String statement, Object parameter) {
		logger.info(statement+"------" + parameter);
		
		return (T)this.getSqlSession().selectOne(statement, parameter);
	}
	
	/**
	 * @param statement (statement Unique identifier matching the statement to use.)
	 * @param entity parameter A parameter object to pass to the statement.
	 * @return	(Mapped object) <T> the returned object type
	 */
	@SuppressWarnings("unchecked")
	public T getEntityById(String statement, Object parameter) {
		logger.info(statement+"------" + parameter);
		
		return (T)this.getSqlSession().selectOne(statement, (Serializable)parameter);
	}
	
	/**
	 * @param statement Unique identifier matching the statement to use.
	 * @param parameter A parameter object to pass to the statement
	 * @return List of mapped object
	 */
	public List<T> getEntitys(String statement, Object parameter){
		logger.info(statement+"------" + parameter);
		return this.getSqlSession().selectList(statement, parameter);
	}
	
	/**
	 * 
	 * @param statement statement Unique identifier matching the statement to use.
	 * @param parameter	parameter只接受Map类型参数 并且里边必须包含 page, pagesize两个键值对（值必须是整数）
	 * @return Page
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Paginater getEntitysPage(String statement, Object parameter, Pager pager){
		logger.info(statement + "------" + parameter);
		
		Page<T> page = new Page<T>();
		 
		if(pager != null){
			Map parMap = new HashMap<>();
			try {
				page.setPageNo(Integer.parseInt(pager.getPageNumber() + ""));	//当前页
				page.setPageSize(pager.getPageSize());	
				
				parMap.put("page", Integer.parseInt(pager.getPageNumber() + ""));
				parMap.put("pagesize", pager.getPageSize());
				parMap.put("pageObject", page);
		        List<T> list = this.getSqlSession().selectList(statement, parMap);
		        
		        Paginater paginater = new Paginater(page.getTotalRecord(), pager.getPageNumber(), pager.getPageSize());
		        paginater.setData(list);
		        return paginater;
		        //page.setResults(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(parMap.containsKey("pageObject")){	//分页执行完去掉分页参数
				parMap.remove("pageObject");
			}
		}
		return new Paginater(0, 1, pager.getPageSize());
	}
	
	/* ***************************************
	 * *****************批量操作****************
	 * ***************************************
	 */
	/**
	 * 返回空字符串
	 * 
	 * @param str
	 * @return
	 * 
	 */
	private static String isNull(Object str) {
		if (isEmpty(String.valueOf(str))) {
			return "";
		} else {
			return String.valueOf(str);
		}
	}
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return 空 true 非空 false
	 */
	private static boolean isEmpty(String str) {
		if (str != null && !"".equals(str) && !"null".equals(str)) {
			return false;
		} else {
			return true;
		}
	}
}
