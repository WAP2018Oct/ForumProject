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