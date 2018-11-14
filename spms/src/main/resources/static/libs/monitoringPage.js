function startPlanning() {
    $.ajax({
        type: "POST",
        url: "/rest/admin/planningperiod/start-new",
    }).done(function (data) {
        alert(data.message);
    });
}