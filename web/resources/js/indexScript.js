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
    }, function() {
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
    }, function() {
        let title = $("<div>").append(
            $("<div class=\"mdl-textfield mdl-js-textfield\">\n" +
                "    <input class=\"mdl-textfield__input\" type=\"text\" id=\"sample1\">\n" +
                "    <label class=\"mdl-textfield__label\" for=\"sample1\">Text...</label>\n" +
                "  </div>")
        );

        let textArea = $("<div>").append(
            $("<label>").append(
                $("<textarea>", {
                        name:"commentText",
                    id:"commentText"
                })
            )
        );

        let submit = $("<div>").append(
            $("<input>", {
                type:"submit",
                name:"submit",
                value:"Add Post",
                id:"submit"
            })
        );
        let content = $('.modalContent').html("").append(title,textArea, submit);

        componentHandler.upgradeElement(content[0]);
        // $.get('API/post/1', onGetPost);
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

    if(onShow) onShow();
    modal.show();
}

function onGetPost(data) {
    data = JSON.parse(data);
    let title = $("<header>", {
        class: "title",
        text: data.postTitle
    });

    let body = $("<div>", {
        class: "body",
        text: data.postContent
    });
    let comments = $("<div>", {
        class: "comments"
    });
    $('.modalContent').html("").append(title,body, comments);
}