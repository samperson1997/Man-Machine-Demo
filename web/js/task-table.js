function executeTask() {
    var taskGroup = getTableContent("task-table");

    console.log(taskGroup)
    var longestTime = 0;
    for (var i = 0; i < taskGroup.length; i++) {
        longestTime = longestTime > parseInt(taskGroup[i][3]) ? longestTime : parseInt(taskGroup[i][3]);
    }
    localStorage.setItem("longestTime", longestTime);

    $.ajax({
        method: "POST",
        url: "/api/start",
        contentType: "application/json;charset=utf8",
        data: JSON.stringify({
            taskGroup: taskGroup
        }),
        dataType: "json",
        success: function (data) {
            window.open("./result.html");
        }
    });
}

/**
 * 遍历表格内容返回数组
 *
 * @param tableId
 * @returns {Array}
 */
function getTableContent(tableId) {
    var mytable = document.getElementById(tableId);
    var data = [];
    var rows = mytable.rows.length;
    for (var i = 1; i < rows; i++) {
        for (var j = 1, cells = mytable.rows[i].cells.length; j < cells; j++) {
            if (!data[i - 1]) {
                data[i - 1] = [];
            }
            data[i - 1][j - 1] = mytable.rows[i].cells[j].innerHTML;
        }
    }
    return data;
}