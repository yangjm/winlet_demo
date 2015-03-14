AeJSEngine.closeDialog = function() {
	if (AeJSEngine.dlg == null) {
		AeJSEngine.dlg = $("<div></div>");
		$(document.body).append(AeJSEngine.dlg);
	} else {
		try {
			AeJSEngine.dlg.dialog('destroy');
		} catch (e) {
		}
		AeJSEngine.dlg.empty();
	}
};

AeJSEngine.openDialog = function(wid, data, title) {
	AeJSEngine.dlg.append(
			AeJSEngine.procStyle(
					data.replace(AeJSEngine.reScriptAll, '')
						.replace(AeJSEngine.reWinPost, 'win$._post(' + wid + ', ')
						.replace(AeJSEngine.reWinGet, 'win$._get(' + wid + ', ')
						.replace(AeJSEngine.reWinUrl, 'win$._url(' + wid + ', ')
						.replace(AeJSEngine.reWinSubmit, 'win$._submit(' + wid + ', ')
					)
			);

	if (title == null)
		title = "";

	AeJSEngine.procScript(wid, data);
	$(function() {
		AeJSEngine.dlg.dialog({
			title: title,
			autoOpen: true,
			height: 'auto',
			width: 'auto',
			resizable: false,
			modal: true,
			close: function(event, ui) {AeJSEngine.closeDialog()}
		});
	});
};
