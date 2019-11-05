var ws;

function connect() {
    var username = document.getElementById("username").value;
    
    var host = document.location.host;
    var pathname = document.location.pathname;
    
    ws = new WebSocket("ws://" +"localhost:8080"+"/room" + "/"+username); //Change this to "coms-309-sb-4.misc.iastate.edu"

    ws.onmessage = function(event) {
    var log = document.getElementById("log");
        console.log(event.data);
        log.innerHTML += event.data + "\n";
    };
}

function close() {
	ws.close();
}

function send() {
    var content = document.getElementById("msg").value;
    
    ws.send(content);
}