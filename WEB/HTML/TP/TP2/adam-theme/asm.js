function isRegister(value) {
  if (value === "A") return true;
  if (value === "B") return true;
  if (value === "C") return true;
  if (value === "D") return true;
  return false;
}

function parseValue(value, registers) {
  value = value.trim();

  if (isRegister(value)) {
    return registers[value];
  }

  if (/^-?\d+$/.test(value)) {
    return parseInt(value, 10);
  }

  throw new Error("Valeur invalide: " + value);
}

function compileAsm(lines) {
  let labels = {};
  let cleanedLines = [];
  let program = [];

  for (let i = 0; i < lines.length; i++) {
    let line = lines[i];

    line = line.replace(/;.*$/, "");
    line = line.trim();

    if (line === "") {
      continue;
    }

    if (/^[A-Za-z_][A-Za-z0-9_]*:$/.test(line)) {
      let labelName = line.slice(0, line.length - 1);
      labels[labelName] = cleanedLines.length;
    } else {
      cleanedLines.push(line);
    }
  }

  for (let i = 0; i < cleanedLines.length; i++) {
    let line = cleanedLines[i];
    let parts = line.split(/\s+/);
    let opcode = parts[0].toUpperCase();
    let rest = line.slice(opcode.length).trim();

    if (opcode === "HLT") {
      program.push({ op: "HLT" });
    } else if (opcode === "JMP" || opcode === "JZ" || opcode === "JNZ") {
      program.push({ op: opcode, arg1: rest });
    } else if (opcode === "PRINT") {
      program.push({ op: "PRINT", arg1: rest });
    } else if (opcode === "MOV" || opcode === "ADD" || opcode === "SUB" || opcode === "CMP") {
      let args = rest.split(",");

      if (args.length !== 2) {
        throw new Error("Syntaxe invalide ligne: " + line);
      }

      let arg1 = args[0].trim();
      let arg2 = args[1].trim();

      program.push({
        op: opcode,
        arg1: arg1,
        arg2: arg2
      });
    } else {
      throw new Error("Opcode inconnu: " + opcode);
    }
  }

  return {
    program: program,
    labels: labels
  };
}

function runAsm(compiled) {
  let program = compiled.program;
  let labels = compiled.labels;
  let registers = {
    A: 0,
    B: 0,
    C: 0,
    D: 0
  };
  let pc = 0;
  let zeroFlag = false;
  let output = [];
  let safety = 1000;

  while (pc >= 0 && pc < program.length) {
    safety = safety - 1;

    if (safety <= 0) {
      throw new Error("boucle infinie ou trop d'instruction.");
    }

    let instruction = program[pc];

    if (instruction.op === "MOV") {
      if (!isRegister(instruction.arg1)) {
        throw new Error("Registre invalide pour MOV: " + instruction.arg1);
      }

      registers[instruction.arg1] = parseValue(instruction.arg2, registers);
      pc = pc + 1;
    } else if (instruction.op === "ADD") {
      if (!isRegister(instruction.arg1)) {
        throw new Error("Registre invalide pour ADD: " + instruction.arg1);
      }

      registers[instruction.arg1] = registers[instruction.arg1] + parseValue(instruction.arg2, registers);
      pc = pc + 1;
    } else if (instruction.op === "SUB") {
      if (!isRegister(instruction.arg1)) {
        throw new Error("Registre invalide pour SUB: " + instruction.arg1);
      }

      registers[instruction.arg1] = registers[instruction.arg1] - parseValue(instruction.arg2, registers);
      pc = pc + 1;
    } else if (instruction.op === "CMP") {
      if (!isRegister(instruction.arg1)) {
        throw new Error("Registre invalide pour CMP: " + instruction.arg1);
      }

      zeroFlag = registers[instruction.arg1] === parseValue(instruction.arg2, registers);
      pc = pc + 1;
    } else if (instruction.op === "JMP") {
      if (!(instruction.arg1 in labels)) {
        throw new Error("Label introuvable: " + instruction.arg1);
      }

      pc = labels[instruction.arg1];
    } else if (instruction.op === "JZ") {
      if (!(instruction.arg1 in labels)) {
        throw new Error("Label introuvable: " + instruction.arg1);
      }

      if (zeroFlag) {
        pc = labels[instruction.arg1];
      } else {
        pc = pc + 1;
      }
    } else if (instruction.op === "JNZ") {
      if (!(instruction.arg1 in labels)) {
        throw new Error("Label introuvable: " + instruction.arg1);
      }

      if (!zeroFlag) {
        pc = labels[instruction.arg1];
      } else {
        pc = pc + 1;
      }
    } else if (instruction.op === "PRINT") {
      output.push(String(parseValue(instruction.arg1, registers)));
      pc = pc + 1;
    } else if (instruction.op === "HLT") {
      output.push("[Program halted]");
      return output;
    } else {
      throw new Error("Instruction inconnue: " + instruction.op);
    }
  }

  output.push("[Program finished]");
  return output;
}