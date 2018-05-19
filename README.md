# Man-Machine-Web-Demo
This is a main.java.demo project for man vs machine contest of Developer Testing.<br>
Backend codes (another student's graduation project) are written by himself.

### 系统前后端连接接口
1.配置任务进任务组后点“执行”后调的执行的方法（传递任务组数组）:
（default package)里MyThread.java里startThread

2.提供读取数据库得到分数（包括BC，MC，total总分）的方法，每隔10秒调一次这个方法，刷新实时得分数据（这个取list里最后一个）及历史总分折线图:
mysql里DatabaseUtil.java里getScoreFromDatabase


3.点“生成报告”调提供一个获得数据库中报告展示所需数据方法（展示在下面报告上）:
mysql里DatabaseUtil.java里getResultEntityFromDatabase(注：ResultEntity对象在htmlparser.entity里）


4.按“导出报告”调用的生成excel文件的方法:
excel里ExcelGenerate.java里excelGenerate