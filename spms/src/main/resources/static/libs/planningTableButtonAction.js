function savePlan() {
    var planningTableCellArray = $(".planning-table-input-cell").map(function () {
        var planId = $(this).data("planid");
        var companyId = $(this).data("companyid");
        var planPerCompanyId = $(this).data("planpercompanyid");
        var month = $(this).data("month");
        var value = $(this).val();

        var tableCellData = {}
        tableCellData["planId"] = planId;
        tableCellData["companyId"] = companyId;
        tableCellData["planPerCompanyId"] = planPerCompanyId;
        tableCellData["month"] = month;
        tableCellData["content"] = value;

        return tableCellData;
    }).get()

    $.ajax({
        type: "POST",
        url: "/rest/plan/save",
        data: JSON.stringify(planningTableCellArray),
        contentType: "application/json",
    }).done(function (data) {
        alert(data.message);
    });
}

function setPlanStatus(planId, status) {
    $.ajax({
        type: "POST",
        url: "/rest/plan/" + planId + "/status/" + status,
        contentType: "application/json",
    });
}

