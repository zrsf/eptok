package com.yun.yweb.common;
/**
 * 业务常量
 * @author darell
 *
 */
public class ConfigKey {
	/**
	 * 服务器系统版本号
	 */
	public static final String HTTP_CLIENT_VERSION="1.01";
	/**
	 * xml头部
	 */
	public static final String HTTP_CLIENT_XML_HEAD="<?xml version=\"1.0\"  encoding=\"utf-8\" ?>";

	/**
	 * HTTP客户端报文客户端名称标签(输入)
	 */
	public static final String HTTP_CLIENT_XML_CLIENT_NODE="CLIENT";
	
	/**
	 * HTTP客户端报文版本标签(输入)
	 */
	public static final String HTTP_CLIENT_XML_VERSION_NODE="VERSION";
	/**
     * HTTP客户端报文请求名称标签
     */
    public static final String HTTP_CLIENT_XML_ACTION_NODE="ACTION";
	/**
	 * HTTP客户端报文头标签(输入)
	 */
	public static final String HTTP_CLIENT_XML_ENTER_NODE="REQUEST";
	/**
	 * HTTP客户端报文头标签（输出）
	 */
	public static final String HTTP_CLIENT_XML_OUTPUT_NODE="RESPONSE";
	/**
	 * HTTP客户端报文体标签
	 */
	public static final String HTTP_CLIENT_XML_CONT_NODE="DATA";
	/**
	 * HTTP客户端报文状态键标签（输出）
	 */
	public static final String HTTP_CLIENT_XML_TYPE_NODE="RESULT";
	/**
	 * HTTP客户端报文文本键标签（输出）
	 */
	public static final String HTTP_CLIENT_XML_MSG_NODE="MSG";
	/**
	 * 动态查询的查询字段
	 */
	public static final String QUERY_COLUMN = "queryColumn";
	/**
	 * 动态更新的更新字段
	 */
	public static final String UPDATE_COLUMN = "updateColumn";
	/**
	 * 动态更新的更新值
	 */
	public static final String UPDATE_VALUE = "updateValue";
	
	
}
