const IApi = require( 'umi').IApi;

const fs = require("fs");
const path = require("path");




module.exports = (api) => {
    api.describe({
        key: 'registryForms',
    });

    const dir = api.cwd + "/src/forms"
    const files = fs.readdirSync(dir)
    api.register({
        key: 'addEntryImports',
        fn: ()=>{
            return 'import { formRegistry } from "./framework/system/FormRegistry";'
        }
    })
    api.addEntryImports(()=>{
        return {
            source: "./framework/system/FormRegistry",
            specifier: 'formRegistry'
        }
    })

    files.forEach(fileName=>{
        const fullPath = path.join(dir, fileName)
        const stats = fs.statSync(fullPath)
        if (stats.isFile() && fileName.endsWith(".jsx")) {
            api.register({
                key: 'addEntryCode',
                fn: () => {
                    return 'formRegistry.register("' + fileName + '", ' + fileName.substring(0, fileName.length - 4) + ');
                }
            })
        }
    })


};

