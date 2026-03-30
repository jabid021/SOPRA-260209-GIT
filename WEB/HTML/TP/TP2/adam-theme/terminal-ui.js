let table = document.getElementById("terminal-table");
let form = document.getElementById("shell-form");
let input = document.getElementById("shell-input");

form.addEventListener("submit", function(event) {
  event.preventDefault();

  let cmd = input.value;

  if (cmd === "") {
    return;
  }

  let cmdRow = table.insertRow(table.rows.length - 1);
  cmdRow.innerHTML = '<td class="prompt">1&gt;</td><td class="cmd">' + escapeHtml(cmd) + '</td>';

  let outputs = amigaCommand(cmd);

  for (let i = 0; i < outputs.length; i++) {
    let outRow = table.insertRow(table.rows.length - 1);
    outRow.innerHTML = '<td></td><td class="output">' + escapeHtml(outputs[i]) + '</td>';
  }

  input.value = "";
  table.parentElement.scrollTop = table.parentElement.scrollHeight;
  input.focus();
});

function escapeHtml(text) {
  return String(text).replace(/[&<>"' ]/g, function(char) {
    if (char === "&") return "&amp;";
    if (char === "<") return "&lt;";
    if (char === ">") return "&gt;";
    if (char === '"') return "&quot;";
    if (char === "'") return "&#039;";
    if (char === " ") return "&nbsp;";
    return char;
  });
}