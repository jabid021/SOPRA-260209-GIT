
// null => type null
function test1(prenom: string | null) {

}

// ? => undefined
function test2(prenom?: string) {

}

test1("test");
test1(null);

test2();
test2("test");
// test2(null);
