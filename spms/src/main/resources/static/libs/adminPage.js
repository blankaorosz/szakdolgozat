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
            $('#add-user-name').val("");
            $('#add-user-role-name').val("");
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

function openEditUserDialog(userId, userName) {
    var userNameInput = $("#modify-user-name");
    var passwordInput = $("#modify-user-password");
    var passwordInputConfirm = $("#modify-user-password-confirm");

    userNameInput.val(userName);
    passwordInput.val("");
    passwordInputConfirm.val("");

    userNameInput.data('user-id', userId);

    $("#user-modify-form-modal").css("display", "block");
}

function saveUser() {
    var userNameInput = $("#modify-user-name");
    var passwordInput = $("#modify-user-password");
    var passwordInputConfirm = $("#modify-user-password-confirm");

    var userName = userNameInput.val();
    var password = passwordInput.val();
    var passwordConf = passwordInputConfirm.val();
    var userId = userNameInput.data('user-id');

    var valid = true;

    if(userName){
        $('#modify-user-name').css('border-color', '');
    }else {
        $('#modify-user-name').css('border-color', 'red');
        valid = false;
    }

    if(password){
        $('#modify-user-password').css('border-color', '');
    }else {
        $('#modify-user-password').css('border-color', 'red');
        valid = false;
    }

    if(passwordConf){
        $('#modify-user-password-confirm').css('border-color', '');
    }else {
        $('#modify-user-password-confirm').css('border-color', 'red');
        valid = false;
    }

    if (passwordConf !== password) {
        $("#pass-not-match-msg").css("display", "block");
        $('#modify-user-password').css('border-color', 'red');
        $('#modify-user-password-confirm').css('border-color', 'red');
        valid = false;
    } else {
        $("#pass-not-match-msg").css("display", "none");
    }

    if (valid) {
        var jsonReq = {
            userName : userName,
            userPassword : password
        }

        $.ajax({
            type: "POST",
            url: "/rest/user/save/" + userId,
            data: JSON.stringify(jsonReq),
            contentType: "application/json"
        }).done(function (data) {
            if (data.success) {
                $('#user-table-row-' + userId).removeClass("highlight");
            }

            $("#close-user-modify-form-modal").click();

            alert(data.message);
        });
    }
}