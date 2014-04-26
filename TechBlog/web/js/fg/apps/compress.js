/**
 * @Description Compressor page script
 * @author Charles Chen
 * @date 14-4-26
 * @version 1.0
 */

window.onload = function () {
    // Check if the HTML5 file API is supported
    if (!window.File || !window.FileReader || !window.FileList || !window.Blob) {
        alert("Your browser cannot support HTML5 file API. You may need to paste your code in the text area.");
        return;
    }

    /* Start: Set action listener for drop file or select file */
    var dropZone = document.getElementById('source');
    dropZone.ondragover = handleDragOver;
    dropZone.ondrop = handleDropFile;
    var fileSelector = document.getElementById('file_selector');
    fileSelector.onchange = handleFileSelect;
    /* End: Set action listener for drop file or select file */

    // Get compressed code from backend while button is clicked
    $("#compress_btn").click(function () {
        var source = $("#source").val().replace(/(^\s*)|(\s*$)/g, "");
        if (source == "")return;
        $.ajax({
            url: "/compress_action!compress.shtml",
            data: {source: source, type: $("#file_type").val()},
            type: "post",
            success: function (data) {
                var ret_data = eval('(' + data + ')');
                if (ret_data.retcode != 0) {
                    alert(ret_data.mess);
                    return;
                }
                $("#dest").val(unescape(ret_data.result));
            },
            error: function (xhr, textStatus, error) {
                var message = "Ready State: " + XMLHttpRequest.readyState + "\n";
                message += "HTTP Response Code: " + XMLHttpRequest.status + "\n";
                message += "Error Message: " + error;
                alert(message);
            }
        });
    });

    // Initialize the file input style
    $("#file_selector").bootstrapFileInput();

    // Create export file button to export the compressed file
    Downloadify.create('downloadify', {
        filename: function () {
            return "compressed-" + Date.parse(new Date()) + "." + $("#file_type").val();
        },
        data: function () {
            return $("#dest").val();
        },
        onComplete: function () {
        },
        onCancel: function () {
        },
        onError: function () {
            alert("You are trying to export a empty file.");
        },
        transparent: false,
        swf: '/js/downloadify/downloadify.swf',
        downloadImage: '/js/downloadify/download.png',
        width: 100,
        height: 30,
        transparent: true,
        append: false
    });
}

/**
 * Handle file select action, read the file content to left text area
 * @param evt
 */
function handleFileSelect(evt) {
    var file = evt.target.files[0];
    var reader = new FileReader();
    reader.onloadend = function (event) {
        $("#source").val(event.target.result);
        if (file.type == "application/javascript") {
            $("#file_type").val("js");
        }
        if (file.type == "text/css") {
            $("#file_type").val("css");
        }
    }
    reader.readAsBinaryString(file);
}

/**
 * When drop file, read the file ocntent to the left text area
 * @param evt
 */
function handleDropFile(evt) {
    evt.stopPropagation();
    evt.preventDefault();

    var file = evt.dataTransfer.files[0]; // FileList object.
    if (file.type == "application/javascript") {
        $("#file_type").val("js");
    }
    if (file.type == "text/css") {
        $("#file_type").val("css");
    }
    // files is a FileList of File objects. List some properties.
    var reader = new FileReader();
    reader.onloadend = function (event) {
        $("#source").val(event.target.result);
    }
    reader.readAsBinaryString(file);
}

/**
 * Handle file drag action
 * @param evt
 */
function handleDragOver(evt) {
    evt.stopPropagation();
    evt.preventDefault();
    evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
}