function amigaCommand(cmd) {
  cmd = cmd.trim();

  if (cmd === "") {
    return [];
  }

  if (cmd.indexOf("echo ") === 0) {
    let match = cmd.match(/echo\s+"([\s\S]*)"|echo\s+(.*)/i);
    let text = "";

    if (match) {
      text = match[1] || match[2] || "";
    }

    return [text];
  }

  if (cmd === "help") {
    return [
      "Available commands:",
      "echo \"text\"",
      "dir",
      "type filename",
      "write filename \"content\"",
      "append filename \"content\"",
      "asm filename",
      "help"
    ];
  }

  if (cmd === "dir") {
    return [listFiles()];
  }

  if (cmd.indexOf("type ") === 0) {
    let filename = cmd.replace(/^type\s+/, "");
    filename = filename.replace(/['"]/g, "");
    filename = filename.trim();

    let content = readFile(filename);

    if (!content) {
      return ['Can\'t open "' + filename + '". File not found.'];
    }

    return content;
  }

  if (cmd.indexOf("write ") === 0) {
    let match = cmd.match(/^write\s+([^\s]+)\s+"([\s\S]*)"$/i);

    if (!match) {
      return ['Syntax: write filename "content"'];
    }

    let filename = match[1];
    let content = match[2];

    writeFile(filename, content);
    return ['File "' + filename + '" written successfully.'];
  }

  if (cmd.indexOf("append ") === 0) {
    let match = cmd.match(/^append\s+([^\s]+)\s+"([\s\S]*)"$/i);

    if (!match) {
      return ['Syntax: append filename "content"'];
    }

    let filename = match[1];
    let content = match[2];

    appendFile(filename, content);
    return ['Content appended to "' + filename + '".'];
  }

  if (cmd.indexOf("asm ") === 0) {
    let filename = cmd.replace(/^asm\s+/, "");
    filename = filename.replace(/['"]/g, "");
    filename = filename.trim();

    let content = readFile(filename);

    if (!content) {
      return ['ASM file "' + filename + '" not found.'];
    }

    try {
      let compiled = compileAsm(content);
      let result = runAsm(compiled);
      let finalResult = [];

      finalResult.push('[Compiling "' + filename + '"...]');
      finalResult.push('[Running...]');

      for (let i = 0; i < result.length; i++) {
        finalResult.push(result[i]);
      }

      return finalResult;
    } catch (error) {
      return ["ASM Error: " + error.message];
    }
  }

  return ["Unknown command: " + cmd];
}