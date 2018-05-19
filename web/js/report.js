function getReport() {
    $.ajax({
        type: "GET",
        url: "/result",
        contentType: "application/x-www-form-urlencoded",
        data: {},
        dataType: "json",
        success: function (data) {
            //  ArrayList<ResultVO>
        }
    })
}

function excelGenerate() {
    $.ajax({
        type: "GET",
        url: "/report",
        contentType: "application/x-www-form-urlencoded",
        data: {},
        dataType: "json",
        success: function (data) {
            alert("[用于测试] 导出报告结果为" + data.message)
        }
    })
}