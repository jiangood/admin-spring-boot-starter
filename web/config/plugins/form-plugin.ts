import {IApi} from 'umi';
import * as fs from "fs";
import * as path from "path";
import Utils from "./Utils";

// 自动注册src/forms下的表单
const pkgName = '@jiangood/admin-spring-boot-starter';


export default (api: IApi) => {
    api.logger.info('plugin starting... ')
    api.logger.info('info', JSON.stringify(api.env))

    api.describe({
        key: 'admin-spring-boot-stater-route-form',
    });
    const isFramework = api.pkg.name === pkgName;
    api.logger.info('current pkgName', api.pkg.name)
    api.logger.info('is framework ?', isFramework)

    // 导入工具类 FormRegistryUtils
    {
        let importFrom = path.join(api.paths.absSrcPath, 'framework')
        if (!isFramework) {
            importFrom = path.join(api.paths.absNodeModulesPath, pkgName)
        }

        api.logger.info('formRegistryPath', importFrom)
        api.addEntryImports(() => ({
            source: importFrom,
            specifier: '{FormRegistryUtils}'
        }))
    }


    Utils.findFilesSync(api.paths.absSrcPath, /forms\/.*\.jsx$/).forEach(file => {
       importForm(api, file)
    })

};
function importForm(api: IApi, file: string) {
    const name =  Utils.getFileMainName(file)

    api.addEntryImports(() => ({
        source: file,
        specifier: name
    }))

    // register form
    api.addEntryCodeAhead(() => `FormRegistryUtils.register("${name}",${name} );`)
    api.logger.info('新版本 formRegistry.register: ', name, file)
}