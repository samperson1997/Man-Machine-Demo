package demo.controller;

import demo.excel.ExcelGenerate;
import demo.mysql.DatabaseUtil;
import demo.vo.ResultMessageVO;
import demo.vo.ResultVO;
import demo.vo.ScoreVO;
import demo.vo.TaskGroupVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
            method = RequestMethod.POST,
            consumes = {"application/json; charset=UTF-8"}
    )
    @ResponseBody
    public static ResultMessageVO startThread(@RequestBody TaskGroupVO taskGroup) {
        // TODO 转化taskGroup为二维数组
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
        ArrayList<ScoreVO> voList = new ArrayList<>();

//        ArrayList<ScorePO> poList = databaseUtil.getScoreFromDatabase();
//        for (ScorePO po : poList) {
//            voList.add(new ScoreVO(po.getBC(), po.getMC(), po.getTotal()));
//        }

        // 测试用
        voList.add(new ScoreVO(98, 95, 96));
        voList.add(new ScoreVO(96, 93, 91));

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
        ArrayList<ResultVO> voList = new ArrayList<>();
//        ArrayList<ResultEntity> resultEntityList = databaseUtil.getResultEntityFromDatabase();
//        for (ResultEntity resultEntity : resultEntityList) {
//            voList.add(new ResultVO(resultEntity.getId(), resultEntity.getGroup_id(), resultEntity.getSubject(),
//                    resultEntity.getTool(), resultEntity.getTime_budget(), resultEntity.getBC(),
//                    resultEntity.getMC(), resultEntity.getTotal(), resultEntity.getTime_start(), resultEntity.getTime_end()));
//        }

        // 测试用
        voList.add(new ResultVO(1, 1, "MoreTriangle", "Randoop",
                30, 85, 98, 90, "10", "20"));
        voList.add(new ResultVO(2, 1, "MoreTriangle", "Evosuite",
                30, 85, 98, 90, "10", "20"));

        return voList;
    }

    /**
     * 导出报告, 调用的生成excel文件的方法
     */
    @RequestMapping(
            value = "/report",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResultMessageVO excelGenerate() {

        excelGenerate.excelGenerate();
        return new ResultMessageVO(true);
    }
}
