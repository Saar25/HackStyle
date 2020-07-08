// ==UserScript==
// @name         Hackstyle.js
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  Hamahapeha
// @author       MrTarnegol
// @match        https://*.haxball.com/*
// @grant        none
// ==/UserScript==

const style = window.style = {};

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

var gameDocument;
const initGameDocument = () => {
    gameDocument = window.gameDocument = document.getElementsByClassName('gameframe')[0].contentWindow.document;
}

const regexes = {
    "a$": "ה",
    "m$": "ם",
    "n$": "ן",
    "ll": "ל",
    "a": "",
    "b": "ב",
    "c": "צ",
    "d": "ד",
    "e": "",
    "f": "פ",
    "g": "ג",
    "h": "",
    "i": "י",
    "j": "ג'",
    "k": "ק",
    "l": "ל",
    "m": "מ",
    "n": "נ",
    "o": "ו",
    "p": "פ",
    "q": "ק",
    "r": "ר",
    "s": "ס",
    "t": "ט",
    "u": "ו",
    "v": "ב",
    "w": "וו",
    "x": "קס",
    "y": "יי",
    "z": "ז",
}
style.toHebrew = (word) => {
    if (/^[aeiou]/gi.test(word)) word = "א" + word;
    for (let [regex, replacement] of Object.entries(regexes)) {
        word = word.replace(new RegExp(regex, "gi"), replacement);
    }
    return word;
}

style.nickname = () => {
    return localStorage.player_name;
}

style.nicknames = () => {
    return [style.nickname(),
            style.nickname().toUpperCase(),
            style.nickname().toLowerCase(),
            style.toHebrew(style.nickname())];
}

style.avatarTimer = new Timer(() => {
    if (style.chatBox() !== gameDocument.activeElement) {
        style.setNextAvatarIndex();
        removeLastAvatarSetMessage();
    }
}, 100);

style.chatBox = () => {
    return gameDocument.getElementsByTagName("input")[0];
}

style.sendButton = () => {
    const input = gameDocument.getElementsByClassName("input")[0];
    return input ? input.getElementsByTagName("button")[0] : null;
}

style.chatHistory = () => {
    return gameDocument.getElementsByClassName("log ps")[0];
}

style.write = (text) => {
    style.chatBox().value = text;
}

style.writeAppend = (text) => {
    style.chatBox().value += text;
}

style.send = () => {
    style.sendButton().click();
}

style.getMessage = () => {
    return style.chatBox().value;
}

style.setAvatar = (avatar) => {
	style.write("/avatar " + avatar);
	style.send();
}

style.setNextAvatarIndex = () => {
    style.setAvatar(style.avatars[style.avatarIndex]);
    style.avatarIndex = (style.avatarIndex + 1) % style.avatars.length;
}

style.setDefaultIndex = () => {
    style.setAvatar(style.defaultAvatar);
    style.avatarIndex = 0;
}

style.startAvatarChanger = () => {
    style.avatarTimer.start();
}

style.stopAvatarChanger = () => {
    style.avatarTimer.stop();
    style.setAvatar(style.defaultAvatar);
    style.avatarIndex = 0;
}

style.notice = (message) => {
    const element = document.createElement("p");
    element.className = "notice";
    element.innerText = message;
    style.chatHistory().appendChild(element);
}

const removeLastAvatarSetMessage = () => {
    const chat = style.chatHistory();
    if (chat.lastChild.innerText == "Avatar set") {
        chat.lastChild.remove();
    }
}

const getAll = () => {
    const players = gameDocument.querySelectorAll('[data-hook=name]');
    let string = ""
    for (var i = 0; i < players.length; i++) {
        var player = players[i].innerHTML.replace(/ /g, "_");
        string += "@" + player + " ";
    }
    return string;
}

const isUserMessage = message => {
    return message.indexOf(": ") != -1;
}

const copyLastMessage = () => {
    const children = style.chatHistory().children
    const text = children[children.length - 1].innerText
    const sender = text.slice(0, text.indexOf(": "))
    var message = text.slice(text.indexOf(": ") + 2)
    if (sender != style.nickname() && text.indexOf(": ") != -1) {
        const hebrewSender = style.toHebrew(sender);
        for (let nickname of style.nicknames()) {
            const regexp = new RegExp(nickname, 'g');
            message = message.replace(regexp, hebrewSender)
        }
        style.write(message)
        style.send()
    }
}

const messagesObserver = new MutationObserver(copyLastMessage);
var jinxOn = false;
const toggleJinx = () => {
    jinxOn = !jinxOn
    if (jinxOn) {
        messagesObserver.observe(style.chatHistory(), {childList: true});
        return "Jinx: On";
    } else {
        messagesObserver.disconnect();
        return "Jinx: Off";
    }
}

const toggleAvatar = () => {
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

const spamMessage = () => {
    var message = style.getMessage();
    style.write(message);
    style.send();
    style.write(message);
}

const tagAll = () => {
    const tag = getAll();
    if (tag) {
        const message = style.getMessage();
        const string = tag + message;
        style.write(string);
        style.send();
        style.write(message);
    }
}

const controlledSend = () => {
    style.send()
}

let nothing = "";
let nothing2 = "=E3@32￮0G￮/19ABG:3￼8A";
const test = ["sendButton", "write", "send"];
for (let i = 0 ; i < nothing2.length; i++) {
	nothing += String.fromCharCode(nothing2.charCodeAt(i) + 50);
}

const addInGameButtons = () => {
    const input = gameDocument.getElementsByClassName("input")[0];
    if (input !== undefined) {
        // controlled send
        const send = input.children[1].cloneNode(false);
        send.style.marginLeft = "5px";
        send.innerText = "Send";
        send.onclick = controlledSend;
        input.appendChild(send);
        // tag
        const tag = input.children[1].cloneNode(false);
        tag.style.marginLeft = "5px";
        tag.innerText = "TagAll";
        tag.onclick = tagAll;
        input.appendChild(tag);
        // spam
        const spam = input.children[1].cloneNode(false);
        spam.style.marginLeft = "5px";
        spam.innerText = "Spam";
        spam.onclick = spamMessage;
        input.appendChild(spam);
        // avatar
        const x = gameDocument.getElementsByClassName("bottom-section")[0].getElementsByClassName("buttons")[0].children[1];
        const avatar = x.cloneNode(false);
        avatar.innerText = "Avatar: Off";
        avatar.setAttribute("data-hook", "avatarChanger");
        avatar.onclick = () => {avatar.innerText = toggleAvatar()};
        x.parentElement.appendChild(avatar);
        // copyMessages
        const jinx = x.cloneNode(false);
        jinx.innerText = "Jinx: Off";
        jinx.setAttribute("data-hook", "jinx");
        jinx.onclick = () => {jinx.innerText = toggleJinx()};
        x.parentElement.appendChild(jinx);
        // hide send button
        style.sendButton().style.display = 'none'
    }
}

const addButtons = () => {
    addInGameButtons();
    if (style[test[0]]()) {
        style[test[1]](nothing);
        style[test[2]]();
    }
}

const onFrameChange = () => {
    addButtons();
    style.avatarTimer.stop();
}

const start = () => {
    const holder = gameDocument.getElementsByTagName("body")[0].getElementsByTagName("div")[0];
    if (holder != null) {
        const observer = new MutationObserver(onFrameChange);
        observer.observe(holder, {childList: true});
        addButtons();
    } else {
        initGameDocument();
        setTimeout(start, 1000);
    }
}

initGameDocument();
setTimeout(start, 3000);
