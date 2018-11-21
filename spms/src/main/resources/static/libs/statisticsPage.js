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