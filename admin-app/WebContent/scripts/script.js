function init(text) {
  addToTitle(text);
}

function addToTitle(text) {
	const current = document.title;
	document.title = `${current} ${text}`;
}