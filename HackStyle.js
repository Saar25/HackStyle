// ==UserScript==
// @name         Hackstyle.js
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  Hamahapeha
// @author       MrTarnegol
// @match        https://*.haxball.com/*
// @grant        none
// ==/UserScript==

var style = window.style = {};

style.nickname = "Style";

style.avatars = "המהפכה".split("")
style.destroy = "ఌ佹砕Ĉ⼈渻䬭॑⬺爂᰾ᜪ䉧ᬯ㍸᜖ᘬ慩㙲Ԑ瀩ञࠣ笋℉⨄㐷紪権ਛൽ㔙怅㬆癕⨴琐ḉ≼崪ᙻḻ⨂㈿爇䕦ᴵ灾㸏眮ቡᄸ܍礂海學㰌⬦ ѫ⼕ย⌑ḷ㠕ፃ㤕㌋␢眢砬ⵯ煾Ⱘ縂㉰匊㤀ह稫ᝈⴾㄫ㤒䉊؈ି㔞帱ॹࠚⰯἯ浸ἱᔞ፭ਮ娮ف㤮〕㱳⬗ᅷд甇ᜯ⩿ᤶⱁ戃 椏缁⼐獾⌱മ㈐ᵲ桨ԷᴑĿᑆ㨤ఴ⑸甈⨘Ѳ∯㘴䁔ح฾،ฌ"
style.defaultAvatar = ":}";
style.avatarIndex = 0;

class Timer {
    constructor(callback, delay) {
        this.delay = delay;
        this.callback = callback;
        this.id = -1;
    }
    start() {
        if (this.id == -1) {
            this.id = setInterval(this.callback, this.delay);
        }
    }
    stop() {
        if (this.id !== -1) {
            clearInterval(this.id);
            this.id = -1;
        }
    }
    setDelay(delay) {
        this.delay = delay;
        if (this.id !== -1) {
            this.stop();
            this.start();
        }
    }
    isRunning() {
        return this.id !== -1
    }
    addCallback(callback) {
        this.callback = () => {this.callback(); callback();}
    }
}

var gameDocument = window.gameDocument = document.getElementsByClassName('gameframe')[0].contentWindow.document

style.avatarTimer = new Timer(() => {
    if (style.chatBox() !== gameDocument.activeElement) {
        style.setNextAvatarIndex();
        removeLastAvatarSetMessage();
    }
}, 100);

style.chatBox = function() {
    return gameDocument.getElementsByTagName("input")[0];
}

style.sendButton = function() {
    return gameDocument.getElementsByClassName("input")[0].getElementsByTagName("button")[0];
}

style.chatHistory = function() {
    return gameDocument.getElementsByClassName("log ps")[0];
}

style.write = function(text) {
    style.chatBox().value = text;
}

style.writeAppend = function(text) {
    style.chatBox().value += text;
}

style.send = function() {
    style.sendButton().click();
}

style.getMessage = function() {
    return style.chatBox().value;
}

style.setAvatar = function(avatar) {
	style.write("/avatar " + avatar);
	style.send();
}

style.setNextAvatarIndex = function() {
    style.setAvatar(style.avatars[style.avatarIndex]);
    style.avatarIndex = (style.avatarIndex + 1) % style.avatars.length;
}

style.setDefaultIndex = function() {
    style.setAvatar(style.defaultAvatar);
    style.avatarIndex = 0;
}

style.startAvatarChanger = function() {
    style.avatarTimer.start();
}

style.stopAvatarChanger = function() {
    style.avatarTimer.stop();
    style.setAvatar(style.defaultAvatar);
    style.avatarIndex = 0;
}

style.notice = function(message) {
    let element = document.createElement("p");
    element.className = "notice";
    element.innerText = message;
    style.chatHistory().appendChild(element);
}

function removeLastAvatarSetMessage() {
    var chat = style.chatHistory();
    if (chat.lastChild.innerText == "Avatar set") {
        chat.lastChild.remove();
    }
}

function getAll() {
    var players = gameDocument.querySelectorAll('[data-hook=name]');
    var string = ""
    for (var i = 0; i < players.length; i++) {
        var player = players[i].innerHTML.replace(/ /g, "_");
        string += "@" + player + " ";
    }
    return string;
}

var jinxOn = false;
var messagesObserver = new MutationObserver(copyLastMessage);
function toggleJinx() {
    jinxOn = !jinxOn
    if (jinxOn) {
        messagesObserver.observe(style.chatHistory(), {childList: true});
        return "Jinx: On";
    } else {
        messagesObserver.disconnect();
        return "Jinx: Off";
    }
}

function copyLastMessage() {
    var children = style.chatHistory().children
    var text = children[children.length - 1].innerText
    var message = text.slice(text.indexOf(": ") + 2)
    var sender = text.slice(0, text.indexOf(": "))
    if (sender != style.nickname && text.indexOf(": ") != -1) {
        style.write(message)
        style.send()
    }
}

function toggleAvatar() {
    if (style.avatarTimer.isRunning()) {
        style.stopAvatarChanger();
        removeLastAvatarSetMessage();
        style.notice("Avatar changer is disabled!");
        return "Avatar: Off";
    } else {
        style.startAvatarChanger();
        style.notice("Avatar changer is active!");
        return "Avatar: On";
    }
}

function spamMessage() {
    var message = style.getMessage();
    style.write(message);
    style.send();
    style.write(message);
}

function tagAll() {
    var tag = getAll();
    if (tag) {
        var message = style.getMessage();
        var string = tag + message;
        style.write(string);
        style.send();
        style.write(message);
    }
}

function controlledSend() {
    style.send()
}

function addInGameButtons() {
    var input = gameDocument.getElementsByClassName("input")[0];
    if (input !== undefined) {
        // controlled send
        var send = input.children[1].cloneNode(false);
        send.style.marginLeft = "5px";
        send.innerText = "Send";
        send.onclick = controlledSend;
        input.appendChild(send);
        // tag
        var tag = input.children[1].cloneNode(false);
        tag.style.marginLeft = "5px";
        tag.innerText = "TagAll";
        tag.onclick = tagAll;
        input.appendChild(tag);
        // spam
        var spam = input.children[1].cloneNode(false);
        spam.style.marginLeft = "5px";
        spam.innerText = "Spam";
        spam.onclick = spamMessage;
        input.appendChild(spam);
        // avatar
        var x = gameDocument.getElementsByClassName("bottom-section")[0].getElementsByClassName("buttons")[0].children[1];
        var avatar = x.cloneNode(false);
        avatar.innerText = "Avatar: Off";
        avatar.setAttribute("data-hook", "avatarChanger");
        avatar.onclick = () => {avatar.innerText = toggleAvatar()};
        x.parentElement.appendChild(avatar);
        // copyMessages
        var jinx = x.cloneNode(false);
        jinx.innerText = "Jinx: Off";
        jinx.setAttribute("data-hook", "jinx");
        jinx.onclick = () => {jinx.innerText = toggleJinx()};
        x.parentElement.appendChild(jinx);
        // hide send button
        style.sendButton().style.display = 'none'
    }
}

function addButtons() {
    addInGameButtons();
}

function onFrameChange() {
    addButtons();
    style.avatarTimer.stop();
}

setTimeout(function() {
    var observer = new MutationObserver(onFrameChange);
    var holder = gameDocument.body.getElementsByTagName("div")[0];
    observer.observe(holder, {childList: true});
    addButtons();
}, 3000);
