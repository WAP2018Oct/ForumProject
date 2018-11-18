/* INIT */
$(function () {
    $('.post-list .mdl-list__item').click(listItemClickHandler);
    $('#addPost').click(addPostClickHandler);

    $('.dotMenu').click(function(e){
        e.stopPropagation();

    });
    $('.mdl-menu__item').click(function(e) {
        e.stopPropagation();
    })
});


/* CLICK HANDLERS */
function listItemClickHandler() {
    // unfocus focused element;
    $(this).blur();

    const bound = this.getBoundingClientRect();
    const postId = $(this).attr('postid');

    // console.log($(this));
    createModal({
        'top': bound.y + bound.height / 2 + 'px',
        'left': bound.x + bound.width / 2 + 'px',
        'width': bound.width + 'px',
        'height': bound.height + 'px'
    }, function () {
        $.get('API/post/' + postId, onGetPost);
    });
}

function addPostClickHandler() {
    $(this).blur();

    const bound = this.getBoundingClientRect();

    createModal({
        'top': bound.y + bound.height / 2 + 'px',
        'left': bound.x + bound.width / 2 + 'px',
        'width': bound.width + 'px',
        'height': bound.height + 'px'
    }, drawNewPostDialog);
}

function submitPostHandler() {
    const title = $("#title").val();
    const description = $("#description").val();
    $(".rightSpinner.hidden").removeClass("hidden");

    $.post('API/post/', {
        title,
        description
    }, onPostPost);
}


/* AJAX RESPONSES */
function onGetPost(data) {
    data = JSON.parse(data);

    console.log(data);
    let title = $("<header>", {
        class: "title",
        text: data.post.postTitle
    });

    let body = $("<div>", {
        class: "body",
        text: data.post.postContent
    });
    let comments = $("<div>", {
        class: "comments"
    });

    /* https://getmdl.io/components/index.html#lists-section */
    let commentList = $("<div>", {
        class: "mdl-list comment-list",
    });

    $.each(data.comments, function( index, value ) {

        let commentArea = $("<textarea>", {
            text: value.comment,
            //disabled: true,
            col: 20,
            rows: 3,
            class: "mdl-textfield__input textareaComment comment_"+ value.id + " textarea_comment_"+ value.id
        });
        commentList.append(commentArea);

        /*commentList.append($("<br/>", {}));*/
        let btnEdit = $("<button>", {
            text: "Edit",
            class: "mdl-button mdl-js-button mdl-button--raised mdl-button--colored comment_"+ value.id
        });

        let btnDelete = $("<button>", {
            text: "Delete",
            class: "mdl-button mdl-js-button mdl-button--raised mdl-button--accent deleteButton comment_"+ value.id
        });

        commentList.append(btnEdit, btnDelete);

        btnEdit.click(function(event) {
            let text_comment = $('.textarea_comment_'+value.id).val();
            $.ajax({
                type: 'PUT',
                url: '/comment?id='+value.id+'&comment='+text_comment,
                success: function(result) {
                    // Do something with the result
                    alert("Update successfully!");
                },
                error: function() {
                    // Do something with the result
                    alert("Can not update this comment. Please have a look again!");
                },
            });
        });

        btnDelete.click(function(event) {
            $.ajax({
                url: '/comment?id='+value.id,
                type: 'DELETE',
                success: function(result) {
                    // Do something with the result
                    $(".comment_"+value.id).hide();
                    alert("Delete successfully!");
                },
                error: function() {
                    // Do something with the result
                    alert("Can not delete this comment. Please have a look again!");
                },
            });
        });

    });

    var textArea = $("<div>").append(
        $("<label>", {
            text: "Do you want to add new comment?"
            }
        ).append("<br/>")
            .append(
            $("<textarea>", {
                name: "commentTextAdd",
                id: "commentTextAdd",
                class: "mdl-textfield__input textareaComment",
                rows: 10,
                cols: 40
            })
        )
    );

    let btnAdd = $("<button>", {
        class: "mdl-button mdl-js-button mdl-button--raised mdl-button--colored btnAddComment",
        text: "Add New Comment"
    });

    comments.append(commentList, textArea, btnAdd);

    btnAdd.click(function(event) {
        let comment = $("#commentTextAdd").val();
        $.ajax({
            url: '/comment?post_id='+data.post.id+'&comment='+comment,
            type: 'POST',
            success: function(result) {
                // Do something with the result
                alert("This comment is added successfully!");
            },
            error: function() {
                // Do something with the result
                alert("Can not add this comment. Please have a look again!");
            },
        });

    });

    $('.modalContent').html("").append(title, body, comments);
}

function onPostPost(data) {
    console.log(data);
    $(".modal").remove();
}


function drawNewPostDialog() {
    const lineBreak = $("<br/>");

    const title = $("<div>").append(
        $("<h2>", {
            text: "Create New Post"
        })
    );

    const titleInput = $("<div>", {
        class: "mdl-textfield mdl-js-textfield mdl-textfield--floating-label"
    }).append(
        $("<input>", {
            class: "mdl-textfield__input",
            type: "text",
            id: "title"
        })
    ).append(
        $("<label>", {
            class: "mdl-textfield__label",
            for: "title",
            text: "Post Title"
        })
    );

    const textArea = $("<div>", {
        class: "mdl-textfield mdl-js-textfield mdl-textfield--floating-label",
    }).append(
        $("<textarea>", {
            class: "mdl-textfield__input",
            type: "text",
            rows: "10",
            id: "description",
            name: "description"
        })
    ).append(
        $("<label>", {
            class: "mdl-textfield__label",
            for: "description",
            text: "Post Description"
        })
    );

    const submit = $("<div>").append(
        $("<button>", {
            type: "submit",
            class: ["mdl-button mdl-js-button", "mdl-button--raised", "mdl-js-ripple-effect", "mdl-button--colored", "submitButton"].join(" "),
            text: "Add New Post"
        })
    );

    const loader = $("<div>", {
        class: "mdl-spinner mdl-js-spinner is-active rightSpinner hidden"
    });

    submit.find("button").click(submitPostHandler);
    const content = $('.modalContent').html("").append(title, titleInput, lineBreak, textArea, lineBreak, submit, loader);

    componentHandler.upgradeElements(content[0]);
}

function createModal(initialCSS, onShow) {
    const modal = $('<div>', {
        class: 'modal'
    });

    const modalContent = $('<div>', {
        class: 'modalContent'
    });

    const loader = $('<div>', {
        class: ['mdl-spinner mdl-js-spinner is-active main']
    });

    modal.append(modalContent);
    modalContent.append(loader);
    modalContent.css(initialCSS);

    componentHandler.upgradeElement(loader[0]);

    modal.click(function () {
        $(this).remove();
    });
    modalContent.click(function (e) {
        e.stopPropagation();
    });

    $('body').append(modal);

    if (onShow) onShow();
    modal.show();

}









