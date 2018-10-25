function savePlan() {
    var planningTableCellArray = $(".planning-table-input-cell").map(function () {
        var planId = $(this).data("planid");
        var companyId = $(this).data("companyid");
        var planPerCompanyId = $(this).data("planpercompanyid");
        var month = $(this).data("month");

        var tableCellData = {}
        tableCellData["planId"] = planId;
        tableCellData["companyId"] = companyId;
        tableCellData["planPerCompanyId"] = planPerCompanyId;
        tableCellData["month"] = month;

        return tableCellData;
    }).get()

    $.ajax({
        type: "POST",
        url: "/rest/plan/save",
        data: JSON.stringify(planningTableCellArray),
        contentType: "application/json",
    });
}