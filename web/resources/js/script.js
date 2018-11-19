
    $(function(){

        //----------- For Accordion ------------//
        function close_accordion_section() {
            $('.accordion .accordion-section-title').removeClass('active');
            $('.accordion .accordion-section-content').slideUp(300).removeClass('open');
        }

        $('.accordion-section-title').click(function(e) {
            // Grab current anchor value
            var currentAttrValue = $(this).attr('href');

            if($(e.target).is('.active')) {
                close_accordion_section();
            }else {
                close_accordion_section();

                // Add active class to section title
                $(this).addClass('active');
                // Open up the hidden content panel
                $('.accordion ' + currentAttrValue).slideDown(300).addClass('open');
            }

            e.preventDefault();
        });
        //--------------------------------------//



        $('#myBtn').click(function() {
            alert("Ok");
            $('#myModal').css({"display":"block"});
        });

        $('#btn_add').click(addUser);
        $('.btn_del').click(function(event){
            let elementId = event.target.id;
           //alert(elementId);

            $.ajax({
                url: '/user?id='+event.target.id,
                type: 'DELETE',

                success: function() {
                    alert("User has been removed");
                    $(`tr[name=${elementId}]`).css({"display":"none"});
                    processData(data);
                },
                error: function() {
                    // Do something with the result
                    alert("Can not remove the user. Please try again");
                },
            });

        });
        $('#btn_upt').click(function(event){

            var euserId = $('#eid').val();
            var euserFirstName = $('#efname').val();
            var euserLastName = $('#elname').val();
            var euserUsername = $('#euname').val();
            var euserPassword = $('#epw').val();
            var euserRole = $('#erole').val();

            var user = {firstName:euserFirstName, lastName:euserLastName, username:euserUsername, password:euserPassword, role:euserRole};

            $.ajax({
                url: '/user?id='+euserId+'&username='+euserUsername+'&role='+euserRole+'&firstName='+euserFirstName+'&lastName='+euserLastName+
                    '&password='+euserPassword+'&',
                type: 'PUT',
                data: user,
            success: function() {
                alert("User has been updated");
                // processData(data);
            },
            error: function() {
                // Do something with the result
                alert("Can not update user information. Please try again");
            },

            });
        });

        function addUser(){

            var userFirstName = $('#fname').val();
            var userLastName = $('#lname').val();
            var userUsername = $('#uname').val();
            var userPassword = $('#pw').val();
            var userRole = $('#role').val();
            if(userFirstName=="" || userLastName=="" || userPassword=="" || userRole==""||userUsername==""){
                alert("Please enter all fields");

            }
            else{

                var user = {firstName:userFirstName, lastName:userLastName, username:userUsername, password:userPassword, role:userRole};
                $.post('user',{user: JSON.stringify(user)}, processData, "json")
                $('#fname').val("");
                $('#lname').val("");
                $('#uname').val("");
                $('#pw').val("");
                $('#role').val("");
            }

        }


        function processData(data){
            //data = JSON.parse(data);
            var tdEdit = $('<img />', {'src' :'http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/256/Actions-document-edit-icon.png', 'width': '22px', 'height':'26', 'name':data.id});
            var tdDelete = $('<img />', {'src' :'http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/256/Actions-edit-delete-icon.png', 'width': '22px', 'height':'26', 'id':data.id});

            var td0=$('<td style="border: 2px solid #eeeeee; border-collapse: collapse">').append(tdEdit).append(tdDelete);
            var td1=$('<td  style="border: 2px solid #eeeeee; border-collapse: collapse">').text(data.id);
            var td2=$('<td  style="border: 2px solid  #eeeeee; border-collapse: collapse">').text(data.firstName);
            var td3 = $('<td  style="border: 2px solid  #eeeeee; border-collapse: collapse">').text(data.lastName);
            var td4 = $('<td  style="border: 2px solid  #eeeeee; border-collapse: collapse">').text(data.username);
            var tr = $('<tr  style="border: 2px solid  #eeeeee; border-collapse: collapse">').append(td0.css({"border": "2px solid #eeeeee", "border-collapse": "collapse"})).append(td1).append(td2).append(td3).append(td4);
            $('#tbl_users>tbody').append(tr);
        }
    })

    window.onclick = function(event) {

        if (event.target == document.getElementById('myModal')) {
            document.getElementById('myModal').style.display = "none";
        }



    }



