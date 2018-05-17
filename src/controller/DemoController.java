package controller;

import excel.ExcelGenerate;
import htmlparser.entity.ResultEntity;
import mysql.DatabaseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import po.ScorePO;
import vo.ResultMessageVO;
import vo.ResultVO;
import vo.ScoreVO;

import java.util.ArrayList;

@Controller
public class DemoController {

    private DatabaseUtil databaseUtil = new DatabaseUtil();
    private ExcelGenerate excelGenerate = new ExcelGenerate();

    /**
     * 配置任务进任务组后执行
     *
     * @param taskGroup
     */
    @RequestMapping(
            value = "/start",
            method = RequestMethod.GET
    )
    @ResponseBody
    public static ResultMessageVO startThread(String[][] taskGroup) {
        // TODO
        return new ResultMessageVO(true);
    }

    /**
     * 读取数据库得到分数（包括BC，MC，total总分）
     * 每隔10秒调一次这个方法，刷新实时得分数据（这个取list里最后一个）及历史总分折线图
     *
     * @return
     */
    @RequestMapping(
            value = "/score",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ArrayList<ScoreVO> getScoreFromDatabase() {
        ArrayList<ScorePO> poList = databaseUtil.getScoreFromDatabase();
        ArrayList<ScoreVO> voList = new ArrayList<>();
        for (ScorePO po : poList) {
            voList.add(new ScoreVO(po.getBC(), po.getMC(), po.getTotal()));
        }

        return voList;
    }

    /**
     * 生成报告, 获得数据库中报告展示所需数据方法
     *
     * @return
     */
    @RequestMapping(
            value = "/result",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ArrayList<ResultVO> getResultEntityFromDatabase() {
        ArrayList<ResultEntity> resultEntityList = databaseUtil.getResultEntityFromDatabase();

        ArrayList<ResultVO> voList = new ArrayList<>();
        for (ResultEntity resultEntity : resultEntityList) {
            voList.add(new ResultVO(resultEntity.getId(), resultEntity.getGroup_id(), resultEntity.getSubject(),
                    resultEntity.getTool(), resultEntity.getTime_budget(), resultEntity.getBC(),
                    resultEntity.getMC(), resultEntity.getTotal(), resultEntity.getTime_start(), resultEntity.getTime_end()));
        }

        return voList;
    }

    /**
     * 导出报告, 调用的生成excel文件的方法
     */
    @RequestMapping(
            value = "/generate",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResultMessageVO excelGenerate() {

        excelGenerate.excelGenerate();
        return new ResultMessageVO(true);
    }
}
