function startPlanning() {
    var result = confirm("Are you sure you want to start the planning period?");
    if(result) {
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

}