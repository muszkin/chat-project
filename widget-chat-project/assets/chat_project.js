!(function(){
    var __module = {
        createElement: function(htmlStr) {
            var frag = document.createDocumentFragment(),
            temp = document.createElement('div');
            temp.innerHTML = htmlStr;
            while (temp.firstChild) {
            frag.appendChild(temp.firstChild);
        }
            return frag;
        },
        htmlContainer: function () {
            var html = 
            `<div id="chat-app-container" style="position: fixed; width: 0px; height: 0px; bottom: 0px; right: 0px; z-index: 2147483647;">
                <iframe src="http://localhost:3000/" frameborder="0" style="z-index: 2147483000!important; position: fixed!important; bottom: 20px; right: 20px; height: calc(100% - 20px - 20px); width: 370px!important; min-height: 250px!important; max-height: 590px!important; border-radius: 8px!important; overflow: hidden!important; opacity: 1!important;">
                    alternative content for browsers which do not support iframe.
                </iframe>
            </div>`;
            return html;    


        }
    }
    document.body.insertBefore(__module.createElement(__module.htmlContainer()), document.body.childNodes[0]);
})();