(function () {
    /* INIT */
    $(function () {
        $('#manageUsers').click(userManagement);
        $('#addPost').click(addPostClickHandler);

        $('.post-list .mdl-list__item').click(listItemClickHandler);

        $('.dotMenu').click(function (e) {
            e.stopPropagation();
        });
        $('.mdl-menu__item').click(postMenuItemClickHandler);
    });


    function userManagement() {
        location.href = "/user"
    }


    /* CLICK HANDLERS */
    function listItemClickHandler() {
        // unfocus focused element;
        $(this).blur();

        const bound = this.getBoundingClientRect();
        const postId = $(this).attr('postid');

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

    function editPostHandler() {
        const title = $("#title").val();
        const description = $("#description").val();
        const postId = $(".modalContent h2").attr("postId");

        $.ajax({
            method: "PUT",
            url: "API/post/" + postId,
            data: {
                title,
                description
            }
        }).done(onEditPostResponse);
    }

    function postMenuItemClickHandler(e) {
        e.stopPropagation();
        const listItem = $(this).parent().closest("li");
        const postId = listItem.attr('postid');

        if ($(this).hasClass("editPost")) {
            const bound = listItem[0].getBoundingClientRect();

            createModal({
                'top': bound.y + bound.height / 2 + 'px',
                'left': bound.x + bound.width / 2 + 'px',
                'width': bound.width + 'px',
                'height': bound.height + 'px'
            }, function () {
                $.get('API/post/' + postId, onEditPost);
            });
        } else if ($(this).hasClass('deletePost')) {
            $.ajax({
                method: "DELETE",
                url: "API/post/" + postId,
            }).done(onDeletePost);
        }
    }

    function commentMenuItemClickHandler(e) {
        e.stopPropagation();

        const listItem = $(this).parent().closest("li");
        const commentId = listItem.attr('commentid');
        const postId = $(this).closest(".modalContent").find("header.title").attr("postid");

        if ($(this).hasClass("editComment")) {
            /*
            <div class="mdl-textfield mdl-js-textfield">
                <textarea class="mdl-textfield__input" type="text" rows= "3" id="sample5" ></textarea>
                <label class="mdl-textfield__label" for="sample5">Text lines...</label>
            </div>
            * */

            const detached = listItem.find(".mdl-list__item-text-body").detach();

            listItem.find(".mdl-list__item-primary-content").append(
                $("<textarea>", {
                    text: detached.text(),
                    class: "mdl-textfield__input",
                    type: "text"
                })
            ).append(
                $("<button>", {
                    class: "mdl-button mdl-js-button mdl-js-ripple-effect cancelButton",
                    type: "button",
                    text: "Cancel"
                })
            ).append(
                $("<button>", {
                    class: "mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--colored editSaveButton",
                    type: "button",
                    text: "Edit"
                })
            );

            listItem.find(".cancelButton").click(function () {
                listItem.find("textarea.mdl-textfield__input").remove();
                listItem.find(".cancelButton").remove();
                listItem.find(".editSaveButton").remove();
                listItem.find(".mdl-list__item-primary-content").append(detached);
            });

            listItem.find(".editSaveButton").click(function () {
                const text_comment = listItem.find(".mdl-textfield__input").val();
                $.ajax({
                    type: 'PUT',
                    url: '/comment?id=' + commentId + '&comment=' + text_comment + '&post_id=' + postId,
                    success: function (result) {
                        detached.html(text_comment);
                        listItem.find("textarea.mdl-textfield__input").remove();
                        listItem.find(".cancelButton").remove();
                        listItem.find(".editSaveButton").remove();
                        listItem.find(".mdl-list__item-primary-content").append(detached);
                    },
                    error: function () {
                        listItem.find("textarea.mdl-textfield__input").remove();
                        listItem.find(".cancelButton").remove();
                        listItem.find(".editSaveButton").remove();
                        listItem.find(".mdl-list__item-primary-content").append(detached);
                        alert("Can not update this comment. Please have a look again!");
                    },
                });
            });
        } else if ($(this).hasClass('deleteComment')) {
            $.ajax({
                url: '/comment?id=' + commentId + '&post_id=' + postId,
                type: 'DELETE',
                success: function (result) {
                    // Do something with the result
                    $(".comment_" + commentId).hide();
                    alert("Delete successfully!");
                },
                error: function () {
                    // Do something with the result
                    alert("Can not delete this comment. Please have a look again!");
                },
            });
        }
    }


    /* AJAX RESPONSES */
    function onGetPost(data) {
        data = JSON.parse(data);

        let title = $("<header>", {
            class: "title",
            postId: data.post.id,
            text: data.post.postTitle
        });

        let body = $("<div>", {
            class: "body",
            text: data.post.postContent
        });
        let comments = $("<div>", {
            class: "comments"
        });

        let commentList = $("<ul>", {
            class: "mdl-list comment-list",
        });

        $.each(data.comments, function (index, value) {
            let commentListItem = drawComment(value);
            commentListItem.find(".mdl-menu__item").click(commentMenuItemClickHandler);
            commentList.append(commentListItem);
        });

        let breakLine = $("<br/>", {});

        let textArea = $("<div>").append(
            $("<label>", {
                text: "Do you want to add new comment?",
                class: "labelArea"
            }).append(
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

        comments.append(commentList, breakLine, textArea, btnAdd);

        btnAdd.click(function (event) {
            let comment = $("#commentTextAdd").val();
            $.ajax({
                url: '/comment?post_id=' + data.post.id + '&comment=' + comment,
                type: 'POST',
                success: function (result) {
                    result = JSON.parse(result);
                    const commentItem = drawComment(result);
                    commentItem.find(".mdl-menu__item").click(commentMenuItemClickHandler);

                    $(".modalContent .comments .mdl-list").append(commentItem);
                    alert("This comment is added successfully!");
                },
                error: function () {
                    // Do something with the result
                    alert("Can not add this comment. Please have a look again!");
                },
            });

        });
        const modalContent = $('.modalContent');
        modalContent.html("").append(title, body, comments);

        componentHandler.upgradeElements(modalContent[0]);
    }

    function onPostPost(data) {
        data = JSON.parse(data);
        $(".modal").remove();

        const listItem = drawPost(data);
        $('.post-list').prepend(listItem);

        /*Attack events*/
        listItem.click(listItemClickHandler);
        listItem.find('.dotMenu').click(function (e) {
            e.stopPropagation();
        });
        listItem.find('.mdl-menu__item').click(postMenuItemClickHandler);

        componentHandler.upgradeElements(listItem[0]);
    }

    function onDeletePost(data) {
        data = JSON.parse(data);
        $(".mdl-list__item.lastHidden").removeClass("lastHidden");

        $(".mdl-list__item[postid='" + data.id + "']").addClass("hidden lastHidden");

        const snackbarContainer = document.querySelector('#bottom-snackbar');

        const snackData = {
            message: 'Post deleted.',
            actionHandler: function () {
                undoDelete(data);
            },
            actionText: 'Undo'
        };
        snackbarContainer.MaterialSnackbar.showSnackbar(snackData);

    }

    function onEditPost(data) {
        data = JSON.parse(data);
        const lineBreak = $("<br/>");

        const title = $("<div>").append(
            $("<h2>", {
                text: "Edit Post",
                postId: data.post.id
            })
        );

        const titleInput = $("<div>", {
            class: "mdl-textfield mdl-js-textfield mdl-textfield--floating-label"
        }).append(
            $("<input>", {
                class: "mdl-textfield__input",
                type: "text",
                id: "title",
                value: data.post.postTitle
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
                name: "description",
                text: data.post.postContent
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
                text: "Edit Post"
            })
        );

        const loader = $("<div>", {
            class: "mdl-spinner mdl-js-spinner is-active rightSpinner hidden"
        });

        submit.find("button").click(editPostHandler);
        const content = $('.modalContent').html("").append(title, titleInput, lineBreak, textArea, lineBreak, submit, loader);
        componentHandler.upgradeElements(content[0]);
    }

    function onEditPostResponse(data) {
        data = JSON.parse(data);
        $(".modal").remove();

        const listItem = drawPost(data);
        const editedPost = $('.post-list .mdl-list__item[postid=' + data.id + ']');
        editedPost.html(listItem.html());

        /*Attack events*/
        editedPost.find('.dotMenu').click(function (e) {
            e.stopPropagation();
        });
        editedPost.find('.mdl-menu__item').click(postMenuItemClickHandler);

        componentHandler.upgradeElements(editedPost[0]);
    }


    function undoDelete(data) {
        $(".mdl-list__item.lastHidden").removeClass("hidden lastHidden");

        /* should send POST request to server to restore post*/
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

    function drawPost(data) {
        console.log(data);
        return listItem = $("<li>", {
            class: "mdl-list__item mdl-list__item--three-line",
            postId: data.id
        }).append(
            $("<span>", {
                class: "mdl-list__item-primary-content"
            }).append(
                $("<i>", {
                    class: "material-icons mdl-list__item-avatar",
                    text: "person"
                })
            ).append(
                data.user.avatarLink.length > 0 ?
                    $("<img>", {
                        src: data.user.avatarLink,
                        alt: "user avatar",
                        class: "user-avatar"
                    }) : ""
            ).append(
                $("<span>", {
                    text: data.postTitle
                })
            ).append(
                $("<span>", {
                    class: "mdl-list__item-text-body",
                    text: data.postContent
                })
            )
        ).append(
            $("<span>", {
                class: "mdl-list__item-secondary-content"
            }).append(
                $("<div>", {
                    class: "material-icons mdl-badge mdl-badge--overlap",
                    "data-badge": 0,
                    text: 'comment'
                }))
        ).append(
            $("<button>", {
                id: "post-menu-lower-right" + data.id,
                class: "mdl-button mdl-js-button mdl-button--icon dotMenu"
            }).append(
                $('<i>', {
                    class: "material-icons",
                    text: "more_vert"
                })
            )
        ).append(
            $("<ui>", {
                class: "mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect",
                for: "post-menu-lower-right" + data.id
            }).append(
                $("<li>", {
                    class: "mdl-menu__item editPost",
                    text: "Edit Post"
                })
            ).append(
                $("<li>", {
                    class: "mdl-menu__item deletePost",
                    text: "Delete Post"
                })
            )
        );
    }

    function drawComment(value) {
        return $("<li>", {
            class: `mdl-list__item mdl-list__item--two-line comment_${value.id}`,
            commentId: value.id
        }).append(
            $("<span>", {
                class: "mdl-list__item-primary-content"
            }).append(
                $("<i>", {
                    class: "material-icons mdl-list__item-avatar",
                    text: "person"
                })
            ).append(
                value.author.avatarLink.length > 0 ?
                    $("<img>", {
                        src: value.author.avatarLink,
                        alt: "user avatar",
                        class: "user-avatar"
                    }) : ""
            ).append(
                $("<span>", {
                    class: "mdl-list__item-text-body",
                    text: value.comment
                })
            )
        ).append(
            $("<span>", {
                class: "mdl-list__item-secondary-content"
            }).append(
                $("<button>", {
                    id: `comment-menu-lower-right${value.id}`,
                    class: "mdl-button mdl-js-button mdl-button--icon dotMenu"
                }).append(
                    $("<i>", {
                        class: "material-icons",
                        text: "more_vert"
                    })
                )
            ).append(
                $("<ul>", {
                    class: "mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect",
                    for: `comment-menu-lower-right${value.id}`
                }).append(
                    $("<li>", {
                        class: "mdl-menu__item editPost editComment",
                        text: "Edit Comment"
                    })
                ).append(
                    $("<li>", {
                        class: "mdl-menu__item editPost deleteComment",
                        text: "Delete Comment"
                    })
                )
            )
        );

        // const commentArea = $("<textrea>", {
        //     text: value.comment,
        //     col: 20,
        //     rows: 3,
        //     class: "mdl-textfield__input textareaComment comment_" + value.id + " textarea_comment_" + value.id
        // });
        //
        // let btnEdit = $("<button>", {
        //     text: "Edit",
        //     class: "mdl-button mdl-js-button mdl-button--raised mdl-button--colored comment_" + value.id
        // });
        //
        // let btnDelete = $("<button>", {
        //     text: "Delete",
        //     class: "mdl-button mdl-js-button mdl-button--raised mdl-button--accent deleteButton comment_" + value.id
        // });
        //
        // commentList.append(btnEdit, btnDelete);
        //
        // btnEdit.click(function (event) {
        //     let text_comment = $('.textarea_comment_' + value.id).val();

        // });
        //
        // btnDelete.click(function (event) {
        //     if (confirm('Are you sure you want to delete this comment?')) {
        //
        //     }
        // });
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

})();








