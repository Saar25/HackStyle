// ==UserScript==
// @name         Haxball Terror
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  try to take over the world!
// @author       You
// @match        https://*.haxball.com/*
// @grant        none
// ==/UserScript==

window.gameDocument = document.getElementsByClassName('gameframe')[0].contentWindow.document
window.style = {};

style.write = function(text) {
    gameDocument.getElementsByTagName("input")[0].value = text;
}

style.send = function() {
    gameDocument.getElementsByClassName("input")[0].getElementsByTagName("button")[0].click();
}

style.getMessage = function() {
    return gameDocument.getElementsByTagName("input")[0].value;
}

function copyText(text) {
    var textArea = document.createElement("textarea");
    document.body.appendChild(textArea);
    textArea.value = text;
    textArea.focus();
    textArea.select();
    document.execCommand('copy');
    document.body.removeChild(textArea);
}

function getAll() {
    var players = gameDocument.querySelectorAll('[data-hook=name]');
    var string = ""
    for (var i = 0; i < players.length; i++) {
        if (players[i].innerText == "") string += "@_ ";
        else string += "@" + players[i].innerText + " ";
    }
    return string;
}

function printAll() {
    var string = getAll();
    var p = document.createElement("p");
    p.innerText = ">>> Copy this: " + string;
    gameDocument.getElementsByClassName("log ps")[0].appendChild(p);
    var copy = document.createElement("button");
    copy.innerText = "Copy";
    p.appendChild(copy);
    copy.onclick = function() {
        copyText(string);
    }
}

function tagAll() {
    var message = style.getMessage();
    var string = getAll() + message;
    style.write(string);
    style.send();
    style.write(message);
}

function addInGameButtons() {
    var input = gameDocument.getElementsByClassName("input")[0];
    if (input !== undefined) {
        var tag = input.children[1].cloneNode(false);
        tag.style.marginLeft = "5px";
        tag.innerText = "TagAll";
        tag.onclick = tagAll;
        input.appendChild(tag);
        var print = input.children[1].cloneNode(false);
        print.style.marginLeft = "5px";
        print.innerText = "PrintAll";
        print.onclick = printAll;
        input.appendChild(print);
    }
}

function addButtons() {
    addInGameButtons();
}

setTimeout(function() {
    var observer = new MutationObserver(addButtons);
    var holder = gameDocument.body.getElementsByTagName("div")[0];
    observer.observe(holder, {childList: true});
    addButtons();
}, 3000);

