/**    
 * @Title: ProcedureService.java   
 * @Description: 用jFinal Db调用存储过程
 * @author wangShaoFeng     
 * @date 上午9:15:34   
 */
package com.net.toooen.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.ICallback;

/**
 * @author wangShaoFeng
 * @Description:
 * @date 2016年2月2日
 */
public class ProcedureService implements ICallback {

    // 存储过程的输入参数
    public String in_lotteryName = null;
    // 存储过程的输出参数
    public int out_lotteryNum = 0;

    @Override
    public void run(Connection conn) throws SQLException {

        // 存储过程调用对象
        CallableStatement callableProc = null;
        try {
            callableProc = conn.prepareCall("{ call proc_lottery(?,?)}");
            // 设置输入参数
            callableProc.setString(1, in_lotteryName);
            // 设置输出参数类型
            callableProc.registerOutParameter(2, Types.INTEGER);
            // 执行存储过程
            callableProc.execute();
            // 得到返回值
            out_lotteryNum = callableProc.getInt(2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbKit.close(callableProc, conn);
        }

    }
    
    public static void main(String[] args) {
        ProcedureService procedureService = new ProcedureService();
        procedureService.in_lotteryName = "四等奖";
        Db.execute(procedureService);
        System.out.println("抽奖个数为："+procedureService.out_lotteryNum);
    }

}
