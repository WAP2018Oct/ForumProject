$(function () {
    $('.post-list .mdl-list__item').click(listItemClickHandler);
});

function listItemClickHandler() {
    // unfocus focused element;
    $(this).blur();

    let bound = this.getBoundingClientRect();
    console.log(this, bound);

    // console.log($(this));
    createModal({
        'top': bound.y + bound.height / 2 + 'px',
        'left': bound.x + bound.width / 2 + 'px',
        'width': bound.width + 'px',
        'height': bound.height + 'px'
    });
}

function createModal(initialCSS) {
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

    $.get('API/post/1', onGetPost, "json")
    modal.show();
}

function onGetPost(data) {
    $('.modalContent').html(data);
}