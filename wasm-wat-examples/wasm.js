// Get the wasm file name
const args = process.argv.slice(2);
wasm_fn =args[0];
// Import object for printing
const importObj = {
  console: {
    log:function(msg){
      console.log(msg);
    }
  }
}
// Read the wasm file
const fs = require('fs');
const wasmBuffer = fs.readFileSync(wasm_fn); 
// Create the WebAssembly object and run the main() function
WebAssembly.instantiate(wasmBuffer,importObj).then(wasmModule => {
  // Exported function live under instance.exports
  const { main } = wasmModule.instance.exports;
  main()
});

/* The version for 
https://webassembly.github.io/wabt/demo/wat2wasm/

const wasmInstance =
      new WebAssembly.Instance(wasmModule, {});
const { main } = wasmInstance.exports;

console.log(main());

*/
