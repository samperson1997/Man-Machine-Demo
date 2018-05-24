count = 0;

function addRow() {
    if ($("#setting-id").val() != "" && $("#setting-subject").val() != "" &&
        $("#setting-tool").val() != "" && $("#setting-time").val() != "") {

        if ($("#setting-time").val() <= 7200) {
            count++;
            $("#setting-table").append("<tr id=\"setting-table-tr-" + count + "\">\n" +
                "                    <td id=\"setting-id-" + count + "\">" + $("#setting-id").val() + "</td>\n" +
                "                    <td id=\"setting-subject-" + count + "\">" + $("#setting-subject").val() + "</td>\n" +
                "                    <td id=\"setting-tool-" + count + "\">" + $("#setting-tool").val() + "</td>\n" +
                "                    <td id=\"setting-time-" + count + "\">" + $("#setting-time").val() + "</td>\n" +
                "                    <td><button class=\"button-red\" id=\"delete-button" + count + "\" onclick=\"deleteRow()\">删除任务</button></td>\n" +
                "                    <td id=\"setting-choose-button-" + count + "\"><button class=\"button-blue\" id=\"choose-button" + count + "\" onclick=\"choose()\">选入任务组</button></td>\n" +
                "                </tr>");
            $("#setting-id").val("");
            $("#setting-subject").val("");
            $("#setting-tool").val("");
            $("#setting-time").val("");
            $("#setting-tip").text("");
        } else {
            $("#setting-tip").text("time_budget必须不能超过7200秒");
        }
    } else {
        $("#setting-tip").text("请将信息填写完整");
    }
}

function deleteRow() {
    var current = event.target.id.substr(13);
    $("#setting-table-tr-" + current).remove();
}

function choose() {
    var current = event.target.id.substr(13);

    $("#task-table").append("<tr>\n" +
        "                    <td>" + $("#setting-id-" + current).text() + "</td>\n" +
        "                    <td>" + $("#setting-subject-" + current).text() + "</td>\n" +
        "                    <td>" + $("#setting-tool-" + current).text() + "</td>\n" +
        "                    <td>" + $("#setting-time-" + current).text() + "</td>\n" +
        "                </tr>");
    var detachButton = $("#choose-button" + current).remove();
}

function emptyTask() {
    $("#task-table").empty();
    $("#task-table").append("<tr>\n" +
        "                    <th>ID</th>\n" +
        "                    <th>subject</th>\n" +
        "                    <th>tool</th>\n" +
        "                    <th>time_budget</th>\n" +
        "                </tr>");

    for (var i = 1; i <= count; i++) {
        console.log($("#setting-choose-button-" + i).children().length);
        if ($("#setting-choose-button-" + i).children().length === 0) {
            $("#setting-choose-button-" + i).append("<button class=\"button-blue\" id=\"choose-button" + count + "\" onclick=\"choose()\">选入任务组</button>")
        }
    }
}