function getReport() {
    $.ajax({
        type: "GET",
        url: "/api/result",
        contentType: "application/x-www-form-urlencoded",
        data: {},
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                $("#bottom-table").append("<tr>\n" +
                    "                <th>" + data[i].id + "</th>\n" +
                    "                <th>" + data[i].subject + "</th>\n" +
                    "                <th>" + data[i].tool + "</th>\n" +
                    "                <th>" + data[i].time_budget + "</th>\n" +
                    "                <th>" + Math.round(data[i].bc) + "</th>\n" +
                    "                <th>" + Math.round(data[i].mc) + "</th>\n" +
                    "                <th>" + Math.round(data[i].total) + "</th>\n" +
                    "            </tr>"
                )
                ;
            }
        }
    })
}

function excelGenerate() {
    $.ajax({
        type: "GET",
        url: "/api/report",
        contentType: "application/x-www-form-urlencoded",
        data: {},
        dataType: "json",
        success: function (data) {
            // alert("[用于测试] 导出报告结果为" + data.message)
        }
    })
}