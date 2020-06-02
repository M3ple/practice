package cn.cjx.think_in_java.chapter20_annotation;

import java.lang.reflect.Field;

/**
 * @功能描述:
 * @使用对象:xx系统
 * @接口版本:
 * @创建日期: 2019/5/27 13:44
 * @创建人:陈俊旋
 */
public class DBTable {
    @SQLString(name = "maple")
    private String name;
    @SQLString(name = "maple2")
    private String name2;
    @SQLString(name = "maple3")
    private String name3;

    public static void main(String[] args) {
        System.out.println(DBTable.class.getCanonicalName());
        try {
            Class<?> clazz = Class.forName("cn.cjx.think_in_java.chapter20_annotation.DBTable");
            for (Field field : clazz.getDeclaredFields()) {
                SQLString sqlString = field.getAnnotation(SQLString.class);
                System.out.println(sqlString.name());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
