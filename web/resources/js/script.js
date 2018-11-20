$(function () {
    //----------- For Accordion ------------//
    function close_accordion_section() {
        $('.accordion .accordion-section-title').removeClass('active');
        $('.accordion .accordion-section-content').slideUp(300).removeClass('open');
    }

    $('.accordion-section-title').click(function (e) {
        const currentAttrValue = $(this).attr('href');

        if ($(e.target).is('.active')) {
            close_accordion_section();
        } else {
            close_accordion_section();

            $(this).addClass('active');
            $('.accordion ' + currentAttrValue).slideDown(300).addClass('open');
        }

        e.preventDefault();
    });
    //--------------------------------------//


    $('#myBtn').click(function () {

        $('#myModal').css({"display": "block"});
    });

    $('#logoutuser').click(function () {

            location.href = "/logout"

        }

    );
    $('#btn_add').click(addUser);
    $('.btn_del').click(function (event) {
        let elementId = event.target.id;

        $.ajax({
            url: '/user?id=' + event.target.id,
            type: 'DELETE',

            success: function () {
                alert("User has been removed");
                $(`tr[name=${elementId}]`).css({"display": "none"});
                processData(data);
            },
            error: function () {
                // Do something with the result
                alert("Can not remove the user. Please try again");
            },
        });

    });
    $('#btn_upt').click(function (event) {
        const euserId = $('#eid').val();
        const euserFirstName = $('#efname').val();
        const euserLastName = $('#elname').val();
        const euserUsername = $('#euname').val();
        const euserPassword = $('#epw').val();
        const euserRole = $('#erole').val();

        const user = {
            firstName: euserFirstName,
            lastName: euserLastName,
            username: euserUsername,
            password: euserPassword,
            role: euserRole
        };

        $.ajax({
            url: '/user?id=' + euserId + '&username=' + euserUsername + '&role=' + euserRole + '&firstName=' + euserFirstName + '&lastName=' + euserLastName +
                '&password=' + euserPassword + '&',
            type: 'PUT',
            data: user,
            success: function () {
                alert("User has been updated");
                // processData(data);
            },
            error: function () {
                // Do something with the result
                alert("Can not update user information. Please try again");
            },
        });
    });

    function addUser() {
        const userFirstName = $('#fname').val();
        const userLastName = $('#lname').val();
        const userUsername = $('#uname').val();
        const userPassword = $('#pw').val();
        const userRole = $('#role').val();
        if (userFirstName === "" || userLastName === "" || userPassword === "" || userRole === "" || userUsername === "") {
            alert("Please enter all fields");
        } else {
            const user = {
                firstName: userFirstName,
                lastName: userLastName,
                username: userUsername,
                password: userPassword,
                role: userRole
            };
            $.post('user', {user: JSON.stringify(user)}, processData, "json");
            $('#fname').val("");
            $('#lname').val("");
            $('#uname').val("");
            $('#pw').val("");
            $('#role').val("");
        }
    }


    function processData(data) {
        //data = JSON.parse(data);
        const tdEdit = $('<img />', {
            'src': 'http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/256/Actions-document-edit-icon.png',
            'width': '22px',
            'height': '26',
            'name': data.id
        });
        const tdDelete = $('<img />', {
            'src': 'http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/256/Actions-edit-delete-icon.png',
            'width': '22px',
            'height': '26',
            'id': data.id
        });

        const td0 = $('<td>').append(tdEdit, tdDelete);
        const td1 = $('<td>').text(data.id);
        const td2 = $('<td>').text(data.firstName);
        const td3 = $('<td>').text(data.lastName);
        const td4 = $('<td>').text(data.username);
        const tr = $('<tr>').append(td0, td1, td2, td3, td4);

        $('#tbl_users>tbody').append(tr);
    }
});


window.onclick = function (event) {
    if (event.target == document.getElementById('myModal')) {
        document.getElementById('myModal').style.display = "none";
    }
};



