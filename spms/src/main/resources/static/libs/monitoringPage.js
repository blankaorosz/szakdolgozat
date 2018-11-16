function startPlanning() {
    $.ajax({
        type: "POST",
        url: "/rest/monitoring/planning-period/start-new",
    }).done(function (data) {
        alert(data.message);
        if(data.success) {
            window.location.replace("/statisticsPage")
        }
    });
}