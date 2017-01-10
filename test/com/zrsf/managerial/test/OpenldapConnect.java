package com.zrsf.managerial.test;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class OpenldapConnect {
	//public static DirContext ctx =  null ;
	private static LdapContext ctx = null;
	public static String root =  "dc=zrsf,dc=com";  //root   
	private final static Control[] connCtls = null;
	
	public static void  main(String[] args) {
		//OpenldapConnect LDAPTest1 =  new  OpenldapConnect();
		String account = "root";//操作LDAP的帐户。默认就是root
	   
	    Hashtable<String, Object> env =  new  Hashtable<String, Object> ();   
	    env.put(Context.INITIAL_CONTEXT_FACTORY,  "com.sun.jndi.ldap.LdapCtxFactory" );   
	    env.put(Context.PROVIDER_URL,  "ldap://192.168.75.130/");       
	    env.put(Context.SECURITY_AUTHENTICATION,  "simple" );   
	    env.put(Context.SECURITY_PRINCIPAL,  "cn="+ account + "," + root);   
	    env.put(Context.SECURITY_CREDENTIALS,  "111111" );   
	    try  {   
	      //ctx =  new  InitialDirContext(env); 
	      ctx = new InitialLdapContext(env, connCtls);
	      System.out.println( "认证成功" );
	      //add();
	      //query();
	      //edit();
	      //delete();
	      if(authenricate("ldapuser", "123456") == true){
	            System.out.println( "该用户认证成功" ); 
	      }
	    }   
	     catch  (javax.naming.AuthenticationException e) {   
	      e.printStackTrace();   
	      System.out.println( "认证失败" );   
	    }   
	     catch  (Exception e) {   
	      System.out.println( "认证出错：" );   
	      e.printStackTrace();   
	    }   
	    
	     if  (ctx !=  null ) {   
	       try  {   
	        ctx.close();   
	      }   
	       catch  (NamingException e) {   
	         //ignore   
	      }   
	    }   
	  }
	
    public static boolean authenricate(String UID, String password) {
        boolean valide = false;
        String userDN = getUserDN(UID);
  
        try {
            ctx.addToEnvironment(Context.SECURITY_PRINCIPAL, userDN);
            ctx.addToEnvironment(Context.SECURITY_CREDENTIALS, password);
            ctx.reconnect(connCtls);
            System.out.println(userDN + " 验证通过");
            valide = true;
        } catch (AuthenticationException e) {
            System.out.println(userDN + " 验证失败");
            System.out.println(e.toString());
            valide = false;
        } catch (NamingException e) {
            System.out.println(userDN + " 验证失败");
            valide = false;
        }
  
        return valide;
    }
    
    private static String getUserDN(String uid) {
        String userDN = "";
        try {
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            NamingEnumeration<SearchResult> en = ctx.search(root, "uid=" + uid, constraints);
            if (en == null || !en.hasMoreElements()) {
                System.out.println("未找到该用户");
            }
            // maybe more than one element
            while (en != null && en.hasMoreElements()) {
                Object obj = en.nextElement();
                if (obj instanceof SearchResult) {
                    SearchResult si = (SearchResult) obj;
                    userDN += si.getName();
                    userDN += "," + root;
                } else {
                    System.out.println(obj);
                }
            }
        } catch (Exception e) {
            System.out.println("查找用户时产生异常。");
            e.printStackTrace();
        }
  
        return userDN;
    }
	
	public static void add() {
        try {
            BasicAttributes attrs = new BasicAttributes();
            BasicAttribute objclassSet = new BasicAttribute("objectClass");
            objclassSet.add("person");
            attrs.put(objclassSet);
            attrs.put("cn", "HY");
            attrs.put("sn", "HuangYun");
            ctx.createSubcontext("cn=HY,ou=People," + root, attrs);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception in add():" + e);
        }
    }
	
	public static void query() throws Exception{
		SearchControls constraints = new SearchControls();
		constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
		//constraints.setSearchScope(SearchControls.ONELEVEL_SCOPE);
		//NamingEnumeration   en   =   ctx.search( " ",   "uid=* ",   constraints);   //查询所有用户
		NamingEnumeration<SearchResult> en = ctx.search(root, "ou=People", constraints);
		while (en != null && en.hasMoreElements()) {
	        Object obj = en.nextElement();
	        if(obj instanceof SearchResult) {
	            SearchResult si = (SearchResult) obj;
	            System.out.println( "name:   " + si.getName());
	            Attributes attrs = si.getAttributes();
	            if(attrs == null) {
	            	System.out.println( "No   attributes ");
	            }else {
	                for(NamingEnumeration<? extends Attribute> ae = attrs.getAll(); ae.hasMoreElements();) {
	                    Attribute attr = (Attribute)ae.next();
	                    String attrId = attr.getID();
	                    for(Enumeration<?> vals = attr.getAll();vals.hasMoreElements();) {
	                        System.out.print(attrId + ":   ");
	                        Object o = vals.nextElement();
	                        if(o instanceof byte[]) {
	                        	System.out.println(new   String((byte[])o));
	                        }else {
	                        	System.out.println(o);
	                        }
	                    }
	                }
	            }
	        }else {
	        	System.out.println(obj);
	        }
	        System.out.println();
		}
	}
	
	public static void edit(){
		
		String newDisplayName = "xiaohui";

		ModificationItem modificationItem[] = new ModificationItem[1];
		modificationItem[0] =
			new ModificationItem(
			DirContext.REPLACE_ATTRIBUTE,
			new   BasicAttribute("sn", newDisplayName));
		try {
			ctx.modifyAttributes("cn=HY,ou=People,dc=zrsf,dc=com", modificationItem);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static void delete() {
		try {
			ctx.destroySubcontext("cn=HY,ou=People,dc=zrsf,dc=com");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
