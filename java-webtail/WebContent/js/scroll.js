window.onload=toBottom;

function toBottom() {
	var bChecked = document.getElementById('gotobottom').checked;
	if (bChecked == true) {
		window.scrollTo(0, document.body.scrollHeight);
	} else {
		
	}
}

function timedRefresh(timeoutPeriod) {
	window.setTimeout("pagerefresh();",timeoutPeriod);
}

function pagerefresh() {
	document.getElementById('headerform').submit();
}


// Read a page's GET URL variables and return them as an associative array.
function getUrlVars()
{
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}