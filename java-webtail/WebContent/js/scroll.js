window.onload=toBottom;

function toBottom()
{
	window.scrollTo(0, document.body.scrollHeight);
}

function timedRefresh(timeoutPeriod) {
	setTimeout("document.getElementById('headerform').submit();",timeoutPeriod);
}