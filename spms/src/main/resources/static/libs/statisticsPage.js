function refreshOfficeData() {
    var from = $( "#datepicker-from" ).datepicker("getDate").getTime();
    var to = $( "#datepicker-to" ).datepicker("getDate").getTime();

    $.ajax({
        type: "POST",
        url: "/rest/statistics/refresh-office-data/" + from + "/" + to,
    }).done(function (data) {
        alert(data.message);
    });
}

function startMonitoring() {
    var result = confirm("Are you sure you want to start the monitoring period?");
    if(result) {
        $.ajax({
            type: "POST",
            url: "/rest/statistics/monitoring-period/start",
        }).done(function (data) {
            alert(data.message);
            if(data.success) {
                window.location.replace("/monitoringPage")
            }
        });
    }

}