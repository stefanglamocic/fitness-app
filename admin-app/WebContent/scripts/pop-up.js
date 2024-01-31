function closePopUp() {
    document.querySelector('#pop-up').close();
}

function getPopUp(title, content) {
    document.querySelector('#pop-up-title').innerHTML = title;
    document.querySelector('#pop-up-content').innerHTML = content;

    return document.querySelector('#pop-up');
}

function getConfirmDialog(title, content) {
    document.querySelector('#okBtn').hidden = true;
    document.querySelector('#cancelBtn').hidden = false;
    document.querySelector('#confirmBtn').hidden = false;

    return getPopUp(title, content);
}

function getInfoDialog(title, content) {
    document.querySelector('#okBtn').hidden = false;
    document.querySelector('#cancelBtn').hidden = true;
    document.querySelector('#confirmBtn').hidden = true;

    return getPopUp(title, content);
}