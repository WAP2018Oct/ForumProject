$(function () {
    $('.post-list .mdl-list__item').click(listItemClickHandler);
    $('#addPost').click(addPostClickHandler);
});


/* CLICK HANDLERS */
function listItemClickHandler() {
    // unfocus focused element;
    $(this).blur();

    let bound = this.getBoundingClientRect();

    // console.log($(this));
    createModal({
        'top': bound.y + bound.height / 2 + 'px',
        'left': bound.x + bound.width / 2 + 'px',
        'width': bound.width + 'px',
        'height': bound.height + 'px'
    }, function () {
        $.get('API/post/1', onGetPost);
    });
}

function addPostClickHandler() {
    $(this).blur();

    let bound = this.getBoundingClientRect();

    createModal({
        'top': bound.y + bound.height / 2 + 'px',
        'left': bound.x + bound.width / 2 + 'px',
        'width': bound.width + 'px',
        'height': bound.height + 'px'
    }, function () {
        let title = $("<div>").append(
            $("<div class=\"mdl-textfield mdl-js-textfield\">\n" +
                "    <input class=\"mdl-textfield__input\" type=\"text\" id=\"sample1\">\n" +
                "    <label class=\"mdl-textfield__label\" for=\"sample1\">Text...</label>\n" +
                "  </div>")
        );

        let textArea = $("<div>").append(
            $("<label>").append(
                $("<textarea>", {
                    name: "commentText",
                    id: "commentText"
                })
            )
        );

        let submit = $("<div>").append(
            $("<input>", {
                type: "submit",
                name: "submit",
                value: "Add Post",
                id: "submit"
            })
        );
        let content = $('.modalContent').html("").append(title, textArea, submit);

        componentHandler.upgradeElement(content[0]);
    });
}


function createModal(initialCSS, onShow) {
    let modal = $('<div>', {
        class: 'modal'
    });

    let modalContent = $('<div>', {
        class: 'modalContent'
    });

    let loader = $('<div>', {
        class: ['mdl-spinner mdl-js-spinner is-active']
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
            class: "comment_"+ value.id + " textarea_comment_"+ value.id
        });
        commentList.append(commentArea);
        commentList.append($("<br/>", {
        }));

        let btnEdit = $("<button>", {
            text: "Edit",
            class: "mdl-button--raised mdl-button--colored comment_"+ value.id
        });

        let btnDelete = $("<button>", {
            text: "Delete",
            class: "mdl-button--raised mdl-button--colored comment_"+ value.id
        });

        commentList.append(btnEdit, btnDelete);
        commentList.append($("<br/>", {
        }));

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

    let btnAdd = $("<button>", {
        class: "mdl-button--raised mdl-button--colored btn-add-comment",
        text: "Add Comment"
    });

    comments.append(commentList, btnAdd);

    btnAdd.click(function(event) {

        $(this).blur();
        let bound = this.getBoundingClientRect();

        createModal({
            'top': bound.y + bound.height / 2 + 'px',
            'left': bound.x + bound.width / 2 + 'px',
            'width': bound.width + 'px',
            'height': bound.height + 'px'
        }, function () {
            {
                let textArea = $("<div>").append(
                    $("<label>").append(
                        $("<textarea>", {
                            name: "commentTextAdd",
                            id: "commentTextAdd",
                            class: "comments"
                        })
                    )
                );

                /*let textArea =
                        $("<textarea>", {
                            name: "commentTextAdd",
                            id: "commentTextAdd",
                            class: "comments"
                        });*/


                let submit = $("<div>").append(
                    $("<input>", {
                        type: "submit",
                        name: "submit",
                        value: "Add",
                        class: "mdl-button--raised mdl-button--colored btn-add comments",
                        id: "submit"
                    })
                );

                let content = $('.modalContent').html("").append(textArea, submit);

                submit.click(function(event) {
                    //let comment_add = $('#commentTextAdd').val();
                    let comment_add = "xxx";
                    $.ajax({
                        url: '/comment?post_id='+data.post.id+'&comment='+comment_add,
                        type: 'POST',
                        success: function(result) {
                            // Do something with the result
                            alert("Add successfully!");
                        },
                        error: function() {
                            // Do something with the result
                            alert("Can not add this comment. Please have a look again!");
                        },
                    });
                });

                //componentHandler.upgradeElement(content[0]);
            }
        });

    });


    $('.modalContent').html("").append(title, body, comments);
}




