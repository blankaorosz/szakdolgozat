function createUser() {
    var userName = $("#add-user-name").val();
    var userRole = $("#add-user-role-name").val();
    var userPassword = $("#add-user-password").val();

    if(userName){
        $('#add-user-name').css('border-color', '');
    }else {
        $('#add-user-name').css('border-color', 'red');
    }

    if(userRole){
        $('#add-user-role-name').css('border-color', '');
    }else {
        $('#add-user-role-name').css('border-color', 'red');
    }

    if(userPassword){
        $('#add-user-password').css('border-color', '');
    }else {
        $('#add-user-password').css('border-color', 'red');
    }

    if(userName && userPassword && userRole) {
        var jsonReq = {
            userName : userName,
            userRole : userRole,
            userPassword : userPassword
        }

        $.ajax({
            type: "POST",
            url: "/rest/user/create",
            data: JSON.stringify(jsonReq),
            contentType: "application/json",
        }).done(function (data) {
            if (data.success) {
                var rowContent = $("<tr id='user-table-row-" + data.content.id + "'>" +
                "               <td>" + data.content.userName + "</td>" +
                "               <td></td>" +
                "               <td>" + data.content.userRole + "</td>" +
                "               <td>" +
                "                   <button class='spms-button'" +
                "                           onclick='deleteUser(" + data.content.id + ")'>" +
                "                       delete" +
                "                   </button>" +
                "                   <button class='spms-button' onclick='editUser()'> edit </button>" +
                "               </td>" +
                "           </tr>");

                $('#admin-user-listing-table').DataTable().row.add(rowContent[0]).draw(false);
            }

            alert(data.message);
        });
    }
}

function deleteUser(userId) {
    var confirmed = confirm("Are you sure to delete the user?");

    if (confirmed) {
        $.ajax({
            type: "POST",
            url: "/rest/user/delete/" + userId,

        }).done(function (data) {
            if (data.success) {
                $('#admin-user-listing-table').DataTable()
                    .row($("#user-table-row-" + userId))
                    .remove()
                    .draw(false);
            }

            alert(data.message);
        });
    }
}