package org.jeecg.common.relation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.jeecg.common.relation.entity.Relation;
import org.jeecg.common.relation.mapper.RelationMapper;
import org.jeecg.common.relation.service.IRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.sql.*;
import java.util.*;

/**
 * @Description: 管理关系
 * @Author: jeecg-boot
 * @Date:   2019-11-02
 * @Version: V1.0
 */
@Service
public class RelationServiceImpl extends ServiceImpl<RelationMapper, Relation> implements IRelationService {
              @Autowired
              private RelationMapper relationMapper;
     /*   public static void main(String[] args) {
                getAllsonID("sjk");//不需要这个测试，直接采用SpringBoot
               的测试方法就可以
        }*/
     // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
         final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
         final String DB_URL = "jdbc:mysql://localhost:3306/jeecg-boot";
        // 数据库的用户名与密码，需要根据自己的设置
         final String USER = "root";
         final String PASS = "abc=123";
        public Set<String>  getAllsonID(String managerid) {


                /*QueryWrapper qw=new QueryWrapper();
                List<Relation> relationLists=relationMapper.selectList(qw);//已经取得这个数据库的集合*/
                List<Relation> relationLists=selectList();//已经取得这个数据库的集合
                Set<String> resultSet=new HashSet<>();
                getLeaf(managerid,relationLists,resultSet);

                return  resultSet;
                //然后形成一个递归的类
                //然后根据这个类获取所有的ID
        }

        private  List<Relation> selectList()
        {
                Connection conn = null;
                Statement stmt = null;
                List<Relation> relationList=new ArrayList<>();
                try{
                        // 注册 JDBC 驱动
                        Class.forName(JDBC_DRIVER);

                        // 打开链接
                        System.out.println("连接数据库...");
                        conn = DriverManager.getConnection(DB_URL,USER,PASS);

                        // 执行查询
                        System.out.println(" 实例化Statement对象...");
                        stmt = conn.createStatement();
                        String sql;
                        //sql = "SELECT id, name, url FROM websites";
                        sql="SELECT create_by,create_time,update_by,update_time,sys_org_code,managerid,normalid,id FROM relation";
                        ResultSet rs = stmt.executeQuery(sql);

                        // 展开结果集数据库
                        while(rs.next()){
                                // 通过字段检索
                               /* int id  = rs.getInt("id");
                                String name = rs.getString("name");
                                String url = rs.getString("url");*/

                                Relation r=new Relation();
                                r.setId(rs.getString("id").trim());
                                r.setCreateBy(rs.getString("create_by").trim());
                                r.setManagerid(rs.getString("managerid").trim());
                                r.setNormalid(rs.getString("normalid").trim());

                                relationList.add(r);

                               /* // 输出数据
                                System.out.print("ID: " + id);
                                System.out.print(", 站点名称: " + name);
                                System.out.print(", 站点 URL: " + url);
                                System.out.print("\n");*/
                        }
                        // 完成后关闭
                        rs.close();
                        stmt.close();
                        conn.close();
                }catch(SQLException se){
                        // 处理 JDBC 错误
                        se.printStackTrace();
                }catch(Exception e){
                        // 处理 Class.forName 错误
                        e.printStackTrace();
                }finally{
                        // 关闭资源
                        try{
                                if(stmt!=null) stmt.close();
                        }catch(SQLException se2){
                        }// 什么都不做
                        try{
                                if(conn!=null) conn.close();
                        }catch(SQLException se){
                                se.printStackTrace();
                        }
                }
                System.out.println("Goodbye!");
                return  relationList;
        }

        private   void getLeaf(String managerid,List<Relation> recordList,Set<String> resultSet)
        {
                List<Relation> templist=new ArrayList<>();
                for(Relation r:recordList)
                {
                        if(r.getManagerid().equals(managerid))
                        {
                                templist.add(r);
                                resultSet.add(managerid);//中间的节点也记录
                        }
                }
                if(templist.size()==0)
                {
                        resultSet.add(managerid);//叶节点记录
                        return;
                }
                for(Relation rx:templist)
                {
                        managerid=rx.getNormalid();
                        getLeaf(managerid,recordList,resultSet);
                }
        }
}
