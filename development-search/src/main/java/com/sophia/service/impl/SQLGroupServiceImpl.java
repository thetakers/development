package com.sophia.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophia.domain.SQLGroup;
import com.sophia.repository.SQLGroupRepository;
import com.sophia.repository.impl.JpaRepositoryImpl;
import com.sophia.service.NPJdbcTemplateService;
import com.sophia.service.SQLGroupService;
import com.sophia.utils.SQLFilter;
import com.sophia.vo.GridResponse;
import com.sophia.vo.QueryRequest;

@Service
public class SQLGroupServiceImpl extends JpaRepositoryImpl<SQLGroupRepository> implements SQLGroupService  {

	Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired NPJdbcTemplateService npJdbcTemplateService;

	private String sql="select t.*,c.name as pText from TB_SM_SQLGROUP t left join TB_SM_SQLGROUP c on t.parentid =  c.id ";

	public String save(SQLGroup sqlGroup){

		//生成GROUP PATH
		return getRepository().save(sqlGroup).getId();
	}

	@Override
	public Map<String,Object> findById(String id) {
		SQLFilter sqlFilter = SQLFilter.getInstance();
		sqlFilter.setMainSql(sql);
		sqlFilter.EQ("id", id);
		return npJdbcTemplateService.getNamedParameterJdbcTemplate().queryForMap(sqlFilter.getSql(), sqlFilter.getParams());
	}

	public GridResponse<Map<String,Object>> list(QueryRequest queryRequest){
		SQLFilter sqlFilter = SQLFilter.getInstance();
		sqlFilter.addCondition(queryRequest.getCondition());
		sqlFilter.setMainSql(sql);
		if(queryRequest.getTreeNode()!=null){
			sqlFilter.EQ("parentid", queryRequest.getTreeNode().getString("id"));
		}
		return npJdbcTemplateService.grid(sqlFilter,queryRequest.getPageSize(),queryRequest.getPageNo());
	}
}
