package fkshang.com.kw;

/**
 * @author baj
 * @creat 2022-08-08 17:32
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TableBuild {
    public static void main(String[] args) throws Exception {
        TableBuild tableBuild = new TableBuild();
        tableBuild.ready();
    }

    void ready() throws Exception {//数据准备，先从数据库中取出建表的表名字段等信息，全部添加到datalist中
//        Class.forName("com.mysql.cj.jdbc.Driver");
        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/createtable?characterEncoding=UTF-8&serverTimezone=UTC","root", "root");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost/magic-boot-02?useSSL=false&zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF8&autoReconnect=true&serverTimezone=Asia/Shanghai",
                "root",
                "6666");
        Statement statement = con.createStatement();
        String sql1 = "select * from data";
        ResultSet rs = statement.executeQuery(sql1);
        List<Data> datalist = new ArrayList<Data>();
        while (rs.next()) {
            Data d = new Data();
            d.setTablename(rs.getString("tablename"));//表名
            d.setTablecname(rs.getString("tablecname"));//表注释
            d.setFiledname(rs.getString("filedname"));//字段名
            d.setFiledcname(rs.getString("filedcname"));//字段注释
            d.setIskey(rs.getString("iskey"));//是否为主键
            d.setIsnull(rs.getString("isnull"));//是否可为空
            d.setCode(rs.getString("code"));//用来匹配字段格式的
            datalist.add(d);
        }
        build(con, datalist);
    }

    void build(Connection con, List<Data> datalist) throws SQLException, IOException {//生成建表语句文本
        StringBuffer CT = new StringBuffer();//用来生成建表语句
        StringBuffer AddTip = new StringBuffer();//用来生成添加注释语句
        StringBuffer PK = new StringBuffer();//用来生成联合主键语句
        StringBuffer createtablesql = new StringBuffer();//最终组合成的完整建表语句
        List<String> PKlist = new ArrayList<String>();//用来暂时存放主键字段名的list
        int i;
        for (i = 0; i <= (datalist.size() - 1); i++) {
            if (datalist.get(i).getTablename().length() == 0) continue;//如果表名为空的数据项，则跳过
            if (datalist.get(i).getFiledname().length() == 0) {//一个新表开始，重新创建一个表，因为数据库存储的数据，每一个表结束会另起一行，数据中只包含表名，没有数据名，
                System.out.println(datalist.get(i).getTablename() + "表创建");//控制台打印建表提示
                CT.append("create table " + datalist.get(i).getTablename() + "(\r\n");//添加建表语句
                AddTip.append("comment on table " + datalist.get(i).getTablename() + " is '" + datalist.get(i).getTablecname() + "';\r\n");//添加表注释
                continue;
            } else {//除去创建一个新表，剩下的为字段的创建
                System.out.println("\t" + datalist.get(i).getFiledname() + "字段创建");//控制台打印字段提示
                CT.append("\t" + datalist.get(i).getFiledname());//字段名

                //以下为字段类型的取值，针对其他不同的数据规则以下代码一般不适用，由于本次任务字段类型被放到了另一张表中，所以需要使用data表中的code去匹配对应的type表中的type类型，以此来确定字段类型
                String code = datalist.get(i).getCode();//
                String sql2 = "select type from type where code = '" + code + "'";
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(sql2);
                String type = null;
                while (rs.next()) {
                    type = rs.getString("type");
                }
                if (type.equals("YYYY-MM-DD") || type.equals("YYYY-MM-DD HH:MM:SS")) {//日期类型
                    CT.append(" date");
                } else if (type.indexOf("n(") != -1) {//形如18n(2)这种type为number类型，写成number(18,2)
                    String t1 = "";
                    String t2 = "";
                    List<String> t = Arrays.asList(type.split(""));
                    for (int j = 0; j < t.size(); j++) {
                        if (t.get(j).charAt(0) >= 48 && t.get(j).charAt(0) <= 57) {
                            t1 += t.get(j);
                        } else break;
                    }
                    for (int j = 0; j < t.size(); j++) {
                        if (t.get(j).charAt(0) == 40) {
                            for (int k = j + 1; k < t.size(); k++) {
                                if (type.charAt(k) >= 48 && type.charAt(k) <= 57) {
                                    t2 += t.get(k);
                                }
                            }
                        }
                    }
                    CT.append(" number(" + t1 + "," + t2 + ")");
                } else if (type.indexOf("n") != -1) {//剩下为varchar2类型
                    String t = "";
                    for (int j = 0; j < type.length(); j++) {
                        if (type.charAt(j) >= 48 && type.charAt(j) <= 57) {
                            t += type.charAt(j);
                        }
                    }
                    CT.append(" varchar2(" + t + ")");
                } else {
                }
                //字段类型结束

                if (datalist.get(i).getIskey().equals("Y")) {//字段是否是联合主键
                    PKlist.add(datalist.get(i).getFiledname());//是则把字段名加入到联合主键集合中
                }

                if (datalist.get(i).getIsnull().equals("N")) {//字段是否可为空
                    CT.append(" not null");
                }
                CT.append(",");

                //把联合主键拼接到建表语句的末尾
                if (i == (datalist.size() - 1) || !datalist.get(i).getTablename().equals(datalist.get(i + 1).getTablename())) {//当下一条数据开始为新的表时
                    if (PKlist.size() > 0) {
                        //添加联合主键
                        PK.append("\tCONSTRAINT PK_" + datalist.get(i).getTablename() + " PRIMARY KEY (");
                        for (String str : PKlist) {//把存有主键的list用逗号分隔开转化成String类型
                            PK.append(str).append(",");
                        }
                        PK = PK.deleteCharAt(PK.length() - 1);//去掉拼接完成后最后一个逗号
                        PKlist.clear();//清空PKlist
                        PK.append(")");

                        CT.append("\r\n");
                        CT.append(PK);//把生成的主键语句拼接到建表语句中
                        PK.delete(0, PK.length());//拼接完后清空创建主键语句
                        CT.append("\r\n);");
                    }
                }
                CT.append("\r\n");
                //添加字段注释
                AddTip.append("comment on column " + datalist.get(i).getTablename() + '.' + datalist.get(i).getFiledname() + " is '" + datalist.get(i).getFiledcname() + "';\r\n");

                //在建表语句结束之后拼接上注释语句，一起放到汇总的sql语句当中
                if (i == (datalist.size() - 1) || !datalist.get(i).getTablename().equals(datalist.get(i + 1).getTablename())) {//当下一条数据开始为新的表时
                    createtablesql.append(CT);
                    createtablesql.append(AddTip);
                    CT.delete(0, CT.length());
                    AddTip.delete(0, AddTip.length());
                }
            }
        }

        //输出到文本文件
        File f = new File("1.txt");
        if (!f.exists()) {
            f.createNewFile();
        }
        BufferedWriter output = new BufferedWriter(new FileWriter(f));
        output.write(createtablesql.toString());
        output.close();
    }
}

//public class TableBuild {
//    public static void main(String[] args) throws Exception {
//        TableBuild tableBuild = new TableBuild();
//        tableBuild.ready();
//    }
//
//    void ready() throws Exception {//数据准备，先从数据库中取出建表的表名字段等信息，全部添加到datalist中
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/createtable?characterEncoding=UTF-8&serverTimezone=UTC","root", "root");
//        Statement statement = con.createStatement();
//        String sql1 = "select * from data";
//        ResultSet rs = statement.executeQuery(sql1);
//        List<Data> datalist = new ArrayList<Data>();
//        while(rs.next()) {
//            Data d = new Data();
//            d.setTablename(rs.getString("tablename"));//表名
//            d.setTablecname(rs.getString("tablecname"));//表注释
//            d.setFiledname(rs.getString("filedname"));//字段名
//            d.setFiledcname(rs.getString("filedcname"));//字段注释
//            d.setIskey(rs.getString("iskey"));//是否为主键
//            d.setIsnull(rs.getString("isnull"));//是否可为空
//            d.setCode(rs.getString("code"));//用来匹配字段格式的
//            datalist.add(d);
//        }
//        build(con,datalist);
//    }
//
//    void build(Connection con,List<Data> datalist) throws SQLException, IOException {//生成建表语句文本
//        StringBuffer CT = new StringBuffer();//用来生成建表语句
//        StringBuffer AddTip = new StringBuffer();//用来生成添加注释语句
//        StringBuffer PK = new StringBuffer();//用来生成联合主键语句
//        StringBuffer createtablesql = new StringBuffer();//最终组合成的完整建表语句
//        List<String> PKlist = new ArrayList<String>();//用来暂时存放主键字段名的list
//        int i;
//        for(i=0;i<=(datalist.size()-1);i++){
//            if(datalist.get(i).getTablename().length()==0) continue;//如果表名为空的数据项，则跳过
//            if(datalist.get(i).getFiledname().length()==0){//一个新表开始，重新创建一个表，因为数据库存储的数据，每一个表结束会另起一行，数据中只包含表名，没有数据名，
//                System.out.println(datalist.get(i).getTablename()+"表创建");//控制台打印建表提示
//                CT.append("create table " + datalist.get(i).getTablename() + "(\r\n");//添加建表语句
//                AddTip.append("comment on table "+datalist.get(i).getTablename()+" is '"+datalist.get(i).getTablecname()+"';\r\n");//添加表注释
//                continue;
//            }else{//除去创建一个新表，剩下的为字段的创建
//                System.out.println("\t"+datalist.get(i).getFiledname()+"字段创建");//控制台打印字段提示
//                CT.append("\t"+datalist.get(i).getFiledname());//字段名
//
//                //以下为字段类型的取值，针对其他不同的数据规则以下代码一般不适用，由于本次任务字段类型被放到了另一张表中，所以需要使用data表中的code去匹配对应的type表中的type类型，以此来确定字段类型
//                String code = datalist.get(i).getCode();//
//                String sql2 = "select type from type where code = '"+code+"'";
//                Statement statement = con.createStatement();
//                ResultSet rs = statement.executeQuery(sql2);
//                String type = null;
//                while (rs.next()){
//                    type = rs.getString("type");
//                }
//                if(type.equals("YYYY-MM-DD")||type.equals("YYYY-MM-DD HH:MM:SS")){//日期类型
//                    CT.append(" date");
//                }else if(type.indexOf("n(")!=-1){//形如18n(2)这种type为number类型，写成number(18,2)
//                    String t1 = "";
//                    String t2 = "";
//                    List<String> t = Arrays.asList(type.split(""));
//                    for(int j = 0;j<t.size();j++){
//                        if(t.get(j).charAt(0)>=48 && t.get(j).charAt(0)<=57){
//                            t1 += t.get(j);
//                        }
//                        else break;
//                    }
//                    for(int j=0;j<t.size();j++){
//                        if(t.get(j).charAt(0)==40){
//                            for(int k=j+1;k<t.size();k++){
//                                if(type.charAt(k) >= 48 && type.charAt(k) <= 57){
//                                    t2 += t.get(k);
//                                }
//                            }
//                        }
//                    }
//                    CT.append(" number("+t1+","+t2+")");
//                }else if(type.indexOf("n")!=-1) {//剩下为varchar2类型
//                    String t = "";
//                    for (int j = 0; j < type.length(); j++) {
//                        if (type.charAt(j) >= 48 && type.charAt(j) <= 57) {
//                            t += type.charAt(j);
//                        }
//                    }
//                    CT.append(" varchar2(" + t + ")");
//                }else{}
//                //字段类型结束
//
//                if(datalist.get(i).getIskey().equals("Y")){//字段是否是联合主键
//                    PKlist.add(datalist.get(i).getFiledname());//是则把字段名加入到联合主键集合中
//                }
//
//                if(datalist.get(i).getIsnull().equals("N")){//字段是否可为空
//                    CT.append(" not null");
//                }
//                CT.append(",");
//
//                //把联合主键拼接到建表语句的末尾
//                if(i==(datalist.size()-1)||!datalist.get(i).getTablename().equals(datalist.get(i+1).getTablename())){//当下一条数据开始为新的表时
//                    if(PKlist.size()>0){
//                        //添加联合主键
//                        PK.append("\tCONSTRAINT PK_" + datalist.get(i).getTablename() + " PRIMARY KEY (");
//                        for(String str : PKlist){//把存有主键的list用逗号分隔开转化成String类型
//                            PK.append(str).append(",");
//                        }
//                        PK = PK.deleteCharAt(PK.length()-1);//去掉拼接完成后最后一个逗号
//                        PKlist.clear();//清空PKlist
//                        PK.append(")");
//
//                        CT.append("\r\n");
//                        CT.append(PK);//把生成的主键语句拼接到建表语句中
//                        PK.delete(0,PK.length());//拼接完后清空创建主键语句
//                        CT.append("\r\n);");
//                    }
//                }
//                CT.append("\r\n");
//                //添加字段注释
//                AddTip.append("comment on column "+datalist.get(i).getTablename()+'.'+datalist.get(i).getFiledname()+" is '"+datalist.get(i).getFiledcname()+"';\r\n");
//
//                //在建表语句结束之后拼接上注释语句，一起放到汇总的sql语句当中
//                if(i==(datalist.size()-1)||!datalist.get(i).getTablename().equals(datalist.get(i+1).getTablename())){//当下一条数据开始为新的表时
//                    createtablesql.append(CT);
//                    createtablesql.append(AddTip);
//                    CT.delete(0,CT.length());
//                    AddTip.delete(0,AddTip.length());
//                }
//            }
//        }
//
//        //输出到文本文件
//        File f = new File("1.txt");
//        if(!f.exists()){
//            f.createNewFile();
//        }
//        BufferedWriter output = new BufferedWriter(new FileWriter(f));
//        output.write(createtablesql.toString());
//        output.close();
//    }
//}

