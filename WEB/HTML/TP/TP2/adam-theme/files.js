let files = {
  "Disk.info": [
    "Disk name: Work",
    "Free space: 880 kB"
  ],
  "top_anime.txt": [
    "1: psychopass",
    "2: fullmetal alchemist",
    "3: dragon ball",
    "4: death note",
    "5: Code geass"
  ],
  "demo.asm": [
    "; Petit programme de démonstration",
    "MOV A, 5",
    "ADD A, 7",
    "PRINT A",
    "HLT"
  ]
};

function splitLines(text) {
  let cleanText = text.replace(/\r\n/g, "\n");
  return cleanText.split("\n");
}

function readFile(filename) {
  if (files[filename]) {
    return files[filename];
  }

  return null;
}

function writeFile(filename, content) {
  files[filename] = splitLines(content);
}

function appendFile(filename, content) {
  let newLines = splitLines(content);

  if (!files[filename]) {
    files[filename] = [];
  }

  for (let i = 0; i < newLines.length; i++) {
    files[filename].push(newLines[i]);
  }
}

function listFiles() {
  let names = Object.keys(files);
  return names.join("    ");
}