package org.jeecg.common.relationsp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.common.relation.entity.Relation;
import org.jeecg.common.relation.mapper.RelationMapper;
import org.jeecg.common.relationsp.entity.Relationsp;
import org.jeecg.common.relationsp.mapper.RelationspMapper;
import org.jeecg.common.relationsp.service.IRelationspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: 特殊关系的处理
 * @Author: jeecg-boot
 * @Date:   2019-11-03
 * @Version: V1.0
 */
@Service
public class RelationspServiceImpl extends ServiceImpl<RelationspMapper, Relationsp> implements IRelationspService {
    @Autowired
    private RelationspMapper relationspMapper;
     /*   public static void main(String[] args) {
                getAllsonID("sjk");//不需要这个测试，直接采用SpringBoot
               的测试方法就可以
        }*/
     final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://localhost:3306/jeecg-boot";
    // 数据库的用户名与密码，需要根据自己的设置
    final String USER = "root";
    final String PASS = "abc=123";

    public Set<String> getDirectsonID(String managerid) {

       // QueryWrapper qw=new QueryWrapper();
        //List<Relationsp> relationLists=relationspMapper.selectList(qw);//已经取得这个数据库的集合
        List<Relationsp> relationLists=selectList();//已经取得这个数据库的集合
        Set<String> resultSet=new HashSet<>();
        //特殊的，不采用递归，就是采用一层来进行获取管控的目标
        for(Relationsp rp :relationLists)
        {
            if(rp.getManagerid().equals(managerid))
            {
                resultSet.add(rp.getNormalid());
            }
        }


        return  resultSet;

    }
    private  List<Relationsp> selectList()
    {
        Connection conn = null;
        Statement stmt = null;
        List<Relationsp> relationList=new ArrayList<>();
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
            sql="SELECT create_by,create_time,update_by,update_time,sys_org_code,managerid,normalid,id FROM relationspecial";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                               /* int id  = rs.getInt("id");
                                String name = rs.getString("name");
                                String url = rs.getString("url");*/

                Relationsp r=new Relationsp();
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
}
