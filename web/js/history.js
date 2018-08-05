function loadHistoryTask() {

    $.ajax({
        method: "GET",
        url: "/api/history",
        contentType: "application/json;charset=utf8",
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                $("#history-task-group-table").append("<tr><td>历史任务组" + (i + 1) +
                    "</td><td><button class=\"button-blue\" onclick=\"\">查&nbsp;&nbsp;看</button>" +
                    "<button class=\"button-red\" onclick=\"\">删&nbsp;&nbsp;除</button></td></tr>")
            }
        }
    });
}