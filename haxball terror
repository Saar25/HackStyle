window.gameDocument = document.getElementsByClassName('gameframe')[0].contentWindow.document
var waiting = 5000;

function addTagButton() {
    var input = gameDocument.getElementsByClassName("input")[0];
    if (input !== undefined) {
        if (input.children[2] === undefined) {
            var tag = input.children[1].cloneNode(false);
            tag.style.marginLeft = "5px";
            tag.innerText = "TagAll";
            tag.onclick = function() {
                var players = gameDocument.querySelectorAll('[data-hook=name]');
                var string = ""
                for (var i = 0; i < players.length; i++) {
                    if (players[i].innerText == "") string += "@_ ";
                    else string += "@" + players[i].innerText + " ";
                }
                var p = document.createElement("p");
                p.innerText = ">>> Copy this: " + string;
                gameDocument.getElementsByClassName("log ps")[0].appendChild(p);
            }
            input.appendChild(tag);
        }
        setTimeout(addTagButton, waiting);
        if (waiting < 60000) waiting += 1000;
    } else {
        setTimeout(addTagButton, 3000);
        waiting = 5000;
    }
}

addTagButton();
