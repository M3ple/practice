package cn.cjx.excel.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @功能描述:
 * @使用对象:xx系统
 * @创建日期: 2020/7/18 10:33
 * @创建人:陈俊旋
 */
@Data
public class User {
    @Excel(name = "姓名", orderNum = "1")
    private String name;
    @Excel(name = "年龄", orderNum = "3")
    private String age;
    @Excel(name = "性别", orderNum = "2")
    private String sex;
}
